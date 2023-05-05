package develhope.DClinic.medicalstuff.exceptions;

public class MedicalReportsNotFoundException extends Exception {

    public MedicalReportsNotFoundException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "Medical Report List not Found";
    }

}
