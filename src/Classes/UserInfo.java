package Classes;

public class UserInfo {

	String dbName;
	String username;
	String email;
	String password;
	
	public UserInfo(String dbName, String username, String password, String email) {
		this.dbName = dbName;
		this.username = username;
		this.email = email;
		this.password = password;
	}

	public String getDbName() {
		return this.dbName;
	}
	
	public String getUsername(){
		return this.username;
	}

	public String getEmail(){
		return this.email;
	}
	
	public String getPassword(){
		return this.password;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
//	public UserInfo(String Title, String Password, String Email, AES PassPhrase) {
//		setTitle(Title);
//		setPassword(Password);
//		setPassPhrase(PassPhrase);
//		setEmail(Email);
//	}
//	
//	public UserInfo(String Title, String Username, String Email, String Password, AES PassPhrase) {
//		setTitle(Title);
//		setUsername(Username);
//		setEmail(Email);
//		setPassword(Password);
//		setPassPhrase(PassPhrase);
//	}
//	
//	public String getPassword(AES pass) throws Throwable {
//		try {
//			return pass.decrypt(this.Password).toString();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
////			return "Wrong PassPhrase";
//			return "Wrong Pass";
//		}
//	}
//	
//	public String getTuple() throws Exception {
//		return this.Title + this.Username + this.Email + this.Password;
//	}
//
//	public String getUsername() throws Exception {
//		return getPassPhrase().decrypt(this.Username).toString();
//	}
//	
//	public String getEmail() throws Exception {
//		return getPassPhrase().decrypt(this.Email).toString();
//	}
//	
//	AES getPassPhrase() {
//		return this.PassPhrase;
//	}
//	
//	void setPassword(String Password) {
//		this.Password = Password;
//	}
//	
//	void setTitle(String Title) {
//		this.Title = Title;
//	}
//
//	void setPassPhrase(AES PassPhrase) {
//		this.PassPhrase = PassPhrase;
//	}
//	
//	void setUsername(String Username) {
//		this.Username = Username;
//	}
//	
//	void setEmail(String Email) {
//		this.Email = Email;
//	}
//	 
//	public void changeTitle(String title) {
//		setTitle(title);
//	}
//	
//	public void changeUsername(String username) {
//		setUsername(username);
//	}
//	
//	public void changePassword(String password) {
//		setPassword(password);
//	}
//	
//	public void changeEmail(String email) {
//		setEmail(email);
//	}
	
}
