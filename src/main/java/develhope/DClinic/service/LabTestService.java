package develhope.DClinic.service;

import develhope.DClinic.domain.LabTest;
import develhope.DClinic.domain.LabTestRequestDTO;
import develhope.DClinic.domain.LabTestResponseDTO;
import develhope.DClinic.repository.LabTestRepository;
import develhope.DClinic.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    public LabTest insertNewTest(LabTestRequestDTO labTestRequestDTO){
        LabTest test = new LabTest();
        test.setPatient(patientRepository.findPatientByFiscalCode(labTestRequestDTO.getFiscalCode()));
        test.setDate(LocalDate.now());
        test.setNameParameter(labTestRequestDTO.getNameParameter());
        test.setValue(labTestRequestDTO.getValue());
        labTestRepository.save(test);
        return test;
    }

    public void deleteByID(long id_test){
            labTestRepository.deleteById(id_test);
    }

    public LabTestResponseDTO getByID (long id_test){
        LabTestResponseDTO testResponseDTO = new LabTestResponseDTO();
        LabTest test = labTestRepository.getById(id_test);
        testResponseDTO.setPatient(test.getPatient());
        testResponseDTO.setDate(test.getDate());
        testResponseDTO.setNameParameter(test.getNameParameter());
        testResponseDTO.setValue(test.getValue());
        //Inserire i parametri
        return testResponseDTO;
    }

    public LabTest update(long id, LabTestRequestDTO labTestRequestDTO){
        LabTest update = new LabTest();
        update.setId_test(id);
        update.setPatient(patientRepository.findPatientByFiscalCode(labTestRequestDTO.getFiscalCode()));
        update.setDate(LocalDate.now());
        update.setNameParameter(labTestRequestDTO.getNameParameter());
        update.setValue(labTestRequestDTO.getValue());
        labTestRepository.saveAndFlush(update);
        return update;
    }

    public List<LabTestResponseDTO> getAllTestOfPatientSV(long id) {
        List<LabTest> listOfPatient = labTestRepository.findAllByPatientId(id);
        List<LabTestResponseDTO> listOfPatientDTO = new ArrayList<>();
        for (LabTest x : listOfPatient) {
            LabTestResponseDTO responseDTO = new LabTestResponseDTO();
            responseDTO.setPatient(x.getPatient());
            responseDTO.setDate(x.getDate());
            responseDTO.setNameParameter(x.getNameParameter());
            responseDTO.setValue(x.getValue());
            //Inserire i parametri
            listOfPatientDTO.add(responseDTO);
        }
        return listOfPatientDTO;
    }
}
