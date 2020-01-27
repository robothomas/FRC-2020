/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.wheel;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.*;

import java.text.BreakIterator;

import javax.swing.text.Position;

import edu.wpi.first.wpilibj.I2C;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;

import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorMatch;

public class SenseColor extends SubsystemBase {
  /**
   * Creates a new SenseColor.
   */
  private final I2C.Port i2cPort = I2C.Port.kOnboard;
  private final ColorSensorV3 m_colorSensor = new ColorSensorV3(i2cPort);
  private int proximity = m_colorSensor.getProximity();
  private double IR = m_colorSensor.getIR();
  private Color detectedColor = m_colorSensor.getColor();
  private final ColorMatch m_colorMatcher = new ColorMatch();

  private final Color kBlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
  private final Color kGreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
  private final Color kRedTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
  private final Color kYellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);

  public String colorString;
  public ColorMatchResult match = m_colorMatcher.matchClosestColor(detectedColor);

  boolean isBlue = getRawColor() >= blueLowerBound && getRawColor() <= blueUpperBound;
  boolean isRed = getRawColor() >= redLowerBound && getRawColor() <= redUpperBound;
  boolean isYellow = getRawColor() >= yellowLowerBound && getRawColor() <= yellowUpperBound;
  boolean isGreen = getRawColor() >= greenLowerBound && getRawColor() <= greenUpperBound;

  public enum Colour {
    RED(redLowerBound, redUpperBound ,1) {
      public Colour next() {
        return YELLOW;
      }
     
    },
    YELLOW(yellowLowerBound, yellowUpperBound,2) {
      public Colour next() {
        return BLUE;
      }
      
    },
    BLUE(blueLowerBound, blueUpperBound,3) {
      public Colour next() {
        return GREEN;
      }
      
    },
    GREEN(greenLowerBound, greenUpperBound,4) {
      public Colour next() {
        return YELLOW;
      }
    };

    
    private final double upper;
    private final double lower;
    private final int position;

    public abstract Colour next();


    public double getLower() {
      return lower;
    }

    public double getUpper() {
      return upper;
    }
    
    public Colour nextIn(int n){
      n=n%4;

      if (n == YELLOW.position){return YELLOW;}

      else if (n == BLUE.position){return BLUE;}

      else if (n == GREEN.position){return GREEN;}

      else if (n == RED.position){return RED;}
      
      return null;
    }  


    private Colour(final double upperBound, final double lowerBound, final int position) {
       this.upper = upperBound;
       this.lower = lowerBound;
       this.position = position;
    } 
        
  }


  public boolean getIsBlue(){
    isBlue = getRawColor() >= Colour.BLUE.lower && getRawColor() <= Colour.BLUE.upper;

    return isBlue; 
  }

 public boolean getIsRed(){

  isRed = getRawColor() >= Colour.RED.lower && getRawColor() <= Colour.RED.upper; 

  return isRed; 

 }

 public boolean getIsYellow(){

  isYellow = getRawColor() >= Colour.YELLOW.lower && getRawColor() <= Colour.YELLOW.upper; 

  return isYellow; 

 }

 public boolean getIsGreen(){

  isGreen = getRawColor() >= Colour.GREEN.lower && getRawColor() <= Colour.GREEN.upper; 

  return isGreen; 

 }


  public Color getColor() {
    return detectedColor;
  }

  public double getRawColor() {
    return IR;
  }

  public int getProximity() {
    return proximity;
  }

  public Colour getColour(){
    if (getIsBlue()) {
      return  Colour.BLUE;

    } else if (getIsRed()) {
      return Colour.RED;

    } else if (getIsGreen()) {
      return Colour.GREEN;

    } else if (getIsYellow()) {
      return Colour.YELLOW;

    } else {
      return null;

    }

  }




  public String getColorString() {
    
    if (getIsBlue()) {
      return  colorString = "Blue";

    } else if (getIsRed()) {
      return colorString = "Red";

    } else if (getIsGreen()) {
      return colorString = "Green";

    } else if (getIsYellow()) {
      return colorString = "Yellow";

    } else {
      return colorString = "Error: Unknown";

    }
  }

  

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    detectedColor = m_colorSensor.getColor();
    IR = m_colorSensor.getIR();
    proximity = m_colorSensor.getProximity();
    match = m_colorMatcher.matchClosestColor(detectedColor);
  }
}
