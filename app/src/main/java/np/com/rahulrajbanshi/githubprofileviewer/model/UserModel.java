package np.com.rahulrajbanshi.githubprofileviewer.model;

/**
 * Created by RAHUL on 8/23/2017.
 */

public class UserModel {

    private String userFullName;
    private String userName;
    private int userId;
    private String avatarUrl;
    private String accountType;
    private boolean isUserSiteAdmin;
    private String company;
    private String blog;
    private String location;
    private String email;
    private String bio;
    private int noOfFollowers;
    private int noOfUsersFollowing;
    private int noOfPublicRepos;
    private int noOfPublicGists;
    private String userCreated;
    private String userUpdated;


    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public boolean isUserSiteAdmin() {
        return isUserSiteAdmin;
    }

    public void setUserSiteAdmin(boolean userSiteAdmin) {
        isUserSiteAdmin = userSiteAdmin;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getBlog() {
        return blog;
    }

    public void setBlog(String blog) {
        this.blog = blog;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public int getNoOfFollowers() {
        return noOfFollowers;
    }

    public void setNoOfFollowers(int noOfFollowers) {
        this.noOfFollowers = noOfFollowers;
    }

    public int getNoOfUsersFollowing() {
        return noOfUsersFollowing;
    }

    public void setNoOfUsersFollowing(int noOfUsersFollowing) {
        this.noOfUsersFollowing = noOfUsersFollowing;
    }

    public int getNoOfPublicRepos() {
        return noOfPublicRepos;
    }

    public void setNoOfPublicRepos(int noOfPublicRepos) {
        this.noOfPublicRepos = noOfPublicRepos;
    }

    public int getNoOfPublicGists() {
        return noOfPublicGists;
    }

    public void setNoOfPublicGists(int noOfPublicGists) {
        this.noOfPublicGists = noOfPublicGists;
    }

    public String getUserCreated() {
        return userCreated;
    }

    public void setUserCreated(String userCreated) {
        this.userCreated = userCreated;
    }

    public String getUserUpdated() {
        return userUpdated;
    }

    public void setUserUpdated(String userUpdated) {
        this.userUpdated = userUpdated;
    }
}
