/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fairportfirst.frc2014.commands.autonomous;

import com.fairportfirst.frc2014.commands.CommandBase;
import com.sun.squawk.platform.SystemEvents;

/**
 *
 * @author Tyler
 */
public class DriveForTimeCommand extends CommandBase {
    
    long time = 0;
    long start = 0;
    
    public DriveForTimeCommand(long time) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(driveSubsystem);
        this.time = time;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        start = System.currentTimeMillis();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        driveSubsystem.driveTank(-1.0, -1.0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (System.currentTimeMillis() - start) <= time;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}