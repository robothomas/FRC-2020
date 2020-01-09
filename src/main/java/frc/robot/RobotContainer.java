/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019-2020 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import frc.robot.drive.TalonDrivetrain;
import frc.robot.vision.Limelight;
import frc.robot.drive.RevDrivetrain;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;

import static frc.robot.Constants.*;

import java.util.Arrays;


/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // Drive Controller
  XboxController xbox = new XboxController(Constants.kControllerPort);

  // Position Selection
  public enum Start {
    LEFT, CENTER, RIGHT;    
  }

  Start position = Start.CENTER;

  SendableChooser choosePosition = new SendableChooser<Start>();
  
  // Drive Subsystem(s)
  private final TalonDrivetrain tdrive = new TalonDrivetrain();
  private final RevDrivetrain rdrive = new RevDrivetrain();

  // Limelight Subsystem
  private final Limelight limelight = new Limelight();

  // Drive with Controller 
  Command ManualDrive = new RunCommand(() -> tdrive.tankDrive(xbox.getRawAxis(5), xbox.getRawAxis(1)));

  // 
 
  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
    choosePosition.setDefaultOption("Center", Start.CENTER);
    choosePosition.addOption("Left", Start.LEFT);
    choosePosition.addOption("Right", Start.LEFT);
    SmartDashboard.putData("Starting Position", choosePosition);
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
  }

  /** 
   * @param int accepts 1 through 3. 1 is left, 2, is center, 3 is right
  */
  public Pose2d startingPoseChooser() {
    // all possible starting positions on field
    var left = new Pose2d(-1, 0, Rotation2d.fromDegrees(0));
    var center = new Pose2d();
    var right = new Pose2d(1, 0, Rotation2d.fromDegrees(0));

    // find driver selected position on field
    var pose = choosePosition.getSelected().toString();

    // set position on field
    position = position.valueOf(pose);

    switch(position) {
      case LEFT:
        return left;
      case CENTER:
        return center;
      case RIGHT:
        return right;
      default:
        return center;
    }
     
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    TrajectoryConfig config = new TrajectoryConfig(
      MaxSafeVelocityMeters, MaxSafeAccelerationMeters);
    
    Trajectory trajectory = TrajectoryGenerator.generateTrajectory(
      Arrays.asList(startingPoseChooser(), new Pose2d(1.0, 0, new Rotation2d()),
        new Pose2d(2.3, 1.2, Rotation2d.fromDegrees(90.0))), config);
    
    RamseteCommand base = new RamseteCommand(
      trajectory, 
      tdrive::getPose, 
      new RamseteController(Ramsete.kb, Ramsete.kzeta), 
      tdrive.getFeedforward(), 
      tdrive.getKinematics(), 
      tdrive::getSpeeds, 
      tdrive.getLeftDrivePID(), 
      tdrive.getRightDrivePID(), 
      tdrive::setOutputVolts, 
      tdrive);

    return base.andThen(() -> tdrive.setOutputVolts(0, 0));
  }
}