package org.usfirst.frc.team1245.robot;

import edu.wpi.cscore.CvSource;

public class RobotMap {
	//Driver selection for different controllers
	private static final Driver driver = Driver.Cooper;
	
	public static Driver getDriver() {
		return driver;
	}
	
	//LED PCM port
	private static final int led = 0;
	
    // Talon SRX channels
    private static final int frontLeft = 4;
    private static final int rearLeft = 3;
    private static final int frontRight = 1;
    private static final int rearRight = 2;

    private static final int leftArmOfZanathyr = 0;
    private static final int rightArmOfZanathyr = 1;

    private static final int forwardLifterChannel = 1;
    private static final int reverseLifterChannel = 2;
    private static final int releaseSolenoidChannel = 3;
    
    //private static final int forwardLifterPressureChannel = 2;
    //private static final int reverseLifterPressureChannel = 3;
    //private static final int forwardLifterChannel2 = 2;
    //private static final int reverseLifterChannel2 = 0;
    //private static final int forwardLifterPressureChannel2 = 6;
    //private static final int reverseLifterPressureChannel2 = 7;

    //Output stream for camera on dashboard
    private static CvSource cameraOutputStream;
    
    private static final int gyroChannel = 0;

    //Deadzone values, don't set them to 1;
    private static final double translationalDeadZone = 0.1;
    private static final double rotationalDeadZone = 0.1;
    private static final double gyroDeadZone = .25;
    
    
   private static final int compressorChannel = 7; //check if works
    
    public static int getCompressorChannel() {
    	return compressorChannel;
    }
    
    public static double getGyroDeadZone() {
    	return gyroDeadZone;
    }
    
    public static int getLED() {
    	return led;
    }

    public static int getFrontLeft() {
        return frontLeft;
    }

    public static int getRearLeft() {
        return rearLeft;
    }

    public static int getFrontRight() {
        return frontRight;
    }

    public static int getRearRight() {
        return rearRight;
    }

    public static int getLeftArmOfZanathyr() {
        return leftArmOfZanathyr;
    }

    public static int getRightArmOfZanathyr() {
        return rightArmOfZanathyr;
    }

    public static int getForwardLifterChannel() { return forwardLifterChannel; }

    public static int getReverseLifterChannel() { return reverseLifterChannel; }
    
    public static int getReleaseSolenoidChannel() {
    	return releaseSolenoidChannel;
    }
    
    public static int getGyroChannel() {
    	return gyroChannel;
    }

    //public static int getForwardLifterPressureChannel() { return forwardLifterPressureChannel; }

    //public static int getReverseLifterPressureChannel() { return reverseLifterPressureChannel; }
    
    //public static int getForwardLifterChannel2() { return forwardLifterChannel2; }

    //public static int getReverseLifterChannel2() { return reverseLifterChannel2; }

    //public static int getForwardLifterPressureChannel2() { return forwardLifterPressureChannel2; }

    //public static int getReverseLifterPressureChannel2() { return reverseLifterPressureChannel2; }

    public static CvSource getCameraOutputStream() {
        return cameraOutputStream;
    }

    public static double getTranslationalDeadZone() {
        if (translationalDeadZone == 1) {
            return .99;
        }
        return translationalDeadZone;
    }
    public static double getRotationalDeadZone(){
        if (rotationalDeadZone == 1){
            return .99;
        }
        return rotationalDeadZone;
    }
}