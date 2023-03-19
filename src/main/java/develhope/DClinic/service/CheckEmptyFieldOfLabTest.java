package develhope.DClinic.service;

import develhope.DClinic.domain.LabTestDTO;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.HashSet;

@Service
public class CheckEmptyFieldOfLabTest {
    public HashSet<String> checkEmptyFieldNewLabTest(LabTestDTO u){
        HashSet<String> MESSAGE_ERROR = new HashSet<>();
        if(u.getPatient() == null){
            MESSAGE_ERROR.add("PATIENT NOT INSERT");
        }
        if(u.getDescription() == null){
            MESSAGE_ERROR.add("DESCRIPTION NOT INSERT");
        }
        if(u.getResult() == null){
            MESSAGE_ERROR.add("RESULT NOT INSERT");
        }
        return MESSAGE_ERROR;
    }

    public void checkAndInsertDate(LabTestDTO u){
        if(u.getDate() == null){
            u.setDate(LocalDateTime.now());
        }
    }
    
   

}

