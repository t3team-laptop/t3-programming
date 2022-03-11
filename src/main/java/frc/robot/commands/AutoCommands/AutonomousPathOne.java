// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.AutoCommands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Indexing;
import frc.robot.subsystems.Intake;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AutonomousPathOne extends SequentialCommandGroup {
  /** Creates a new AutonomousPathOne. */
  public AutonomousPathOne(DriveTrain dt, Indexing indexing, Intake intake) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(new MoveCommand(dt, 6, 0, true, false), new MoveCommand(dt, 5, 0.1, true, false), new MoveCommand(dt, 3, .5, true, true));
  }
}
