// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
//check to see if tan method is in degrees or radians
package frc.robot.subsystems;

import edu.wpi.first.networktables.*;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Limelight extends SubsystemBase {
  /** Creates a new Limelight. */
  NetworkTable limeLight;
  NetworkTableEntry tv, tx, ty, ta;
  static double d;
  double a2;
  public Limelight() {
    d = 0;
    limeLight = NetworkTableInstance.getDefault().getTable("limelight");
    tv = limeLight.getEntry("tv");
    tx = limeLight.getEntry("tx");
    ty = limeLight.getEntry("ty");
    
  }
  public double getPercentage(){
    d = (Constants.h2-Constants.h1)/Math.tan(Constants.a1 + a2);
    return Constants.DATA_A*Math.pow(d,2) + Constants.DATA_B*(d) + Constants.DATA_C;
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    a2 = tx.getDouble(0.0);
  }


}
