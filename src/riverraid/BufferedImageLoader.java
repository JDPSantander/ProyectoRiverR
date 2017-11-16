
package riverraid;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author JesusDParraSantander
 */
public class BufferedImageLoader {
    
    private BufferedImage imagen;
    
    /**
     * Este metodo carga la imagen de cualquier camino/ubicación que le demos
     * @param path ubicación de la imagen
     * @return retorna la imagen
     */
    public BufferedImage loadImage(String path){
        
        try {
            imagen = ImageIO.read(getClass().getResource(path));
        } catch (IOException ex) {
            System.out.println("Error al buscar la carpeta de la imagen");
        }
        
        return imagen;
    }
}
