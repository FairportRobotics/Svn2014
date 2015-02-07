/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fairportrobotics.jemo2014.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author Tyler
 */
public class TargetsFoundCommand extends CommandBase {

    public boolean run = true;

    public TargetsFoundCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(visionSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {

        
        
        visionSubsystem.findTargets();

        SmartDashboard.putNumber("Number Of Horz", visionSubsystem.getNumberOfHorizontalTargets());
        SmartDashboard.putNumber("Number Of Vert", visionSubsystem.getNumberOfVerticleTargets());

    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
