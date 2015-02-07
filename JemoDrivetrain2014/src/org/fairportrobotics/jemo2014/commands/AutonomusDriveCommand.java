/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fairportrobotics.jemo2014.commands;

/**
 *
 * @author Tyler
 */
public class AutonomusDriveCommand extends CommandBase {
    
    public AutonomusDriveCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(driveSubsystem);
        requires(ultrasonicSubsystem);
        
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        int counter = 10;
        
        long startTime;
        
        ultrasonicSubsystem.relayOff();
        
        while(counter >= 1){
            
            startTime = System.currentTimeMillis();
            
            driveSubsystem.drive(-0.2, -0.2, 0, 0);
            System.out.println("Driving Forward!!!!!" + ultrasonicSubsystem.distanceInches());
            if(ultrasonicSubsystem.distanceInches()<=48)
            {
                counter--;
                
                System.out.println("In Range!!!!!");
            }else{
                counter= 3;
            }
            
            System.out.println("Time:" + (System.currentTimeMillis() - startTime));
            
        }
        
        ultrasonicSubsystem.relayOn();
        
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