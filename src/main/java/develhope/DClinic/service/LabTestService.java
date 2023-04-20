package develhope.DClinic.service;

import develhope.DClinic.domain.*;
import develhope.DClinic.exceptions.PatientNotFoundException;
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

    public LabTest insertNewTest(LabTestRequestDTO labTestRequestDTO){
        LabTest test = new LabTest();
        //test.setPatient(patientRepository.findPatientByFiscalCode(labTestRequestDTO.getFiscalCode()));
        test.setDate(LocalDate.now());
        labTestRepository.save(test);
        return test;
    }

    /**
     * @author Camilla Conte
     * Method to insert a new lab test with Set of LabParam inside
     */
    /*public void insertNewLabTestCami(LabTestDTOCami labTestDTOCami) throws Exception {
        //trovo il paziente
        Optional<Patient> patientToFind =
                patientRepository.findPatientByFiscalCode(labTestDTOCami.getPatientFiscalCode());
        if(patientToFind.isEmpty()){
            throw new PatientNotFoundException("Patient with fiscalcode " + labTestDTOCami.getPatientFiscalCode()
                                                + "not found");
        }
        if(labTestDTOCami.getLabParameters().isEmpty()){
            throw new Exception ("Lab Parameters non inserted");
        }
        Set<LabParameter> labParametersForTest = labTestDTOCami.getLabParameters();
        for(LabParameter labParameter : labParametersForTest){

        }
        LabTest labTest = new LabTest(patientToFind.get(), labParametersForTest);

    }*/

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
            update.setPatient(patientRepository.findPatientByFiscalCode(labTestRequestDTO.getFiscalCode()));
        }
        update.setDate(LocalDate.now());
        //Inserire i parametri
        labTestRepository.saveAndFlush(update);
        return update;
    }

    public List<LabTestResponseDTO> getAllTestOfPatientSV(String fiscalCode) {
        Patient patient = patientRepository.findPatientByFiscalCode(fiscalCode);
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
