/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.fairportrobotics.jemo2014;

import org.fairportrobotics.jemo2014.commands.AutonomusDriveCommand;

/**
 *
 * @author Tyler
 */
public class AutonomusMode {
    
    public static final AutonomusMode follow = new AutonomusMode(0);
    public static final AutonomusMode catchBall = new AutonomusMode(1);
    public static final AutonomusMode disabled = new AutonomusMode(2);
    
    public int value = 0;
    
    public AutonomusMode(int val){
        value = val;
    }
    
}
