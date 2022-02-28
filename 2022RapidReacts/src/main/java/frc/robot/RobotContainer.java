// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

//Command and Control
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.DriveForwardDistance;
//Driving
import frc.robot.commands.DriveForwardTimed;
import frc.robot.commands.DriveWithJoysticks;
import frc.robot.subsystems.DriveTrain;

//Miscellaneous
import frc.robot.subsystems.Jukebox;

//Indexing
import frc.robot.subsystems.Indexing;
import frc.robot.commands.MoveIndexing;

//Intake
import frc.robot.subsystems.Intake;
import frc.robot.commands.MoveIntake;
import frc.robot.commands.RunIntake;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final DriveTrain driveTrain;
  private final DriveWithJoysticks driveWithJoysticks;
  public final DriveForwardTimed driveForwardTimed;
  public final DriveForwardDistance driveForwardDistance;
  public static XboxController driverJoystick;
  JoystickButton A, B, X, Y, LB, RB, LT, RT, M1, M2;

  //Indexing
  private final Indexing indexing;
  private final MoveIndexing moveIndexingFORWARD;

  //Intake
  private final Intake intake;
  private final MoveIntake moveIntakeUp;
  private final MoveIntake moveIntakeDown;
  private final RunIntake runIntakeForward;

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    driveTrain = new DriveTrain();
    driveWithJoysticks = new DriveWithJoysticks(driveTrain);
    driveWithJoysticks.addRequirements(driveTrain);
    driveTrain.setDefaultCommand(driveWithJoysticks);

    driveForwardTimed = new DriveForwardTimed(driveTrain);
    driveForwardTimed.addRequirements(driveTrain);
    driveForwardDistance = new DriveForwardDistance(driveTrain);
    driveForwardDistance.addRequirements(driveTrain);


    driverJoystick = new XboxController(Constants.JOYSTICK_NUMBER);

    indexing = new Indexing();
    moveIndexingFORWARD = new MoveIndexing(indexing);

    intake = new Intake();
    moveIntakeUp = new MoveIntake(intake, 1, Constants.INTAKE_MOVE_SPEED_UP);
    moveIntakeDown = new MoveIntake(intake, -1, Constants.INTAKE_MOVE_SPEED_DOWN);
    runIntakeForward = new RunIntake(intake, false);

    //Declare Joystick Buttons
    A = new JoystickButton(driverJoystick, Constants.BUT_A);
    B = new JoystickButton(driverJoystick, Constants.BUT_B);
    X = new JoystickButton(driverJoystick, Constants.BUT_X);
    Y = new JoystickButton(driverJoystick, Constants.BUT_Y);
    LB = new JoystickButton(driverJoystick, Constants.BUT_LB);
    RB = new JoystickButton(driverJoystick, Constants.BUT_RB);
    LT = new JoystickButton(driverJoystick, Constants.LEFT_TRIG);
    RT = new JoystickButton(driverJoystick, Constants.RIGHT_TRIG);
    M1 = new JoystickButton(driverJoystick, Constants.BUT_M1);
    M2 = new JoystickButton(driverJoystick, Constants.BUT_M2);

    //Start jukebox
    Jukebox jukebox = new Jukebox();
    jukebox.startJukebox();

    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    A.whenHeld(moveIndexingFORWARD);
    A.whenHeld(runIntakeForward);
    X.whenHeld(moveIntakeUp);
    Y.whenHeld(moveIntakeDown);
    //Y.whenHeld(runIntakeForward);
    //B.whenHeld(runIntakeBackward);
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    
    return driveForwardDistance;
  }
}
