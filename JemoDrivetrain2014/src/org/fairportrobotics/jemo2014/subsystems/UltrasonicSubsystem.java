/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fairportrobotics.jemo2014.subsystems;

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.fairportrobotics.jemo2014.RobotMap;
import org.fairportrobotics.jemo2014.commands.ReadUltrasonicCommand;

/**
 *
 * @author chris
 */
public class UltrasonicSubsystem extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
    AnalogChannel ultraSonic;
    Relay relay;
    static UltrasonicSubsystem instance;
    
    public UltrasonicSubsystem()
    {
        ultraSonic = new AnalogChannel(RobotMap.ULTRASONIC);
        relay = new Relay(1);
        relay.setDirection(Relay.Direction.kForward);
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
        setDefaultCommand(new ReadUltrasonicCommand());
    }
    
    public double distanceInches()
    {
        return ultraSonic.getVoltage() / 0.0098;
    }
    
    public double distanceFeet()
    {
        return distanceInches()/12;
    }
    
    public double distanceMeters()
    {
        return distanceFeet()*0.3048;
    }
    
    
    public void relayOn()
    {
        relay.set(Relay.Value.kOn);
    }
    
    public void relayOff()
    {
        relay.set(Relay.Value.kOff);
    }
    
    public static UltrasonicSubsystem getInstance()
    {
        if(instance == null)
        {
            instance = new UltrasonicSubsystem();
        }
        
        return instance;
    }
}
