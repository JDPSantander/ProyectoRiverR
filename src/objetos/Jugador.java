
package objetos;

import Framework.GameObject;
import Framework.ObjectId;
import Framework.Texturas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;
import riverraid.Animacion;
import riverraid.Camara;
import riverraid.Juego;
import riverraid.Manejador;

/**
 *
 * @author JesusDParraSantander
 */
public class Jugador extends GameObject {
    
    private float width = 48, height =96;
    private float gravedad = 0.5f;
    private final float MAXIMA_VELOCIDAD =10;
    
    private Manejador handler;
    private Camara cam;
    private Vidas vida;
    Texturas tex = Juego.getInstancia();
    
  

    public Jugador(float x, float y, Manejador handler, Camara cam, ObjectId id) {
        super(x, y, id);
        this.handler = handler;
        this.cam=cam;
        
    }
    
    /**
     * Mueve al avión en los ejes X o Y. 
     * @param object recibe el objeto que se moverá 
     */
    
    @Override
    public void tick(LinkedList<GameObject> object) {
        x += velocidadX;
        y--;    // Mueve el avion automaticamente hacia arriba 
        y += velocidadY;
        //vida = new Vidas(getX(),getY(),ObjectId.Vidas);
  
        //y += velocidadY;   // Para mover el avion manualmente hacia arriba
        
     
        colision(object);

       //avionIzquierda.correrAnimacion();
       
    }
    
    /**
     * Recorre la lsita de juegos de objeto
     * @param object recibe como parametro los objetos de la lista LinkedList de GameObject (objetos de juego creados por nosotros)
     */
    private void colision(LinkedList <GameObject> object){
        
        for(int i=0; i< handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);  // Guarda en un objeto de juego temporal
            
            if(tempObject.getID() == ObjectId.Bloque){ // Si el objeto es un bloque...
             
                if(getBoundsArriba().intersects(tempObject.getBounds())){  // si los bordes del jugador intersectan con los bordes superiores...
                    y=tempObject.getY() + 32;  // para que el jugador quede justo debajo del borde superior y choque
                    velocidadY=0;   //velocidad del jugador en el eje Y = 0
                   
                }
                
                if(getBounds().intersects(tempObject.getBounds())){  // si los bordes del jugador intersectan con los bordes del bloque inferior...
                    y=tempObject.getY() - height;  // para que el jugador quede justo encima del bloque y alinearlo con el piso
                    velocidadY=0;   //velocidad del jugador en el eje Y = 0
                    
                }

                if(getBoundsDerecha().intersects(tempObject.getBounds())){  // si los bordes del jugador intersectan con los bordes de la derecha...
                    x=tempObject.getX() - width;  // para que el jugador choque con el borde derecho
                }
                if(getBoundsIzquierda().intersects(tempObject.getBounds())){  // si los bordes del jugador intersectan con los bordes de la izquierda...
                    x=tempObject.getX() + 35;  // para que el jugador choque con el borde izquierdo 
                }
            } else if(tempObject.getID() == ObjectId.Puerta){  // si el objeto, con el que se encuentra el avion, es una puerta
                //CAMBIA DE NIVEL
                if(getBounds().intersects(tempObject.getBounds())){ // si el avion intersecta los bordes de la puerta...
                   handler.cambiarNivel();  
                   
                }
                
            } else if(tempObject.getID() == ObjectId.Enemigos){
                if(getBounds().intersects(tempObject.getBounds()) || getBoundsIzquierda().intersects(tempObject.getBounds()) || getBoundsDerecha().intersects(tempObject.getBounds())){
                    //y = tempObject.getY()+70;
                    Vidas.VIDA-=34;
                    //vida.setX(vida.getX());
                    //vida.setY(vida.getY());
                    if(Vidas.VIDA<=0){
                        Vidas.VIDA=0;
                        // OPCIONES DE PERDER
                    }
                }
            }
        }
    }

    @Override
    /**
     * Para dibujar el jugador. 
     */
    public void render(Graphics g) {

           g.drawImage(tex.jugador[13], (int)x, (int)y, 60,96, null);  // 60,96 es el tamaño que va a tener en el juego, lo adapta para que la imagen tome ese tamaño (agranda o decrece la imagen) 

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
