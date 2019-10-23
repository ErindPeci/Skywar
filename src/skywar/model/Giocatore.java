
package skywar.model;

import java.io.Serializable;

/**
 * Classe che definisce un giocatore
 * Un giocatore ha un nickname, un punttegio
 * la data della partita   e la durata della partita
 * 
 * @author ErioPc
 */


public class Giocatore  implements Serializable{
    
        
    
        private static final long serialVersionUID=1L;
        
        private String nickName;
        private   int punteggio;
        private String data;
        
        
        /**
	 * Costruttore, un giocatore è costituito mediante un nickname(String)
	 * 
	 * @param nick il nickname del giocatore
	 */
	public Giocatore(String nick)
        {
		this.nickName = nick;
		this.data = "Errore: data non impostata";
		this.punteggio = 0;
	}
        
        
        /**
	 * Calcola il punttegio presso dall giocatore utilizzando  la formula
         * P = N_a x 1000/N_b 
         * (P = punteggio conseguito, N_a = numero aerei colpiti, N_b = numero proiettili lanciate). 
	 * @param aeriColpiti il numero di aeri colpiti
         * @param proietLanciate numero di proiettile lanciate fino a quel momento di gioco
	 */
	public  void calcolaPunteggio(int aeriColpiti,int proietLanciate)
        {
               
              this.punteggio = aeriColpiti*1000/proietLanciate;
             
	}
        
        
        /**
	 * Imposta il punteggio presso nella partita
	 * 
	 * @param punti il punteggio conseguito
	 */
	public void setPunteggio(int punti)
        {
		this.punteggio = punti;
	}
        
        
        
        /**
	 * Ritorna il punteggio presso dal giocatore
         * 
	 * @return il punteggio
	 */
	public int getPunteggio()
        {
		return this.punteggio;
	}

        
        
        /**
	 * Imposta la data in quale è stata svolta la partita
	 * 
	 * @param dat la data 
	 */
	public void setData(String dat)
        {
		this.data = dat;
	}
        
        
        /**
	 * Ritorna la data in quale è stata giocata la partita
	 * 
	 * @return la data della partita
	 */
	public String getData()
        {
		return this.data;
	}
        
        
        /**
	 * Ritorna il nickname del giocatore
	 * 
	 * @return il nickname
	 */
	public String getNickName()
        {
		return this.nickName;
	}
        
    /**
     *
     * @return Giocatore 
     */
    @Override
	public String toString() 
        {
		return "Giocatore [nickName=" + nickName + ", punteggio=" + punteggio + ", data=" + data +  
				  "]";
	}

        
        
        
        
        
        
    
}
