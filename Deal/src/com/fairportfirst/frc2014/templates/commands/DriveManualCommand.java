package com.fairportfirst.frc2014.templates.commands;

import com.fairportfirst.frc2014.templates.DriveMode;
import com.fairportfirst.frc2014.templates.OI;

/**
 *
 * @author Yaro
 */
public class DriveManualCommand extends CommandBase {
    
    public DriveManualCommand() {
        requires(driveSubsystem);
    }

    protected void initialize() {
    }

    protected void execute() {
        if(getDriveMode()==DriveMode.LINEAR){
            driveSubsystem.drive(OI.getLeftDrive(), OI.getRightDrive());
        }else if(getDriveMode()==DriveMode.RAMPED){
            driveSubsystem.drive(OI.getLeftOptimizedDrive(), OI.getRightOptimizedDrive());
        }else if(getDriveMode()==DriveMode.SQUARE){
            driveSubsystem.drive(OI.getLeftSimboticsDrive(),OI.getRightSimboticsDrive());
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }
    
    protected void interrupted() {
        
    }
}
