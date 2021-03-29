// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.DriveTrain;

public class AutoAim extends CommandBase {
  /** Creates a new AutoAim. */
  private Limelight limy;
  private DriveTrain drivetrain;
  private PIDController aim; 
  public AutoAim(Limelight l, DriveTrain dt) {
    // Use addRequirements() here to declare subsystem dependencies.
    limy = l;
    drivetrain = dt;
    addRequirements(limy);
    addRequirements(drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    aim = new PIDController(Constants.SHOOTER_KP, Constants.SHOOTER_KI, Constants.SHOOTER_KD);
    aim.setTolerance(Constants.SHOOTER_TOLERANCE);
    aim.enableContinuousInput(-27, 27);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(limy.getTv() == 1.0){
    double speed = aim.calculate(limy.getXOffset(), 0);
    drivetrain.driveAuto(speed, speed*-1);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    drivetrain.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return aim.atSetpoint();
  }
}
