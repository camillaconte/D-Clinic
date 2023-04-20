package develhope.DClinic.exceptions;

public class MedicalReportNameNotFoundException extends Exception {

    public MedicalReportNameNotFoundException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "Medical Report Name not inserted";
    }
}
