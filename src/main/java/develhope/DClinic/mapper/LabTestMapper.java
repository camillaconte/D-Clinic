package develhope.DClinic.mapper;

import develhope.DClinic.domain.LabTest;
import develhope.DClinic.domain.LabTestDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LabTestMapper {
    public LabTest mapToLabTest(final LabTestDTO labTestDTO) {
        return new LabTest(
                labTestDTO.getId(),
                labTestDTO.getPatient(),
                labTestDTO.getDate(),
                labTestDTO.getDescription(),
                labTestDTO.getResult()
        );
    }

    public LabTestDTO mapToLabTestDTO(final LabTest labTest) {
        return new LabTestDTO(
                labTest.getId_test(),
                labTest.getPatient(),
                labTest.getDate(),
                labTest.getResult(),
                labTest.getDescription()
        );
    }

    public List<LabTestDTO> mapToLabTestDTOList(final List<LabTest> labTestList) {
        return labTestList.stream()
                .map(l -> new LabTestDTO(l.getId_test(), l.getPatient(), l.getDate(), l.getResult(), l.getDescription()))
                .collect(Collectors.toList());
    }
}
