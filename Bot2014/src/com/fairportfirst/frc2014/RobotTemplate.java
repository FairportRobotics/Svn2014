/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package com.fairportfirst.frc2014;

import com.fairportfirst.frc2014.commands.CommandBase;
import com.fairportfirst.frc2014.commands.autonomous.AutonomousCommandGroup;
import com.fairportfirst.frc2014.commands.autonomous.DisabledAutonomusCommand;
import com.fairportfirst.frc2014.commands.autonomous.TwoBallAutonomusCommand;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class RobotTemplate extends IterativeRobot {

    Command autonomousCommand;
    SendableChooser auto;
    private static SendableChooser operMode;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        // instantiate the command used for the autonomous period

        auto = new SendableChooser();

        auto.addDefault("One Ball Auto", AutonomusMode.oneBall);
        auto.addObject("Disabled Auto", AutonomusMode.disabled);
        auto.addObject("Two Ball Auto", AutonomusMode.twoBall);

        SmartDashboard.putData("Autonomus Mode", auto);

        operMode = new SendableChooser();

        operMode.addDefault("Normal Mode", OperMode.normalMode);
        operMode.addObject("Demo Mode", OperMode.demoMode);

        SmartDashboard.putData("Operation Mode", operMode);

        // Initialize all subsystems
        CommandBase.init();



    }

    public static DriveMode getDriveMode() {
        return DriveMode.tankDrive;
    }

    public static OperMode getOperMode() {
        return (OperMode) operMode.getSelected();
    }

    public void autonomousInit() {
        // schedule the autonomous command (example)

        if (auto.getSelected() == AutonomusMode.oneBall) {
            autonomousCommand = new AutonomousCommandGroup();
        } else if (auto.getSelected() == AutonomusMode.twoBall) {
            autonomousCommand = new TwoBallAutonomusCommand();
        } else if (auto.getSelected() == AutonomusMode.disabled) {
            //autonomousCommand = new DisabledAutonomusCommand();
        }

        autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.

        if (autonomousCommand != null) {
            autonomousCommand.cancel();
        }
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }

    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
