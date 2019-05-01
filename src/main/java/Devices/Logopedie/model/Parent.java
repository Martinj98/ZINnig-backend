package Devices.Logopedie.model;

public class Parent {
    private int id;
    private int Childid;
    private String email;
    private String username;
    private String password;
    private int phonenumber;
    private int parent_notifications;

    public Parent(int id, int Childid, String email, String username, String password, int phonenumber,
            int parent_notifications) {
        this.setId(id);
        this.setChildid(Childid);
        this.setEmail(email);
        this.setUsername(username);
        this.setPassword(password);
        this.setPhonenumber(phonenumber);
        this.setParent_notifications(parent_notifications);
    }

    /**
     * @return the parent_notifications
     */
    public int getParent_notifications() {
        return parent_notifications;
    }

    /**
     * @param parent_notifications the parent_notifications to set
     */
    public void setParent_notifications(int parent_notifications) {
        this.parent_notifications = parent_notifications;
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
     * @return the childid
     */
    public int getChildid() {
        return Childid;
    }

    /**
     * @param childid the childid to set
     */
    public void setChildid(int childid) {
        this.Childid = childid;
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