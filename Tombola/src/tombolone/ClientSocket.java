package tombolone;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.TableView.TableRow;

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
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

public class ClientSocket {

	Socket s;

	protected Shell shell;
	private Text txtNumero;
	private Table table_1;
	private Table table;
	
	private TableColumn c0;
	private TableColumn c1;
	private TableColumn c2;
	private TableColumn c3;
	private TableColumn c4;
	private TableColumn c5;
	private TableColumn c6;
	private TableColumn c7;
	private TableColumn c8;

	private TableItem r1;
	private TableItem r2;
	private TableItem r3;
	
	private Display display;
	
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
		display = Display.getDefault();
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
		shell.setSize(878, 486);
		shell.setText("SWT Application");
		shell.setLayout(new org.eclipse.swt.layout.FormLayout());
		
		txtNumero = new Text(shell, SWT.BORDER);
		FormData fd_txtNumero = new FormData();
		txtNumero.setLayoutData(fd_txtNumero);
		ArrayList  Numeri = new ArrayList ();
		

		Button btnNumero = new Button(shell, SWT.NONE);
		fd_txtNumero.top = new FormAttachment(btnNumero, 2, SWT.TOP);
		fd_txtNumero.left = new FormAttachment(btnNumero, 20);
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
		fd_btnNumero.left = new FormAttachment(btnRecuperaNumeri, 35);
		FormData fd_btnRecuperaNumeri = new FormData();
		fd_btnRecuperaNumeri.top = new FormAttachment(0, 10);
		fd_btnRecuperaNumeri.left = new FormAttachment(0, 10);
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
						Numeri.add(s.getInputStream().read());
						System.out.print(s.getInputStream().read() + " ");
					}
				
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				// Riceve i 15 numeri
				// Apre il thread di comunicazione che riceverà i comandi
				// successivi
				
				for (int i = 0; i < 15; i++) {
					System.out.println(Numeri.get(i) + " ");
				}
			}
		});
		btnRecuperaNumeri.setText("Recupera Numeri");
		
		table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		FormData fd_table = new FormData();
		fd_table.right = new FormAttachment(txtNumero, 438, SWT.RIGHT);
		fd_table.top = new FormAttachment(txtNumero, 0, SWT.TOP);
		fd_table.left = new FormAttachment(txtNumero, 31);
		fd_table.bottom = new FormAttachment(0, 97);
		table.setLayoutData(fd_table);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		
		//aggiungo nove colonne  
		//da 1 a 10
		c0 = new TableColumn(table, SWT.NONE);
		c0.setWidth(44);
		c0.setText("1- 10");
		// da 11 a 20
		c1 = new TableColumn(table, SWT.NONE);
		c1.setWidth(44);
		c1.setText("11- 20");
		//da 21 a 30
		c2 = new TableColumn(table, SWT.NONE);
		c2 .setWidth(44);
		c2 .setText("21- 30");
		//da 31 a 40
		c3 = new TableColumn(table, SWT.NONE);
		c3.setWidth(44);
		c3.setText("31- 40");
		//da 41 a 50
		c4 = new TableColumn(table, SWT.NONE);
		c4.setWidth(44);
		c4.setText("41- 50");
		//da 51 a 60
		c5 = new TableColumn(table, SWT.NONE);
		c5.setWidth(44);
		c5.setText("51- 60");
		//da 61 a 70
		c6 = new TableColumn(table, SWT.NONE);
		c6.setWidth(44);
		c6.setText("61- 70");
		//da 71 a 80
		c7 = new TableColumn(table, SWT.NONE);
		c7.setWidth(44);
		c7.setText("71- 80");
		//da 81 a 90
		c8 = new TableColumn(table, SWT.NONE);
		c8.setWidth(44);
		c8.setText("81- 90");
		
		
		
		
		//aggiungo tre righe
		r1 = new TableItem(table, SWT.NONE);
		r2 = new TableItem(table, SWT.NONE);
		r3 = new TableItem(table, SWT.NONE);
				
	}
	
	public void InserisciDentroCartella(ArrayList Numeri){
		ArrayList a1 = new ArrayList();
		ArrayList a2 = new ArrayList();
		ArrayList a3 = new ArrayList();
		ArrayList a4 = new ArrayList();
		ArrayList a5 = new ArrayList();
		ArrayList a6 = new ArrayList();
		ArrayList a7 = new ArrayList();
		ArrayList a8 = new ArrayList();
		
		display.asyncExec(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				for(int i = 0; i< 15; i++){
					int numero = (int) Numeri.get(i);
					if(numero <= 10){
						a1.add(numero);
					}else{
						if(numero >11 && numero <=  20){
							a2.add(numero);
						}
						if(numero >20 && numero <=  30){
							a3.add(numero);
						}
						if(numero >30 && numero <=  50){
							a4.add(numero);
						}
						if(numero >50 && numero <=  60){
							a5.add(numero);
						}
						if(numero >60 && numero <=  70){
							a6.add(numero);
						}
						if(numero >70 && numero <=  80){
							a7.add(numero);
						}
						if(numero >80 && numero <=  90){
							a8.add(numero);
						}
						
					}
					
				}
				
				
			}
		});
	}
}
