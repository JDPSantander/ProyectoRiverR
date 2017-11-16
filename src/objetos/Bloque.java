
package objetos;

import Framework.GameObject;
import Framework.ObjectId;
import Framework.Texturas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;
import riverraid.Juego;

/**
 *
 * @author JesusDParraSantander
 */
public class Bloque extends GameObject{
    
    Texturas tex = Juego.getInstancia();
    private int type;
    
    public Bloque(float x, float y, int type, ObjectId id){
        super(x,y,id);
        this.type=type;
    }

    @Override
    public void tick(LinkedList<GameObject> object) {
        
    }

    @Override
    public void render(Graphics G) {
       /*G.setColor(Color.white);   Dibuja el bloque con rectangulos, forma clásica
       G.drawRect((int)x, (int)y, 32, 32);*/
       
       if(type==0){
           G.drawImage(tex.bloque[0], (int)x, (int)y,32,32, null);  // 32, 32 es el tamaño en pixeles del bloque, al añadir este tamaño, adapta la imagen al tamaño (la agranda o decrece)
       }
       if(type==1){
           G.drawImage(tex.bloque[1], (int)x, (int)y,32,32, null);
       }
       
       
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x,(int)y,32,32);
    }
}
