package com.fairportfirst.frc2014.commands;

import com.fairportfirst.frc2014.OperMode;
import com.fairportfirst.frc2014.RobotTemplate;

/**
 *
 * @author bradmiller
 */
public class TankDriveCommand extends CommandBase {

    public TankDriveCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(driveSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if (RobotTemplate.getOperMode() == OperMode.normalMode) {
            driveSubsystem.driveTank(oi.getLeftStickY(), oi.getRightStickY());
        }else if(RobotTemplate.getOperMode() == OperMode.demoMode){
            double left = oi.getLeftStickY(),right = oi.getRightStickY();
            
//            left*=left;//Square the left input. Adds precision.
//            right*=right;//Square the right input. Adds precision.
            
            left/=2;//Divide in half. Restricts the speed to half.
            right/=2;//Divide in half. Restricts the speed to half.
            
            driveSubsystem.driveTank(left, right);   
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return driveSubsystem.isDriveEnabled();
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
