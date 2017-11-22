
package riverraid;
import Framework.EntradaTeclado;
import Framework.GameObject;
import Framework.ObjectId;
import static Framework.ObjectId.Bloque;
import static Framework.ObjectId.Test;
import Framework.Texturas;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import objetos.Bloque;
import objetos.Jugador;
import objetos.Prueba;
import objetos.Puerta;
import objetos.Vidas;

/**
 *
 * @author JesusDParraSantander
 */
public class Juego extends Canvas implements Runnable{
    
    private boolean corriendo=false;
    private Thread hilo;
    public static int WIDTH,HEIGHT;
    
    //Objetos
    Camara cam;
    Manejador manejador;
    public BufferedImage level = null, balas = null;// fondo = null; //  clouds = null; (14V)
    static Texturas tex;
    //private Vidas vida;
    
    public static int NIVEL =1;
    
    /** 
     * Método para inicializar todo, es llamado antes de iniciar el hilo. Instancia el manejador y le añade el objeto 
     * de tipo GameObject
     */
    public void init(){
        WIDTH = getWidth();
        HEIGHT = getHeight();
        
        tex = new Texturas();
        
        BufferedImageLoader loader = new BufferedImageLoader();
        level = loader.loadImage("/nivel.png");  // cargando el nivel
        //fondo = loader.loadImage("/back_1.png"); 
        balas = loader.loadImage("/disparo.png");
        
        
        cam = new Camara(0,0);
        
        manejador = new Manejador(cam);
       //vida = new Vidas();
        
        manejador.cargarImagenNivel(level);
        
       //manejador.addObject(new Jugador(100,100,manejador,ObjectId.Jugador));
        
        //manejador.crearNivel();
        
        this.addKeyListener(new EntradaTeclado(manejador));
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
        //vida.tick(); // agregando el timer de la vida
        for(int i=0; i<manejador.object.size(); i++){  // recorre la lista de objetos que hay en el manejador
            if(manejador.object.get(i).getID() == ObjectId.Jugador){  // si el objeto en el que se encuentra es igual al jugador
                cam.tick(manejador.object.get(i)); // el objeto que se pasa por el parametro será el jugador
            }
        }
        
    }
    
    /**
     * Renderizado de imagenes y fondo por medio de un buffer de la clase Canvas. Si el buffer ya ha sido inicializado 
     * anteriormente, no lo vuelve a iniciar y sale del método por medio del return. Utiliza 3 buffers donde cada uno almacena
     * una imagen o ventana que ha sido cargada con anterioridad, la principal se muestra por pantalla y las otras dos
     * se ubican detrás de la ventana principal; este ciclo se repite cada vez que la imagen que está al frente deja de 
     * usarse y la de atrás pasa a ser la principal, cargando así otras dos imagenes detrás de esta nueva
     * para tenerlas listas al momento de ser necesitadas. Además, aquí se usa la clase cámara para enfocar la pantalla 
     * en donde está nuestro jugador
     */
    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs==null){
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        
        Graphics2D g2d = (Graphics2D) g; // castear la variable g y convertirlo en un objeto de tipo Graphics2D
        
        ///////////////////////////////////////////////////////
        // DIBUJAR AQUI
        
        
        g.setColor(new Color(25,191,224));  // creando nuevo color del fondo (14V)
        g.fillRect(0, 0, getWidth(), getHeight());
        //g.drawImage(fondo, 0, 0, this);   // Dibuja el fondo de forma estática. 0,0 son las posiciones en la pantalla
        
        //.translate: traslada el origen del contexto de Graphics2D a el punto x,y en el sistema actual de coordenadas
        //modifica el contexto Graphics2D de modo tal que su nuevo origen corresponda al punto x,y
        // todas las coordenadas usada en las operaciones de renderizado subsecuentes sobre este contexto gráfico son relativas a este nuevo origen
        
        g2d.translate(cam.getX(), cam.getY()); //inicio de la camara
      
        manejador.render(g);    // Es afectado por el inicio y el final de la camara
        //vida.render(g);
        
        g2d.translate(-cam.getX(), -cam.getY()); // final de la camara
        
        //////////////////////////////////////////////////////
        
        
        g.dispose();
        bs.show();
        
        
    }
   
    
    public static Texturas getInstancia(){
        return tex;
    }
    
}
