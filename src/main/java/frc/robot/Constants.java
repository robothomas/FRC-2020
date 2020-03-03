/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.revrobotics.ColorMatch;

import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj.util.Units;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

	// Port Numbers

	// Drive Ports
	public static final int kLeftFrontPort = 1;
	public static final int kRightFrontPort = 3;
	public static final int kLeftRearPort = 2;
	public static final int kRightRearPort = 4;

	// Xbox controller port
	public static final int kXboxPort = 0;

	// Spinner port
	public static final int kSpinnerPort = 10; 

	// Compressor port
	public static final int compressorModule = 20;

	// Solenoid ports
	public static final int leftPoseMoverPort = 0;
	public static final int rightPoseMoverPort = 1; 

	// Arm motor ports
	public static final int leftArmPort = 7;

	// Shooter motor port
	public static final int kShooterPort = 5;

	//public static final int kLeftShooterWheelPort = 6;
	//public static final int kRightShooterWheelPort = 5;

	// Conveyor belt motor port
	public static final int kConveyorPort = 8;

	// Feeder wheel motor port
	public static final int kPluckerPort = 6;

	// Solenoid controlling gear ports 
	public static final int leftGearPort = 2;
	public static final int rightGearPort = 3;

	// Robot Measurements
	public static final double kTicksPerRev = 4096;
	public static final double kTrackWidthMeters = Units.inchesToMeters(10);
	public static final double kGearRatio = 7.29;
	public static final double kDriveWheelRadiusMeters = Units.inchesToMeters(3.0);
	public static final double kShooterWheelRadiusMeters = Units.inchesToMeters(3.0);
	public static final double MaxSafeVelocityMeters = Units.feetToMeters(2);
	public static final double MaxSafeAccelerationMeters = Units.feetToMeters(2);
	public static final double targetToCameraHeight = 5;
	public static final double cameraAngle = 0;

	public static final double shooterAngle = 57;
	 
	public static final double straightDriveDeadband = 0.05;

	public static final double highGoalHeight = Units.inchesToMeters(96);
	
	public static final double shooterHeight = Units.inchesToMeters(18);

	// Color Value Bounds
	public static final Color kBlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);

	public static final Color kGreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);

	public final static Color kRedTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
  
	public final static Color kYellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);

	// Constant Speeds
	public static final double drivePercentLimit = 1;
	public static final double rotationMagnitude = 360;

	public static final double armPercentSpeed = 0.4;

	public static final double intakeVolts = 4;
	public static final double shooterVolts = 12;

	public static final double conveyorVolts = 12;
	
	public static final double inPluckerVolts = 12;
	public static final double outPluckerVolts = 12;

	public static final double intakeRPM = 1000;
	public static final double shooterRPM = 3000;

	public static final double colorSwitchSpeed = 0.2;
	public static final double colorSpeed = 0.1;

	// Field Measurements
	public static final double cameraHeight = Units.inchesToMeters(30);

	public static final double ballTargetHeight = Units.inchesToMeters(81.25);
	public static final double collectorTargetHeight = Units.inchesToMeters(11);

	public static final double cameraToBallTargetHeight = Units.inchesToMeters(51.25);

	public static final double shooterDistanceFromTargetMeters = 5;

	public static final double shooterRampUpTime = 2;

}
