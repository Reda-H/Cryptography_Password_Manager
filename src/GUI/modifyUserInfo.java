package GUI;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import Classes.Database;

public class modifyUserInfo {
	private static Text text_username;
	private static Text text_email;
	private static Text text_password;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args, Database[] dbs, int selected, int selected2) {
		Display display = Display.getDefault();
		Shell shell = new Shell();
		shell.setSize(228, 159);
		shell.setText("SWT Application");
		
		Label lblInformationToEncrypt = new Label(shell, SWT.NONE);
		lblInformationToEncrypt.setBounds(10, 10, 147, 14);
		lblInformationToEncrypt.setText("Information to Encrypt:");
		
		Label lblUsername = new Label(shell, SWT.NONE);
		lblUsername.setBounds(20, 30, 60, 14);
		lblUsername.setText("Username:");
		
		Label lblEmail = new Label(shell, SWT.NONE);
		lblEmail.setText("Email:");
		lblEmail.setBounds(20, 55, 60, 14);
		
		Label lblPassword = new Label(shell, SWT.NONE);
		lblPassword.setText("Password:");
		lblPassword.setBounds(20, 80, 60, 14);
		
		Button btnSave = new Button(shell, SWT.NONE);
		btnSave.setBounds(122, 105, 95, 28);
		btnSave.setText("Save");
		
		text_username = new Text(shell, SWT.BORDER);
		text_username.setBounds(86, 30, 131, 19);
		text_username.setText(dbs[selected].userInfoArray[selected2].getUsername());
		
		text_email = new Text(shell, SWT.BORDER);
		text_email.setBounds(86, 55, 131, 19);
		text_email.setText(dbs[selected].userInfoArray[selected2].getEmail());
		
		text_password = new Text(shell, SWT.BORDER);
		text_password.setBounds(86, 80, 131, 19);
		text_password.setText(dbs[selected].userInfoArray[selected2].getPassword());
		
		btnSave.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				dbs[selected].userInfoArray[selected2].setUsername(text_username.getText());
				dbs[selected].userInfoArray[selected2].setEmail(text_email.getText());
				dbs[selected].userInfoArray[selected2].setPassword(text_password.getText());
				shell.close();
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

}
