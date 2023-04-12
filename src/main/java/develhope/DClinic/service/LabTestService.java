package develhope.DClinic.service;

import develhope.DClinic.domain.LabTest;
import develhope.DClinic.domain.LabTestRequestDTO;
import develhope.DClinic.domain.LabTestResponseDTO;
import develhope.DClinic.domain.Patient;
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

    public LabTest insertNewTest(LabTestRequestDTO labTestRequestDTO){
        LabTest test = new LabTest();
        test.setPatient(patientRepository.findPatientByFiscalCode(labTestRequestDTO.getFiscalCode()));
        test.setDate(LocalDate.now());
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
        //Inserire i parametri
        return testResponseDTO;
    }

    public LabTest update(String uuid, LabTestRequestDTO labTestRequestDTO){
        LabTest update = new LabTest();
        update.setUuid(uuid);
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
            responseDTO.setUuid(x.getUuid());
            responseDTO.setPatient(x.getPatient());
            responseDTO.setDate(x.getDate());
            //Inserire i parametri
            listOfPatientDTO.add(responseDTO);
        }
        return listOfPatientDTO;
    }
}
