package develhope.DClinic.exceptions;

public class PatientNotFoundException extends Exception {

    public PatientNotFoundException(String message){
        super(message);
    }

    @Override
    public String getMessage() {
        return "Patient non found";
    }

}
