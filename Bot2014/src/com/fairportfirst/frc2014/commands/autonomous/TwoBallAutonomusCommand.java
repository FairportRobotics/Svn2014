/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fairportfirst.frc2014.commands.autonomous;

import com.fairportfirst.frc2014.RobotMap;
import com.fairportfirst.frc2014.commands.BoomExtendCommand;
import com.fairportfirst.frc2014.commands.BoomRetractCommand;
import com.fairportfirst.frc2014.commands.BoomSpinCommand;
import com.fairportfirst.frc2014.commands.ExtendBoomCommand;
import com.fairportfirst.frc2014.commands.ExtendShooterCommand;
import com.fairportfirst.frc2014.commands.RetractShooterCommand;
import com.fairportfirst.frc2014.commands.ShootCommand;
import com.fairportfirst.frc2014.commands.ToggleBoomCommand;
import com.fairportfirst.frc2014.commands.WaitCommand;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 * @author Tyler
 */
public class TwoBallAutonomusCommand extends CommandGroup {
    
    public TwoBallAutonomusCommand() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.
        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
        
        addSequential(new ExtendBoomCommand());
        addSequential(new DriveToWallCommand());
        addSequential(new WaitCommand(500));
        addSequential(new ShootCommand(false));
        addSequential(new LoadBallCommand());
        //addSequential(new ExtendBoomCommand());
        addSequential(new DriveForTimeCommand(250));
        addSequential(new WaitCommand(2500));
        addSequential(new ShootCommand(false));
        
    }
}
