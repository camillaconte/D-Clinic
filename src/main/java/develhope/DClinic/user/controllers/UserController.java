package develhope.DClinic.user.controllers;

import develhope.DClinic.user.domain.entities.User;
import develhope.DClinic.user.domain.dto.UserDTO;
import develhope.DClinic.user.exceptions.UserNotFoundException;
import develhope.DClinic.user.repositories.UserRepository;
import develhope.DClinic.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
     * TODO valutare se ha senso dare la possibilità di caricare più file
     * (visto che alla fine gliene facciamo caricare uno solo!)
     */
    @PostMapping("/upload-profile/{userId}")
    public ResponseEntity uploadProfilePicture(@PathVariable Integer userId, @RequestParam MultipartFile[] profilePicture) {
        if(profilePicture.length == 0){
            return ResponseEntity.noContent().build();
        }
        else if (profilePicture.length > 1) {
            return ResponseEntity.badRequest().body("Too much profile pictures, please upload only one!");
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

    @RequestMapping (value = "/profile-picture-any/{userId}", method = RequestMethod.GET,
            produces = { MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_GIF_VALUE})
    public ResponseEntity viewAnyProfilePicture(@PathVariable long userId){
        try {
            log.info("Requested profile picture for user: " + userId);
            return ResponseEntity.ok(userService.viewProfilePicture(userId));
        }catch (UserNotFoundException e){
            log.warn(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (IOException e) {
            log.warn(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    /**
     * TODO Change MediaType to PDF (it says "Attribute value must be constant" when I set MediaType.APPLICATION_PDF)
     */

    @RequestMapping(value = "/view-PDF/{userId}", method = RequestMethod.GET, produces = MediaType.IMAGE_GIF_VALUE)
    public ResponseEntity viewPDF(@PathVariable long userId){
        try {
            log.info("Requested PDF for user: " + userId);
            return ResponseEntity.ok(userService.viewProfilePicture(userId));
        }catch (UserNotFoundException e){
            log.warn(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (IOException e) {
            log.warn(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }


    @PostMapping("/create-user")
    public User create(@RequestBody UserDTO userDTO){
        //user.setId(null); <-- ma perché Lorenzo lo fa?
        User newUser = new User();
        newUser.setFiscalCode(userDTO.getEmail());
        newUser.setPassword(newUser.getPassword());
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