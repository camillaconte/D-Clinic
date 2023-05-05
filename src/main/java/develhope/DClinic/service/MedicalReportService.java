package develhope.DClinic.service;

import develhope.DClinic.domain.Entities.Doctor;
import develhope.DClinic.domain.DTO.MedicalReportDTO;
import develhope.DClinic.exceptions.DoctorNotFoundException;
import develhope.DClinic.exceptions.MedicalReportNameNotInsertedException;
import develhope.DClinic.exceptions.MedicalReportsNotFoundException;
import develhope.DClinic.exceptions.PatientNotFoundException;
import develhope.DClinic.repository.DoctorRepository;
import develhope.DClinic.repository.MedicalReportRepository;
import develhope.DClinic.repository.PatientRepository;
import develhope.DClinic.domain.MedicalReport;
import develhope.DClinic.domain.Entities.Patient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class MedicalReportService {

    @Autowired
    MedicalReportRepository medicalReportRepository;

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    DoctorRepository doctorRepository;

    Logger log = LoggerFactory.getLogger(MedicalReportService.class);

    public MedicalReport createNewReport(MedicalReportDTO medicalReportDTO) throws PatientNotFoundException,
            MedicalReportNameNotInsertedException, DoctorNotFoundException {
        log.info("Trying to create new report by doctor with id " + medicalReportDTO.getDoctorId() +
                "for patient with id " + medicalReportDTO.getPatientId());
        Optional<Patient> patient = patientRepository.findById(medicalReportDTO.getPatientId());
        Optional<Doctor> doctor = doctorRepository.findById(medicalReportDTO.getDoctorId());
        if (patient.isEmpty()) {
            throw new PatientNotFoundException("Patient with id " + medicalReportDTO.getPatientId() +
                    "not found in database");
        }
        if (doctor.isEmpty()) {
            throw new DoctorNotFoundException("Doctor with id " + medicalReportDTO.getDoctorId() +
                    "not found in database");
        }
        if (medicalReportDTO.getReportName().isEmpty()) {
            throw new MedicalReportNameNotInsertedException("Medical Report name not set yet");
        }
        MedicalReport newReport = new MedicalReport(
                medicalReportDTO.getReportName(),
                patient.get(),
                doctor.get(),
                medicalReportDTO.getHistory(),
                medicalReportDTO.getHistoryOfPresentIllness(),
                medicalReportDTO.getPhysicalExam(),
                medicalReportDTO.getConclusions(),
                LocalDateTime.now());
        log.info("New report created!");
        patient.get().getMedicalReportsList().add(newReport);
        log.info("New report added to patient with id " + patient.get().getId());
        doctor.get().getMedicalReportsList().add(newReport);
        log.info("New report added to doctor with id " + doctor.get().getId());
        return medicalReportRepository.save(newReport);
    }

    //-----------------------------------------------------------------------------------------------------//

    public Set<MedicalReport> getAllPatientReportsByFiscalCode(String patientFiscalCode)
            throws PatientNotFoundException, MedicalReportsNotFoundException {
        Optional<Patient> patientToFind = patientRepository.findPatientByFiscalCode(patientFiscalCode);
        if(patientToFind.isEmpty()){
            throw new PatientNotFoundException("No patients found with fiscal code " + patientFiscalCode);
        }
        Optional<Set<MedicalReport>> patientReportsList =
                medicalReportRepository.findAllByPatientFiscalCode(patientFiscalCode);
        if(patientReportsList.isEmpty()){
            throw new MedicalReportsNotFoundException
                    ("No reports found for patient with fiscal code " + patientFiscalCode);
        }
        return patientToFind.get().getMedicalReportsList();
    }

    //-----------------------------------------------------------------------------------------------------//

    public Set<MedicalReport> getAllDoctorReportsByDoctorId(long doctorId) throws Exception {
        Optional<Doctor> doctor = doctorRepository.findById(doctorId);
        if (doctor.isEmpty()) {
            throw new DoctorNotFoundException("Doctor with id " + doctorId + "not found in database");
        }
        Optional<Set<MedicalReport>> doctorReportsList = medicalReportRepository.findAllByDoctorId(doctorId);
        if (doctorReportsList.isEmpty()) {
            throw new Exception("Not found a list of medical report for doctor with id " + doctorId);
        }
        return doctorReportsList.get();
    }

    //-----------------------------------------------------------------------------------------------------//

    public MedicalReport updateHistory (long medicalReportId, String newHistory) throws MedicalReportsNotFoundException {
        Optional<MedicalReport> medicalReport = medicalReportRepository.findById(medicalReportId);
        if(medicalReport.isEmpty()) {
            throw new MedicalReportsNotFoundException("Medical Report with id " + medicalReportId + "not found");
        }
        medicalReport.get().setHistory(newHistory);
        return medicalReportRepository.save(medicalReport.get());
    }

    //-----------------------------------------------------------------------------------------------------//

    public String getLastPatientHistory(long patientId) throws MedicalReportsNotFoundException, PatientNotFoundException {
        Optional<Patient> patient = patientRepository.findById(patientId);
        if (patient.isEmpty()) {
            throw new PatientNotFoundException("No patients with id " + patientId + " found");
        }
        Optional<Set<MedicalReport>> medicalReports = medicalReportRepository.findAllByPatientId(patientId);
        if (medicalReports.isEmpty()) {
            throw new MedicalReportsNotFoundException("No reports for patient with id " + patientId);
        }
        return medicalReportRepository.findLastHistoryByPatientId(patientId);
    }

    /*
    //prendo tutti i MedicalRecord (grazie al medicalRecordsRepository)
    //faccio un ciclo (o uno stream) per vedere chi ha come paziente il paziente che mi interessa
    public List<MedicalReport> getAllPatientReports(Patient patient) {
        List<MedicalReport> patientRecords = new ArrayList<>();
        for (MedicalReport record : medicalReportRepository.findAll()){
            if (record.getPatient() == patient){
                patientRecords.add(record);
            }
        } return patientRecords;
    }

    public Set<MedicalReport> findAllReportsByPatientId(long patientId) {
        Optional<Patient> patientToFind = patientRepository.findById(patientId);
        Set<MedicalReport> medicalReportList = null;
        if (!patientToFind.isPresent()) {
            throw new IllegalStateException("no patients with this id");
        } else {
            Patient patient = patientToFind.get();
            medicalReportList = patient.getMedicalReportsList();
        }
        return medicalReportList;
    }


    public String getLastHistory() {
        Optional<MedicalReport> lastReport = medicalReportRepository.findLast();
        if (lastReport.isPresent()) {
            String history = lastReport.get().getHistory();
            return history;
        } else {
            throw new IllegalStateException("It was not possible to find a history");
        }
    }


    public void updateReportHistory(String reportName, String history){
        Optional<MedicalReport> reportToFind = medicalReportRepository.findByName(reportName);
        if(reportToFind.isPresent()){
            MedicalReport report = reportToFind.get();
            report.setHistory(history);
            medicalReportRepository.save(report);
        } else {
            throw new IllegalStateException("there are no records with this name");
        }
    }*/


}
