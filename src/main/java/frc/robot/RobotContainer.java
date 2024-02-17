// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Autos;
import frc.robot.commands.IntakeButtonCmd;
import frc.robot.commands.ShooterButtonCmd;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.CommandPS4Controller;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

public class RobotContainer {

  
  // The robot's subsystems and commands are defined here...
  private final IntakeSubsystem m_intakeSubsystem;
  private final ShooterSubsystem m_shooterSubsystem;


  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final CommandPS4Controller m_operatorController =
      new CommandPS4Controller(1);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    m_intakeSubsystem = IntakeSubsystem.getInstance();
    m_shooterSubsystem = ShooterSubsystem.getInstance();

    m_intakeSubsystem.setDefaultCommand(
      new IntakeButtonCmd(0)//
    );

    m_shooterSubsystem.setDefaultCommand(
      new ShooterButtonCmd(0)//
    );
    
    // Configure the trigger bindings
    configureBindings();
  }

  public static Command shooter_intake_conDelay() {
    return new SequentialCommandGroup(
      new ShooterButtonCmd(0.75).withTimeout(1.7),
      new ParallelCommandGroup(
        new ShooterButtonCmd(0.75),
        new IntakeButtonCmd(-0.5)).withTimeout(2));
    
  }
  
  private void configureBindings() {
    
    m_operatorController.R1().whileTrue(new ShooterButtonCmd(-0.75));
    m_operatorController.cross().whileTrue(new IntakeButtonCmd(0.5));
    m_operatorController.square().whileTrue(new IntakeButtonCmd(-0.5));
    m_operatorController.R2().whileTrue(shooter_intake_conDelay());
    


    //m_driverController.b().whileTrue(m_exampleSubsystem.exampleMethodCommand());
  }

  
  public Command getAutonomousCommand() {
    
    return null;
  }
}
