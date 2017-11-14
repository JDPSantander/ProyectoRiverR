
package riverraid;
import javax.swing.JFrame;
import java.awt.Dimension;


/**
 *
 * @author JesusDParraSantander
 */
public class Ventana {
    
    public Ventana(int w, int h, String titulo, Juego game){
        
        game.setPreferredSize(new Dimension(w,h));
        game.setMaximumSize(new Dimension(w,h));
        game.setMinimumSize(new Dimension(w,h));
        
        
        JFrame frame = new JFrame(titulo);
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        game.iniciar();
    }
}
