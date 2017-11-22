
package objetos;

import Framework.GameObject;
import Framework.ObjectId;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;
import riverraid.Camara;
import riverraid.Manejador;

/**
 *
 * @author JesusDParraSantander
 */
public class Vidas extends GameObject{
    
    public static int VIDA = 100;
    
    Manejador handler;
    Camara cam;
    
   
    public Vidas(float x, float y,Manejador handler, Camara cam, ObjectId id) {
        super(x, y, id);
        this.handler = handler;
        this.cam=cam;
    }
    public Vidas(float x, float y, ObjectId id) {
        super(x, y, id);
        
    }

    
    
    //public void tick(){
        //VIDA--; para disminuir la vida
    //}
    
    public void render(Graphics g){
        
        g.setColor(Color.gray);
        // x, y, ancho, alto
        g.fillRect((int)x, (int)y, 200, 32);
        g.setColor(Color.green);
        g.fillRect((int)x, (int)y, VIDA*2, 32);
        g.setColor(Color.white);
        g.drawRect((int)x, (int)y, 200, 32);
    }

    @Override
    public void tick(LinkedList<GameObject> object) {
        x += velocidadX;
        y--;    // Mueve la barra de vida hacia arriba
        y += velocidadY;
    }

    @Override
    public Rectangle getBounds() {
        return null;
    }
}
