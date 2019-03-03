/**
 * 
 */
package Classes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author Flawles
 *
 */
public class test {

	/**
	 * @param args 
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		String answer;
		Database[] dbs = new Database[20];
		int countDbs = 0;
		boolean fTime = true;
		String passPhrase = enterPass();
		AES masterPass = new AES(passPhrase);
		try {
		BufferedReader freader = new BufferedReader(new FileReader("src/Files/save_dbs_list.txt"));
		fTime = false;
		freader.close();
		} catch (FileNotFoundException e){
			System.out.println("First time");
			fTime = true;
		}
		//First Time, enter a passphrase to be used
		if(fTime) {
			//Create first db
			dbs[countDbs] = enterDb(masterPass);
			countDbs++;
			System.out.println("Save dbs ?");
			answer = scanner.nextLine();
			if(answer.equalsIgnoreCase("y")) {
				saveDbs(dbs, countDbs, masterPass);
			}
		}
		//Every db is a file
		//Every file has 20 lines of info
			//encrypt the lines of info
		//Not first connection
		System.out.println("Access to your dbs (y/n)");
		answer = scanner.nextLine();
		countDbs = 1;
		if(answer.equalsIgnoreCase("y")) {
			dbs = retrieveDbs(masterPass);
			printDbs(dbs, countDbs);
		}
		//Save -> to files
		//Now enter passphrase -> decrypt with it everything and give access to it
		//Enter Master PassPhrase
		
		// TODO Auto-generated method stub

	}
	
	public static String enterPass() {
		Scanner scanner = new Scanner(System.in);
		String pass;
		System.out.println("Enter a chosen passphrase to encrypt your data: ");
		pass = scanner.nextLine();
		if(pass.getBytes().length < 16)
			pass = padTo16(pass);
		
		return pass;
	}
	
	public static Database enterDb(AES aes) {
		Scanner scanner = new Scanner(System.in);
		String dbName;
		System.out.println("Chose a name for your Db: ");
		dbName = scanner.nextLine();
		Database db = new Database(dbName);
		System.out.println("You created your first db, now create infos to encrypt: ");
		enterInfo(db);
		return db;
		
	}
	
	public static void enterInfo(Database db) {
		Scanner scanner = new Scanner(System.in);
		String username, email, password;
		System.out.println("Username: ");
		username = scanner.nextLine();
		System.out.println("Email: ");
		email = scanner.nextLine();
		System.out.println("Password: ");
		password = scanner.nextLine();
		db.addUser(username, email, password);
		System.out.println("Created user with: username: " + username + "email: " + email + "password: " + password);
	}
	
	public static Database[] retrieveDbs(AES aes) throws Exception {
		BufferedReader freader = new BufferedReader(new FileReader("src/Files/save_dbs_list.txt"));
		Database[] dbs = new Database[20];
		String dbname, username, email, password, str;
		int count = 0;
		while((str=freader.readLine())!=null) {
//			dbname = unPad(aes.decrypt(str));
			dbname = aes.decrypt(str);
			username = aes.decrypt(freader.readLine());
			email = aes.decrypt(freader.readLine());
			password = aes.decrypt(freader.readLine());
			dbs[count] = new Database(dbname);
			dbs[count].addUser(username, email, password);
			count++;
			break;
		}
		freader.close();
		return dbs;
	}
	
	public static void saveDbs(Database[] dbs, int count, AES aes) throws Exception{
		BufferedWriter fwriter = new BufferedWriter(new FileWriter("src/Files/save_dbs_list.txt"));
		for(int i=0; i<count; i++) {
			fwriter.append(aes.encrypt(dbs[i].title) + "\r");
			for(int j=0; j<dbs[i].userInfoArrayLength; j++) {
				fwriter.append(aes.encrypt(dbs[i].userInfoArray[j].getUsername()) + "\r");
				fwriter.append(aes.encrypt(dbs[i].userInfoArray[j].getEmail()) + "\r");
				fwriter.append(aes.encrypt(dbs[i].userInfoArray[j].getPassword()) + "\r");
			}
		}
		fwriter.close();
	}
	
	public static String padTo16(String str) {
		while(str.getBytes().length < 16)
			str += "_";
		return str;
	}
	
	public static String unPad(String str) {
		for(int i=0; i<str.length(); i++) {
			if(str.charAt(i) != '_')
				str += str.charAt(i);
		}
		return str;
	}
	
	public static void printDbs(Database[] dbs, int count) {
		for(int i=0; i<count; i++) {
			System.out.println(dbs[i].title);
			for(int j=0; j<dbs[i].userInfoArrayLength; j++) {
				System.out.println(dbs[i].userInfoArray[0].getUsername());
				System.out.println(dbs[i].userInfoArray[0].getEmail());
				System.out.println(dbs[i].userInfoArray[0].getPassword());
			}
		}
	}

}
