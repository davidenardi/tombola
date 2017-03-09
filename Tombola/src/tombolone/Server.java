package tombolone;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;

import it.fabiobiscaro.socket.tombola.buddy.Cartella;
import it.fabiobiscaro.socket.tombola.buddy.Tabellone;

public class Server {

	
	
	static ArrayList<PrintWriter> clientlist=new ArrayList<PrintWriter>();
	
	
	
	// 1) Alla connessione il server dà la lista dei numeri
	// 1bis) Il gioco comincia, il server avvisa tutti (GIOCO ON)
	// 2) Il server invia un un numero (NUMERO n)
	// 3) Il client avvisa quando ha vinto (VINCITA quale)
	// 4) Il server avvisa tutti quando ha vinto un client e aggiorna la vincita
	// (VINTO nomeClient)
	// 5) Il server dichiara la fine della partita (GIOCO OFF)

	public static void main(String[] args) {
		Tabellone t = new Tabellone();
		// Apertura server
		try {
			// Crei un server di connessione
			ServerSocket ss = new ServerSocket(9999);
			while (true) {
				// riceva una connessione
				Socket s = ss.accept();
				// riceva del testo
				InputStreamReader isr = new InputStreamReader(s.getInputStream());
				BufferedReader in = new BufferedReader(isr);
				String comando = in.readLine();
				if(comando.compareTo("CARTELLA") == 0){
					// Invio i numeri
					// TODO Auto-generated method stub
					Cartella c = new Cartella();
					// L'elenco dei numeri da dare al client
					int numeri[] = c.getNumeri();
					Arrays.sort(numeri);
					// Permuta per ottenere le righe finali (un elemento ogni tre nel vettore ordinato)
					int tmp = numeri[1];
					numeri[1] = numeri[3];
					numeri[3] = numeri[9];
					numeri[9] = numeri[13];
					numeri[13] = numeri[11];
					numeri[11] = numeri[5];
					numeri[5] = numeri[2];
					numeri[2] = numeri[6];
					numeri[6] = numeri[4];
					numeri[4] = numeri[12];
					numeri[12] = numeri[8];
					numeri[8] = numeri[10];
					numeri[10] = numeri[5];
					numeri[5] = tmp;
					for (int i : numeri) {
						//System.out.print(i + " ");
						s.getOutputStream().write(i);
					}
				}
				
				if(comando.compareTo("NUMERO") == 0){
					int numero = t.estraiNumero();
					for (PrintWriter printWriter : clientlist) {
						printWriter.println(Integer.toString(numero));
					}
				}
				
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		

	}

}
