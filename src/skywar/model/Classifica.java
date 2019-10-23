package skywar.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Classe che definisce la classifica
 * 
 * @author ErioPC
 *
 */
public class Classifica {

	private Giocatore giocatore;
	private ArrayList<Giocatore> lista;


	/**
	 * Costruttore, La classifica è costruita mediante un giocatore il quale verra'
	 * aggiunto ad una lista di giocatori
	 * 
	 * @param player il giocatore
	 */
	public Classifica(Giocatore player){

		this.giocatore  = player;
		this.lista = new ArrayList<Giocatore>();
	}

	/**
	 * Ritorna la lista dei giocatori
	 * 
	 * @return la lista dei giocatori
	 */
	public ArrayList<Giocatore> getLista(){
		return this.lista;
	}
	
	/**
	 * Imposta l'ora e data della partita per un determinato giocatore e 
	 * lo salva sul file di testo: "classifica.txt"
	 */
	public void salvaGiocatore(){

		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy ");
		Calendar cal = Calendar.getInstance();
		String dataString = dateFormat.format(cal.getTime());
		giocatore.setData(dataString);
                
		//scrivo in append
		String record = giocatore.getNickName() +";\t\t"+giocatore.getPunteggio()+ ";\t\t"+giocatore.getData();

		try {
			PrintWriter pw = new PrintWriter(new FileWriter("classifica.txt", true));
			pw.println(record);
			pw.close();
		} catch (IOException ioe) {
			System.out.println("! Errore IOException : "+ioe);
		}

		CaricaESalva();
	}

	/**
	 * Carica tutti i giocatori presenti nel file "classifica.txt" in un ArrayList e realizza
	 * l'ordinamento in forma decrescente utilizzando come primo parametro per l'ordinamento il
	 * punteggio conseguito, a parità di punteggio viene utilizzato un secondo parametro cioe'
	 * quello della durata della partita.
	 * Infine viene realizzato il salvataggio
	 */
	public void CaricaESalva(){

		try{
			Scanner sc = new Scanner(new File( "classifica.txt" ));

			while( sc.hasNextLine()){
				String linea = sc.nextLine(); 

				StringTokenizer tk = new StringTokenizer(linea, ";\t");

				while (tk.hasMoreTokens()) {
					String nickname = tk.nextToken();
					int punteggio = Integer.parseInt(tk.nextToken());
				
					String data = tk.nextToken();

					Giocatore g  = new Giocatore(nickname);
					g.setPunteggio(punteggio);
					g.setData(data);
					lista.add(g);
				}

			}

			sc.close();

		}catch(FileNotFoundException e){
			System.out.println("ERRORE: File Non Esiste");
		}

		Collections.sort(lista, new Comparator<Giocatore>(){

			@Override
			public int compare(Giocatore g1, Giocatore g2) {

				int c;
				c = g2.getPunteggio() - g1.getPunteggio();

				 

				return c;
			}

		});

		String buffer="";
		for(Giocatore g : lista){

			buffer = buffer+ g.getNickName() +";\t\t"+g.getPunteggio() +";\t\t"+g.getData()+"\n";
		}
		
		//scrivo in append

		try {
			PrintWriter pw = new PrintWriter(new FileWriter("classifica.txt"));

			pw.print(buffer);
			//chiudo file
			pw.close();
		} catch (IOException e) { //eccezione
		}

	}

}
