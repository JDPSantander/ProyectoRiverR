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
     * Mueve la camara automáticamente enfocando al personaje. En x, la pantalla enfoca al jugador en sus coordenadas x 
     * y se ubica en la mitad de la pantalla; la pantalla se moverá de lado a lado manualmente según sea la entrada 
     * por teclado que haga el usuario para mover al avión. En y, se enfoca en las coordenadas Y del jugador y lo 
     * ubica en el borde inferior. Como las coordenadas Y del jugador están variando automáticamente (el avión está 
     * subiendo posiciones en Y automaticamente), la camara lo sigue en esas posiciones en Y. 
     * @param player recibe el objeto de tipo jugador
     */
    public void tick(GameObject player){  // se usa algoritmo llamado tweening (interpolación)
        
        //Para mover la pantalla en las coordenadas X. -30 es el valor que se usa para ubicar al avión justo en
        //la mitad de la pantalla
        x = (-player.getX()-30)  + Juego.WIDTH/2;  // para mover la pantalla en las coordenadas X 
        
        //y++;  Para mover la camara automaticamente hacia arriba
        
        //Mueve la pantalla en las coordenadas Y del jugador. +105 es para ubicarlo en el borde inferior de la pantalla
        y = (-player.getY()+105) + Juego.WIDTH/2;
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
