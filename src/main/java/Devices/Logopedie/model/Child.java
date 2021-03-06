package Devices.Logopedie.model;

public class Child {
    private int id;
    private int logopedistid;
    private String username;
    private String password;
    private String email;
    private int phonenumber;
    private int child_notifications;
    private String child_photo;
    private int munten;
    private String huiswerk;

    public Child(int id, int logopedistid, String username, String password, String email, int phonenumber,
            int child_notifications, String child_photo, int munten, String huiswerk) {
        this.setId(id);
        this.setLogopedistid(logopedistid);
        this.setUsername(username);
        this.setPassword(password);
        this.setEmail(email);
        this.setPhonenumber(phonenumber);
        this.setChild_notifications(child_notifications);
        this.setChild_photo(child_photo);
        this.setMunten(munten);
        this.setHuiswerk(huiswerk);
    }

    public String getHuiswerk() {
        return huiswerk;
    }

    public void setHuiswerk(String huiswerk) {
        this.huiswerk = huiswerk;
    }

    public int getMunten() {
        return munten;
    }

    public void setMunten(int munten) {
        this.munten = munten;
    }

    /**
     * @return the child_photo
     */
    public String getChild_photo() {
        return child_photo;
    }

    /**
     * @param child_photo the child_photo to set
     */
    public void setChild_photo(String child_photo) {
        this.child_photo = child_photo;
    }

    /**
     * @return the child_notifications
     */
    public int getChild_notifications() {
        return child_notifications;
    }

    /**
     * @param child_notifications the child_notifications to set
     */
    public void setChild_notifications(int child_notifications) {
        this.child_notifications = child_notifications;
    }

    /**
     * @return the phonenumber
     */
    public int getPhonenumber() {
        return phonenumber;
    }

    /**
     * @param phonenumber the phonenumber to set
     */
    public void setPhonenumber(int phonenumber) {
        this.phonenumber = phonenumber;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the logopedistid
     */
    public int getLogopedistid() {
        return logopedistid;
    }

    /**
     * @param logopedistid the logopedistid to set
     */
    public void setLogopedistid(int logopedistid) {
        this.logopedistid = logopedistid;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }


}