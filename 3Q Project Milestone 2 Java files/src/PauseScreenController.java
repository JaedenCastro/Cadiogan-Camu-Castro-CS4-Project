import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 *
 * @author windows10 user
 */
public class PauseScreenController implements Initializable {
    
    @FXML
    private Label label;
    
    @FXML
    private void resume (ActionEvent event) throws IOException {
        newScreen(event, "BattleScreen.fxml");
    }
    
    @FXML
    private void menu (ActionEvent event) throws IOException {
        newScreen(event, "MenuScreen.fxml");
    }
    
    @FXML
    private void settings (ActionEvent event) throws IOException {
        newScreen(event, "OptionsScreen.fxml");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
