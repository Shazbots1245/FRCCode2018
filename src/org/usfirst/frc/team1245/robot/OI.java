package org.usfirst.frc.team1245.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;

public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);

    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // robot.commands the same as any other Button.

    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:

    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());

    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());

    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());


    public static XboxController driverPad;
    public static Joystick driverStick;


    public OI(){
        //Initialize Joysticks and Controllers here
    	if(RobotMap.getDriver()==Driver.Cooper) {
    		driverPad = new XboxController(0);
    	} else if (RobotMap.getDriver()==Driver.Justin) {
        driverStick = new Joystick(0);
    	} else {
    		driverPad = new XboxController(0);
    	}
        //left joystick 'true' movement
        //right joystick rotational movement
    }

    //Dead Zone Function
    public static double deadZone (double val, double deadZone){
        if (Math.abs(val) > deadZone){
            if (val > 0){
                return (val - deadZone) / (1 - deadZone);
            } else {
                return -(-val - deadZone) / (1 - deadZone);
            }
        }
        return 0;
    }
    public static double scaleSpeed(double val, double scale){
        return val*scale;
    }
}
