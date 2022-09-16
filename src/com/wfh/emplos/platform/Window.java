/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wfh.emplos.platform;

/**
 *
 * @author Sonu Aryan <cosmo-developer@github.com>
 */
public class Window {
    private final String title;
    private Window(String title){
        this.title=title;
    }
    
    public String getTitle() {
        return title;
    }
}
