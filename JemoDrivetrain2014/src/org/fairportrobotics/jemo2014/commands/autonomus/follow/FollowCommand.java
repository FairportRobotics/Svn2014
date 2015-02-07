/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fairportrobotics.jemo2014.commands.autonomus.follow;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.fairportrobotics.jemo2014.commands.FindTargetsCommand;

/**
 *
 * @author Tyler
 */
public class FollowCommand extends CommandGroup {
    
    public FollowCommand() {
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
        
        addParallel(new FindTargetsCommand());
        addParallel(new FollowDriveCommand());
        
    }
}
