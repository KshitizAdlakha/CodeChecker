public interface IUser {
	String getName();
	void setName(String _name);
	String getUsername();
	void setUsername(String _username);
	String getPassword();
	void setPassword();
	
	void login(String _password);
	// Get rid of resetPassword() replaced by setter
	void forgotPassword();
}
