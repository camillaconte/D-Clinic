package develhope.DClinic.services;

import develhope.DClinic.controllers.repositories.MedicalRecordsRepo;
import develhope.DClinic.controllers.repositories.PatientRepo;
import develhope.DClinic.domain.MedicalRecord;
import develhope.DClinic.domain.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MedicalRecordsService {

    @Autowired
    MedicalRecordsRepo medicalRecordsRepo;

    @Autowired
    PatientRepo patientRepo;

    //domanda: il nuovo record viene salvato nella tabella medicalRecords
    //e gli viene assegnato il paziente che ho dato in ingresso
    //a quel punto automaticamente quel record sarò anche nella lista di record del paziente
    public MedicalRecord createNewRecord (String name, Patient patient){
        MedicalRecord medicalRecord = new MedicalRecord(name, patient);
        medicalRecord.setCreationDate(LocalDateTime.now());
        medicalRecord.setName(name);
        medicalRecord.setPatient(patient);
        return medicalRecordsRepo.save(medicalRecord);
    }

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
