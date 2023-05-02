package develhope.DClinic.controller;

import develhope.DClinic.domain.DownLoadProfilePictureDTO;
import develhope.DClinic.domain.User;
import develhope.DClinic.domain.UserDTO;
import develhope.DClinic.exceptions.UserNotFoundException;
import develhope.DClinic.repository.UserRepository;
import develhope.DClinic.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    private Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    /**
     * TODO inserisci controllo: se[] dim 0 (vuoto) lancia eccezione
     */
    @PostMapping("/{id}/upload-profile")
    public ResponseEntity uploadProfilePicture(@PathVariable Integer userId, @RequestParam MultipartFile[] profilePicture) {
        if(profilePicture.length == 0){
            return ResponseEntity.noContent().build();
        }
        else if (profilePicture.length > 1) {
            return ResponseEntity.badRequest().body("Too much profile pitcures, please upload only one!");
        }
        try {
            log.info("Uploading profile picture for user " + userId);
            //upload the single profile picture into the hard disk
            //and write its partial path into correspondent user entity
            return ResponseEntity.status(HttpStatus.OK).body(userService.uploadProfilePicture(userId, profilePicture[0]));
        } catch (UserNotFoundException e) {
            log.warn(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            log.warn(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }


    /**
     * To set the correct content type nell'header
     * I have to set png type to ResponseEntity
     * TODO find a way to set multiple MediaType (GIF, PNG...PDF!)
     */
    @RequestMapping(value = "/{id}/profile-picture-download/{userId}", method = RequestMethod.GET,
            produces = MediaType.IMAGE_PNG_VALUE)
    public @ResponseBody byte[] downloadProfileImage(@PathVariable long userId, HttpServletResponse response) throws Exception {
        DownLoadProfilePictureDTO downLoadProfileDTO = userService.downLoadProfilePicture(userId);
        //System.out.println("Downloading " + profilePictureName);
        //cosa da fare nel Controller: dire qual'è l'ESTENSIONE
        //qui il profilePictureName lo prendo dal DTO!!!
        String fileName = downLoadProfileDTO.getUser().getProfilePictureFileName();
        if(fileName == null) throw new Exception ("User does not have a profile picture!");
        String extension = FilenameUtils.getExtension(fileName);
        switch (extension){
            case "gif":
                response.setContentType(MediaType.IMAGE_GIF_VALUE);
                break;
            case "jpg":
            case "jpeg":
                response.setContentType(MediaType.IMAGE_JPEG_VALUE);
                break;
            case "png":
                response.setContentType(MediaType.IMAGE_PNG_VALUE);
                break;
        }
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"" );
        return downLoadProfileDTO.getProfileImage();
    }

    @PostMapping("/create-user")
    public User create(@RequestBody UserDTO userDTO){
        //user.setId(null); <-- ma perché Lorenzo lo fa?
        User newUser = new User(userDTO.getFirstName(), userDTO.getLastName(), userDTO.getEmail());
        return userRepository.save(newUser);
    }

    @GetMapping("/get-all-users")
    public List<User> getAll(){
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public User getOne(@PathVariable long id) throws Exception {
        Optional<User> userToFind = userRepository.findById(id);
        if(!userToFind.isPresent()){
            throw new Exception ("User with id " + id + " not present in database");
        }
        User newUser = userToFind.get();
        return newUser;
    }

    @GetMapping("/{id}/profile")
    public void getProfileImage(){}

    @PutMapping("/{id}")
    public void update(@RequestBody User user, @PathVariable long id){
        user.setId(id);
        userRepository.save(user);
    }

    @DeleteMapping("/delete-one/{userId}")
    public void delete(@PathVariable long userId) throws Exception {
        //userRepository.deleteById(id); : questo non basta, mi serve della logica!
        userService.removeUser(userId);
    }
}