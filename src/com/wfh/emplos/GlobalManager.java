/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wfh.emplos;

import java.util.ArrayList;

/**
 *
 * @author Sonu Aryan <cosmo-developer@github.com>
 */
public class GlobalManager {

    private final ArrayList<Object> deps;
    private static GlobalManager MANAGER = null;

    private GlobalManager() {
        deps = new ArrayList<>();
    }

    public static GlobalManager getDefault() {
        if (MANAGER != null) {
            return MANAGER;
        }
        MANAGER = new GlobalManager();
        return MANAGER;
    }

    public void addDep(Object dep) {
        deps.removeIf((Object t) -> t.getClass().equals(dep.getClass()));
        deps.add(dep);
    }

    public Object getDep(Class clz) {
        for (int i = 0; i < deps.size(); i++) {
            if (deps.get(i).getClass().toString().equals(clz.toString())) {
                return deps.get(i);
            }
        }
        return null;
    }
}
