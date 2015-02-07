/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fairportfirst.frc2014.commands;

/**
 *
 * @author Tyler
 */
public class WaitCommand extends CommandBase {
    
    private long startTime = 0;
    private long waitTime = 0;
    
    public WaitCommand(long waitTime) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        this.waitTime = waitTime;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        startTime = System.currentTimeMillis();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (System.currentTimeMillis() - startTime) >= waitTime;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}