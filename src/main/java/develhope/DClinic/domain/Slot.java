package develhope.DClinic.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="slots")
public class Slot {

    @Id
    @SequenceGenerator(
            name = "slot_id_sequence",
            sequenceName = "slot_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "slot_id_sequence")
    private long id;

    /*
    Posso scrivere:
    - data e ora di inizio e data e ora di fine
    - posso fare due LocalDateTime oppure due LocalDate e due Time
    - posso scrivere solo l'ora di inizio, tanto so che gli slot durano 1 ora
    - posso creare una enum per tutti i possibili slot della giornata (es 8 valori per una giornata lavorativa tipo)
     */
    private LocalDateTime dateAndTime;

    @ManyToOne
    private Doctor doctor;

    @ManyToOne
    private Clinic clinic;

    // prestazione sanitaria (es. "visita cataratta")
    // todo al posto di string mettere enum oppure una entity (mooolto meglio entity)
    private String medicalService;

    /*
    - si può fare a meno della classe Appointment e usare solo la classe Slot
    - si possono avere entrambe, e il risultato è un po' pià aderente alla realtà
    - se ho l'appointment posso fare a meno di scrivere il patient perché tanto è dentro l'appointment,
      ma posso anche scriverlo lo stesso perché è comodo
     */

    @ManyToOne
    private Patient patient;

    @OneToOne
    private Appointment appointment;

    // posso fare a meno di questo boolean: se c'è un patient o un appointment è ovvio che lo slot è occupato
    // però un boolean è comodo, è veloce da leggere - ma devo anche ricordarmi di aggiornarlo
    private boolean occupied;

}
