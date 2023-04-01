import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
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

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to exit?", ButtonType.YES, ButtonType.CANCEL);
        alert.setTitle("Logout/Exit");
        alert.setHeaderText(null);


        ButtonType result = alert.showAndWait().orElse(ButtonType.CANCEL);


        if (result == ButtonType.YES) {

            System.exit(0);
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
