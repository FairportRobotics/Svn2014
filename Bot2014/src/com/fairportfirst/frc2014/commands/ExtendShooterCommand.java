/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fairportfirst.frc2014.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author user
 */
public class ExtendShooterCommand extends CommandBase {
    
    private int numPistons = 4;
    
    private boolean safty = false;
    
    public ExtendShooterCommand(int numPistons, boolean safty) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        
        this.numPistons = numPistons;
        this.safty = safty;
        
        requires(shooterSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        
        if(safty){
            if(oi.isShootButtonDown() && oi.isShootButton2Down()){
                shooterSubsystem.extend(numPistons);
            }
        }else{
            shooterSubsystem.extend(numPistons);
        }
        
        
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
