/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.fairportfirst.frc2014;

/**
 *
 * @author Tyler
 */
public class OperMode {
    
    public static OperMode normalMode = new OperMode(0);
    public static OperMode demoMode = new OperMode(1);
    
    private int val;
    
    public OperMode(int val){
        this.val = val;
    }
    
}
