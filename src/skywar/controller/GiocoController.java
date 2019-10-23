
package skywar.controller;

import java.awt.Frame;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.geometry.Point3D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.ClosePath;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.PathElement;
import javafx.stage.Stage;
import javafx.util.Duration;
import static skywar.controller.ImpostazioniController.bullets;
import static skywar.controller.ImpostazioniController.scelta;  
import skywar.model.Classifica;
import skywar.model.Giocatore;
import skywar.model.Utility;
/**
 * Gioco.fxml Controller Class
 */
public  class GiocoController extends Frame implements   ActionListener  {
            
            @FXML
            private AnchorPane AP;
            @FXML
            private ImageView Cannone ;
            @FXML
            private Label Proiettili;
            @FXML
            private Label Colpiti;
            @FXML
            private   ImageView Aereo1 ;
            @FXML
            private  ImageView Aereo2;
            @FXML
            private  ImageView Aereo3 ; 
            @FXML
            private Circle Proiettile;
            @FXML
            private  Button Up;
            @FXML
            private Button Down;
            @FXML
            private Button Shoot;
            @FXML
            private ImageView fire ;
            @FXML
            private Button exit;

            /**
             * numero tatale di proiettile
             */
            public  static   int shots = 10; 

            /**
             * Velocita iniziale di proiettile
             */
            public  static double Speed = 110 ;
            
            private Stage stage = this.stage;

            private String nickname ;
         
            private  Scene scene=this.scene ;
            private static final Logger logger = Logger.getLogger(GiocoController.class.getName());
            private static int A = 0; //aeri colpiti
            private static boolean paw=false;

            /**
             * ArrayList dei giocatori registrati
             */
            public static ArrayList<Giocatore> listaGiocatori = null;

            /**
             * Ogetto static   giocatore 
             */
            public static Giocatore giocatore ;

            /**
             * Ogetto static classifica
             */
            public static Classifica classifica = null;
            private final Random random = new Random();
            private static final double MAX_RATE = 3;
            private static final double MIN_ABSOLUTE_RATE = 1;
            private static final double MIN_Y=25;
            private static final double MAX_Y=200;


            
            
