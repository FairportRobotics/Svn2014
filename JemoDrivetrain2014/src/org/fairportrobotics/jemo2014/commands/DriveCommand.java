package org.fairportrobotics.jemo2014.commands;

/**
 *
 * @author bradmiller
 */
public class DriveCommand extends CommandBase {

    public DriveCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(driveSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        driveSubsystem.drive(oi.getLeftstickY(), oi.getRightstickY(), -oi.getLeftstickX(), -oi.getRightstickX());
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
