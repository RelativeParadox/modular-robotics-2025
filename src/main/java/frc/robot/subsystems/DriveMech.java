package frc.robot.subsystems;

import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Constants.DriveConstants;

public class DriveMech {
    
    private final SparkMax motorLeftFront = new SparkMax(DriveConstants.leftLeaderID, DriveConstants.motorType);
    private final SparkMax motorLeftBack = new SparkMax(DriveConstants.leftFollowerID, DriveConstants.motorType);
    private final SparkMax motorRightFront = new SparkMax(DriveConstants.rightLeaderID, DriveConstants.motorType);
    private final SparkMax motorRightBack = new SparkMax(DriveConstants.rightFollowerID, DriveConstants.motorType); 

    private SparkMaxConfig globalConfig = new SparkMaxConfig();
    private SparkMaxConfig rightConfig = new SparkMaxConfig();

    public DriveMech()
    {
        //Set all motors to stall at the limit and brake on idle
        globalConfig.smartCurrentLimit(DriveConstants.motorStallLimit);
        globalConfig.idleMode(IdleMode.kBrake);

        //Apply global config and inverse right motor
        rightConfig.apply(globalConfig);
        rightConfig.inverted(true);

        //Set Leader Configs
        motorLeftFront.configure(globalConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        motorRightFront.configure(rightConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

        //Set Follower Configs
        motorLeftBack.configure(globalConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        motorRightBack.configure(rightConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

        //SendableRegistry.addChild(m_robotDrive, m_leftLeader);
        //SendableRegistry.addChild(m_robotDrive, m_rightLeader);
    }

    public void drivetrain_periodic(XboxController m_driverController)
    {
        // https://gm0.org/en/latest/docs/software/tutorials/mecanum-drive.html

        double y = -m_driverController.getLeftY();
        double x = m_driverController.getLeftX() * 1.1;
        double rx = m_driverController.getRightX();

        double factor = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);

        double frontLeftPow = (y + x + rx) / factor;
        double backLeftPow = (y - x + rx) / factor;
        double frontRightPow = (y - x - rx) / factor;
        double backRightPow = (y + x - rx) / factor;

        motorLeftFront.set(frontLeftPow);
        motorLeftBack.set(backLeftPow);
        motorRightFront.set(frontRightPow);
        motorRightBack.set(backRightPow);
    }

}
