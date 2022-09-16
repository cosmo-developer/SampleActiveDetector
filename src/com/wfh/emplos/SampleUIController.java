/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wfh.emplos;

import com.wfh.emplos.activedetector.ActiveDetector;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 *
 * @author Sonu Aryan <cosmo-developer@github.com>
 */
public class SampleUIController implements Initializable {
    
    
    @FXML
    private Label keyboardCountLabel;
    @FXML
    private Label mouseMoveCoord;
    @FXML
    private Label mouseCountLabel;
    @FXML
    private Label activeWindowTitle;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.keyboardCountLabel.setText("");
        this.mouseCountLabel.setText("");
        this.activeWindowTitle.setText("");
        this.mouseMoveCoord.setText("");
        ActiveDetector detector=(ActiveDetector)GlobalManager.getDefault().getDep(ActiveDetector.class);
        detector.setKeyboardClicks(this.keyboardCountLabel.textProperty());
        detector.setMouseClicks(this.mouseCountLabel.textProperty());
        detector.setMouseCoord(this.mouseMoveCoord.textProperty());
        detector.setActiveWindow(this.activeWindowTitle.textProperty());
        
    } 
    
}
