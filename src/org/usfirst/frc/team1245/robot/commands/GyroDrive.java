package org.usfirst.frc.team1245.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.Timer;
import org.usfirst.frc.team1245.robot.Robot;
import org.usfirst.frc.team1245.robot.RobotMap;
import org.usfirst.frc.team1245.robot.Side;

/**
 * Created by Zelie on 2/5/18.
 */
public class GyroDrive extends Command {

    private Gyro gyro;
    private Timer timer;
    private boolean finished = false;
    private Side sideSwitch, sideRobot;
    
    private double angle, kP;

    public GyroDrive(Side sideSwitch, Side sideRobot) {
        requires(Robot.drivetrain);

        //timer = new Timer();
        this.sideSwitch = sideSwitch;
        this.sideRobot = sideRobot;
        if(this.sideSwitch != Robot.getDumpSide()) {
        	this.sideSwitch = Robot.getDumpSide();
        	//look up how to print to console if it this happens
        }
       
    }
    
    public GyroDrive() {
    	new GyroDrive(Side.Right, Side.Center); //because crashing because we don't have a default sucks
    }

    protected void initialize() {
    	timer = new Timer();
    	timer.start();
    	gyro = new AnalogGyro(RobotMap.getGyroChannel());
        gyro.reset();
    }

    protected void execute() {
    	//double gyroPosition = gyro.getAngle();
    	//double gyroMultiplier = .05;
    	
    	if(timer.get()<1.5) {
    		Robot.drivetrain.getDrivetrain().driveCartesian(.5, 0, .035);
    	} else if (timer.get()<2.5) {
    		Robot.drivetrain.getDrivetrain().driveCartesian(.2, 0, .035);
    	} else if (timer.get()<3.7) {
    		Robot.drivetrain.getDrivetrain().driveCartesian(0, 0, 0);
    		Robot.gripper.leftArmOfZanathyr.set(0); //-#
    		Robot.gripper.rightArmOfZanathyr.set(0);
    	} else {
    		Robot.gripper.leftArmOfZanathyr.set(0);
    		Robot.gripper.rightArmOfZanathyr.set(0);
    		Robot.drivetrain.getDrivetrain().driveCartesian(0, 0, 0);
    	}
    	/*
    	angle = gyro.getAngle();
    	kP = 0.05;
    	if(this.sideRobot==Side.Center && this.sideSwitch == Side.Right) {//done
    		if(timer.get()<1.0){
    			Robot.drivetrain.getDrivetrain().driveCartesian(0, .4, -angle*kP);
    		} else if(timer.get()<2.45) {//middle, drive up, shoot, back, drop, we don't need to release as it shoots up and into it.
    			Robot.drivetrain.getDrivetrain().driveCartesian(.5, 0, -angle*kP);//0,.5,.03
    		} else if(timer.get()<3.2){
    			Robot.drivetrain.getDrivetrain().driveCartesian(.2, 0, -angle*kP);
    		} else if(timer.get()<4.7){
    			Robot.drivetrain.getDrivetrain().driveCartesian(0, 0, -angle*kP);
    			Robot.gripper.rightArmOfZanathyr.set(0.8);
    			Robot.gripper.leftArmOfZanathyr.set(0.8);
    		} else {
    			Robot.drivetrain.getDrivetrain().driveCartesian(0, 0, -angle*kP);
    			Robot.gripper.rightArmOfZanathyr.set(0);
    			Robot.gripper.leftArmOfZanathyr.set(0);
    		}
    	}
    	
    	else if(this.sideRobot==Side.Center && this.sideSwitch == Side.Left) {//done
    		if(timer.get()<1.0) {
    			Robot.drivetrain.getDrivetrain().driveCartesian(0, -.4, -angle*kP);
    		} else if(timer.get()<2.45) {//middle, drive up, shoot, back, drop, we don't need to release as it shoots up and into it.
    			Robot.drivetrain.getDrivetrain().driveCartesian(.5, 0, -angle*kP);//0,.5,.03
    		} else if(timer.get()<3.2){
    			Robot.drivetrain.getDrivetrain().driveCartesian(.2, 0, -angle*kP);
    		} else if(timer.get()<4.7){
    			Robot.drivetrain.getDrivetrain().driveCartesian(0, 0, -angle*kP);
    			Robot.gripper.rightArmOfZanathyr.set(0.8);
    			Robot.gripper.leftArmOfZanathyr.set(0.8);
    		} else {
    			Robot.drivetrain.getDrivetrain().driveCartesian(0, 0, -angle*kP);
    			Robot.gripper.leftArmOfZanathyr.set(0);
    			Robot.gripper.rightArmOfZanathyr.set(0);
    		}
    	}
    	
    	else if(this.sideRobot==Side.Left && this.sideSwitch == Side.Left) {//add turn right
    		if(timer.get()<1.45) {//middle, drive up, shoot, back, drop, we don't need to release as it shoots up and into it.
    			Robot.drivetrain.getDrivetrain().driveCartesian(.5, 0, -angle*kP);//0,.5,.03
    		} else if(timer.get()<2.2){
    			Robot.drivetrain.getDrivetrain().driveCartesian(.2, 0, -angle*kP); //reaches level with the thing
    		} else if(timer.get()<2.7){
    			Robot.drivetrain.getDrivetrain().driveCartesian(.2, 0, -angle*kP);
    		} else if(timer.get()<3.2) {
    			Robot.drivetrain.getDrivetrain().driveCartesian(0, 0, 90-angle*kP);// turn
    		} else if(timer.get()<4) {
    			Robot.drivetrain.getDrivetrain().driveCartesian(.2, 0, 90-angle*kP);
    		} else if(timer.get()<5) {
    			Robot.drivetrain.getDrivetrain().driveCartesian(0, 0, 90-angle*kP);
    			Robot.gripper.rightArmOfZanathyr.set(.8);
    			Robot.gripper.leftArmOfZanathyr.set(.8);
    		} else {
    			Robot.drivetrain.getDrivetrain().driveCartesian(0, 0, 90-angle*kP);
    			Robot.gripper.rightArmOfZanathyr.set(0);
    			Robot.gripper.leftArmOfZanathyr.set(0);
    		}
    	}
    	else if(this.sideRobot==Side.Left && this.sideSwitch == Side.Right) {//done
    		if(timer.get()<1.45) {//middle, drive up, shoot, back, drop, we don't need to release as it shoots up and into it.
    			Robot.drivetrain.getDrivetrain().driveCartesian(.5, 0, -angle*kP);//0,.5,.03
    		} else if(timer.get()<2.2){
    			Robot.drivetrain.getDrivetrain().driveCartesian(.2, 0, -angle*kP); //reaches level with the thing
    		} else if(timer.get()<2.7){
    			Robot.drivetrain.getDrivetrain().driveCartesian(.2, 0, -angle*kP);
    		} else {
    			Robot.drivetrain.getDrivetrain().driveCartesian(0, 0, -angle*kP);
    		}
    	}
    	
    	else if(this.sideRobot==Side.Right && this.sideSwitch == Side.Left) {//done
    		if(timer.get()<1.45) {//middle, drive up, shoot, back, drop, we don't need to release as it shoots up and into it.
    			Robot.drivetrain.getDrivetrain().driveCartesian(.5, 0, -angle*kP);//0,.5,.03
    		} else if(timer.get()<2.2){
    			Robot.drivetrain.getDrivetrain().driveCartesian(.2, 0, -angle*kP); //reaches level with the thing
    		} else if(timer.get()<2.7){
    			Robot.drivetrain.getDrivetrain().driveCartesian(.2, 0, -angle*kP);
    		} else {
    			Robot.drivetrain.getDrivetrain().driveCartesian(0, 0, -angle*kP);
    		}
    	}
    	else if(this.sideRobot==Side.Right && this.sideSwitch == Side.Right) {//add turn to left and shoot
    		if(timer.get()<1.45) {//middle, drive up, shoot, back, drop, we don't need to release as it shoots up and into it.
    			Robot.drivetrain.getDrivetrain().driveCartesian(.5, 0, -angle*kP);//0,.5,.03
    		} else if(timer.get()<2.2){
    			Robot.drivetrain.getDrivetrain().driveCartesian(.2, 0, -angle*kP); //reaches level with the thing
    		} else if(timer.get()<2.7){
    			Robot.drivetrain.getDrivetrain().driveCartesian(.2, 0, -angle*kP);
    		} else if(timer.get()<3.2) {
    			Robot.drivetrain.getDrivetrain().driveCartesian(0, 0, -90-angle*kP);// turn
    		} else if(timer.get()<4) {
    			Robot.drivetrain.getDrivetrain().driveCartesian(.2, 0, -90-angle*kP);
    		} else if(timer.get()<5) {
    			Robot.drivetrain.getDrivetrain().driveCartesian(0, 0, -90-angle*kP);
    			Robot.gripper.rightArmOfZanathyr.set(.8);
    			Robot.gripper.leftArmOfZanathyr.set(.8);
    		} else {
    			Robot.drivetrain.getDrivetrain().driveCartesian(0, 0, -90-angle*kP);
    			Robot.gripper.rightArmOfZanathyr.set(0);
    			Robot.gripper.leftArmOfZanathyr.set(0);
    		}
    	}
    	*/
    }

    @Override
    protected boolean isFinished() {
        return finished;
    }
}