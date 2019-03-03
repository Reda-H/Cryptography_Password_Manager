package GUI;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class passPhraseEntry {

	protected Shell shell;
	private Text text;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			passPhraseEntry window = new passPhraseEntry();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(364, 81);
		shell.setText("SWT Application");
		
		Label lblEnterPassphrase = new Label(shell, SWT.NONE);
		lblEnterPassphrase.setBounds(10, 10, 107, 14);
		lblEnterPassphrase.setText("Enter PassPhrase:");
		
		text = new Text(shell, SWT.BORDER | SWT.PASSWORD);
		text.setBounds(142, 10, 212, 19);

		Label errorLabel = new Label(shell, SWT.NONE);
		errorLabel.setBounds(10, 35, 176, 28);
		
		Button btnSubmit = new Button(shell, SWT.NONE);
		btnSubmit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String[] args = null;
				try {
					mainInterface.main(args, text.getText());
					shell.close();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					errorLabel.setText("Wrong PassPhrase.");
					
				}
			}
		});
		btnSubmit.setBounds(192, 35, 162, 28);
		btnSubmit.setText("Submit");
		
	}

}
