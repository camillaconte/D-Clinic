package develhope.DClinic;

import com.fasterxml.jackson.databind.ObjectMapper;
import develhope.DClinic.controller.DoctorController;
import develhope.DClinic.domain.Doctor;
import develhope.DClinic.domain.Patient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * @author camillaconte
 * Test per il DoctorController
 * TODO capire perché al momento falliscono i test
 * Problema di application.properties???
 */
@SpringBootTest
@ActiveProfiles(value = "test")
@AutoConfigureMockMvc
public class DoctorControllerTests {

    @Autowired
    private DoctorController doctorController;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void doctorControllerLoads() {
        assertThat(doctorController).isNotNull();
    }

    /**
     *   Metodo "AUSILIARIO"
     *   per il metodo di test @Test readSingleDoctor
     */

    private Doctor getDoctorFromFiscalCode(String fiscalCode) throws Exception{
        //fai una API request di tipo GET
        //per risalire a un dottore tramite il suo fiscalCode
        MvcResult result = this.mockMvc.perform(get("/get-by-fiscalCode/" + fiscalCode))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        //una volta che ho il mio MvcResult
        // (che corrisponde alla domanda "Com'è andata questa GET?")
        //voglio anche ritornare il dottore salvato
        //ma già che ci sono voglio anche sapere
        //che sia il dottore sia il suo id NON SONO NULLI
        try {
            String doctorJSON = result.getResponse().getContentAsString();
            Doctor doctor = objectMapper.readValue(doctorJSON, Doctor.class);

            assertThat(doctor).isNotNull();
            assertThat(doctor.getId()).isNotNull();

            return doctor;
        }catch (Exception e){
            return null;
        }
    }

    //TRATTANDOSI DI UN TEST
    //devo per forza dargli io un esempio di specifico dottore!
    private Doctor createDoctor() throws Exception {
        Doctor doctor = new Doctor();
        doctor.setFirstname("Jack");
        doctor.setLastname("Sparrow");
        doctor.setFiscalCode("SPRJCK73P12A944P");
        doctor.setEmail("jack.sparrow@pirates.com");
        doctor.setTelephoneNumber("3387456734");
        doctor.setSpecialization("Neurology");
        doctor.setPassword("eyrhdnn");
        //sfrutto il metodo che vedi qui sotto
        //che restituisce un dottore dopo averlo creato nel Mock DB
        return createDoctor(doctor);
    }

    //faccio l'Overload del metodo qui sopra
    private Doctor createDoctor(Doctor doctor) throws Exception {
        //uso un altro metodo "ausiliario" creato sotto
        //più che altro perché altrimenti veniva chilometrico
        //e forse anche per potere riutilizzare il codice!
        //questo metodo ausiliario
        //restituisce un McvResult
        MvcResult result = createDoctorRequest(doctor);
        //però NON MI BASTA avere l'Mvc result
        //voglio anche che venga restituito l'oggetto di tipo Student
        //che avevo dato in ingresso
        Doctor doctorFromResponse = objectMapper.readValue(result.getResponse().getContentAsString(), Doctor.class);

        assertThat(doctorFromResponse).isNotNull();
        assertThat(doctorFromResponse.getId()).isNotNull();

        return doctorFromResponse;
    }

    //questo metodo non restituisce un Doctor
    //ma un MvcResult!
    //però N.B. in realtà POI NON LO USO per arrivare al metodo
    //@Test createStudentTest ma per altri @Test
    private MvcResult createDoctorRequest() throws Exception {
        Doctor doctor = new Doctor();
        doctor.setFirstname("Jack");
        doctor.setLastname("Sparrow");
        doctor.setFiscalCode("SPRJCK73P12A944P");
        doctor.setEmail("jack.sparrow@pirates.com");
        doctor.setTelephoneNumber("3387456734");
        doctor.setSpecialization("Neurology");
        doctor.setPassword("eyrhdnn");
        return createDoctorRequest(doctor);
    }

    //https://www.baeldung.com/jackson-object-mapper-tutorial
    //anche qui OVERLOAD del metodo precedente
    private MvcResult createDoctorRequest(Doctor doctor) throws Exception {
        if(doctor == null) return null;
        //SE INVECE il dottore non è nullo, quindi è STATO FORNITO
        //lo trasformo in JSON
        String doctorJSON = objectMapper.writeValueAsString(doctor);
        //uso quel JSON per RITORNARE UN MvcResult
        //FAI ("perform") UNA RICHIESTA API di tipo POST
        //e restituiscimi il risultato
        //"result" in un certo senso è == a una RESPONSE, giusto???
        //infatti la parola "result" mi sembra che faccia confusione
        //se usassimo RESPONSE forse sarebbe più chiaro?
        return this.mockMvc.perform(post("/create-doctor")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(doctorJSON))
                .andDo(print()) //stampa tutta la response
                .andExpect(status().isOk())
                .andReturn(); //termina la tua chiamata
    }

    //------ ED ECCO IL METODO DI TEST CHE RISULTA DAI (tre su quattro) METODI SOPRA!!! ------
    @Test
    void createDoctorTest() throws Exception {
        Doctor doctorFromResponse = createDoctor();
    }


    @Test
    void readDoctorsList() throws Exception {
        //fai una richiesta API per creare e inserire un dottore!!!
        //questo rende il test indipendente
        //ogni test deve essere a sè, giusto???
        createDoctorRequest();
        //fai una richiesta API di tipo GET
        //e stampa il risultato
        MvcResult result =this.mockMvc.perform(get("/get-all-doctors/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        List<Doctor> doctorsFromResponse = objectMapper.readValue(result.getResponse().getContentAsString(), List.class);
        System.out.println("Doctors in database are: " + doctorsFromResponse.size());
        assertThat(doctorsFromResponse.size()).isNotZero();
    }

    @Test
    void readSingleDoctor() throws Exception {
        //creo uno studente nel Mock Database
        Doctor doctor = createDoctor();
        //provo a recuperare quello studente
        Doctor doctorFromResponse = getDoctorFromFiscalCode(doctor.getFiscalCode());
        //dimmi che l'id dello studente che hai recuperato dal DB
        //è uguale all'id dello studente che avevi creato
        //questo per testare il fatto che la GET funzioni bene
        //(e che non vada a prendere un altro id ???)
        assertThat(doctorFromResponse.getFiscalCode()).isEqualTo(doctor.getFiscalCode());
    }


}
