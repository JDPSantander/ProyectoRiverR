
package Framework;

import java.awt.image.BufferedImage;
import riverraid.BufferedImageLoader;

/**
 * Carga todas las texturas(imagenes) en nuestro juego
 * @author JesusDParraSantander
 */
public class Texturas {
    
    SpriteSheet bs, ps, shoots,explosions;  // bs = block_sheet  y ps = player_sheet
    private BufferedImage block_sheet = null;
    private BufferedImage player_sheet = null;
    public BufferedImage disparos = null;
    public BufferedImage enemigo1 = null;
    public BufferedImage explosion_sheet = null;
    
    public BufferedImage[] bloque = new BufferedImage[3];
    public BufferedImage[] jugador = new BufferedImage[14];
    public BufferedImage[] explosiones = new BufferedImage[16];
    
    public Texturas(){
        
        BufferedImageLoader loader = new BufferedImageLoader();
        
        try{
            block_sheet = loader.loadImage("/spritesShoots.png");
            player_sheet = loader.loadImage("/spritesAirplane.png");
            disparos = loader.loadImage("/disparo.png");
            bloque[2] = loader.loadImage("/bsc_2.png");
            enemigo1 = loader.loadImage("/hammerandsickle.png");
            explosion_sheet = loader.loadImage("/Explosion.png");
            
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Error al cargar las imagenes de sprites");
        }
        
        bs = new SpriteSheet(block_sheet);
        ps = new SpriteSheet(player_sheet);
        explosions = new SpriteSheet(explosion_sheet);
        
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
        
        explosiones[0] = explosions.agarrarImagen(4, 3, 55, 55); //1 de izquierda a derecha, arriba a abajo
        explosiones[1] = explosions.agarrarImagen(66, 3, 55, 57); //2
        explosiones[2] = explosions.agarrarImagen(132, 3, 58, 56);  //3
        explosiones[3] = explosions.agarrarImagen(193, 3, 59, 56);  //4
        explosiones[4] = explosions.agarrarImagen(5, 69, 53, 55); //5
        explosiones[5] = explosions.agarrarImagen(70, 69, 52, 54); //6
        explosiones[6] = explosions.agarrarImagen(134, 69, 52, 53); //7
        explosiones[7] = explosions.agarrarImagen(199, 70, 50, 47);  //8
        explosiones[8] = explosions.agarrarImagen(7, 139, 49, 45); //9
        explosiones[9] = explosions.agarrarImagen(72, 140, 45, 44);  //10
        explosiones[10] = explosions.agarrarImagen(137, 135, 55, 55); //11
        explosiones[11] = explosions.agarrarImagen(203, 140, 47, 45); //12
        explosiones[12] = explosions.agarrarImagen(9, 205, 51, 44);  //13
        explosiones[13] = explosions.agarrarImagen(75, 205, 59, 50);  //14
        explosiones[14] = explosions.agarrarImagen(140, 206, 40, 30); //15
        explosiones[15] = explosions.agarrarImagen(204, 209, 36, 26); //16
        
       
    }
}
