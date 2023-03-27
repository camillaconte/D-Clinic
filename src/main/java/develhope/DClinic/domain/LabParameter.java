package develhope.DClinic.domain;

import jakarta.persistence.*;

import javax.annotation.processing.Generated;

/**
 * @author camilla conte
 * Entity per i PARAMETRI DI LABORATORIO
 * (e.g. Emoglobina, Creatinina)
 *
 * RAZIONALE: nella pratica clinica un referto con gli esami di lab
 * potrebbe possedere più che una "String result" una List<LabParameter>
 *
 */

@Entity
@Table
public class LabParameter {

    @Id
    @SequenceGenerator(
            name = "labParameter_id_sequence",
            sequenceName = "labParameter_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "labParameter_id_sequence")
    private long id;

    @Column(nullable = false)
    private String name;

    /**
     * I valori di riferimento cambiano in base al sesso
     * e in realtà ultimamente si è capito che possono variare anche in base alla fascia di età
     * ...ma non complichiamoci la vita!
     */
    @Column(name = "reference_man", nullable = false)
    private double referenceValueMale;

    @Column(name = "reference_woman", nullable = false)
    private double referenceValueFemale;

    /**
     * come si riporta l'unità di misura? (che sarà diversa per ogni parametro)
     */

    /**
     * ULTERIORE COMPLICAZIONE (da inserire in un secondo momento):
     * un esame di lab potrebbe contenere altri sottoesami:
     * esempio Emocromo: contiene Emoglobina, Globuli rossi, Globuli bianchi...
     */


    /**
     * in realtà questo parametro si ricava
     * confrontando value e referenceValue: quindi potremmo ometterlo
     * e inserire la logica necessaria...
     * ...ma al front end potrebbe servire avere un campo specifico
     *
     * Significato: se il value è dentro al range di riferimento
     * (Esempio: name = Emoglobina, value = 12.5
     * --> se l'esame di lab è di una donna, OK, in range (12-16 g/dl)
     * --> se l'esame di lab è di un uomo: lieve anemia! (range 14-18 g/dl)
     */
    @Column(name = "not_in_range")
    private String isNotInRangeStar;

    public LabParameter(){}

    public LabParameter(long id, String name, double referenceValueMale,
                        double referenceValueFemale, String isNotInRangeStar) {
        this.id = id;
        this.name = name;
        this.referenceValueMale = referenceValueMale;
        this.referenceValueFemale = referenceValueFemale;
        this.isNotInRangeStar = isNotInRangeStar;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getReferenceValueMale() {
        return referenceValueMale;
    }

    public void setReferenceValueMale(double referenceValueMale) {
        this.referenceValueMale = referenceValueMale;
    }

    public double getReferenceValueFemale() {
        return referenceValueFemale;
    }

    public void setReferenceValueFemale(double referenceValueFemale) {
        this.referenceValueFemale = referenceValueFemale;
    }

    public String getIsNotInRangeStar() {
        return isNotInRangeStar;
    }

    public void setIsNotInRangeStar(String isNotInRangeStar) {
        this.isNotInRangeStar = isNotInRangeStar;
    }
}
