/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fairportfirst.frc2014.commands;

/**
 *
 * @author Brian
 */
public class BoomSpinCommand extends CommandBase {

    private int time = 0;

    private long startTime = 0;

    private int dir = 0;
    
    public BoomSpinCommand(int timeOut, int dir) {
        this.time = timeOut;
        this.dir = dir;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        startTime = System.currentTimeMillis();

        if (time == 0) {
            if (oi.isBoomSpinButtonDown()) {
                boomSubsystem.spin();
            } else {
                boomSubsystem.stopSpin();
            }
        } else {
            
            if(dir == 0){
               boomSubsystem.spin(); 
            }else{
                boomSubsystem.spinReverse();
            }
            
            
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        boolean stop = false;

        if (time == 0) {
            stop = !oi.isBoomSpinButtonDown();
        } else {
            if (System.currentTimeMillis() - startTime >= time) {
                stop = true;
            }
        }

        if (stop) {
            boomSubsystem.stopSpin();
        }

        return stop;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        boomSubsystem.stopSpin();
    }
}
