package com.fairportfirst.frc2014.templates.commands;

/**
 *
 * @author Mister Mitchell "Mitch" John Halley, Freshman
 */
public class WinchStopCommand extends CommandBase {
    
    public WinchStopCommand() {
        requires(newWinchSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        newWinchSubsystem.stopWinch();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
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