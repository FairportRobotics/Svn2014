/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fairportfirst.frc2014;

/**
 *
 * @author Tyler
 */
public class AutonomusMode {
    
    
    int value;
    
    public static AutonomusMode disabled = new AutonomusMode(1);
    public static AutonomusMode oneBall = new AutonomusMode(2);
    public static AutonomusMode twoBall = new AutonomusMode(3);
    
    public AutonomusMode(int num){
        value = num;
    }
    
    public int getValue(){
        return value;
    }
    
}
