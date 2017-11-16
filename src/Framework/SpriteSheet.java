
package Framework;

import java.awt.image.BufferedImage;

/**
 * Fija las imagenes en el juego descomponiendolas del spritesheet (hoja de imagenes) y busca la correcta que se necesite 
 * @author JesusDParraSantander
 */
public class SpriteSheet {
    
    private BufferedImage imagen;
    
    /**
     * Guarda la imagen que recibe en el BufferedImage
     * @param image recibe la imagen (spritesheet)
     */
    public SpriteSheet(BufferedImage image){
        imagen=image;
    }
    
    
    /**
     * Recibe por parametro las filas, columnas, ancho y alto que querramos de la imagen del spritesheet 
     * y subdivide la imagen que se encuentra en los limites que le hemos dado por parametro
     * Cada columna y fila hace una referencia a la ubicación en pixeles de la imagen
     * @param columnas la columna que queremos
     * @param filas pixel de esa fila
     * @param ancho
     * @param alto
     * @return regresa la subimagen que deseamos del spritesheet
     */
    public BufferedImage agarrarImagen(int columnas, int filas, int ancho, int alto){
        //BufferedImage img = imagen.getSubimage(((columnas * ancho) -ancho) , ((filas * alto) - alto), ancho, alto);
        BufferedImage img = imagen.getSubimage(columnas , filas, ancho, alto);
        return img;
    }
}
