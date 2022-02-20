// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;


import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;


import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.teleopDrive;


import frc.robot.subsystems.Drivetrain;



/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in
 * the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of
 * the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  
    private final Field2d field2d = new Field2d();

    // subsystems

    private Drivetrain drivetrain = new Drivetrain(field2d);
   
    private Autonomous autoHelper = new Autonomous(drivetrain);
    
    // Joystick objects
    private Joystick joystick = new Joystick(Constants.JOYSTICK_PORT);
    private JoystickButton BOOST = new JoystickButton(joystick, 5);



    /**
     * The container for the robot. Contains subsystems, OI devices, and commands.
     */
    public RobotContainer() {
        // Configure the button bindings
        configureButtonBindings();

 

    }

    /**
     * Use this method to define your button->command mappings. Buttons can be
     * created by
     * instantiating a {@link GenericHID} or one of its subclasses ({@link
     * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing
     * it to a {@link
     * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
     */
    private void configureButtonBindings() {
        drivetrain.setDefaultCommand(new teleopDrive(drivetrain, () -> joystick.getRawAxis(Constants.LEFT_AXIS),
                () -> joystick.getRawAxis(Constants.RIGHT_AXIS), ()-> false));
        BOOST.whileHeld(new teleopDrive(drivetrain, () -> joystick.getRawAxis(Constants.LEFT_AXIS), () -> joystick.getRawAxis(Constants.RIGHT_AXIS), ()->joystick.getRawButton(5)) );
  
       
    }

    
}