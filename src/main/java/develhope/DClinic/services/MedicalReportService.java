package develhope.DClinic.services;

import develhope.DClinic.repositories.MedicalReportRepo;
import develhope.DClinic.repositories.PatientRepo;
import develhope.DClinic.domain.MedicalReport;
import develhope.DClinic.domain.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MedicalReportService {

    MedicalReportRepo medicalReportRepo;
    PatientRepo patientRepo;

    @Autowired
    public MedicalReportService(MedicalReportRepo medicalReportRepo, PatientRepo patientRepo){
        this.medicalReportRepo = medicalReportRepo;
        this.patientRepo = patientRepo;
    }

    /*@Autowired
    public MedicalRecordsService (PatientRepo patientRepo){
        this.patientRepo = patientRepo;
    }*/


    //prendo tutti i MedicalRecord (grazie al medicalRecordsrepo)
    //faccio un ciclo (o uno stream) per vedere chi ha come paziente il paziente che mi interessa
    public List<MedicalReport> getAllPatientRecords(Patient patient) {
        List<MedicalReport> patientRecords = new ArrayList<>();
        for (MedicalReport record : medicalReportRepo.findAll()){
            if (record.getPatient() == patient){
                patientRecords.add(record);
            }
        } return patientRecords;
    }

    //prima lo creo "vuoto", cioè solo name e id (che si autogenera)
    //poi faccio un update mediante inserimento e.g. della String history
    public void createNewReport(MedicalReport report) {
        medicalReportRepo.save(report);
    }


    public void updateRecordHistory(String name, String history){
        Optional<MedicalReport> reportToFind = medicalReportRepo.findByName(name);
        if(reportToFind.isPresent()){
            MedicalReport report = reportToFind.get();
            report.setHistory(history);
            medicalReportRepo.save(report);
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
        medicalReport.setName(name);
        medicalReport.setPatient(patient);
        return medicalReportRepo.save(medicalReport);
    }

    //Come faccio a dire in quale record voglio aggiungere history?
    //Mi faccio dare la lista dei records dal DB tramite la classe repo
    //La restituisco al front end (il medico vede tutti i record? possibilmente in ordine di tempo?)
    //Il medico clicca sul nome del record da aggiornare: getRecordbyName?
    //...
    public void setHistory(String name, String history) throws Exception {
        List<MedicalReport> records = medicalReportRepo.findAll();
        for (MedicalReport record : records){
            if (record.getName() == name){
                record.setHistory(history);
            } else {
                throw new Exception("No records with this name!");
            }
        }
    }

    //cliccando sul nome del record nella lista
    //voglio che mi restituisca il record
    //getRecordByName
    public MedicalReport getRecordByName(String name) throws Exception {
        Optional<MedicalReport> optionalMedicalRecord = medicalReportRepo.findByName(name);
        if(optionalMedicalRecord.isPresent()){
           MedicalReport record = optionalMedicalRecord.get();
           return record;
        } else {
            throw new Exception("No records with this name!");
        }
    }


}
