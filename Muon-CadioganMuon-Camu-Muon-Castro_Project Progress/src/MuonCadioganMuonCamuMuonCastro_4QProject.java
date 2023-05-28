/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML.java to edit this template
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author jaede
 */
public class MuonCadioganMuonCamuMuonCastro_4QProject extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("FXML/MenuScreen.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    /*
    @FXML Text l1,l2, l3, l4, l5, l6, l7;
    private void nextLog(String s){
        l7.setText(l6.getText());
        l6.setText(l5.getText());
        l5.setText(l4.getText());
        l4.setText(l3.getText());
        l3.setText(l2.getText());
        l2.setText(l1.getText());
        l1.setText(s);
    }

     */
    
}
