package GUI;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;

import java.io.BufferedWriter;
import java.io.FileWriter;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;

import Classes.AES;
import Classes.Database;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class addNewDb {
	private static Text newDbName;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args, Database[] dbs, int position) {
		Display display = Display.getDefault();
		Shell shell = new Shell();
		shell.setSize(340, 101);
		shell.setText("SWT Application");
		
		Label lblNameOfThe = new Label(shell, SWT.NONE);
		lblNameOfThe.setBounds(10, 10, 153, 14);
		lblNameOfThe.setText("Name of the New Database:");
		
		newDbName = new Text(shell, SWT.BORDER);
		newDbName.setBounds(169, 10, 153, 19);
		
		Button btnSubmit = new Button(shell, SWT.NONE);
		btnSubmit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Database db = enterDb(newDbName.getText());
				addNewInfo.main(args, db);
				dbs[position] = db;
				shell.close();
			}
		});
		btnSubmit.setBounds(169, 35, 153, 28);
		btnSubmit.setText("Submit The Name");
		
		Label errorLabel = new Label(shell, SWT.NONE);
		errorLabel.setBounds(10, 36, 60, 14);

		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
	
	public static Database enterDb(String dbName) {
		Database db = new Database(dbName);
		return db;
		
	}

	public static void enterInfo(Database db, String username, String email, String password) {
		db.addUser(username, email, password);
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

}
