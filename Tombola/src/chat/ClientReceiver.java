package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientReceiver extends Thread {
	
	private ClientSocket cs=new ClientSocket();
	private Socket s;
	
	public ClientReceiver(ClientSocket cs,Socket s) {
		// TODO Auto-generated constructor stub
		this.cs=cs;
		this.s=s;
	}
	@Override
	public void run() {
		/*
		 * all'infinito resta in ascolto
		 * riceve messaggio
		 * manda al client e modifica
		 * 
		 */
		BufferedReader in;
		try {
			in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			while(true){
				String messaggio=in.readLine();
				cs.addMessage(messaggio);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
