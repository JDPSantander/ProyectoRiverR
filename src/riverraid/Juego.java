
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
    private BufferedImage level = null;
    static Texturas tex;
    
    
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
        
        
        cam = new Camara(0,0);
        
        manejador = new Manejador();
        
        
        cargarImagenNivel(level);
        
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
        
        Graphics2D g2d = (Graphics2D) g; // castear la variable g y convertirlo en un objeto de tipo Graphics2D
        
        ///////////////////////////////////////////////////////
        // DRAW HERE
        g.setColor(Color.black);
        g.fillRect(0, 0, getWidth(), getHeight());
        
        //.translate: traslada el origen del contexto de Graphics2D a el punto x,y en el sistema actual de coordenadas
        //modifica el contexto Graphics2D de modo tal que su nuevo origen corresponda al punto x,y
        // todas las coordenadas usada en las operaciones de renderizado subsecuentes sobre este contexto gráfico son relativas a este nuevo origen
        
        g2d.translate(cam.getX(), cam.getY()); //inicio de la camara
        
        manejador.render(g);    // Es afectado por el inicio y el final de la camara
        
        g2d.translate(-cam.getX(), -cam.getY()); // final de la camara
        
        //////////////////////////////////////////////////////
        
        
        g.dispose();
        bs.show();
        
        
    }
    
    /**
     * Carga la imagen del mapa de acuerdo a la estructura(imagen) hecha en paint con colores e identifica en el codigo los diferentes colores de cada pixel 
     * de la imagen para colocar en esa ubicación (color) la imagen deseada 
     * @param image Recibe la imagen del nivel hecha en paint
     */
    private void cargarImagenNivel(BufferedImage image){
        
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
                 
                    manejador.addObject(new Bloque(xx*32, yy*32,1, ObjectId.Bloque)); // Agrega un bloque en cada pixel blanco
                }    
                //Para verificar si el pixel en el que nos encontramos es de color azul (lo que supone es nuestro jugador)
                if (rojo == 0 && verde == 0 && azul ==255){ //esta combinación de colores es igual a azul puro
                 
                    manejador.addObject(new Jugador(xx*32, yy*32,manejador, ObjectId.Jugador)); // Agrega nuestro jugador (bloque azul) en el pixel azul
                }    
            }
        }
    }
    
    public static Texturas getInstancia(){
        return tex;
    }
    
}
