package tombolone;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


public class Server {
	static ServerSocket ss;
	static Socket socketClient;
	
	static ArrayList<PrintWriter> clientlist=new ArrayList<PrintWriter>();
	
	private static class ServerThread extends Thread{
		
		private Socket client;
		
		public ServerThread(Socket socketClient) {
			// TODO Auto-generated constructor stub
			client = socketClient;
		}

		public void run(){
			super.run();
			/*
			 * resta in attesa dei messaggi client
			 * riceve il messaggio
			 * manda messaggio a tutti i client
			 */
			try {
				BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
				PrintWriter out = new PrintWriter(client.getOutputStream(),true);
				for(int i = 0; i< 5; i++){
					int nCasuale = (int) Math.round(Math.random() * 10) ;
					System.out.println("mandato: " + nCasuale);
					out.write("ciao");
				}
				
				while(true){
					String messaggio = in.readLine();
					//manda il messaggio a tutti
					for (PrintWriter printWriter : clientlist) {
						printWriter.println(messaggio);
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		/*
		 * server socket in ascolto
		 * Per ogni connessione crea un socket e thread
		 * 	aggiunge ad un vettore di client i client (Quindi in questo caso il clinet sara la scheda di gioco)
		 * ritorna in ascolto
		 * 
		 */
		ss=new ServerSocket(9999);
		while(true){
			socketClient = ss.accept();
			//aggiunge ad un vettore
			PrintWriter out = new PrintWriter(socketClient.getOutputStream(),true);
			clientlist.add(out);
			//crea socket e passa socket
			ServerThread st = new ServerThread(socketClient);
			st.start();
			//ritorna in ascolto
		}
		
		
	}

}


