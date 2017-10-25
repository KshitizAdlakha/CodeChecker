public interface IAdmin extends IUser {
	void createUser(String _username, String _password, String _name);
	void updateUser(String _old_username, String _new_username,
			String _oldPassword, String _newPassword,
			String _old_name, String _new_name);
	void deleteUser(String _username);
	void getUserDetails(String _username);
	
}
