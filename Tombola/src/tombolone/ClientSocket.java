package tombolone;

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
import org.eclipse.swt.widgets.Table;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.wb.swt.SWTResourceManager;

public class ClientSocket {
	int n =0;
	protected Shell shlScheda;
	static Socket client;
	private Table scheda;

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
		shlScheda.open();
		shlScheda.layout();
		while (!shlScheda.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlScheda = new Shell();
		shlScheda.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		shlScheda.setSize(450, 276);
		shlScheda.setText("Scheda");
		
		
		Button btnConnessione = new Button(shlScheda, SWT.NONE);
		btnConnessione.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(n == 0){
				try {
					n = 1;
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
				
				
			}
		});
		btnConnessione.setBounds(162, 213, 105, 25);
		btnConnessione.setText("Connessione");
		
		TableViewer Scheda = new TableViewer(shlScheda, SWT.BORDER | SWT.FULL_SELECTION);
		scheda = Scheda.getTable();
		scheda.setBounds(0, 0, 434, 207);

	}

	public void addMessage(String messaggio) {
		// TODO Auto-generated method stub
		Display.getDefault().asyncExec(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				
			}
			
		});
		
	}
}

