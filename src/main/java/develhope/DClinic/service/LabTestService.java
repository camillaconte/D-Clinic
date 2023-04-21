package develhope.DClinic.service;

import develhope.DClinic.domain.*;
import develhope.DClinic.exceptions.PatientNotFoundException;
import develhope.DClinic.repository.LabParameterRepository;
import develhope.DClinic.repository.LabTestRepository;
import develhope.DClinic.repository.PatientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private CheckEmptyField checkEmptyField;
    @Autowired
    private LabParameterRepository labParameterRepository;

    private Logger LOGGER = LoggerFactory.getLogger(LabTestService.class);

    public LabTest insertNewTest(LabTestRequestDTO labTestRequestDTO) throws Exception {

        LabTest test = new LabTest();
        Optional<Patient> optionalPatient = patientRepository.findPatientByFiscalCode(labTestRequestDTO.getFiscalCode());
        if(optionalPatient.isEmpty()){
            LOGGER.warn("Patient not found");
            throw  new Exception("Patient not found");
        }
        Patient patient = optionalPatient.get();
        test.setPatient(patient);
        test.setDate(LocalDate.now());
        Set<LabParameter> parameters = labTestRequestDTO.getLabParameter();
        for (LabParameter x : parameters){
            LabParameter parameter = x;
            parameter.setLabTest(test);
            parameter.setPatient(patient);
            labParameterRepository.save(parameter);
            test.getLabParameters().add(x);
            labTestRepository.save(test);
            patient.getLabParametersList().add(parameter);
            patientRepository.save(patient);
        }
        labTestRepository.save(test);
        patient.getLabTest().add(test);
        patientRepository.save(patient);
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
        Set<LabParameter> list = labParameterRepository.getByLabTestTestId(test.get().getTestId());
        for(LabParameter x : list){
            testResponseDTO.getLabParameter().add(x);
        }
        return testResponseDTO;
    }

    public LabTest update(long id, LabTestRequestDTO labTestRequestDTO){
        LabTest update = new LabTest();
        update.setTestId(id);
        Patient patient = patientRepository.findPatientByFiscalCode(labTestRequestDTO.getFiscalCode()).get();
        if(labTestRequestDTO.getFiscalCode() != null){
            update.setPatient(patient);
        }
        update.setDate(LocalDate.now());
        if(labTestRequestDTO.getLabParameter() != null){
            Set<LabParameter> parameters = labTestRequestDTO.getLabParameter();
            for (LabParameter x : parameters){
                LabParameter parameter = x;
                parameter.setLabTest(update);
                parameter.setPatient(patient);
                labParameterRepository.save(parameter);
                update.getLabParameters().add(x);
                labTestRepository.save(update);
                patient.getLabParametersList().add(parameter);
                patientRepository.save(patient);
            }
        }
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
            Set<LabParameter> list = labParameterRepository.getByLabTestTestId(x.getTestId());
            for(LabParameter param : list){
                responseDTO.getLabParameter().add(param);
            }
            listOfPatientDTO.add(responseDTO);
        }
        return listOfPatientDTO;
    }

}
