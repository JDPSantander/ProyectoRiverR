
package objetos;

import Framework.GameObject;
import Framework.ObjectId;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;

/**
 *
 * @author JesusDParraSantander
 */
public class Jugador extends GameObject {
    
    private float width = 48, height =96;
    private float gravedad = 0.5f;
    private final float MAXIMA_VELOCIDAD =10;

    public Jugador(float x, float y, ObjectId id) {
        super(x, y, id);
    }
    
    /**
     * Mueve la caja en los ejes X o Y. 2) verifica si el jugador está cayendo o saltando para crear el efecto de gravedad
     * @param object recibe el objeto que se moverá 
     */
    
    @Override
    public void tick(LinkedList<GameObject> object) {
        x += velocidadX;
        //y += velocidadY;
        
        if( cayendo || saltando){
            velocidadY += gravedad;
            if(velocidadY>MAXIMA_VELOCIDAD){
                velocidadY=MAXIMA_VELOCIDAD;
            }
        }
    }

    @Override
    /**
     * Para dibujar el jugador. 2) Convierte la variable de la gráfica en una variable de gráfica 2D para crear las cajas 
     */
    public void render(Graphics g) {
        
       g.setColor(Color.blue);
       g.fillRect((int)x,(int)y,(int)width,(int)height);
       
       Graphics2D g2d = (Graphics2D) g;
       
       g2d.setColor(Color.red);
       g2d.draw(getBounds());
       g2d.draw(getBoundsDerecha());
       g2d.draw(getBoundsIzquierda());
       g2d.draw(getBoundsArriba());
    }

    @Override
    /**
     * Devuelve un rectangulo que rodeará al jugador para usar estas coordenadas en la detección de colisiones
     */
    public Rectangle getBounds() {
       return new Rectangle((int) ((int)x+(width/2))-((int)(width/2)/2),(int) ((int)y+(height/2)),(int)width/2,(int)height/2) ;
    }
    
    /**
     * Borde superior del mapa para detectar colisión
     * @return la posicion en x, y, el ancho y el alto
     */
    public Rectangle getBoundsArriba() {
       return new Rectangle((int) ((int)x+(width/2))-((int)(width/2)/2),(int)y,(int)width/2,(int)height/2) ;
    }
    
    /**
     * Borde derecho del mapa para detectar colisión
     * @return la posicion en x, y, el ancho y el alto
     */
    public Rectangle getBoundsDerecha() {
       return new Rectangle((int) ((int)x+width-5),(int)y+5,(int)5,(int)height-10) ;
    }
    
    /**
     * Borde izquierdo del mapa para detectar colisión
     * @return la posicion en x, y, el ancho y el alto
     */
    public Rectangle getBoundsIzquierda() {
       return new Rectangle((int)x,(int)y+5,(int)5,(int)height-10) ;
    }
    
    
    
}
