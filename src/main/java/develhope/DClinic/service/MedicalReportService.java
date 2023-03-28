package develhope.DClinic.service;

import develhope.DClinic.repository.MedicalReportRepository;
import develhope.DClinic.repository.PatientRepo;
import develhope.DClinic.domain.MedicalReport;
import develhope.DClinic.domain.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MedicalReportService {

    MedicalReportRepository medicalReportRepository;
    PatientRepo patientRepo;

    @Autowired
    public MedicalReportService(MedicalReportRepository medicalReportRepository, PatientRepo patientRepo){
        this.medicalReportRepository = medicalReportRepository;
        this.patientRepo = patientRepo;
    }

    /**
     * CREATE methods
     */

    /**
     * Problema pratico: se sono sulla pagina dell'appuntamento, come faccio a creare un record dove il campo paziente
     * corrisponde proprio al campo paziente dell'appuntamento?
     */

    public ResponseEntity createReportResponse(MedicalReport report) {
        try {
            MedicalReport reportToSave = report;
            medicalReportRepository.save(reportToSave);
            return ResponseEntity.ok(reportToSave);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

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

    public List<MedicalReport> findAllReportsByPatientId(long patientId) {
        Optional<Patient> patientToFind = patientRepo.findById(patientId);
        List<MedicalReport> medicalReportList = null;
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


    /*public void updateReportHistory(String reportName, String history){
        Optional<MedicalReport> reportToFind = medicalReportRepository.findByName(reportName);
        if(reportToFind.isPresent()){
            MedicalReport report = reportToFind.get();
            report.setHistory(history);
            medicalReportRepository.save(report);
        } else {
            throw new IllegalStateException("there are no records with this name");
        }
    }*/



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
