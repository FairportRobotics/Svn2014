/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fairportfirst.frc2014.subsystems;

import com.fairportfirst.frc2014.RobotMap;
import com.fairportfirst.frc2014.commands.FindTargetsCommand;
import edu.wpi.first.wpilibj.Dashboard;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.camera.AxisCamera;
import edu.wpi.first.wpilibj.camera.AxisCameraException;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.image.BinaryImage;
import edu.wpi.first.wpilibj.image.ColorImage;
import edu.wpi.first.wpilibj.image.CriteriaCollection;
import edu.wpi.first.wpilibj.image.NIVision;
import edu.wpi.first.wpilibj.image.NIVisionException;
import edu.wpi.first.wpilibj.image.ParticleAnalysisReport;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author Tyler
 */
public class VisionSubsystem extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public static final int THREAD_SLEEP_TIME = 500;
    static final int IMAGE_HEIGHT = 320;
    static final int IMAGE_WIDTH = 240;
    //color value ranges
    static final int RED_MIN = 0;
    static final int RED_MAX = 10;
    static final int GREEN_MIN = 150;
    static final int GREEN_MAX = 255;
    static final int BLUE_MIN = 0;
    static final int BLUE_MAX = 255;
    
    static final int HUE_MIN = 49;
    static final int HUE_MAX = 141;
    static final int SAT_MIN = 200;
    static final int SAT_MAX = 255;
    static final int VAL_MIN = 150;
    static final int VAL_MAX = 255;
    
    
    static VisionSubsystem instance; 
    AxisCamera camera;
    NIVision vision;
    ColorImage image;
    CriteriaCollection cc;
    int numVertTargets = 0;
    int numHorzTargets = 0;
    int loopCounter = 0;
    private Target[] horzTargets = new Target[1];
    private Target[] vertTargets = new Target[1];
    private int runTime = 0;
    private boolean atLeastOneHorzFound = false;

    public VisionSubsystem() {
        camera = AxisCamera.getInstance(RobotMap.CAMERA_IP);
        camera.writeResolution(AxisCamera.ResolutionT.k320x240);
        //camera.writeBrightness(0);
        //camera.writeColorLevel(100);
        cc = new CriteriaCollection();
        cc.addCriteria(NIVision.MeasurementType.IMAQ_MT_AREA, 150, 65535, false);
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
        //setDefaultCommand(new FindTargetsCommand());
    }

    public class Target {

        int x, y;

        public Target(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }

    public void findTargets() {

        try {
            //numHorzTargets = 0;
            numVertTargets = 0;

            image = camera.getImage();

            //BinaryImage thresholdImage = image.thresholdRGB(RED_MIN, RED_MAX, GREEN_MIN, GREEN_MAX, BLUE_MIN, BLUE_MAX);
            //BinaryImage thresholdImage = image.thresholdHSL(105, 137, 230, 255, 133, 183);
            BinaryImage thresholdImage = image.thresholdHSV(HUE_MIN, HUE_MAX, SAT_MIN, SAT_MAX, VAL_MIN, VAL_MAX);

            BinaryImage filteredImage = thresholdImage.particleFilter(cc);

            if (runTime % 10 == 0) {
                image.write("/Frame Raw" + runTime / 10 + ".png");
                thresholdImage.write("/Frame Threshold"+ runTime / 10 + ".png");
            }


            if (filteredImage.getNumberParticles() > 0) {

                for (int i = 0; i < filteredImage.getNumberParticles(); i++) {

                    ParticleAnalysisReport par = filteredImage.getParticleAnalysisReport(i);

                    if (par.boundingRectHeight < par.boundingRectWidth) {

                        numHorzTargets++;

                        SmartDashboard.putNumber("Num Horizantal", numHorzTargets);

                        atLeastOneHorzFound = true;
                        SmartDashboard.putNumber("WE FOUND VISION!", DriverStation.getInstance().getMatchTime());

//                        if (horzTargets[i] == null) {
//                            horzTargets[i] = new Target(par.center_mass_x, par.center_mass_y);
//                        } else if (horzTargets[i] != null) {
//
//                            Target[] tempHorzTargets = horzTargets;
//
//                            horzTargets = new Target[i + 1];
//
//                            for (int z = 0; z < tempHorzTargets.length; z++) {
//                                horzTargets[z] = tempHorzTargets[z];
//                            }
//
//                            horzTargets[i + 1] = new Target(par.center_mass_x, par.center_mass_y);
//
//                        }

                    } else if (par.boundingRectWidth < par.boundingRectHeight) {

                        numVertTargets++;
//                        if (vertTargets[i] == null) {
//                            vertTargets[i] = new Target(par.center_mass_x, par.center_mass_y);
//                        } else if (vertTargets[i] != null) {
//
//                            Target[] tempVertTargets = vertTargets;
//
//                            vertTargets = new Target[i + 1];
//
//                            for (int z = 0; z < tempVertTargets.length; z++) {
//                                horzTargets[z] = tempVertTargets[z];
//                            }
//
//                            vertTargets[i + 1] = new Target(par.center_mass_x, par.center_mass_y);
//
//                        }
                    }
                }
            }

            filteredImage.free();
            thresholdImage.free();
            image.free();

        } catch (AxisCameraException ex) {
            ex.printStackTrace();
            DriverStationLCD.getInstance().println(DriverStationLCD.Line.kUser1, 0, "WE HAVE A CAMERA ERROR!!!!!");
        } catch (NIVisionException ex) {
            ex.printStackTrace();
        }

        runTime++;

    }

    public void resetVision(){
        atLeastOneHorzFound = false;
        numHorzTargets = 0;
        numVertTargets = 0;
        
    }
    
    public Target[] getVertTargets() {
        return vertTargets;
    }

    public int getNumberOfVerticleTargets() {
        return numVertTargets;
    }

    public Target[] getHorzTargets() {
        return horzTargets;
    }

    public int getNumberOfHorizontalTargets() {
        return numHorzTargets;
    }

    public boolean atLeastOneHorzFound() {
        return atLeastOneHorzFound;
    }

    public static VisionSubsystem getInstance() {

        if (instance == null) {
            instance = new VisionSubsystem();
        }

        return instance;
    }
}
