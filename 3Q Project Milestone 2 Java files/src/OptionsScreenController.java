
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
public class OptionsScreenController extends ControllerBase implements Initializable {
    
    @FXML
    private Label label;
    
    @FXML
    private void back (ActionEvent event) throws IOException {
        newScreen(event, "PauseScreen.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
