/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fairportfirst.frc2014.subsystems;

import com.fairportfirst.frc2014.commands.ChecklistClearCommand;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 * @author Tyler
 */
public class CheckListSubsystem extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public CheckListSubsystem(){
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
        setDefaultCommand(new ChecklistClearCommand());
    }
    
    
}