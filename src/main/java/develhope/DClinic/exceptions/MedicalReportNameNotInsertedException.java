package develhope.DClinic.exceptions;

public class MedicalReportNameNotInsertedException extends Exception {

    public MedicalReportNameNotInsertedException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "Medical Report Name not inserted";
    }
}
