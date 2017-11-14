/**
 * 
 * An interface describing the functionality that all
 * users share within the plagiarism detection software.
 *
 */
public interface IUser {
	/**
	 * Gets the name of the user
	 * @return the name of the user
	 */
	String getName();
	/**
	 * Sets the name of the user.
	 * @param _name - the name given to the user
	 */
	void setName(String _name);
	/**
	 * Gets the username of the user
	 * @return the name of the user
	 */
	String getUsername();
	/**
	 * Sets the username (unique identifier) of the user.
	 * @param _username - the username given to the user
	 */
	void setUsername(String _username);
	/**
	 * Gets the password of the user
	 * @return the password of the user
	 */
	String getPassword();
	/**
	 * Sets the password of the user
	 * @param _password - the password 
	 */
	void setPassword(String _password);
	/**
	 * Authenticates the user before allowing access to the application.
	 * If successful, the user assumes the identity of the user associated
	 * with the provided username.
	 * @param username - the username entered by someone attempting to login
	 * @param _password - the password entered by someone attempting to login
	 */
	void login(String username, String _password);
	// Get rid of resetPassword() replaced by setter
	/**
	 * Sends a verification message to the user before allowing him
	 * to reset his password.
	 */
	void forgotPassword();
}
