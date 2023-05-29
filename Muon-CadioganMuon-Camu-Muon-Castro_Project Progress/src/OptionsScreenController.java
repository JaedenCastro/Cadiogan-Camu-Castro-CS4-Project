import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.StringConverter;

public class OptionsScreenController extends ControllerBase implements Initializable {

    @FXML
    private Slider volumeSlider;

    @FXML
    private Label label;
    /**
     * Transition back to the pause screen.
     *
     * @param event The action event triggered by the button.
     * @throws IOException If an I/O error occurs.
     */
    @FXML
    private void back(ActionEvent event) throws IOException {
        newScreen(event, "PauseScreen.fxml");
    }

    private MediaPlayer mediaPlayer;
    private static final String MEDIA_URL = new File("../Muon-CadioganMuon-Camu-Muon-Castro_Project Progress/src/Music/bgmusic.mp3")
            .toURI().toString();
    /**
     * Initializes the controller and sets up the options screen.
     *
     * @param url The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param rb  The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Media media = new Media(MEDIA_URL);
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);

        // Set up the volume slider
        volumeSlider.setMin(0.0);
        volumeSlider.setMax(1.0);
        volumeSlider.setValue(0.5);
        volumeSlider.setBlockIncrement(0.1);

        // Bind the slider value to the MediaPlayer volume using a linear scale
        DoubleProperty volumeProperty = mediaPlayer.volumeProperty();
        volumeSlider.valueProperty().bindBidirectional(volumeProperty);
        volumeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            volumeSlider.setValue(newValue.doubleValue());
        });

        // Set up the string converter
        StringConverter<Double> volumeConverter = new StringConverter<Double>() {
            @Override
            public String toString(Double value) {
                return String.format("%.1f dB", 20.0 * Math.log10(value));
            }

            @Override
            public Double fromString(String string) {
                return Math.pow(10.0, Double.parseDouble(string) / 20.0);
            }
        };

        volumeSlider.setLabelFormatter(new StringConverter<Double>() {
            @Override
            public String toString(Double value) {
                return volumeConverter.toString(value);
            }

            @Override
            public Double fromString(String string) {
                return volumeConverter.fromString(string);
            }
        });
    }
}
