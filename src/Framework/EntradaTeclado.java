
package Framework;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import riverraid.Manejador;

/**
 *
 * @author JesusDParraSantander
 */
public class EntradaTeclado extends KeyAdapter{
    
    Manejador handler;
    
    public EntradaTeclado(Manejador handler){
        this.handler = handler;
    }
    
    @Override
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();  // recibe y guarda el codigo de la tecla que el usuario presiona
        
        
        for(int i=0; i< handler.object.size(); i++){ // recorre la lista del manejador y los objetos del juego
            GameObject tempObject = handler.object.get(i); // recorre toda la lista de los enumeradores donde están enemigos, jugador, etc
            
            if(tempObject.getID()==ObjectId.Jugador){  // revisa si el objeto es un jugador 
                
                if(key == KeyEvent.VK_D){  // si se presiona la tecla D
                    tempObject.setVelocidadX(5);
                }
                if(key == KeyEvent.VK_A){  // si se presiona la tecla A
                    tempObject.setVelocidadX(-5);
                }
                /*if(key == KeyEvent.VK_SPACE && !tempObject.isSaltando()){ // si presiona espacio y no está saltando, salta (reduce posiciones en eje Y) 
                    
                    //tempObject.setSaltando(true);
                    tempObject.setVelocidadY(-10);
                }*/
                if(key == KeyEvent.VK_W){
                    int y=0;
                    tempObject.setVelocidadY(-3);
                }
               
            }
        }
        
        
        if(key == KeyEvent.VK_ESCAPE){  // Si el usuario presiona la tecla escape
            System.exit(1);  // sale de la aplicacion retornando 1
        }
    }
    @Override
    public void keyReleased(KeyEvent e){
        
        int key = e.getKeyCode();  // recibe y guarda el codigo de la tecla que el usuario presiona
        
        
        for(int i=0; i< handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i); // recorre toda la lista de los enumeradores donde están enemigos, jugador, etc
            
            if(tempObject.getID()==ObjectId.Jugador){  // revisa si el objeto es un jugador 
                
                if(key == KeyEvent.VK_D){  // si se presiona la tecla D
                    tempObject.setVelocidadX(0);
                }
                if(key == KeyEvent.VK_A){
                    tempObject.setVelocidadX(0);
                }
                if(key == KeyEvent.VK_W){
                    tempObject.setVelocidadY(0);
                }
            }
        }
        
    }
}
