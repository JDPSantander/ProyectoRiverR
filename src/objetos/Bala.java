
package objetos;

import Framework.GameObject;
import Framework.ObjectId;
import Framework.Texturas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;
import riverraid.Juego;
import riverraid.Manejador;

/**
 *
 * @author JesusDParraSantander
 */
public class Bala extends GameObject{
    
    Texturas tex = Juego.getInstancia();
    
    Manejador handler;

    public Bala(float x, float y, ObjectId id, int velocidadY, Manejador handler) {
        super(x, y, id);
        this.velocidadY = velocidadY;
        this.handler = handler;
    }

    @Override
    public void tick(LinkedList<GameObject> object) {
        y += velocidadY;
        colision(object);
    }
    
    /**
     * Recorre la lsita de juegos de objeto
     * @param object recibe como parametro los objetos de la lista LinkedList de GameObject (objetos de juego creados por nosotros)
     */
    private void colision(LinkedList <GameObject> object){
        
        for(int i=0; i< handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);  // Guarda en un objeto de juego temporal

            if(tempObject.getID() == ObjectId.Enemigos){
                if(getBounds().intersects(tempObject.getBounds())){
                    tempObject.setElimineado(0);
                    
                }
            
            }
        }
    }

    @Override
    /**
     * Metodo que dibuja formas o sprites que se pueden importar
     */
    public void render(Graphics g) {
        
        g.drawImage(tex.disparos, (int)x, (int)y, 16,41, null);
    }

    @Override
    public Rectangle getBounds() {
       return new Rectangle((int)x,(int)y,16,16);
    }
    
}
