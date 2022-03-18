// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Turret extends SubsystemBase {
  private WPI_TalonFX turretMotor;
  private Limelight limelight;
  private boolean trackingSwitch;
  /** Creates a new Turret. */
  public Turret(Limelight limy) {
    limelight = limy;
    turretMotor = new WPI_TalonFX(Constants.TURRET_SPINNY_MOTOR);
    trackingSwitch = false;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void runTurretFinder(double vel) {
    turretMotor.set(vel);
  }

  public void stopTurryFindy() {
    turretMotor.stopMotor();
  }

  public void toggleTracking(){
    trackingSwitch = !trackingSwitch;
  }

  public boolean getTrackingSwitch(){
    return trackingSwitch;
  }
}
