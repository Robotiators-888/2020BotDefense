// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.MecanumDriveCommand;
import frc.robot.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.Joystick;



public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final Joystick m_stick = new Joystick(Constants.JOYSTICK_PORT);
  private final DriveSubsystem m_driveSubsystem = new DriveSubsystem(
    this, 
    () -> {
      return (1 - m_stick.getRawAxis(Constants.JOYSTICK_SELECTOR_AXIS)) / 2;
    });


  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
    
    //params : DriveSubsystem Object | X Axis Supplier with Dead Zone | Y Axis Supplier with Dead Zone | Z Axis Supplier with Dead Zone
    MecanumDriveCommand driveTrain = new MecanumDriveCommand(m_driveSubsystem,
    () -> applyDeadZone(m_stick.getRawAxis(Constants.JOYSTICK_X_AXIS)),
    ()-> applyDeadZone(m_stick.getRawAxis(Constants.JOYSTICK_Y_AXIS)),
    () -> applyDeadZone(m_stick.getRawAxis(Constants.JOYSTICK_Z_AXIS)),
    () -> m_stick.getRawButton(1));

    m_driveSubsystem.setDefaultCommand(driveTrain);  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {}

  /*
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_autoCommand;
  }
  */

  private double applyDeadZone(double axisVal){
    double dz = Constants.DEAD_ZONE;
    if (axisVal>dz && axisVal<-dz){
      return 0;
    }
    return axisVal;
  }
}
