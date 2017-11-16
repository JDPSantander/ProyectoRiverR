
package riverraid;

import Framework.GameObject;
import Framework.ObjectId;
import java.awt.Graphics;
import java.util.LinkedList;
import objetos.Bloque;

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
