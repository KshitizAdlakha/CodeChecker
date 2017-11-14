/**
 * An interface describing the functionality of admins in the
 * plagiarism detection software. Admins are the type of user
 * that will handle the user data for users of the plagiarism 
 * detection software.
 */
public interface IAdmin extends IUser {
	/**
	 * Creates a user for the application with the specified attributes.
	 * @param _username - the unique identifier of the created user (used for login)
	 * @param _password - the created user's password (used to login)
	 * @param _name - the human name of the created user
	 * @param _type - the type of the user to be created (Instructor or Admin)
	 */
	void createUser(String _username, String _password, String _name, String _type);
	/**
	 * Updates the user to have the specified attributes. Any blank field for a 
	 * new attribute means that that attribute will be left the same.
	 * @param _old_username - the username of the user to be updated (cannot be blank)
	 * @param _new_username - the updated username of the specified user
	 * @param _newPassword - the updated password of the specified user
	 * @param _new_name - the updated human name of the specified user
	 */
	void updateUser(String _old_username, String _new_username, 
			String _newPassword, String _new_name);
	
	/**
	 * Deletes the specified user from the database
	 * @param _username - the unique identifier of the user to be deleted.
	 */
	void deleteUser(String _username);
	
	/**
	 * Returns a text representation of a user.
	 * @param _username - the unique identifier of the user to be displayed
	 * @return a text representation of a user
	 */
	String getUserDetails(String _username);
}
