package Devices.Logopedie.model;

public class Logopedist {
	private int id;
    private int praktijk_id;
    private String email;
    private String password;
    private String username;
    private int phonenumber;

    public Logopedist(int id, int praktijk_id, String email, String password, String username, int phonenumber) {
        this.id = id;
        this.setPraktijk_id(praktijk_id);
        this.setEmail(email);
        this.setPassword(password);
        this.setUsername(username);
        this.setPhonenumber(phonenumber);
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
     * @return the praktijk_id
     */
    public int getPraktijk_id() {
        return praktijk_id;
    }

    /**
     * @param praktijk_id the praktijk_id to set
     */
    public void setPraktijk_id(int praktijk_id) {
        this.praktijk_id = praktijk_id;
    }

    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

}