
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.Media;

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
    
    private MediaPlayer mediaPlayer;
    private Slider volumeSlider;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Media media = new Media("file:///path/to/mediafile.mp3");
        mediaPlayer = new MediaPlayer(media);
        
         volumeSlider = new Slider(0, 1, 1);
        volumeSlider.setOrientation(Orientation.HORIZONTAL);
        volumeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            mediaPlayer.setVolume(newValue.doubleValue());
        });
    }    
    
}
