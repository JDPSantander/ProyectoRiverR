
package riverraid;
import Framework.GameObject;
import Framework.ObjectId;
import static Framework.ObjectId.Bloque;
import static Framework.ObjectId.Test;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import objetos.Bloque;
import objetos.Jugador;
import objetos.Prueba;

/**
 *
 * @author JesusDParraSantander
 */
public class Juego extends Canvas implements Runnable{
    
    private boolean corriendo=false;
    private Thread hilo;
    public static int WIDTH,HEIGHT;
    
 
    Manejador manejador;
    
    /** 
     * Método para inicializar todo, es llamado antes de iniciar el hilo. Instancia el manejador y le añade el objeto 
     * de tipo GameObject
     */
    public void init(){
        WIDTH = getWidth();
        HEIGHT = getHeight();
        
        manejador = new Manejador();
        
        manejador.addObject(new Jugador(100,100,ObjectId.Jugador));
        
        manejador.crearNivel();
        
    }

    public synchronized void iniciar(){
        if(corriendo){
            return;
        }
        corriendo=true;
        hilo = new Thread(this);
        hilo.start();
        
    }
    
    /**
     * Corre el juego en un determinado número de ticks por segundo (60 ticks por segundo) mientras hace un renderizado 
     * de las imagenes actualizandolas al número de ticks
     */
    @Override
    public void run() {
        
        init();
        this.requestFocus();
        
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int updates = 0;
        int frames = 0;
        while(corriendo){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1){
                tick();
                updates++;
                delta--;
            }
            render();
            frames++;
   
            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                System.out.println("FPS: " + frames + " TICKS: " + updates);
                frames = 0;
                updates = 0;
            }
        }
    }
    
    private void tick(){
        manejador.tick();
    }
    
    /**
     * Renderizado de imagenes y fondo por medio de un buffer de la clase Canvas. Si el buffer ya ha sido inicializado 
     * anteriormente, no lo vuelve a iniciar y sale del método por medio del return. Utiliza 3 buffers donde cada uno almacena
     * una imagen o ventana que ha sido cargada con anterioridad, la principal se muestra por pantalla y las otras dos
     * se ubican detrás de la ventana principal; este ciclo se repite cada vez que la imagen que está al frente deja de 
     * usarse y la de atrás pasa a ser la principal, cargando así otras dos imagenes detrás de esta nueva
     * para tenerlas listas al momento de ser necesitadas
     * 
     */
    
    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs==null){
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        
        ///////////////////////////////////////////////////////
        // DRAW HERE
        g.setColor(Color.black);
        g.fillRect(0, 0, getWidth(), getHeight());
        manejador.render(g);
        
        //////////////////////////////////////////////////////
        
        
        g.dispose();
        bs.show();
        
        
    }
    
}
