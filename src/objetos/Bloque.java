
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
public class Bloque extends GameObject{
    
    public Bloque(float x, float y, ObjectId id){
        super(x,y,id);
    }

    @Override
    public void tick(LinkedList<GameObject> object) {
        
    }

    @Override
    public void render(Graphics G) {
       G.setColor(Color.white);
       G.drawRect((int)x, (int)y, 32, 32);
       
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x,(int)y,32,32);
    }
}
