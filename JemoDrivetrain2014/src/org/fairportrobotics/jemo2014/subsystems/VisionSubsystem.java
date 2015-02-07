/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fairportrobotics.jemo2014.subsystems;

import edu.wpi.first.wpilibj.camera.AxisCamera;
import edu.wpi.first.wpilibj.camera.AxisCameraException;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.image.BinaryImage;
import edu.wpi.first.wpilibj.image.ColorImage;
import edu.wpi.first.wpilibj.image.CriteriaCollection;
import edu.wpi.first.wpilibj.image.NIVision;
import edu.wpi.first.wpilibj.image.NIVisionException;
import edu.wpi.first.wpilibj.image.ParticleAnalysisReport;
import edu.wpi.first.wpilibj.networktables2.util.List;
import org.fairportrobotics.jemo2014.RobotMap;
import org.fairportrobotics.jemo2014.commands.TargetsFoundCommand;

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
    static final int GREEN_MIN = 200;
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

    Target[] targets = new Target[0];
    
    public class Target{
        
        private double x = 0,y = 0;
       
        private double height = 0, width = 0;
        
        public Target(double x, double y, double height, double width){
            this.x = x;
            this.y = y;
            this.height = height;
            this.width = width;
        }
        
        public boolean isHorizontal(){
            return width < height;
        }
        
        public boolean isVertical(){
            return height < width;
        }
        
        public double getArea(){
            return height*width;
        }
        
        public double getNormalizedArea(){
            return getNormalizedHeight() * getNormalizedWidth();
        }
        
        public double getHeight(){
            return height;
        }
        
        public double getWidth(){
            return width;
        }
        
        public double getNormalizedHeight(){
            return height/IMAGE_HEIGHT;
        }
        
        public double getNormalizedWidth(){
            return width/IMAGE_WIDTH;
        }
        
        public double getXPos(){
            return x;
        }
        
        public double getYPos(){
            return y;
        }
        
    }
    
    public VisionSubsystem() {
        camera = AxisCamera.getInstance(RobotMap.CAMER_IP);
        camera.writeResolution(AxisCamera.ResolutionT.k320x240);
        //camera.writeBrightness(0);
        //camera.writeColorLevel(100);
        cc = new CriteriaCollection();
        cc.addCriteria(NIVision.MeasurementType.IMAQ_MT_AREA, 150, 65535, false);
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
        setDefaultCommand(new TargetsFoundCommand());
    }

    public void findTargets() {

        try {
            numHorzTargets = 0;
            numVertTargets = 0;

            image = camera.getImage();

            //BinaryImage thresholdImage = image.thresholdRGB(RED_MIN, RED_MAX, GREEN_MIN, GREEN_MAX, BLUE_MIN, BLUE_MAX);
            //BinaryImage thresholdImage = image.thresholdHSL(105, 137, 230, 255, 133, 183);
            BinaryImage thresholdImage = image.thresholdHSV(HUE_MIN, HUE_MAX, SAT_MIN, SAT_MAX, VAL_MIN, VAL_MAX);

            BinaryImage filteredImage = thresholdImage.particleFilter(cc);

            if (filteredImage.getNumberParticles() > 0) {
                
                targets = new Target[filteredImage.getNumberParticles()];

                for (int i = 0; i < filteredImage.getNumberParticles(); i++) {

                    ParticleAnalysisReport par = filteredImage.getParticleAnalysisReport(i);
                    
                    targets[i] = new Target(par.center_mass_x_normalized, par.center_mass_y_normalized, par.boundingRectHeight, par.boundingRectWidth);
                }

            }
            else
            {
                targets = null;
            }

            filteredImage.free();
            thresholdImage.free();
            image.free();

        } catch (AxisCameraException ex) {
            ex.printStackTrace();
        } catch (NIVisionException ex) {
            ex.printStackTrace();
        }

    }
    
    public Target[] getTargets(){
        return targets;
    }

    public int getNumberOfVerticleTargets() {
        return numVertTargets;
    }

    public int getNumberOfHorizontalTargets() {
        return numHorzTargets;
    }

    public static VisionSubsystem getInstance() {

        if (instance == null) {
            instance = new VisionSubsystem();
        }

        return instance;
    }
}
