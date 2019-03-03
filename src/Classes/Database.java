package Classes;

public class Database {
	
	public String title;
	public int userInfoArrayLength = 0;
	public UserInfo[] userInfoArray = new UserInfo[20];
	
	public Database(String title) {
		this.title = title;
	}
	
	
	public UserInfo[] getUserArray() {
		return this.userInfoArray;
	}
	
	public UserInfo addUser(String username, String email, String password) {
		this.userInfoArray[userInfoArrayLength] = new UserInfo(this.title, username, email, password);
		this.userInfoArrayLength++;
		return this.userInfoArray[userInfoArrayLength-1];
	}
	
}


//public String getDatabase() {
//	return this.dbFile;
//}

//public String Title;
//public int userInfoArrayLength = 0;
//public UserInfo[] userInfoArray = new UserInfo[20];
//
//
//public Database(String Title, AES aes) throws Exception {
//	setTitle(Title, aes);
//}
//
//public Database(String Title, String Pass, AES aes) throws Exception {
//	this.Title = aes.encrypt(Title).toString();
//	this.Pass = aes.encrypt(Pass).toString();
//}
//
//public void setTitle(String Title, AES aes) throws Exception {
//	this.Title = aes.encrypt(Title);
//}
//
//public String getTitle(AES aes) throws Exception {
//	return aes.decrypt(this.Title).toString();
//}
//
//public void addUserInfo(String Title, String Username, String Email, String Password, AES aes) throws Exception {
//	UserInfo addedUser = new UserInfo(aes.encrypt(Title).toString(), aes.encrypt(Username).toString(), aes.encrypt(Email).toString(), aes.encrypt(Password).toString(), aes);
//	userInfoArray[userInfoArrayLength++] = addedUser;
//}
//
//public void retrieveUserInfo(String Title, String Username, String Email, String Password, AES aes, int i) throws Exception {
//	UserInfo addedUser = new UserInfo(aes.decrypt(Title).toString(), aes.decrypt(Username).toString(), aes.decrypt(Email).toString(), aes.decrypt(Password).toString(), aes);
//	this.userInfoArray[i] = addedUser;
//	userInfoArrayLength++;
//}
//
//public int userLength() {
//	return userInfoArrayLength;
//}
//
////GetArray
//public UserInfo getUserInfoArray(int i){
//	return this.userInfoArray[i];
//}