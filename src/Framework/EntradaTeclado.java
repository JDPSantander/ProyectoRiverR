
package Framework;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import objetos.Bala;
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
                if(key == KeyEvent.VK_SPACE){ 
                    //despues del getX() y getY() se pueden restar o sumar valores para ajustar la posicion inicial de donde saldrá la bala
                    //despues de getDireccion() se multiplica por el valor que queremos que sea nuestra velocidad de disparo (10, por ejemplo)
                    handler.addObject(new Bala(tempObject.getX()+16,tempObject.getY(),ObjectId.Bala,tempObject.getDireccion()*10));
                }
               
            }
            //Para mover las vidas al mismo tiempo que el avión
            if(tempObject.getID()==ObjectId.Vidas){  // revisa si el objeto es un jugador 
                
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
                /* Para hacer que la barra de vida también dispare 
                if(key == KeyEvent.VK_SPACE){ 
                    //despues del getX() y getY() se pueden restar o sumar valores para ajustar la posicion inicial de donde saldrá la bala
                    //despues de getDireccion() se multiplica por el valor que queremos que sea nuestra velocidad de disparo (10, por ejemplo)
                    handler.addObject(new Bala(tempObject.getX()+16,tempObject.getY(),ObjectId.Bala,tempObject.getDireccion()*10));
                }*/
               
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
            //Para detener la barra de vida al mismo tiempo que se detiene al jugador
             if(tempObject.getID()==ObjectId.Vidas){  // revisa si el objeto es un jugador 
                
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
