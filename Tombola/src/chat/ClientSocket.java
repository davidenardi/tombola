package chat;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;


import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class ClientSocket {

	protected Shell shell;
	private Text text_chat;
	private Text text_invia;
	private Button btnInvia;
	static Socket client;

	/**
	 * Launch the application.
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
		
		text_chat = new Text(shell, SWT.BORDER | SWT.READ_ONLY | SWT.WRAP | SWT.V_SCROLL);
		
		text_chat.setBounds(10, 40, 414, 143);
		
		text_invia = new Text(shell, SWT.BORDER);
		text_invia.setBounds(10, 200, 204, 52);
		
		
		Button btnConnessione = new Button(shell, SWT.NONE);
		btnConnessione.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				try {
					client = new Socket("localhost",9999);
					ClientReceiver cs = new ClientReceiver(ClientSocket.this,client);
					cs.start();
				} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnConnessione.setBounds(319, 213, 105, 25);
		btnConnessione.setText("Connessione");
		
		
		btnInvia = new Button(shell, SWT.NONE);
		btnInvia.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					PrintWriter out = new PrintWriter(client.getOutputStream(),true);
					String messaggio = text_invia.getText();
					out.println(messaggio);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnInvia.setBounds(220, 213, 75, 25);
		btnInvia.setText("Invia");

	}

	public void addMessage(String messaggio) {
		// TODO Auto-generated method stub
		Display.getDefault().asyncExec(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				text_chat.append(messaggio+"\n");
			}
			
		});
		
	}
}
