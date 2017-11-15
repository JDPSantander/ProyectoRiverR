
package Framework;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

/**
 *
 * @author JesusDParraSantander
 */
public abstract class GameObject {
    
    protected float x,y;
    protected ObjectId id;
    protected float velocidadX =0 , velocidadY = 0;
    protected boolean cayendo = true;
    protected boolean saltando = false;
    
    
    public GameObject(float x, float y, ObjectId id){
        this.x=x;
        this.y=y;
        this.id=id;
    }
    
    /**
     * Detección de colisiones
     * @param object 
     */
    public abstract void tick(LinkedList<GameObject> object);
    
    public abstract void render(Graphics g);
    
    
    /**
     * Método que devuelve los parametros y limites para las colisiones
     * @return 
     */
    public abstract Rectangle getBounds();
    
    
    public float getX(){
        return x;
    }
    public float getY(){
        return y;
    }
    public void setX(float x){
        this.x=x;
    }
    public void setY(float y){
        this.y=y;
    }
    
    public float getVelocidadX(){
        return velocidadX;
    }
    public float getVelocidadY(){
        return velocidadY;
    }
    public void setVelocidadX(float velocidadX){
        this.velocidadX = velocidadX;
    }
    public void setVelocidadY(float velocidadY){
        this.velocidadY = velocidadY;
    }
    
    // para revisar qué objeto actual del juego es y evitar confusiones
    public ObjectId getID(){
        return id;
    }
    
    public void setSaltando(boolean saltando){
        this.saltando=saltando;
    }
    public void setCayendo(boolean cayendo){
        this.cayendo=cayendo;
    }
    
    public boolean isSaltando(){
        return saltando;
    }
    public boolean isCayendo(){
        return cayendo;
    }
    
    
    
}
