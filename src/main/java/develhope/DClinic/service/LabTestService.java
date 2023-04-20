package develhope.DClinic.service;

import develhope.DClinic.domain.*;
import develhope.DClinic.exceptions.PatientNotFoundException;
import develhope.DClinic.repository.LabParameterRepository;
import develhope.DClinic.repository.LabTestRepository;
import develhope.DClinic.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

/**
 * @author Luca Giorgi
 * Servizi LabTest
 */
@Service
public class LabTestService   {
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private LabTestRepository labTestRepository;

    @Autowired
    private LabParameterRepository labParameterRepository;

    public LabTest insertNewTest(LabTestRequestDTO labTestRequestDTO){
        LabTest test = new LabTest();
        test.setPatient(patientRepository.findPatientByFiscalCode(labTestRequestDTO.getFiscalCode()).get());
        test.setDate(LocalDate.now());
        labTestRepository.save(test);
        return test;
    }

    /**
     * @author Camilla Conte
     * Method to insert a new lab test with Set of LabParam inside
     */
    public LabTest insertNewLabTestCami(LabTestDTOCami labTestDTOCami) throws Exception {
        //trovo il paziente con il codice fiscale che c'è nel DTO
        //TODO trasformarlo in optional quando Luca ha sistemato PatientRepository
        Optional<Patient> patientToFind = patientRepository.findPatientByFiscalCode(labTestDTOCami.getPatientFiscalCode());
        if(patientToFind == null){
            throw new PatientNotFoundException("Patient with fiscalcode " + labTestDTOCami.getPatientFiscalCode()
                                                + "not found");
        }
        if(labTestDTOCami.getRequiredLabParameters().isEmpty()){
            throw new Exception ("Lab Parameters non inserted");
        }
        //creo un nuovo LabTest dandogli il PAZIENTE e un Set vuoto
        LabTest newLabTest = new LabTest(patientToFind.get(), new HashSet<>());
        List<LabParamType> listOfLabParamType = labTestDTOCami.getRequiredLabParameters();
        //a partire dalla Lista di LabParamType (Glucose, Creatinine...)
        //salvo tanti nuovi record LabParameter
        //quanti solo gli elementi della lista
        for(LabParamType type : listOfLabParamType){
            LabParameter newLabParameter = new LabParameter(type, patientToFind.get());
            labParameterRepository.save(newLabParameter);
            //aggiungo al nuovo test il singolo LabParameter
            // salvo il nuovo test
            newLabTest.getLabParameters().add(newLabParameter);
            labTestRepository.save(newLabTest);
            //aggiungo anche al paziente il singolo labParameter
            // salvo il pz
            patientToFind.get().getLabParametersList().add(newLabParameter);
            patientRepository.save(patientToFind.get());
        }
        patientToFind.get().getLabTest().add(newLabTest);
        patientRepository.save(patientToFind.get());
        return newLabTest;
    }


    public void deleteByID(long id_test){
            labTestRepository.deleteById(id_test);
    }

    public LabTestResponseDTO getByID (long id_test){
        LabTestResponseDTO testResponseDTO = new LabTestResponseDTO();
        Optional<LabTest> test = labTestRepository.findById(id_test);
        if(test.isEmpty()) throw new RuntimeException("The laboratory test is not exist");
        testResponseDTO.setPatient(test.get().getPatient());
        testResponseDTO.setDate(test.get().getDate());
        //Inserire i parametri
        return testResponseDTO;
    }

    public LabTest update(long id, LabTestRequestDTO labTestRequestDTO){
        LabTest update = new LabTest();
        update.setTestId(id);
        if(labTestRequestDTO.getFiscalCode() != null){
            update.setPatient(patientRepository.findPatientByFiscalCode(labTestRequestDTO.getFiscalCode()).get());
        }
        update.setDate(LocalDate.now());
        //Inserire i parametri
        labTestRepository.saveAndFlush(update);
        return update;
    }

    public List<LabTestResponseDTO> getAllTestOfPatientSV(String fiscalCode) {
        Patient patient = patientRepository.findPatientByFiscalCode(fiscalCode).get();
        List<LabTest> listOfPatient = labTestRepository.findAllByPatientId(patient.getId());
        List<LabTestResponseDTO> listOfPatientDTO = new ArrayList<>();
        for (LabTest x : listOfPatient) {
            LabTestResponseDTO responseDTO = new LabTestResponseDTO();
            responseDTO.setId(x.getTestId());
            responseDTO.setPatient(x.getPatient());
            responseDTO.setDate(x.getDate());
            //Inserire i parametri
            listOfPatientDTO.add(responseDTO);
        }
        return listOfPatientDTO;
    }

}
