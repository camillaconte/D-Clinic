package develhope.DClinic;

import com.fasterxml.jackson.databind.ObjectMapper;
import develhope.DClinic.controller.MedicalReportController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles(value = "test")
@AutoConfigureMockMvc
public class MedicalReportControllerTests {

    @Autowired
    private MedicalReportController medicalReportController;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void MedicalReportControllerLoads() {
        assertThat(medicalReportController).isNotNull();
    }



}
