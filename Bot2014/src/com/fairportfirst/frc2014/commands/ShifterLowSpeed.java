/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fairportfirst.frc2014.commands;

/**
 *
 * @author Tyler
 */
public class ShifterLowSpeed extends CommandBase {
    
    private long startTime = 0;
    
    public ShifterLowSpeed() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(driveSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        
        if(startTime == 0){
            startTime = System.currentTimeMillis();
        }
        
        driveSubsystem.setDriveEnabled(false);
        driveSubsystem.setShifterLowSpeed();
        
        if(timeSinceInitialized() >= 0.75){
            new TankDriveCommand().start();
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return !driveSubsystem.isHighSpeed();
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}