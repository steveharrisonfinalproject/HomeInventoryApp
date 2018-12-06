/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.finalproject.HomeInventory.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;

/**
 *
 * @author Administrator
 */
public class AuthenticationManager {
    
    private static AuthenticationManager instance;
    
    private AuthenticationManager(){}
    
    private SecurityContext sc;
    
    public static synchronized AuthenticationManager getInstance(){
        if(instance == null){
            instance = new AuthenticationManager();
        }
        return instance;
    }

    public SecurityContext getSc() {
        return sc;
    }

    public void setSc(SecurityContext sc) {
        this.sc = sc;
    }
    
    
}
