// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;

public class AutoDrive extends CommandBase {
  DriveSubsystem dt;
  Timer time;
  
  /** Creates a new AutoDrive. */
  public AutoDrive(DriveSubsystem dt) {
    this.dt = dt;
    addRequirements(dt);
    time = new Timer();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    time.reset();
    time.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(time.get() < 5.0) dt.drive(-0.2, 0, 0);
    else if(time.get() >= 5.0 && time.get() < 10.0) dt.drive(-0.1, 0, 0.8);
    else dt.drive(0, 0, 0);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    time.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return (time.get() > 10);
  }
}
