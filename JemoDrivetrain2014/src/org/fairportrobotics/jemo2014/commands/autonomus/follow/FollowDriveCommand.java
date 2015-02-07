/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fairportrobotics.jemo2014.commands.autonomus.follow;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.fairportrobotics.jemo2014.commands.CommandBase;
import org.fairportrobotics.jemo2014.subsystems.VisionSubsystem.Target;

/**
 *
 * @author Tyler
 */
public class FollowDriveCommand extends CommandBase {
    
    double startArea = 0;
    
    public FollowDriveCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(driveSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        
        Target[] targs = visionSubsystem.getTargets();
        double currentArea = 0;
        double speed = 0;
        int numTargs = 0;
        double targX = 0;
        
        
        if (targs != null && targs.length > 0)
        {
            numTargs = targs.length;
            Target targ = visionSubsystem.getTargets()[0];
            
            if(startArea == 0){
                startArea = targ.getNormalizedArea();
            }
            
            currentArea = targ.getNormalizedArea();
            speed = (currentArea - startArea) / startArea;
            speed /= -4;
            
            if (startArea >= currentArea)
            {
                speed *= 1.5;
            }
            else
            {
                speed *= 1;
            }
            
            targX = targ.getXPos();
            targX *= 0.5;
            
            
        }
        else
        {
            startArea = 0;
            
        }
            
            
            SmartDashboard.putNumber("numTargets", numTargs);
            SmartDashboard.putNumber("startArea", startArea);
            SmartDashboard.putNumber("currentArea", currentArea);
            SmartDashboard.putNumber("speed", speed);
            
            driveSubsystem.drive(speed - targX, speed + targX, 0, 0);
            
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
