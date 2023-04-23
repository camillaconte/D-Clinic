package develhope.DClinic;

import com.fasterxml.jackson.databind.ObjectMapper;
import develhope.DClinic.controller.PatientController;
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

@SpringBootTest
@ActiveProfiles(value = "test")
@AutoConfigureMockMvc
public class PatientControllerTests {

    @Autowired
    private PatientController patientController;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void studentControllerLoads() {
        assertThat(patientController).isNotNull();
    }

    /**
     *   Metodo "AUSILIARIO"
     *   per il metodo di test @Test readSinglePatient
     *   TODO cambiare urlTemplate quando PatientController sarà sistemato
     */

    private Patient getPatientFromId(Long id) throws Exception{
        //fai una API request di tipo GET
        //per risalire a un paziente tramite il suo id
        MvcResult result = this.mockMvc.perform(get("/get-single-patient/" + id))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        //una volta che ho il mio MvcResult
        // (che corrisponde alla domanda "Com'è andata questa GET?")
        //voglio anche ritornare il paziente salvato
        //ma già che ci sono voglio anche sapere
        //che sia il paziente sia il suo id NON SONO NULLI
        try {
            String studentJSON = result.getResponse().getContentAsString();
            Patient patient = objectMapper.readValue(studentJSON, Patient.class);

            assertThat(patient).isNotNull();
            assertThat(patient.getId()).isNotNull();

            return patient;
        }catch (Exception e){
            return null;
        }
    }

    //TRATTANDOSI DI UN TEST
    //devo per forza dargli io un esempio di specifico paziente!
    private Patient createPatient() throws Exception {
        Patient patient = new Patient();
        patient.setFirstName("Jack");
        patient.setLastName("Sparrow");
        patient.setFiscalCode("SPRJCK73P12A944P");
        patient.setEmail("jack.sparrow@pirates.com");
        //sfrutto il metodo che vedi qui sotto
        //che restituisce uno studente dopo averlo creato nel Mock DB
        return createPatient(patient);
    }

    //faccio l'Overload del metodo qui sopra
    private Patient createPatient(Patient patient) throws Exception {
        //uso un altro metodo "ausiliario" creato sotto
        //più che altro perché altrimenti veniva chilometrico
        //e forse anche per potere riutilizzare il codice!
        //questo metodo ausiliario
        //restituisce un McvResult
        MvcResult result = createPatientRequest(patient);
        //però NON MI BASTA avere l'Mvc result
        //voglio anche che venga restituito l'oggetto di tipo patient
        //che avevo dato in ingresso
        Patient patientFromResponse = objectMapper.readValue(result.getResponse().getContentAsString(), Patient.class);

        assertThat(patientFromResponse).isNotNull();
        assertThat(patientFromResponse.getId()).isNotNull();

        return patientFromResponse;
    }

    //questo metodo non restituisce uno Student
    //ma un MvcResult!
    //però N.B. in realtà POI NON LO USO per arrivare al metodo
    //@Test createAStudentTest
    private MvcResult createPatientRequest() throws Exception {
        Patient patient = new Patient();
        patient.setFirstName("Jack");
        patient.setLastName("Sparrow");
        patient.setFiscalCode("SPRJCK73P12A944P");
        patient.setEmail("jack.sparrow@pirates.com");
        return createPatientRequest(patient);
    }

    //https://www.baeldung.com/jackson-object-mapper-tutorial
    //anche qui OVERLOAD del metodo precedente
    private MvcResult createPatientRequest(Patient patient) throws Exception {
        if(patient == null) return null;
        //SE INVECE LO STUDENTE non è nullo, quinsi è STATO FORNITO
        //lo trasformo in JSON
        String patientJSON = objectMapper.writeValueAsString(patient);
        //uso quel JSON per RITORNARE UN MvcResult
        //FAI ("perform") UNA RICHIESTA API di tipo POST
        //e restituiscimi il risultato
        //"result" in un certo senso è == a una RESPONSE, giusto???
        //infatti la parola "result" mi sembra che faccia confusione
        //se usassimo RESPONSE forse sarebbe più chiaro?
        return this.mockMvc.perform(post("/insert-patient")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(patientJSON))
                .andDo(print()) //stampa tutta la response
                .andExpect(status().isOk())
                .andReturn(); //termina la tua chiamata
    }

    //------ ED ECCO IL METODO DI TEST CHE RISULTA DAI (tre su quattro) METODI SOPRA!!! ------
    @Test
    void createPatientTest() throws Exception {
        Patient patientFromResponse = createPatient();
    }


    @Test
    void readPatientsList() throws Exception {
        //fai una richiesta API per creare e inserire un paziente!!!
        //questo rende il test indipendente
        //rispetto a un altro test dove viene creato un utente
        //ogni test deve essere a sè, giusto???
        createPatientRequest();
        //fai una richiesta API di tipo GET
        //e stampa il risultato
        MvcResult result =this.mockMvc.perform(get("/get-all-patients/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        List<Patient> patientsFromResponse = objectMapper.readValue(result.getResponse().getContentAsString(), List.class);
        System.out.println("Patients in database are: " + patientsFromResponse.size());
        assertThat(patientsFromResponse.size()).isNotZero();
    }

    @Test
    void readSinglePatient() throws Exception {
        //creo uno studente nel Mock Database
        Patient patient = createPatient();
        //provo a recuperare quello studente
        Patient patientFromResponse = getPatientFromId(patient.getId());
        //dimmi che l'id dello studente che hai recuperato dal DB
        //è uguale all'id dello studente che avevi creato
        //questo per testare il fatto che la GET funzioni bene
        //(e che non vada a prendere un altro id ???)
        assertThat(patientFromResponse.getId()).isEqualTo(patient.getId());
    }



    /**
     *   Test per vedere se funziona un cambio di id
     *   TODO cambiare urlTemplate quando PatientController sarà sistemato
     */
    @Test
    void updatePatientId() throws Exception{
        Patient patient = createPatient();

        String newName = "Frank";
        patient.setFirstName(newName);

        String patientJSON = objectMapper.writeValueAsString(patient);

        //Voglio il RISULTATO (la risposta) di una API Request di tipo PUT
        MvcResult result = this.mockMvc.perform(put("/set-patient-id/"+ patient.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(patientJSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        //ora mi prendo lo studente dentro il risultato della PUT
        Patient patientFromResponse = objectMapper.readValue(result.getResponse().getContentAsString(), Patient.class);

        assertThat(patientFromResponse.getId()).isEqualTo(patient.getId());
        assertThat(patientFromResponse.getLastName()).isEqualTo(newName);

        //Per verificare che lo studente sia stato davvero aggiornato
        //devo fare anche una GET !!!
        Patient patientFromResponseGet = getPatientFromId(patient.getId());
        assertThat(patientFromResponseGet.getId()).isEqualTo(patient.getId());
        assertThat(patientFromResponseGet.getLastName()).isEqualTo(newName);
    }

    /**
     * TODO sistemare url quando Patient sarà completato
     * @throws Exception
     */
    @Test
    void deletePatient() throws Exception{
        Patient patient = createPatient();
        assertThat(patient.getId()).isNotNull();

        this.mockMvc.perform(delete("/delete-student/"+ patient.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        //per verificare che lo studente sia stato davvero cancellato
        //devo fare anche una richiesta GET
        Patient patientFromResponseGet = getPatientFromId(patient.getId());
        assertThat(patientFromResponseGet).isNull();
    }

}
