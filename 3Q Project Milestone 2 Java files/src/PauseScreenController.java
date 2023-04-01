import java.io.IOException;
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
public class PauseScreenController extends ControllerBase implements Initializable {
    
    @FXML
    private Label label;
    
    @FXML
    public void resume (ActionEvent event) throws IOException {
        newScreen(event, "BattleScreen.fxml");
    }

    @FXML
    public void menu (ActionEvent event) throws IOException {
        newScreen(event, "MenuScreen.fxml");
    }
    
    @FXML
    public void settings (ActionEvent event) throws IOException {
        newScreen(event, "OptionsScreen.fxml");
    }
    @FXML
    public void quit (ActionEvent event) throws IOException {
        newScreen(event, "QuitScreen.fxml");
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
