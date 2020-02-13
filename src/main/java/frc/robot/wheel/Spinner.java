/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.wheel;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.wheel.SenseColor.*;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import static frc.robot.Constants.*;

public class Spinner extends SubsystemBase {

  private SenseColor colorSense;
  private final WPI_TalonSRX SpinnerMotor = new WPI_TalonSRX(kSpinnerPort);
  private int colorSwitches = 10 * 8;

  public Spinner(SenseColor colorSensor) {
    colorSense = colorSensor;
  }

  public void move(double speed) {
    SpinnerMotor.set(speed);
  }

  public void toSelectedColor(String c) {
    Colour objective = Colour.fromChar(c.charAt(0)).nextIn(2);
    move(colorSpeed);

    if (colorSense.getColorChar() == objective.getCapital()){
      move(0);
      System.out.println("Release!");
    }
  }

  public int getCurrentRotations() {
    return colorSwitches;
  }

  public void changeMaxRotations(int maxColorSwitches) {
    colorSwitches = maxColorSwitches;
  };

  public void toSelectedColorSwitches(){
    move(colorSwitchSpeed);

    if(colorSense.getColour() == colorSense.getPrevColour().next()) {
      colorSwitches--;
    }

    if (colorSwitches >= 0){
      move(0);
    }
  
  }
 
  @Override
  public void periodic() {
  }

}

