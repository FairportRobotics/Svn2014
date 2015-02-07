/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fairportrobotics.jemo2014.commands.autonomus.catchball;

import org.fairportrobotics.jemo2014.commands.CommandBase;
import org.fairportrobotics.jemo2014.subsystems.VisionSubsystem.Target;

/**
 *
 * @author Tyler
 */
public class CatchBallDriveCommand extends CommandBase {
    
    public CatchBallDriveCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(driveSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        
        for(int i = 0; i<visionSubsystem.getTargets().length;i++){
            
            Target t = visionSubsystem.getTargets()[i];
            
            if(t.getXPos() < 0){
                driveSubsystem.strafeLeft(t.getXPos());
            }else if(t.getXPos() > 0){
                driveSubsystem.strafeRight(t.getXPos());
            }
            
            if(t.getYPos() < 0){
                driveSubsystem.driveBackward(t.getYPos());
            }else if(t.getYPos() > 0){
                driveSubsystem.driveForward(t.getYPos());
            }
            
        }
        
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
