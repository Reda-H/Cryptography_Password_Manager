package GUI;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;

import Classes.Database;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class addNewInfo {
	private static Text usernameText;
	private static Text emailText;
	private static Text passwordText;

	/**
	 * Launch the application.
	 * @param args
	 * @wbp.parser.entryPoint
	 */
	public static void main(String[] args, Database db) {
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
		
		usernameText = new Text(shell, SWT.BORDER);
		usernameText.setBounds(104, 30, 113, 19);
		
		emailText = new Text(shell, SWT.BORDER);
		emailText.setBounds(104, 55, 113, 19);
		
		passwordText = new Text(shell, SWT.BORDER);
		passwordText.setBounds(104, 80, 113, 19);
		
		Button btnSave = new Button(shell, SWT.NONE);
		btnSave.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				db.addUser(usernameText.getText(), emailText.getText(), passwordText.getText());
				shell.close();
			}
		});
		btnSave.setBounds(122, 105, 95, 28);
		btnSave.setText("Save");

		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

}
