// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.AutoCommands.AutoPeriod;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.DriveTrain;

public class AutonomousTurning extends CommandBase {
  private DriveTrain driveTrain;
  private double curAngle, targetAngle, error;
  private boolean finish;
  /** Creates a new AutonomousTurning. */
  public AutonomousTurning(DriveTrain driveTrain, double targetAngle) {
    this.targetAngle = targetAngle;
    this.driveTrain = driveTrain;
    addRequirements(driveTrain);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    finish = false;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    curAngle = driveTrain.gyro.getAngle();
    if(curAngle % 360 < targetAngle + 3){
      error = targetAngle - curAngle;
      driveTrain.driveArcade(0.2, error * Constants.AUTO_TURNING_KP + Constants.MIN_AUTO_ROTATION_SPEED);
    }
    else if(curAngle % 360 > targetAngle - 3){
      error = targetAngle - curAngle;
      driveTrain.driveArcade(0.2, error * Constants.AUTO_TURNING_KP - Constants.MIN_AUTO_ROTATION_SPEED);
    }
    else{
      finish = true;
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    driveTrain.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return finish;
  }
}
