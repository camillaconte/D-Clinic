package develhope.DClinic.domain;


public class DownLoadProfilePictureDTO {

    private User user;
    private byte[] profileImage;

    public DownLoadProfilePictureDTO(){}

    public DownLoadProfilePictureDTO(User user, byte[] profileImage) {
        this.user = user;
        this.profileImage = profileImage;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public byte[] getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(byte[] profileImage) {
        this.profileImage = profileImage;
    }
}
