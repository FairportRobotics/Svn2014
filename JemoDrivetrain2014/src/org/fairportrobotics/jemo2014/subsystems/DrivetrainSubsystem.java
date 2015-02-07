
package org.fairportrobotics.jemo2014.subsystems;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.fairportrobotics.jemo2014.RobotMap;
import org.fairportrobotics.jemo2014.commands.DriveCommand;

/**
 *
 */
public class DrivetrainSubsystem extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    static DrivetrainSubsystem instance;
    Victor frontleft;
    Victor frontright;
    Victor backleft;
    Victor backright;
    
    public DrivetrainSubsystem()
    {
        
         frontleft = new Victor(RobotMap.FRONT_LEFT_MOTOR);
         frontright = new Victor(RobotMap.FRONT_RIGHT_MOTOR);
         backleft = new Victor(RobotMap.BACK_LEFT_MOTOR);
         backright = new Victor(RobotMap.BACK_RIGHT_MOTOR);
         
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
        setDefaultCommand(new DriveCommand());
    }
    
    public void drive(double leftY, double rightY, double leftX, double rightX)
    {
        
        if(leftX < 0.3 && leftX > -0.3){
            frontleft.set(-leftY);
        
            backleft.set(-leftY);
        }else{
            frontleft.set(leftX);
            backleft.set(-leftX);
        }
        
        if(rightX < 0.3 && rightX > -0.3){
            frontright.set(rightY);
            
            backright.set(rightY);
        }else{
            frontright.set(rightX);
            backright.set(-rightX);
        }
    }
    
    public void driveForward(double speed){
        drive(speed, speed, 0, 0);
    }
    
    public void driveBackward(double speed){
        drive(-speed, -speed, 0, 0);
    }
    
    public void strafeLeft(double speed){
        drive(0, 0, speed, speed);
    }
    
    public void strafeRight(double speed){
        drive(0, 0, -speed, -speed);
    }
    
    public static DrivetrainSubsystem getInstance()
    {
        if(instance==null)
        {
            instance = new DrivetrainSubsystem();
        }
        return instance;
    }
}

