package tombolone;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Table;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;

public class ClientSocket {

	Socket s;

	protected Shell shell;
	private Text txtNumero;
	private Table table_1;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ClientSocket window = new ClientSocket();
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
		shell.setSize(450, 300);
		shell.setText("SWT Application");
		shell.setLayout(new org.eclipse.swt.layout.FormLayout());

		txtNumero = new Text(shell, SWT.BORDER);
		FormData fd_txtNumero = new FormData();
		fd_txtNumero.left = new FormAttachment(0, 80);
		txtNumero.setLayoutData(fd_txtNumero);
		
		

		Button btnNumero = new Button(shell, SWT.NONE);
		FormData fd_btnNumero = new FormData();
		btnNumero.setLayoutData(fd_btnNumero);
		btnNumero.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Socket s;
				try {
					s = new Socket("localhost", 9999);
					PrintWriter out = new PrintWriter(s.getOutputStream(), true);
					out.println("NUMERO");
					int numero = s.getInputStream().read();
					txtNumero.setText(String.valueOf(numero));				
					} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnNumero.setText("Numero");
		
		Button btnRecuperaNumeri = new Button(shell, SWT.NONE);
		fd_btnNumero.top = new FormAttachment(btnRecuperaNumeri, 0, SWT.TOP);
		fd_btnNumero.left = new FormAttachment(btnRecuperaNumeri, 67);
		fd_txtNumero.top = new FormAttachment(btnRecuperaNumeri, 61);
		FormData fd_btnRecuperaNumeri = new FormData();
		fd_btnRecuperaNumeri.top = new FormAttachment(0, 58);
		fd_btnRecuperaNumeri.left = new FormAttachment(0, 42);
		btnRecuperaNumeri.setLayoutData(fd_btnRecuperaNumeri);
		btnRecuperaNumeri.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// Apre il socket
				try {
					Socket s = new Socket("localhost", 9999);
					PrintWriter out = new PrintWriter(s.getOutputStream(), true);
					out.println("CARTELLA");
					for (int i = 0; i < 15; i++) {
						
						System.out.print(s.getInputStream().read() + " ");
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				// Riceve i 15 numeri
				// Apre il thread di comunicazione che riceverà i comandi
				// successivi
			}
		});
		btnRecuperaNumeri.setText("Recupera Numeri");

	}
}
