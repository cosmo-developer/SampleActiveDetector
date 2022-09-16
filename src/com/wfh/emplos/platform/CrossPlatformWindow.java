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
public final class CrossPlatformWindow {
    static{
        System.load(System.getProperty("user.dir")+"/winx.rtl");
    }
    public static native Window getActiveWindow();
}
