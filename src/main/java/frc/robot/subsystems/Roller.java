package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;

import frc.robot.Constants.RollerConstants;

public class Roller {

    //Roller Motor Controller
    private SparkMax m_rollerMotor = new SparkMax(RollerConstants.rollerMotorID, RollerConstants.rollerMotorType);

    public Roller() {
        //init Code here
    }

    public void set_speed(double speed)
    {
        m_rollerMotor.set(speed);
    }
    
}
