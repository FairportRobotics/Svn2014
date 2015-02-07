/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fairportfirst.frc2014.commands.autonomous;

import com.fairportfirst.frc2014.RobotMap;
import com.fairportfirst.frc2014.commands.BoomExtendCommand;
import com.fairportfirst.frc2014.commands.ExtendBoomCommand;
import com.fairportfirst.frc2014.commands.FindTargetsCommand;
import com.fairportfirst.frc2014.commands.WaitCommand;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 * @author Brian
 */
public class AutonomousCommandGroup extends CommandGroup {
    
    public AutonomousCommandGroup() {
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
        
        addSequential(new WaitCommand(1250));
        addSequential(new FindTargetsCommand(), RobotMap.AUTONOMUS_FIND_TIMEOUT);
        addSequential(new ExtendBoomCommand());
        addSequential(new DriveToWallCommand());
        addSequential(new WaitCommand(500));
        addSequential(new FireHotGoalCommand());
    }
}
