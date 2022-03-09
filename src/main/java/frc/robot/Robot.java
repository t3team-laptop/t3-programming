// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.CvSink;
import edu.wpi.first.cscore.CvSource;
import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.wpilibj.TimedRobot;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import frc.robot.Constants;
import frc.robot.subsystems.DriveTrain;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private Command m_autonomousCommand;
  //private Thread m_visionThread;
  private RobotContainer m_robotContainer;

  /*private DriveTrain driveTrain;
  private WPI_TalonFX leftFront = new WPI_TalonFX(Constants.LEFT_FRONT);
  private WPI_TalonFX leftBack = new WPI_TalonFX(Constants.LEFT_BACK);
  private WPI_TalonFX rightFront = new WPI_TalonFX(Constants.RIGHT_FRONT);
  private WPI_TalonFX rightBack = new WPI_TalonFX(Constants.RIGHT_BACK);*/

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
    /*driveTrain = new DriveTrain();
    leftFront.setInverted(true);
    rightFront.setInverted(false);
    leftBack.follow(leftFront);
    leftBack.setInverted(true);
    rightBack.follow(rightFront);
    rightBack.setInverted(false);

    leftBack.configFactoryDefault();
    rightBack.configFactoryDefault();

    leftFront.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
    rightFront.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);

    leftFront.setSensorPhase(false);
    rightFront.setSensorPhase(true);

    leftFront.setSelectedSensorPosition(0, 0, 10);
    rightFront.setSelectedSensorPosition(0, 0, 10);*/

    m_robotContainer = new RobotContainer();
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
  }

  /** This function is called once each time the robot enters Disabled mode. */
  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {
    //enableMotors(false);
  }

  /** This autonomous runs the autonomous command selected by your {@link RobotContainer} class. */
  @Override
  public void autonomousInit() {
    /*enableMotors(true);
    leftFront.setSelectedSensorPosition(0, 0, 10);
    rightFront.setSelectedSensorPosition(0, 0, 10);*/
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    /*double leftPosition = leftFront.getSelectedSensorPosition() * Constants.kDriveTick2Feet;
    double rightPosition = rightFront.getSelectedSensorPosition() * Constants.kDriveTick2Feet;
    double distance = (leftPosition + rightPosition) / 2;

    if (distance < Constants.AUTONOMOUS_TARGET_DISTANCE) {
      driveTrain.driveForward(Constants.AUTONOMOUS_SPEED);
    }
    else{
      driveTrain.driveForward(0.0);
    }*/
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    
  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}

  /*private void enableMotors(boolean on) {
    NeutralMode mode;
    if (on) {
      mode = NeutralMode.Brake;
    } else {
      mode = NeutralMode.Coast;
    }
    leftFront.setNeutralMode(mode);
    rightFront.setNeutralMode(mode);
    leftBack.setNeutralMode(mode);
    rightBack.setNeutralMode(mode);
  }*/
}
