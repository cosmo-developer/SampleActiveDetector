/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wfh.emplos;

import com.wfh.emplos.activedetector.ActiveDetector;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

/**
 *
 * @author Sonu Aryan <cosmo-developer@github.com>
 */
public class SampleActiveDetector extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("SampleUI.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     * @throws org.jnativehook.NativeHookException
     */
    public static void main(String[] args) throws NativeHookException {

        ActiveDetector activeDetector = new ActiveDetector();
        GlobalManager.getDefault().addDep(activeDetector);
        
        LogManager.getLogManager().reset();
        Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setLevel(Level.OFF);

        GlobalScreen.registerNativeHook();
        
        GlobalScreen.addNativeMouseListener(activeDetector);
        GlobalScreen.addNativeKeyListener(activeDetector);
        GlobalScreen.addNativeMouseMotionListener(activeDetector);

        launch(args);
        GlobalScreen.removeNativeMouseListener(activeDetector);
        GlobalScreen.removeNativeMouseListener(activeDetector);
        GlobalScreen.removeNativeKeyListener(activeDetector);
        GlobalScreen.unregisterNativeHook();
    }

}
