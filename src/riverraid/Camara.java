package riverraid;

import Framework.GameObject;

/**
 *
 * @author JesusDParraSantander
 */
public class Camara {
    
    private float x,y;
    
    public Camara(float x, float y){
        this.x=x;
        this.y=y;
    }
    
    /**
     * Mueve la camara automáticamente (horizontalmente hacia la derecha con x--;)
     * @param player 
     */
    public void tick(GameObject player){  // se usa algoritmo llamado tweening (interpolación)
        x = -player.getX() + Juego.WIDTH/2;  // para mover la pantalla enfocando al jugador (si el jugador no se mueve manualmente, la pantalla no lo hará)
    }
    
    public void setX(float x){
        this.x=x;
    }
    
    public void setY(float y){
        this.y=y;
    }
    
    public float getX(){
        return x;
    }
    
    public float getY(){
        return y;
    }
}
