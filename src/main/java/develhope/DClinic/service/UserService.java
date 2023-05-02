package develhope.DClinic.service;

import develhope.DClinic.domain.DownLoadProfilePictureDTO;
import develhope.DClinic.domain.User;
import develhope.DClinic.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    FileStorageService fileStorageService;

    private User getUser(long userId) throws Exception {
        Optional<User> optionalUser = userRepository.findById(userId);
        if(!optionalUser.isPresent()) throw new Exception("User is not present!");
        return optionalUser.get();
    }

    // vedi uso di @SneakyThrows ma richiede Lombok!!!
    public User uploadProfilePicture(long userId, MultipartFile profilePicture) throws Exception {
        //TODO usare Optional per lo user
        User user = getUser(userId);
        if(user.getProfilePictureFileName() != null){
            fileStorageService.remove(user.getProfilePictureFileName());
        }
        //fileStorageService.upload() assigns the file a name, saves it into hard disk
        // and return the name
        String fileName = fileStorageService.upload(profilePicture);
        user.setProfilePictureFileName(fileName);
        return userRepository.save(user);
    }

    public DownLoadProfilePictureDTO downLoadProfilePicture(long userId) throws Exception {
        //prendo l'utente (e se non c'è verrà sollevata un'eccezione)
        User user = getUser(userId);
        //creiamo il nostro "DTO di uscita" e ci mettiamo dentro l'utente
        DownLoadProfilePictureDTO dto = new DownLoadProfilePictureDTO();
        dto.setUser(user);
        if(user.getProfilePictureFileName() == null){
            return dto;
        }
        byte[] profilePictureBytes = fileStorageService.download(user.getProfilePictureFileName());
        dto.setProfileImage(profilePictureBytes);
        return dto;
    }

    public byte[] viewProfilePicture(long userId) throws Exception {
        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isEmpty()) throw new Exception("Cannot find user " + userId);
        String profilePictureFileName = optionalUser.get().getProfilePictureFileName();
        return fileStorageService.download(profilePictureFileName);
    }

    public void removeUser(long userId) throws Exception {
        //DEVO ANCHE CANCELLARE il file dal DB
        User user = getUser(userId); //vabbé dovrei controllare se esiste!
        if(user.getProfilePictureFileName() != null) {
            fileStorageService.remove(user.getProfilePictureFileName());
        }
        userRepository.deleteById(userId);
    }
}
