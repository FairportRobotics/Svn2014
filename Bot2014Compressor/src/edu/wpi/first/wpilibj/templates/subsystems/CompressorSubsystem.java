
package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.templates.commands.CompressorStartCommand;

/**
 *
 */
public class CompressorSubsystem extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
    static CompressorSubsystem instance;
    Compressor compressor;
    
    
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
        setDefaultCommand(new CompressorStartCommand());
    }
    
    public void startCompressor()
    {
        compressor.start();
    }
    
    public static CompressorSubsystem getInstance()
    {
        if (instance==null)
        {
            instance = new CompressorSubsystem();
        }
        
        return instance;
    }
}

