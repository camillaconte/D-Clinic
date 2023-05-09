package develhope.DClinic.medicalstuff.domain.entities;

import develhope.DClinic.user.domain.entities.Patient;
import develhope.DClinic.user.utils.BaseEntity;
import jakarta.persistence.*;

/**
 * @author camilla conte
 * Entity per i PARAMETRI DI LABORATORIO
 * (e.g. Emoglobina, Creatinina)
 *
 * RAZIONALE: nella pratica clinica un referto con gli esami di lab (LabTest)
 * è formato da un Set<LabParameter>
 *
 */

@Entity
@Table (name = "labParameters")
public class LabParameter extends BaseEntity {
    @Id
    @SequenceGenerator(
            name = "labParameter_id_sequence",
            sequenceName = "labParameter_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "labParameter_id_sequence")
    private long parameterId;


    @Enumerated(EnumType.STRING)
    private LabParamType type;

    /**
     * "Emoglobina"..."Creatinina"..."Glicemia"
     */
    private String parameterName;

    @ManyToOne(fetch = FetchType.LAZY)
    private LabTest labTest;

    /**
     * private Patient patient: inserirlo comunque oppure ricavarlo dal LabTest?
     */
    @ManyToOne(fetch = FetchType.LAZY)
    private Patient patient;

    /**
     * "result" mi sembra meglio di "value", è più chiaro di cosa stiamo parlando
     * TODO capire come si riporta l'unità di misura
     * (che cambia a seconda della sottoclasse di LabParameter:
     * Emoglobina in g/dL, Creatinina mg/dl...)
     */

    private double result;

    /**
     * I valori di riferimento in realtà cambiano in base al sesso
     * (e alcuni anche in base alla fascia di età)
     * Però PER IL MOMENTO LASCEREI LE COSE PIù SEMPLICI POSSIBILE
     * (siamo sempre in tempo a implementare successivamente
     * un referenceValueMale e un referenceValueFemale
     * (che peraltro richiederanno un nuovo field nella classe Patient,
     * il sesso appunto)
     */

    private double minReferenceValue;
    private double maxReferenceValue;

    /**
     * boolean isNotInRange: CORRISPONDE A QUELLA STELLINA CHE VEDIAMO
     * NEI NOSTRI ESAMI QUANDO IL RISULTATO NON VA TANTO BENE!
     * <p>
     * Questo parametro si ricava confrontando RESULT e REFERENCEVALUES:
     * (quindi potremmo ometterlo, ma poi dovremmo inserire la logica necessaria a calcolarlo...
     * ...come dice Carlo potrebbe essere comodo un campo specifico)
     *
     * Significato: di base è FALSE (valore default dei boolean: false)
     * cioè di base il nostro LabParam è "IN RANGE", nei valori di riferimento
     * <p>
     * Se il RESULT è davvero dentro ai valori di riferimento: rimane false
     * Esempio: Emoglobina 12.5 g/dl
     * --> se l'esame di lab è di una donna, OK, in range (12-16 g/dl)
     * <p>
     * Ma se il RESULT è fuori dai valori di riferimento
     * (inferiore o superiore), va impostato a TRUE
     * Esempio: Emoglobina 12.5 g/dl (come sopra)
     * --> se l'esame di lab è di un uomo: lieve anemia! (range 14-18 g/dl)
     */

    //@Column(name = "not_in_range")
    private boolean isNotInRange;

    public LabParameter() {}

    /**
     * Costruttore che mi serve per creare un labParam
     * soltanto a partire dal type (Creatinine , Glucose...)
     * @param type
     */
    public LabParameter(LabParamType type, Patient patient){
        this.type = type;
        this.patient = patient;
    }

    public LabParameter(LabParamType type, String parameterName, LabTest labTest, Patient patient, double result,
                        double minReferenceValue, double maxReferenceValue, boolean isNotInRange) {
        this.type = type;
        this.parameterName = parameterName;
        this.labTest = labTest;
        this.patient = patient;
        this.result = result;
        this.minReferenceValue = minReferenceValue;
        this.maxReferenceValue = maxReferenceValue;
        this.isNotInRange = isNotInRange;
    }

    public long getParameterId() {
        return parameterId;
    }

    public void setParameterId(long parameterId) {
        this.parameterId = parameterId;
    }

    public LabParamType getType() {
        return type;
    }

    public void setType(LabParamType type) {
        this.type = type;
    }

    public String getParameterName() {
        return parameterName;
    }

    public void setParameterName(String parameterName) {
        this.parameterName = parameterName;
    }

    public LabTest getLabTest() {
        return labTest;
    }

    public void setLabTest(LabTest labTest) {
        this.labTest = labTest;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public double getMinReferenceValue() {
        return minReferenceValue;
    }

    public void setMinReferenceValue(double minReferenceValue) {
        this.minReferenceValue = minReferenceValue;
    }

    public double getMaxReferenceValue() {
        return maxReferenceValue;
    }

    public void setMaxReferenceValue(double maxReferenceValue) {
        this.maxReferenceValue = maxReferenceValue;
    }

    public boolean isNotInRange() {
        return isNotInRange;
    }

    public void setNotInRange(boolean notInRange) {
        isNotInRange = notInRange;
    }


}
