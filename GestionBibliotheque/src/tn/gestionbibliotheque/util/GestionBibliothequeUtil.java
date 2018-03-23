
package tn.gestionbibliotheque.util;

import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author ASUS
 */
public class GestionBibliothequeUtil {
    public static final String IMAGE_LOC = "/tn/gestionbibliotheque/icons/book.jpg";
    
    public static void setStageIcon(Stage stage){
        stage.getIcons().add(new Image(IMAGE_LOC));
    }
}
