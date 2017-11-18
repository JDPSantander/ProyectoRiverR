
package Framework;

import java.awt.image.BufferedImage;
import riverraid.BufferedImageLoader;

/**
 * Carga todas las texturas(imagenes) en nuestro juego
 * @author JesusDParraSantander
 */
public class Texturas {
    
    SpriteSheet bs, ps;  // bs = block_sheet  y ps = player_sheet
    private BufferedImage block_sheet = null;
    private BufferedImage player_sheet = null;
    
    public BufferedImage[] bloque = new BufferedImage[2];
    public BufferedImage[] jugador = new BufferedImage[1];
    
    
    public Texturas(){
        
        BufferedImageLoader loader = new BufferedImageLoader();
        
        try{
            block_sheet = loader.loadImage("/spritesShoots.png");
            player_sheet = loader.loadImage("/spritesAirplane.png");
            
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
        //1,0,61,109 son coordenadas del avión comunista
        
        jugador[0] = ps.agarrarImagen(1, 0, 61, 109); // aviones
    }
}
