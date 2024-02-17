// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeSubsystem extends SubsystemBase {
  CANSparkMax motorIntake;
  RelativeEncoder encoderIntake;


  public IntakeSubsystem() {
    /** Initialization for the motor */
    motorIntake = new CANSparkMax(11, MotorType.kBrushless);
    motorIntake.restoreFactoryDefaults();
    motorIntake.setInverted(false);
    motorIntake.setIdleMode(IdleMode.kBrake);

    /** Initialization for the relative encoder */
    encoderIntake = motorIntake.getEncoder();
    resetEncoder();
  }

  /** Crea las funciones para las distintas cosas que puede hacer tu sistema **/
  public void setSpeed(double speed){
    motorIntake.set(speed);
  }

  public double getEncoder(){
    return encoderIntake.getPosition();
  }

  public void resetEncoder(){
    encoderIntake.setPosition(0);
  }

  public void stopMotor(){
    motorIntake.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Encoder Intake", getEncoder());
  }

  private static IntakeSubsystem instance;

  public static IntakeSubsystem getInstance(){
    if(instance == null){
      instance = new IntakeSubsystem();
    }
    return instance;
  }


}
