/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wfh.emplos.activedetector;

import com.wfh.emplos.platform.CrossPlatformWindow;
import javafx.application.Platform;
import javafx.beans.property.StringProperty;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.mouse.NativeMouseEvent;

/**
 *
 * @author Sonu Aryan <cosmo-developer@github.com>
 */
public class ActiveDetector implements org.jnativehook.mouse.NativeMouseListener, org.jnativehook.keyboard.NativeKeyListener, org.jnativehook.mouse.NativeMouseMotionListener {

    StringProperty mouseClicks;
    StringProperty keyboardClicks;
    StringProperty mouseCoord;
    StringProperty activeWindowTitle;
    int mouseClickCounts;
    int keyboardClickCounts;

    public StringProperty getMouseClicks() {
        return mouseClicks;
    }

    public void setMouseClicks(StringProperty mouseClicks) {
        this.mouseClicks = mouseClicks;
        this.mouseClickCounts = 0;
    }

    public StringProperty getKeyboardClicks() {
        return keyboardClicks;
    }

    public void setKeyboardClicks(StringProperty keyboardClicks) {
        this.keyboardClickCounts = 0;
        this.keyboardClicks = keyboardClicks;
    }

    public StringProperty getMouseCoord() {
        return mouseCoord;
    }

    public void setMouseCoord(StringProperty mouseCoord) {
        this.mouseCoord = mouseCoord;
    }

    public void setActiveWindow(StringProperty textProperty) {
        this.activeWindowTitle = textProperty;
    }

    void detectActiveWindow() {
        this.activeWindowTitle.set(CrossPlatformWindow.getActiveWindow().getTitle());
    }

    @Override
    public void nativeMouseClicked(NativeMouseEvent nme) {
        mouseClickCounts++;
        Platform.runLater(() -> {
            if (mouseClicks != null) {
                mouseClicks.set("" + mouseClickCounts);

            }
            detectActiveWindow();
        });

    }

    @Override
    public void nativeMousePressed(NativeMouseEvent nme) {
        this.nativeMouseClicked(nme);
    }

    @Override
    public void nativeMouseReleased(NativeMouseEvent nme) {

    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent nke) {
        this.keyboardClickCounts++;
        Platform.runLater(() -> {
            if (this.keyboardClicks != null) {
                this.keyboardClicks.set("" + this.keyboardClickCounts);
            }
            detectActiveWindow();
        });
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent nke) {
        this.nativeKeyTyped(nke);
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent nke) {

    }

    @Override
    public void nativeMouseMoved(NativeMouseEvent nme) {
        if (this.mouseCoord != null) {
            Platform.runLater(() -> {
                this.mouseCoord.set("x:" + nme.getX() + ",y:" + nme.getY());
                detectActiveWindow();
            });
        }
    }

    @Override
    public void nativeMouseDragged(NativeMouseEvent nme) {
        this.nativeMouseMoved(nme);
    }

}
