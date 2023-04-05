package develhope.DClinic.mapper;

import develhope.DClinic.domain.LabParameter;
import develhope.DClinic.domain.LabTest;
import develhope.DClinic.domain.LabTestDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class LabTestMapper {
    public LabTest mapToLabTest(final LabTestDTO labTestDTO) {
        return new LabTest(
                labTestDTO.getPatient(),
                labTestDTO.getDate(),
                labTestDTO.getLabParameter(),
                labTestDTO.getValue(),
                labTestDTO.getDescription()
        );
    }

    public LabTestDTO mapToLabTestDTO(final LabTest labTest) {
        return new LabTestDTO(
                labTest.getPatient(),
                labTest.getDate(),
                labTest.getLabParameter(),
                labTest.getValue(),
                labTest.getDescription()
        );
    }

    public List<LabTestDTO> mapToLabTestDTOList(final List<LabTest> labTestList) {
        return labTestList.stream()
                .map(l -> new LabTestDTO(l.getPatient(), l.getDate(), l.getLabParameter(),l.getValue(), l.getDescription()))
                .collect(Collectors.toList());
    }
}
