package org.usfirst.frc.team1245.robot;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.MjpegServer;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team1245.robot.commands.GyroDrive;
import org.usfirst.frc.team1245.robot.commands.LeftSwitch;
import org.usfirst.frc.team1245.robot.commands.RightSwitch;
import org.usfirst.frc.team1245.robot.subsystems.ArmElevator;
import org.usfirst.frc.team1245.robot.subsystems.Drivetrain;
import org.usfirst.frc.team1245.robot.subsystems.Gripper;
import org.usfirst.frc.team1245.robot.subsystems.ReleaseSolenoid;


public class Robot extends IterativeRobot{
	
	SendableChooser robotPosition;
	
    public static OI oi;
    public static Drivetrain drivetrain = new Drivetrain(RobotMap.getFrontLeft(), RobotMap.getFrontRight(),
            RobotMap.getRearLeft(), RobotMap.getRearRight());
    public static Gripper gripper = new Gripper(RobotMap.getLeftArmOfZanathyr(), RobotMap.getRightArmOfZanathyr());
    public static ArmElevator elevator = new ArmElevator(RobotMap.getForwardLifterChannel(), RobotMap.getReverseLifterChannel());
    		//new ArmElevator(RobotMap.getForwardLifterChannel(), RobotMap.getReverseLifterChannel(), RobotMap.getForwardLifterChannel2(), RobotMap.getReverseLifterChannel2());
    public static ReleaseSolenoid releaseSolenoid = new ReleaseSolenoid(RobotMap.getReleaseSolenoidChannel());
    private Command autonomousCommand;
    private Compressor compressor;
    

    private Thread visionThread;
    private Mat output;
    private UsbCamera grabberCamera;
    private MjpegServer outputServer;
    private CvSink cvSink;
    
    private Side robotSide;
    private static Side dumpSide;

    public static Side getDumpSide() {
    	return dumpSide;
    }
    //Spin up the bot!
    @Override
    public void robotInit(){
    	//compressor = new Compressor(RobotMap.getCompressorChannel());
        //Initialize the input/output
        oi = new OI();
        //default dumpSide is left;
        dumpSide = Side.Left;
        //initialize compressor
        //compressor = new Compressor(RobotMap.getCompressorChannel());
        //Initialize camera stuff
        /*new Thread(() ->  {
        	grabberCamera = CameraServer.getInstance().startAutomaticCapture();
        	grabberCamera.setResolution(640,480);
        	
        	cvSink = CameraServer.getInstance().getVideo();
        	CvSource outputStream = CameraServer.getInstance().putVideo("Blur",
        			640, 480);
        	
        	Mat source = new Mat();
        	output = new Mat();
        	
        	while(!Thread.interrupted()) {
        		cvSink.grabFrame(source);
        		output = source;
        		outputStream.putFrame(output);
        	}
        }).start();*/
        
        //add stuff to SmartDashboard for auto code
        /*robotPosition = new SendableChooser();
        robotPosition.addDefault("Center Position", new GyroDrive(this.getDumpSide(), Side.Center));
        robotPosition.addObject("Left", new GyroDrive(this.getDumpSide(), Side.Left));
        robotPosition.addObject("Right", new GyroDrive(this.getDumpSide(), Side.Right));
        SmartDashboard.putData(robotPosition);*/
    }

    @Override
    public void disabledPeriodic(){
        Scheduler.getInstance().run();
    }

    @Override
    public void autonomousInit(){
    	String gameData;
		gameData = DriverStation.getInstance().getGameSpecificMessage();
          if(gameData.length() > 0) {
        	  if(gameData.charAt(0) == 'L') {
        		  //Put left auto code here
        		  dumpSide = Side.Left;
        	  } else {
        		  //Put right auto code here
        		  dumpSide = Side.Right;
		  	}
          }
          
        if(dumpSide == Side.Left) {
        	autonomousCommand = new LeftSwitch();
        } else {
        	autonomousCommand = new RightSwitch();
        }
        //autonomousCommand = new GyroDrive();
        if (autonomousCommand != null){
            autonomousCommand.start();
        }
    }

    @Override
    public void autonomousPeriodic(){
        Scheduler.getInstance().run();
    }

    @Override
    public void teleopInit(){
        //cancels autonomous command when teleop period starts
        if (autonomousCommand != null){
            autonomousCommand.cancel();
        }
        //compressor.start();
    }

    @Override
    public void disabledInit(){
        //called when you disable the bot
        //maybe at the end of the match?\
    	//compressor.stop();
    }

    @Override
    public void teleopPeriodic(){
        //called periodically during teleop
        Scheduler.getInstance().run();
    }

    @Override
    public void testPeriodic(){
        // this is called.... during the test mode!
        //LiveWindow.run(); and this is deprecated and we don't use test mode
    }
}