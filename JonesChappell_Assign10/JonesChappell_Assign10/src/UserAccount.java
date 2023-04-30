import java.io.Serializable;

/**
 * 
 */

/**
 * @author chappell.jones
 *
 */
public abstract class UserAccount implements Serializable{

	private String username;
	private String password;

	private boolean active;

	/**
	 * The Constructor for creating a UserAccount object
	 * @param username - String for the user
	 * @param password - password for user
	 */
	public UserAccount(String username, String password) {

		this.username = username;
		this.password = password;
		active = true;

	}//End of Constructor

	
	public abstract void getPasswordHelp();
	
	/**
	 * The method to verify if the password is correct
	 * @param password - the password to used for comparison
	 * @return
	 */
	public boolean checkPassword(String password) {
		if (password.equals(this.password)) {
			return true;
		} else {

			return false;
		}
	}// End of checkPassword

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
	 * The deactivate method makes the user account false
	 */
	public void deactivate() {

		active = false;
	}//End of deactivate

	
	
	
	
	
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


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserAccount other = (UserAccount) obj;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return username;
	}


	
}// End of UserAccount class
