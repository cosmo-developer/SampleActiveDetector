/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wfh.emplos.platform;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;

/**
 *
 * @author Sonu Aryan <cosmo-developer@github.com>
 */
public final class CrossPlatformWindow {
    static{
        OperatingSystemMXBean os=ManagementFactory.getOperatingSystemMXBean();
        
        String libraryArchName=null;
        String osName=null;
        
        switch(os.getArch()){
            case "amd64":
                libraryArchName="x86_64";
                break;
            default:
                libraryArchName="x86";
        }
        
        if (os.getName().contains("Windows")){
            osName="win";
        }
        
        System.out.println("loading library:"+"nativelib/"+osName+"/"+libraryArchName+"/winx");
        
        System.loadLibrary("nativelib/"+osName+"/"+libraryArchName+"/winx");
    }
    public static native Window getActiveWindow();
}
