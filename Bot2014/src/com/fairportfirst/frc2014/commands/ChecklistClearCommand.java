/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fairportfirst.frc2014.commands;

import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author Tyler
 */
public class ChecklistClearCommand extends CommandBase {
    
    public ChecklistClearCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        SmartDashboard.putBoolean("Is battery charged?", false);
        SmartDashboard.putBoolean("Are the bumpers attached?", false);
        SmartDashboard.putBoolean("Has the robot been tested?", false);
        SmartDashboard.putBoolean("Is the driver station charged?", false);
        SmartDashboard.putBoolean("Are you in the queuing area?", false);
        //NetworkTable.
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