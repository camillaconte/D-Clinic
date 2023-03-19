package develhope.DClinic.services;

import develhope.DClinic.repositories.MedicalRecordsRepo;
import develhope.DClinic.repositories.PatientRepo;
import develhope.DClinic.domain.MedicalRecord;
import develhope.DClinic.domain.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MedicalRecordsService {

    MedicalRecordsRepo medicalRecordsRepo;
    PatientRepo patientRepo;

    @Autowired
    public MedicalRecordsService(MedicalRecordsRepo medicalRecordsRepo, PatientRepo patientRepo){
        this.medicalRecordsRepo = medicalRecordsRepo;
        this.patientRepo = patientRepo;
    }

    /*@Autowired
    public MedicalRecordsService (PatientRepo patientRepo){
        this.patientRepo = patientRepo;
    }*/


    //prendo tutti i MedicalRecord (grazie al medicalRecordsrepo)
    //faccio un ciclo (o uno stream) per vedere chi ha come paziente il paziente che mi interessa
    public List<MedicalRecord> getAllPatientRecords(Patient patient) {
        List<MedicalRecord> patientRecords = new ArrayList<>();
        for (MedicalRecord record : medicalRecordsRepo.findAll()){
            if (record.getPatient() == patient){
                patientRecords.add(record);
            }
        } return patientRecords;
    }

    //prima lo creo "vuoto", cioè solo name e id (che si autogenera)
    //poi faccio un update mediante inserimento e.g. della String history
    public void createNewRecord(MedicalRecord record) {
        medicalRecordsRepo.save(record);
    }

    public void updateRecordHistory(String name, String history){
        Optional<MedicalRecord> recordToFind = medicalRecordsRepo.findByName(name);
        if(recordToFind.isPresent()){
            MedicalRecord record = recordToFind.get();
            record.setHistory(history);
            medicalRecordsRepo.save(record);
        } else {
            throw new IllegalStateException("there are no records with this name");
        }
    }

    //domanda: il nuovo record viene salvato nella tabella medicalRecords
    //e gli viene assegnato il paziente che ho dato in ingresso
    //a quel punto automaticamente quel record sarò anche nella lista di record del paziente?
    public MedicalRecord createNewRecordForPatient (String name, Patient patient){
        MedicalRecord medicalRecord = new MedicalRecord(name, patient);
        medicalRecord.setCreationDate(LocalDateTime.now());
        medicalRecord.setName(name);
        medicalRecord.setPatient(patient);
        return medicalRecordsRepo.save(medicalRecord);
    }

    //Come faccio a dire in quale record voglio aggiungere history?
    //Mi faccio dare la lista dei records dal DB tramite la classe repo
    //La restituisco al front end (il medico vede tutti i record? possibilmente in ordine di tempo?)
    //Il medico clicca sul nome del record da aggiornare: getRecordbyName?
    //...
    public void setHistory(String name, String history) throws Exception {
        List<MedicalRecord> records = medicalRecordsRepo.findAll();
        for (MedicalRecord record : records){
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
    public MedicalRecord getRecordByName(String name) throws Exception {
        Optional<MedicalRecord> optionalMedicalRecord = medicalRecordsRepo.findByName(name);
        if(optionalMedicalRecord.isPresent()){
           MedicalRecord record = optionalMedicalRecord.get();
           return record;
        } else {
            throw new Exception("No records with this name!");
        }
    }


}
