/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fairportfirst.frc2014.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author Tyler
 */
public class DebugCommand extends CommandBase {
    
    private double lowestVoltage = 0.0;
    
    public DebugCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(debugSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        
        //Begin Voltage Debug
        
        if(lowestVoltage <= DriverStation.getInstance().getBatteryVoltage()){
            lowestVoltage = DriverStation.getInstance().getBatteryVoltage();
        }
        
        SmartDashboard.putNumber("Lowest Voltage", lowestVoltage);
        //End Voltage Debug
        
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}