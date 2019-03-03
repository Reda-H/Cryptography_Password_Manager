package GUI;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import Classes.AES;
import Classes.Database;

import org.eclipse.swt.widgets.List;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Text;

public class mainInterface {
	private static Text newpp_text;

	/**
	 * Launch the application.
	 * @param args
	 * @throws Exception 
	 * @wbp.parser.entryPoint
	 */
	public static void main(String[] args, String passPhrase) throws Exception {
		AES aes = new AES(padTo16(passPhrase));
		Database[] dbs = retrieveDbs(aes);
		Display display = Display.getDefault();
		Shell shell = new Shell();
		shell.setSize(461, 340);
		shell.setText("SWT Application");
		
		List list_db = new List(shell, SWT.BORDER);
		list_db.setBounds(10, 84, 118, 190);
		
		List list_username = new List(shell, SWT.BORDER);
		list_username.setBounds(134, 84, 102, 190);
		
		List list_email = new List(shell, SWT.BORDER);
		list_email.setBounds(242, 84, 102, 190);
		
		List list_password = new List(shell, SWT.BORDER);
		list_password.setBounds(350, 84, 102, 190);
		
		list_username.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				list_email.setSelection(list_username.getFocusIndex());
				list_password.setSelection(list_username.getFocusIndex());
			}
		});
		
		list_email.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				list_username.setSelection(list_email.getFocusIndex());
				list_password.setSelection(list_email.getFocusIndex());
			}
		});
		
		list_password.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				list_username.setSelection(list_password.getFocusIndex());
				list_email.setSelection(list_password.getFocusIndex());
			}
		});
		
		Button btnLoadData = new Button(shell, SWT.NONE);
		btnLoadData.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				list_db.removeAll();
				list_username.removeAll();
				list_email.removeAll();
				list_password.removeAll();
				for(int i=0; i<sizeOfDbs(dbs); i++) {
					if(dbs[i].title != "")
						list_db.add(dbs[i].title);
				}
				for(int j=0; j<dbs[0].userInfoArrayLength; j++) {
					if((dbs[0].userInfoArray[j].getUsername() != "") && (dbs[0].userInfoArray[j].getEmail() != "") && (dbs[0].userInfoArray[j].getPassword() != ""))
					{
						list_username.add(dbs[0].userInfoArray[j].getUsername());
						list_email.add(dbs[0].userInfoArray[j].getEmail());
						list_password.add(dbs[0].userInfoArray[j].getPassword());
					}
				}
			}
		});
		btnLoadData.setBounds(10, 30, 95, 28);
		btnLoadData.setText("Load Data");
		
		list_db.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(list_db.getFocusIndex() != -1) {
					list_username.removeAll();
					list_email.removeAll();
					list_password.removeAll();
					for(int i=0; i<dbs[list_db.getFocusIndex()].userInfoArrayLength; i++)
					{
						list_username.add(dbs[list_db.getFocusIndex()].userInfoArray[i].getUsername());
						list_email.add(dbs[list_db.getFocusIndex()].userInfoArray[i].getEmail());
						list_password.add(dbs[list_db.getFocusIndex()].userInfoArray[i].getPassword());
					}
				}
			}
		});
		
		Button btnAddADatabase = new Button(shell, SWT.NONE);
		btnAddADatabase.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				addNewDb.main(args, dbs, sizeOfDbs(dbs));
			}
		});
		btnAddADatabase.setBounds(10, 280, 118, 28);
		btnAddADatabase.setText("Add a Database");
		
		Button btnAddUserInfo = new Button(shell, SWT.NONE);
		btnAddUserInfo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				addNewInfo.main(args, dbs[list_db.getFocusIndex()]);
			}
		});
		btnAddUserInfo.setBounds(134, 280, 160, 28);
		btnAddUserInfo.setText("Add User Info");
		
		Button btnSaveData = new Button(shell, SWT.NONE);
		btnSaveData.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					saveDbs(dbs, sizeOfDbs(dbs), aes);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnSaveData.setBounds(357, 30, 95, 28);
		btnSaveData.setText("Save Data");
		
		Button btnModifyInfo = new Button(shell, SWT.NONE);
		btnModifyInfo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(list_username.getFocusIndex() >= 0 || list_email.getFocusIndex() >= 0 || list_password.getFocusIndex() >= 0) {
					if(list_username.getFocusIndex() >= 0)
						modifyUserInfo.main(args, dbs, list_db.getFocusIndex(), list_username.getFocusIndex());
					else if(list_email.getFocusIndex() >= 0)
						modifyUserInfo.main(args, dbs, list_db.getFocusIndex(), list_email.getFocusIndex());
					else if(list_password.getFocusIndex() >= 0)
						modifyUserInfo.main(args, dbs, list_db.getFocusIndex(), list_password.getFocusIndex());
				} else {
				}
			}
		});
		btnModifyInfo.setBounds(292, 280, 160, 28);
		btnModifyInfo.setText("Modify User Info");
		
		Label lblDatabases = new Label(shell, SWT.NONE);
		lblDatabases.setBounds(10, 64, 118, 14);
		lblDatabases.setText("Databases:");
		
		Label lblUsername = new Label(shell, SWT.NONE);
		lblUsername.setBounds(134, 64, 60, 14);
		lblUsername.setText("Username:");
		
		Label lblEmail = new Label(shell, SWT.NONE);
		lblEmail.setText("Email:");
		lblEmail.setBounds(242, 64, 60, 14);
		
		Label lblPassword = new Label(shell, SWT.NONE);
		lblPassword.setText("Password:");
		lblPassword.setBounds(350, 64, 101, 14);
		
		Button btnChangePassphrase = new Button(shell, SWT.NONE);
		btnChangePassphrase.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
			try {
				String texty = newpp_text.getText();
				System.out.println("Here");
				saveDbs(dbs, sizeOfDbs(dbs), new AES(padTo16(texty)));
				System.out.println("Here Actually");
				shell.close();
				passPhraseEntry.main(args);
				System.out.println("Here ???");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			}
		});
		btnChangePassphrase.setBounds(163, 30, 139, 28);
		btnChangePassphrase.setText("Change PassPhrase");
		
		newpp_text = new Text(shell, SWT.BORDER);
		newpp_text.setBounds(173, 5, 124, 19);
		
		Label lblPp = new Label(shell, SWT.NONE);
		lblPp.setBounds(149, 8, 23, 14);
		lblPp.setText("PP:");
		
		list_db.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				list_username.setSelection(0);
				list_email.setSelection(0);
				list_password.setSelection(0);
			}
		});

		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
	
	public static Database[] retrieveDbs(AES aes) throws Exception {
		BufferedReader freader = new BufferedReader(new FileReader("src/Files/save_dbs_list.txt"));
		Database[] dbs = new Database[20];
		String dbname = "", username, email, password, str;
		int count = -1;
		while((str=freader.readLine())!=null) {
			if(!(aes.decrypt(str).equalsIgnoreCase(dbname))){
				count++;
				dbname = aes.decrypt(str);
				dbs[count] = new Database(dbname);
			} 
			username = aes.decrypt(freader.readLine());
			email = aes.decrypt(freader.readLine());
			password = aes.decrypt(freader.readLine());
			dbs[count].addUser(username, email, password);
		}
		if(count < 20) {
			for(int i = count+1; i<20; i++) {
				dbs[i] = new Database("");
				dbs[i].addUser("", "", "");
			}
		}
		freader.close();
		return dbs;
	}
	
	public static void saveDbs(Database[] dbs, int count, AES aes) throws Exception{
		BufferedWriter fwriter = new BufferedWriter(new FileWriter("src/Files/save_dbs_list.txt"));
		for(int i=0; i<count; i++) {
			for(int j=0; j<dbs[i].userInfoArrayLength; j++) {
				if(dbs[i].title != "")
				{
					fwriter.append(aes.encrypt(dbs[i].title) + "\r");
					fwriter.append(aes.encrypt(dbs[i].userInfoArray[j].getUsername()) + "\r");
					fwriter.append(aes.encrypt(dbs[i].userInfoArray[j].getEmail()) + "\r");
					fwriter.append(aes.encrypt(dbs[i].userInfoArray[j].getPassword()) + "\r");
				}
			}
		}
		fwriter.close();
	}
	
	
	public static String padTo16(String str) {
		while(str.getBytes().length < 16)
			str += "_";
		return str;
	}
	
	public static int sizeOfDbs(Database[] dbs) {
		for(int i=0; i<20; i++) {
			if(dbs[i].title == "") {
				return i;
			}
		}
		return 20;
	}
}
