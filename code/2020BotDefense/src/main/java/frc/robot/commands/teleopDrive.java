// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.Supplier;

import org.opencv.ml.Boost;

import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class teleopDrive extends CommandBase {
  @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
  private Drivetrain drive;
  private Supplier<Double> Left, Right;
  private Joystick joystick = new Joystick(0);
  private JoystickButton BOOST = new JoystickButton(joystick, 5);
  private double speed;
  private Supplier<Boolean> Boost;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public teleopDrive(Drivetrain drivetrain, Supplier<Double> L, Supplier<Double> R, Supplier<Boolean> boost) {
    this.drive = drivetrain;
    this.Left = L;
    this.Right = R;
    this.Boost = boost;
    addRequirements(drive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    drive.zeroHeading();
    drive.setPosition(0, 0, drive.getGyroHeading());
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(Boost != null){
      speed = 1;
    }
    drive.setMotors(Left.get(), 1 * Right.get(), speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    drive.setMotors(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
