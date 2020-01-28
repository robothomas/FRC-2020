/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import static frc.robot.Constants.*;
/**
 * Mainly for logging values that need adjustment
 */
public class Update {

    // PID Controller Gains
    private double p = angleCorrection.Kp;

    // Starting positions
    private final Pose2d left = new Pose2d(-1, 0, Rotation2d.fromDegrees(0));
    private final Pose2d center = new Pose2d(0, 0, Rotation2d.fromDegrees(0));
    private final Pose2d right = new Pose2d(1, 0, Rotation2d.fromDegrees(0));

    private static final SendableChooser choosePosition = new SendableChooser<>();

    public Update() {
        choosePosition.setDefaultOption("Center", center);
        choosePosition.addOption("Left", left);
        choosePosition.addOption("Right", right);
        SmartDashboard.putData("Starting Position", choosePosition);
        SmartDashboard.putNumber("P value(aim)", angleCorrection.Kp);

        

    }

    public static Pose2d getStartingPose() {
        final Pose2d position = (Pose2d) choosePosition.getSelected();
        return position;
    }

  public void logContinuous() {
    if ()  {
    SmartDashboard.getNumber("P value(aim)", angleCorrection.Kp);
     }
  }

}