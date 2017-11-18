
package objetos;

import Framework.GameObject;
import Framework.ObjectId;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

/**
 *
 * @author JesusDParraSantander
 */
public class Bala extends GameObject{

    public Bala(float x, float y, ObjectId id, int velocidadY) {
        super(x, y, id);
        this.velocidadY = velocidadY;
    }

    @Override
    public void tick(LinkedList<GameObject> object) {
        y += velocidadY;
        
    }

    @Override
    /**
     * Metodo que dibuja formas o sprites que se pueden importar
     */
    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect((int)x, (int)y, 16, 16);
    }

    @Override
    public Rectangle getBounds() {
       return new Rectangle((int)x,(int)y,16,16);
    }
    
}
