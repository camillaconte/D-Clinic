package develhope.DClinic.service;

import develhope.DClinic.domain.Doctor;
import develhope.DClinic.domain.MedicalReportDTO;
import develhope.DClinic.exceptions.DoctorNotFoundException;
import develhope.DClinic.exceptions.MedicalReportNameNotFoundException;
import develhope.DClinic.exceptions.PatientNotFoundException;
import develhope.DClinic.repository.DoctorRepository;
import develhope.DClinic.repository.MedicalReportRepository;
import develhope.DClinic.repository.PatientRepository;
import develhope.DClinic.domain.MedicalReport;
import develhope.DClinic.domain.Patient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
            MedicalReportNameNotFoundException, DoctorNotFoundException {
        log.info("Trying to crate new report by doctor with id " + medicalReportDTO.getDoctorId() +
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
            throw new MedicalReportNameNotFoundException("Medical Report name not set yet");
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

    public Set<MedicalReport> getAllReportsByDoctorId(long doctorId) throws Exception {
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
    }



    //domanda: il nuovo record viene salvato nella tabella medicalRecords
    //e gli viene assegnato il paziente che ho dato in ingresso
    //a quel punto automaticamente quel record sarò anche nella lista di record del paziente?
    public MedicalReport createNewRecordForPatient (String name, Patient patient){
        MedicalReport medicalReport = new MedicalReport(name, patient);
        medicalReport.setCreationDate(LocalDateTime.now());
        medicalReport.setReportName(name);
        medicalReport.setPatient(patient);
        return medicalReportRepository.save(medicalReport);
    }

    //Come faccio a dire in quale record voglio aggiungere history?
    //Mi faccio dare la lista dei records dal DB tramite la classe repo
    //La restituisco al front end (il medico vede tutti i record? possibilmente in ordine di tempo?)
    //Il medico clicca sul nome del record da aggiornare: getRecordbyName?
    //...
    public void setHistory(String name, String history) throws Exception {
        List<MedicalReport> records = medicalReportRepository.findAll();
        for (MedicalReport record : records){
            if (record.getReportName() == name){
                record.setHistory(history);
            } else {
                throw new Exception("No records with this name!");
            }
        }
    }

    //cliccando sul nome del record nella lista
    //voglio che mi restituisca il record
    //getRecordByName
    /*public MedicalReport getRecordByName(String reportName) throws Exception {
        Optional<MedicalReport> optionalMedicalRecord = medicalReportRepository.findByName(reportName);
        if(optionalMedicalRecord.isPresent()){
           MedicalReport record = optionalMedicalRecord.get();
           return record;
        } else {
            throw new Exception("No records with this name!");
        }
    }*/

}
