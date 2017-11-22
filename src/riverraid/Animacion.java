
package riverraid;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author JesusDParraSantander
 */
public class Animacion {
    
    private int velocidad; // velocidad de la animación
    private int frames; // numero de frames que se recorrerán por cada animación (si son 7 imagenes, serán 7 frames)
    
    private int index =0; // indice de dónde estamos en ese determinado momento
    private int count =0;
    
    private BufferedImage[] imagenes; // el número de imagenes que tendremos
    private BufferedImage imagenActual; // la imagen actual que mostraremos
    
    /**
     * Constructor que inicializa los atributos de la clase Animacion
     * @param speed recibe la velocidad con que se repetirá el ciclo de imagenes para recrear la animación
     * @param args recibe el número de imagenes (infinitas) que puede recibir, que serán los estados que irán cambiando 
     * de la animación/movimiento del jugador, enemigo,etc.
     */
    public Animacion(int speed, BufferedImage... args){
        velocidad=speed;
        imagenes = new BufferedImage[args.length];
        
        for(int i=0; i< args.length; i++){
            imagenes[i] = args[i];
        }
        frames = args.length;
        
    }
    /**
     * Metodo para correr imagen por imagen para recrear la animación. Inicia la animación
     */
    public void correrAnimacion(){
        
        index++;
        if(index>velocidad){
            index=0;
            siguienteFrame();
        }
    }
    
    /**
     * Metodo que crea el efecto de animación, recorriendo el vector de imagenes e iniciandolas
     */
    private void siguienteFrame(){
        
        for(int i=0; i<frames; i++){
            if(count ==i){ // busca en qué frame nos encontramos actualmente 
                imagenActual = imagenes[i]; // convierte la imagen/frame en 0 o en el indice en el que estemos
            }
        }
        
        count ++;
        if(count>frames){
            count=0;
        }
    }
    /* OTRA FORMA DE HACER SIGUIENTEFRAME(){      PROBAR PARA VER SI FUNCIONA
    private void nextFrame(){
     currentImg = images[count%frames];
     count++;
    }
    */
    
    /**
     * Dibuja la animación luego de llamar al metodo correr animación
     * @param g recibe el objeto grafico
     * @param x la coordenada en X donde será dibujada la animación
     * @param y la coordenada en Y donde será dibujada la animación
     */
    public void dibujarAnimacion(Graphics g, int x, int y){
        
        g.drawImage(imagenActual, x, y, null);
        
    }
    /**
     * Dibuja la animación luego de llamar al metodo correr animación. Hace que las imagenes de la animación
     * se adapten a la imagen original o a la escala que le pasemos por parametro
     * @param g recibe el objeto grafico 
     * @param x recibe la coordenada en X donde será dibujada la animación
     * @param y recibe la coordenada en Y donde será dibujada la animación
     * @param scaleX recibe la escala/tamaño (ancho) al cual serán adaptadas las imagenes de la animación
     * @param scaleY recibe la escala/tamaño (altura) al cual serán adaptadas las imagenes de la animación
     */
    public void dibujarAnimacion(Graphics g, int x, int y, int scaleX, int scaleY){
        
        g.drawImage(imagenActual, x, y, scaleX, scaleY, null);
        
    }
    
    
    /**
     * Fija la velocidad de la animación
     * @param speed 
     */
    public void setVelocidadAnimacion(int speed){
        velocidad=speed;
    }
    
    //SE PUEDEN CREAR GETTERS Y SETTERS DE ACUERDO A CÓMO DESEAMOS INTERACTUAR CON LAS ANIMACIONES, PARA REINICIAR EL CONTADOR
    // CADA VEZ QUE ENTRA A UNA ANIMACION, O REINICIAR LA VELOCIDAD, ETC.
    
    
}
