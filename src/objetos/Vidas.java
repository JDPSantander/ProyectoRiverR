
package objetos;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author JesusDParraSantander
 */
public class Vidas {
    
    public static int VIDA = 100;
    
    public void tick(){
        //VIDA--; para disminuir la vida
    }
    
    public void render(Graphics g){
        
        g.setColor(Color.gray);
        // x, y, ancho, alto
        g.fillRect(15, 15, 200, 32);
        g.setColor(Color.green);
        g.fillRect(15, 15, VIDA*2, 32);
        g.setColor(Color.white);
        g.drawRect(15, 15, 200, 32);
    }
}
