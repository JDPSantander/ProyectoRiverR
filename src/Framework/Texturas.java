
package Framework;

import java.awt.image.BufferedImage;
import riverraid.BufferedImageLoader;

/**
 * Carga todas las texturas(imagenes) en nuestro juego
 * @author JesusDParraSantander
 */
public class Texturas {
    
    SpriteSheet bs, ps, shoots;  // bs = block_sheet  y ps = player_sheet
    private BufferedImage block_sheet = null;
    private BufferedImage player_sheet = null;
    public BufferedImage disparos = null;
    public BufferedImage enemigo1 = null;
    
    public BufferedImage[] bloque = new BufferedImage[3];
    public BufferedImage[] jugador = new BufferedImage[14];
    
    public Texturas(){
        
        BufferedImageLoader loader = new BufferedImageLoader();
        
        try{
            block_sheet = loader.loadImage("/spritesShoots.png");
            player_sheet = loader.loadImage("/spritesAirplane.png");
            disparos = loader.loadImage("/disparo.png");
            bloque[2] = loader.loadImage("/bsc_2.png");
            enemigo1 = loader.loadImage("/hammerandsickle.png");
            
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Error al cargar las imagenes de sprites");
        }
        
        bs = new SpriteSheet(block_sheet);
        ps = new SpriteSheet(player_sheet);
        
        getTexturas();
    }
    
    public void getTexturas(){
        
                                //columnas,fila, ancho, alto                        
        bloque[0] = bs.agarrarImagen(744, 69, 74, 90);//bloques (tronco)
        bloque[1] = bs.agarrarImagen(609, 60, 93, 99); //bloques (piedra)
        
        // aviones
        jugador[0] = ps.agarrarImagen(2, 130, 61, 78); // primer avion fila inferior
        jugador[1] = ps.agarrarImagen(1, 0, 61, 109); // avión rojo 
        jugador[2] = ps.agarrarImagen(80, 1, 59, 105); // segundo avion fila superior
        jugador[3] = ps.agarrarImagen(163, 13, 50, 86); // tercer avión fila superior
        jugador[4] = ps.agarrarImagen(228, 10, 60, 84); // cuarto avión fila superior
        jugador[5] = ps.agarrarImagen(303, 17, 45, 67); // quinto avión fila superior
        jugador[6] = ps.agarrarImagen(367, 6, 62, 90); // sexto avión fila superior
        jugador[7] = ps.agarrarImagen(438, 7, 72, 88); // septimo avión fila superior
        jugador[8] = ps.agarrarImagen(79, 116, 62, 105); // segundo avión fila inferior
        jugador[9] = ps.agarrarImagen(160, 129, 56, 84); // tercer avión fila inferior
        jugador[10] = ps.agarrarImagen(232, 125, 56, 91); // cuarto avión fila inferior
        jugador[11] = ps.agarrarImagen(305, 145, 44, 63); //quinto avión fila inferior 
        jugador[12] = ps.agarrarImagen(371, 130, 53, 83); // sexto avión fila inferior
        jugador[13] = ps.agarrarImagen(444, 127, 60, 88);  // septimo avión fila inferior
       
    }
}
