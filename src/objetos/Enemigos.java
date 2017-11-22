
package objetos;

import Framework.GameObject;
import Framework.ObjectId;
import Framework.Texturas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import riverraid.Animacion;
import riverraid.Juego;

/**
 *
 * @author JesusDParraSantander
 */
public class Enemigos extends GameObject{

    Texturas tex = Juego.getInstancia();
    private Animacion explosion;
    public static int eliminado2 =1;
    
    public Enemigos(float x, float y, ObjectId id) {
        super(x, y, id);
        velocidadX=5;
        //velocidadY=5;
        explosion = new Animacion(5,tex.explosiones[0],tex.explosiones[1],tex.explosiones[2],tex.explosiones[3],tex.explosiones[4],tex.explosiones[5],tex.explosiones[6],tex.explosiones[7],tex.explosiones[8],tex.explosiones[9],tex.explosiones[10],tex.explosiones[11],tex.explosiones[12],tex.explosiones[13],tex.explosiones[14],tex.explosiones[15]);
    }
    
    /** 
     * Una vez que alcanza el borde derecho de la pantalla o el borde izquierdo, se invierte su dirección en los ejes X
     * para moverse de lado a lado, manteniendo la misma velocidad que llevaba
     * @param object 
     */
    @Override
    public void tick(LinkedList<GameObject> object) {
        x+=velocidadX;
       // y+=velocidadY;
        
        if(x<=0 || x>= Juego.WIDTH -32){
            velocidadX *= -1;
        }
       
        
    }
    
   

    @Override
    public void render(Graphics g) {
        
        if(eliminado==0){
            explosion.correrAnimacion();
            explosion.dibujarAnimacion(g, (int)x, (int)y, 50, 50);
            velocidadX=0;
            //g.drawImage(tex.explosiones[9], (int)x, (int)y,50,50, null);
           
            
        }
        else{
            g.drawImage(tex.enemigo1, (int)x, (int)y, 30,30,null);
        }
        
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x,(int)y,30,30);
    }
    
  
    
}
