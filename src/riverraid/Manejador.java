
package riverraid;

import Framework.GameObject;
import Framework.ObjectId;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.Random;
import objetos.Bloque;
import objetos.Enemigos;
import objetos.Jugador;
import objetos.Puerta;
import objetos.Vidas;

/**
 *
 * @author JesusDParraSantander
 */
public class Manejador {
    
    /**
     * lista de objetos que hay en el juego, se pueden añadir o quitar más objetos a esta lista. La lista será renderizada 
     * y actualizada
     */ 
    public LinkedList<GameObject> object = new LinkedList <GameObject>();
    
    private GameObject tempObject;
    private Camara cam;
    private BufferedImage level2 =null;
    //private Random random,random2;
    //private Vidas vida;
    
    
    public Manejador(Camara cam){
        this.cam=cam;
        
        BufferedImageLoader loader = new BufferedImageLoader();
        level2 = loader.loadImage("/nivel2.png");  // cargando el nivel
        
        //vida = new Vidas();
    }
    
    /**
     * Instancia el objeto de tipo GameObject que se ha creado de manera auxiliar recorriendo los objetos de juego 
     * que hay en la lista
     */
    public void tick(){
        
        for(int i=0; i < object.size() ; i++){
            tempObject = object.get(i);
            
            tempObject.tick(object);    
        }   
    }
    
    /**
     * Dibuja todos los objetos de tipo GameObject que se han creado en la lista
     * @param g 
     */
    public void render(Graphics g){
        for(int i =0; i< object.size(); i++){
            tempObject = object.get(i);
            
            tempObject.render(g);   
        }
    }
    
     
    /**
     * Carga la imagen del mapa de acuerdo a la estructura(imagen) hecha en paint con colores e identifica en el codigo los diferentes colores de cada pixel 
     * de la imagen para colocar en esa ubicación (color) la imagen deseada. Esta imagen hecha en paint debe tener un tamaño
     * de potencias de 2, y debe ser cuadrada (512 x 512, por ejemplo) para funcionar.
     * @param image Recibe la imagen del nivel hecha en paint
     */
    public void cargarImagenNivel(BufferedImage image){
        
        int w= image.getWidth();
        int h= image.getHeight();
        
        System.out.println("Anchura, Altura: " + w +" " +h);
        
        for(int xx= 0; xx<h; xx++){   // recorre cada uno de los pixeles de la imagen con sus dimensiones 
            for(int yy=0; yy<w; yy++){
                
                int pixel = image.getRGB(xx,yy);  // retorna un entero de pixel en el modelo por defecto de color RGB
                int rojo = (pixel >> 16) &0xff; 
                int verde = (pixel >>8) &0xff;
                int azul = (pixel) &0xff;
                
                //Para verificar si el pixel en el que nos encontramos es de color blanco (lo que supone son nuestros bloques)
                if (rojo == 255 && verde == 255 && azul ==255){ //255 es el maximo valor en el espectro de colores
                 
                    addObject(new Bloque(xx*32, yy*32,1, ObjectId.Bloque)); // Agrega un bloque en cada pixel blanco
                }    
                //Para verificar si el pixel en el que nos encontramos es de color azul (lo que supone es nuestro jugador)
                if (rojo == 0 && verde == 0 && azul ==255){ //esta combinación de colores es igual a azul puro
                 
                    addObject(new Jugador(xx*32, yy*32,this,cam, ObjectId.Jugador)); // Agrega nuestro jugador (bloque azul) en el pixel azul
                }
                if (rojo == 255 && verde == 242 && azul ==0){ //punto amarillo de la puerta para pasar de nivel
                 
                    addObject(new Puerta(xx*32, yy*32, ObjectId.Puerta)); // Agrega nuestro jugador (bloque azul) en el pixel azul
                }
                if (rojo == 255 && verde == 128 && azul ==0){
                    //random = new Random();
                    
                    //for(int i=0; i<20; i++){
                        addObject(new Enemigos(xx*32, yy*32, ObjectId.Enemigos));
                    //}
                    
                }
                if( rojo == 34 && verde == 177 && azul == 76){
                    addObject(new Vidas(xx*32,yy*32,this,cam,ObjectId.Vidas));
                }
                
                /*if (rojo == 34 && verde == 177 && azul ==76){ //esta combinación de colores es igual a azul puro
                 
                    manejador.addObject(new Bloque(xx*32, yy*32,1, ObjectId.Bloque)); // Agrega nuestro jugador (bloque azul) en el pixel azul
                } */
            }
        }
    }
    
    /**
     * Método para añadir objetos a nuestra lista de objetos de tipo GameObject
     * @param object recibe el objeto a añadir como parametro
     */
    public void addObject(GameObject object){
        this.object.add(object);
    }
    
    /**
     * Método para remover objetos de nuestra lista de objetos de tipo GameObject
     * @param object recibe el objeto a remover como parametro
     */
    public void removeObject(GameObject object){
        this.object.remove(object);
        
    }
    
    public void cambiarNivel(){
        limpiarNivel();
        cam.setX(0);
        
        switch(Juego.NIVEL){
            case 1:{
                
                cargarImagenNivel(level2);
                
                
                break;
            }
        }
        Juego.NIVEL++;
    }
    
    
    
    /**
     * Remueve todos los objetos que estén en la pantalla
     */
    private void limpiarNivel(){
        object.clear();
    }
    
    /**
     * Crea los bloques del nivel
     */
    /*
    public void crearNivel(){
        
        for(int yy=0; yy < Juego.HEIGHT+32 ; yy+=32){
            addObject(new Bloque(0,yy,ObjectId.Bloque));
        }
        
        for(int xx=0; xx < Juego.WIDTH*2 ; xx+=32){
            addObject(new Bloque(xx,Juego.HEIGHT-32,ObjectId.Bloque));
        }
        
        for(int xx=200; xx < 600 ; xx+=32){
            addObject(new Bloque(xx,400,ObjectId.Bloque));
        }
    }*/
}
