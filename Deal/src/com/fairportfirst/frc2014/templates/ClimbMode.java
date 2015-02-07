package com.fairportfirst.frc2014.templates;

/**
 *
 * @author Brian
 */
public class ClimbMode {
    // Test commit.
    private int value;
    
    public static ClimbMode AUTOMATIC = new ClimbMode(1);
    public static ClimbMode MANUAL = new ClimbMode(2);
            
            
    private ClimbMode(int value){
        this.value = value;
    }
}