            /**
             * Metodo per inizializzare il controller .
             * Inizializza il nome del goiocatore.
             * Controlla i buttoni per la rotazione dell cannone .
             * Crea il giocatore .
             * Carica l aereo , la proiettile e mette on action il path del aereo 
             * inizializza il metodo per il label Colppiti
             * SetShots 10 quando il giocatore vuole rigiocare.
            * @param name per il nickname 
             */
           public void iniz(String name)
            {
                
                setShots(10);
                SetA();
             
                
                System.out.println("Velocita iniziale della proiettile:" + Speed);
             
              
               this.nickname=name;
            
               this.Shoot.setStyle(Utility.buttonStyle);
            
            
           
            
            
            
               
         
           Cannone.setRotate(-10);
           
           Up.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Integer k = -70;
                        Integer k2 = 0;
                         logger.warning("Il valore del'angolo è:" +Cannone.getRotate());
                        if(Cannone.getRotate()> k && Cannone.getRotate()<=k2){
                            Cannone.setRotate( Cannone.getRotate() - 6);
                        }
                    }
           
           });
           
           Down.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Integer k = -80;
                        Integer k2 = -6;
                        logger.warning("Il valore del angolo è" +Cannone.getRotate());
                        if(Cannone.getRotate() > k && Cannone.getRotate()<=k2){
                            Cannone.setRotate(Cannone.getRotate() + 6);
                        }
                       
                         
                    }
           
           });
           
                this.Proiettili.setText(String.valueOf(10));
                setAereo();
                moveAereo();
                caricaAereo();
                caricaProiettile();  
              
                Colpiti(); //metodo che controlla label Colpiti
           
            }
           
           /**
            * Metodo che controlla A per i aerei colpiti
            */
           public void SetA()
           {
               this.A=0;
           }
           
           /**
            * Metodo che set shots 
            * @param sh numero di spari
            */
           public void setShots(int sh)
           {
               this.shots=sh;
           }
           
           
           /**
            * Metodo che ritorna il proettile scelto
            * @return Proiettile
            */
           public Circle getProiettile()
           {
            
                return this.Proiettile;
                   
           }
          
            
           /**
            * Metodo che crea il path del aereo 
            */
           public void moveAereo()
           {    
                //ImageView transition
		PathTransition aereo = new PathTransition();
		PathElement[] path = new PathElement [3];
		path[0]=new MoveTo(-60 ,30);
		path[1]=new LineTo(900,50);
               // path[2]= new LineTo(700,50);    
               // path[3]= new LineTo(750,30);
		path[2]=new ClosePath();

		Path pista = new Path();
		pista.getElements().addAll(path);
                setAereo();
		aereo.setNode(getAereo());
		aereo.setPath(pista);
                aereo.setAutoReverse(true);
		aereo.setInterpolator(Interpolator.LINEAR);
		aereo.setDuration(new Duration(5000));
		//ciclo infinito
		aereo.setCycleCount(Animation.INDEFINITE);
                
                  double newRate = getRandomRate();

                aereo.setRate(newRate);
            
		aereo.play();
                
                
               
           }
           
           /**
            * Metodo che ritorna un Random Rate per il rate del animazione 
            */
            private double getRandomRate() 
            {
                double magnitude = (MAX_RATE - MIN_ABSOLUTE_RATE) * random.nextDouble() + MIN_ABSOLUTE_RATE;
                return random.nextBoolean() ? magnitude : -magnitude;
            }
            
            
            /**
             * Metodo che ritorna un Random Y per l iamgine del aereo 
             * @return  Result Y
             */
            public double getRandomY()
            {
               Random r = new Random();
               
                double Result =  (MAX_Y-MIN_Y) * r.nextDouble() + MIN_Y;
                return Result ;
            }
            
            /**
             * Mette l imaggine dell aereo nelle coordinate random
             */
            public void setAereo()
            {
                getAereo().setLayoutY(getRandomY());
            }
           
           
           /**
            * Metodo che ritorna l'aereo
            * @return imagine dell aereo
            */
           public ImageView getAereo()
           {
               
               
               if(scelta.equalsIgnoreCase("Symbolic"))
                   
                  return this.Aereo1;
               if(scelta.equalsIgnoreCase("Static"))
                   return this.Aereo2;
               else 
                   return this.Aereo3;
               
           }
           
           
           
           
           
           
           /**
            * Metodo per determinare il collisione
            */
           public void Collisione()
           {
               
        getProiettile().boundsInParentProperty().
               addListener((ObservableValue<? extends Bounds> arg0, Bounds oldValue1, Bounds newValue2) -> {
            if (getProiettile().getBoundsInParent().intersects(getAereo().getBoundsInParent())) {
                  
                paw=true;
                 setA();
                
                getAereo().setVisible(false);
                getProiettile().setVisible(false);
                 this.fire.setVisible(true);
                 this.fire.setLayoutX(getProiettile().getLayoutX());
                 this.fire.setLayoutY(getProiettile().getLayoutY());
                 
                try 
                {
                    TimeUnit.MILLISECONDS.sleep(2000);
                } catch (InterruptedException ex) {}
                     this.fire.setVisible(false);
                    
             
                
                System.out.println(getProiettile().getBoundsInParent() + "Proiettile");
                    System.out.println(getAereo().getBoundsInParent()+ "Aereo");
                    System.out.println("SHOOTED!!!!");
                    
              
                 try {
                    TimeUnit.MILLISECONDS.sleep(1400);
                } catch (InterruptedException ex){}
                 getProiettile().setVisible(true);  
                 
                /*  try {
                    TimeUnit.MILLISECONDS.sleep(600);
                } catch (InterruptedException ex){}*/
                   
                 getAereo().setVisible(true);
                 
                 moveAereo();
                    
                           
                       
                
            } 
            else{paw=false;}
        });
          
    }
          /**
           * Metodo che cambia variabile A se aereo sia colpito
           */
         public void setA()
           {
               if(paw==true)
               {
                   A++;
               }
           }
          /**
           * Metodo che ritorna variabile A
           * @return int A 
           */
           public int getA()
           {
               return this.A;
           }
           
           /**
            * Metodo che controlla il label Colpito
            */
           public void Colpiti()
           {   
                this.Colpiti.setText(String.valueOf( getA() ));
           }
             
          
    
         
           
           /**
            * Metodo che controlla la rotazione del cnnone con buttoni di tastiera.
            */
        /* public void moveeCannone(){  
             //try
                this.Cannone.setOnKeyPressed((EventHandler<? super javafx.scene.input.KeyEvent>) e);
                
                if(e.getKeyCode()==KeyEvent.VK_RIGHT)
               {
                   
                Cannone.setRotate(6)  ;}
               if(e.getKeyCode()== KeyEvent.VK_LEFT)
               {Cannone.setRotate( -8);}
               
               e.consume();}
             // }catch(NullPointerException ex){}}
            /*    @Override 
                    public void handle(KeyEvent e){
                    switch(e.getKeyCode())
                      {
                          case KeyEvent.VK_RIGHT:
                        Cannone.setRotate(6);
                                break;
                           case KeyEvent.VK_LEFT:
                           Cannone.setRotate( 8);
                           break;
                      }
                      //e.consume();  
               
               }

            });*/
              
          
            
         
            /**
             * Metodo che carica l'aereo scelto dagli impostazioni
             */
            public void caricaAereo()
            {  
                try
                {
              
                
                if(scelta.equalsIgnoreCase("Symbolic"))
                {
                   Aereo1.setImage(new Image("/risorse/airport-145682_960_720.png"));
                   Aereo1.setRotationAxis(Point3D.ZERO);
                }    
                if(scelta.equalsIgnoreCase("Static"))
                {
                   Aereo2.setImage(new Image("/risorse/fighter-jet-plane-clip-art-at-clker-com-vector-clip-art-online-8sutr4-clipart.png"));
                   
                }
                if(scelta.equalsIgnoreCase("Animated"))
                {
                   Aereo3.setImage(new Image("/risorse/ufo-flying-saucer-animated-gif-17.gif"));
                }
              
                }catch(NullPointerException ne){}                          
            }
            
            
            
            
            /**
             * Metodo che carica il numero delle proiettile 
             */
            public void caricaProiettile()
            {
                try{
                    
                        this.Proiettile.setVisible(true);
                }catch(NullPointerException en){}
            }
            
            
          
            /**
             * Metodo che decide la velocita iniziale della poriettile .
             * @param sp velocita 
             */
           public  static  void setSpeed(double sp)
           {
                Speed=sp;
           }
           
           /**
            * Metodo che ritorna la velocita iniziale
             * @return double Speed ;
            */
           public double getSpeed()
           {
               return this.Speed;
           }
           
           
     
           
           /**
            * Metodo che aggiorna la proiettile.
            * @throws java.lang.InterruptedException exception
            */
          public void Aggiorna() throws InterruptedException
            {
                
               
                double angolo = Math.abs(Cannone.getRotate()) + 15;
                System.out.println("L angolo iniziale è " + angolo );
                angolo = Math.toRadians(angolo);
                //double angolo = 45;
                boolean dead =false;
                double Gravity = 10 ;
                while (dead==false)
                {
                   double X= getProiettile().getLayoutX();
                   double Y=getProiettile().getLayoutY();
                
                   if(X > 1 && Y > 1 && X < AP.getWidth() && Y < AP.getHeight())
                   {
                       System.out.println("x: " + X + " y: " + Y + " maxX: " + AP.getWidth() + " maxY: " + AP.getHeight());
                        //angolo = Math.abs(angolo);
                        double x = getProiettile().getLayoutX();
                        double y = getProiettile().getLayoutY();
                     
                       // System.out.println("Coseno è : " + Math.cos(angolo) );
                       // System.out.println("Seno è : " + Math.sin(angolo) );
                      
                       
                      //Senza Gravita 
                      // getProiettile().setLayoutX(x += Speed * Math.cos(angolo));
                      //  getProiettile().setLayoutY(y -= Speed * Math.sin(angolo));
                      
                      //Con Gravita
                       getProiettile().setLayoutX(x += getSpeed() * Math.cos(angolo));
                      
                      
                        
                        double firstP = Math.tan(angolo) * x;
                        double secNu = Gravity * x * x;
                        double secEm = 2 * getSpeed() * getSpeed() * Math.cos(angolo) * Math.cos(angolo);
                        double secP =secNu/secEm;
                        double newY = firstP - secP;
                       
                        
                        getProiettile().setLayoutY(615- newY);
                      
                         Collisione();
                         
                        System.out.println("ALIVE");
                        TimeUnit.MILLISECONDS.sleep(75);
                   }
                   else
                   {    
                      System.out.println("DEAD");
                        
                        getProiettile().setLayoutX(166);
                        getProiettile().setLayoutY(615);
                        dead = true;
                   }
                }
                  
            }
            
            
            
           
            /**
             * Metodo che controlla la ripetizione of the shoot 
             */
            public void reDo()
                   {
                       int num = Integer.parseInt(bullets);
                       Task task = new Task<Void>() 
                       {
                        @Override
                        public Void call() throws InterruptedException {

                            for (int i = 1; i <= num; i++) {
                                if (isCancelled()) {
                                    break;
                                }
                                Aggiorna();
                            }
                            return null;
                        }
                         };
                        new Thread(task).start();
                   }
           
            
           
           /**
            *Metodo fxml che fa sparare la proiettile con buttone Fire
            * @param ae ActionEvent
            * @throws java.lang.InterruptedException  exception
            */
           @FXML
           public void Spara(ActionEvent ae) throws InterruptedException
           {   
            
               
                Colpiti();
                 int num = Integer.parseInt(bullets);
                 if (num <= shots)
                 {
                    this.shots -= num;
                       this.Proiettili.setText(String.valueOf(shots));
                    reDo();
                 }
                 else{
                    this.shots = 0;
                 }

                if (shots == 0)
                {

                                new java.util.Timer().schedule( 
                       new java.util.TimerTask() {
                           @Override
                           public void run() {
                               Risultati();
                           }
                       }, 
                       3000 
                        );
                }
           
              
           }
           
           
           /**
            * Metoodo che controlla il buttone exit 
            */
           @FXML
           public  void exit() 
           {
               Colpiti();
                    int colp =Integer.parseInt(this.Colpiti.getText());
                    int lanc =Integer.parseInt(this.Proiettili.getText());
                    
                    giocatore = new Giocatore(nickname);
                    giocatore.calcolaPunteggio(colp,10-lanc);
                   
                    
                    classifica = new Classifica(giocatore);
						classifica.salvaGiocatore();

						listaGiocatori = classifica.getLista(); 
                    
                   Risultati();
                   
           }
           
            /**
             *
             * @param ae ActionEvent
             */
            @Override
            public void actionPerformed(java.awt.event.ActionEvent ae) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

  
    
 
        /**
         * Crea il giocatore e l ogetto classfica con parametro giocatore dandogli 
         * la possibilta di calcolare il punteggio.
         * chiama il metodo iniziale di ClassificaController con un parametro ListaGiocatori pressa da Classifica.
	 * Nasconde la finestra corrente e carica la finestra degli risultati
	 * dopo che sono finite le proiettile .
	 */
	
	public void  Risultati()
        {
            Platform.runLater(new Runnable() {
			@Override
			public void run() {
                            
                    System.out.println("Colpiti" + Integer.parseInt(Colpiti.getText()));
                    System.out.println("Proiettili" + Integer.parseInt(Proiettili.getText()));
                            Colpiti();
                    int colp =Integer.parseInt(Colpiti.getText());
                    int lanc =Integer.parseInt(Proiettili.getText());
                    
                    giocatore = new Giocatore(nickname);
                    giocatore.calcolaPunteggio(colp,10);
                   
                    
                    classifica = new Classifica(giocatore);
						classifica.salvaGiocatore();

						listaGiocatori = classifica.getLista();     
                            
                            
                            


		try{
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/skywar/view/Classifica.fxml"));
      			Parent root3 = (Parent) fxmlLoader.load();
			//prendo il GicoController:
                        ClassificaController controller = (ClassificaController)fxmlLoader.getController();
			Stage stage = new Stage();
			stage.setResizable(false);
			stage.setScene(new Scene(root3));
                        stage.show();
			controller.ini(listaGiocatori);

		

		}catch(IllegalStateException ie) {
			System.out.println("! Errore: GiocoController.fxml non trovato!"+ie);
			ie.printStackTrace();
		}catch(IOException ioe){
			System.out.println("! Errore: fxmlLoader.load()"+ioe);
			ioe.printStackTrace();
		}catch(NullPointerException ne){
			System.out.println("! Errore: Parent root1 e' null! "+ne);
			ne.printStackTrace();
		}
                
                //nascondo la finestra corrente
			(Colpiti.getScene().getWindow()).hide();

	}
    
    
            });
                    }
    

}