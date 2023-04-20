package develhope.DClinic.exceptions;

public class DoctorNotFoundException extends Exception {

    public DoctorNotFoundException(String message) { super(message); }

    @Override
    public String getMessage(){
        return "Doctor not found";
    }
}
