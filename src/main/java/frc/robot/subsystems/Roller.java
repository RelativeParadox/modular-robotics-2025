package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;

import frc.robot.Constants.RollerConstants;
import frc.robot.Subsystem;

public class Roller extends Subsystem{

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
