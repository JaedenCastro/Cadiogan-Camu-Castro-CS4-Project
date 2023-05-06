
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
import javafx.scene.media.MediaView;
import java.io.File;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
/**
 *
 * @author windows10 user
 */
public class OptionsScreenController extends ControllerBase implements Initializable {

    @FXML private Slider volumeSlider;

    public Slider test;
    @FXML
    private Label label;
    
    @FXML
    private void back (ActionEvent event) throws IOException {
        newScreen(event, "PauseScreen.fxml");
    }
    
    private MediaPlayer mediaPlayer;
    private static final String MEDIA_URL =
            new File("Muon-CadioganMuon-Camu-Muon-Castro_Project Progress/src/Music/bgmusic.mp3").toURI().toString();
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Media media = new Media(MEDIA_URL);
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);


        volumeSlider.setMin(0);
        volumeSlider.setMax(100);
        volumeSlider.setValue(1);
        mediaPlayer.volumeProperty().bind(volumeSlider.valueProperty());
        volumeSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                mediaPlayer.setVolume(newValue.doubleValue());
            }

            public void onVolumeSliderChanged() {
                double volume = volumeSlider.getValue();
                mediaPlayer.setVolume(volume);
            }
        })

    ;}


};
    

    

