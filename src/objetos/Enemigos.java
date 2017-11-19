
package objetos;

import Framework.GameObject;
import Framework.ObjectId;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;
import riverraid.Juego;

/**
 *
 * @author JesusDParraSantander
 */
public class Enemigos extends GameObject{

    public Enemigos(float x, float y, ObjectId id) {
        super(x, y, id);
        velocidadX=5;
        //velocidadY=5;
    }
    
    /** 
     * Una vez que alcanza el borde derecho de la pantalla o el borde izquierdo, se invierte su dirección en los ejes X
     * para moverse de lado a lado, manteniendo la misma velocidad que llevaba
     * @param object 
     */
    @Override
    public void tick(LinkedList<GameObject> object) {
        x+=velocidadX;
        y+=velocidadY;
        
        if(x<=0 || x>= Juego.WIDTH -32){
            velocidadX *= -1;
        }
       
        
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.ORANGE);
        g.fillRect((int)x, (int)y, 16, 16);
    }

    @Override
    public Rectangle getBounds() {
        return null;
    }
    
    
}
