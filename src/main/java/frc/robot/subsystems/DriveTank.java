package frc.robot.subsystems;

import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.util.sendable.SendableRegistry;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.Constants.DriveConstants;
import frc.robot.Subsystem;

public class DriveTank extends Subsystem{
    
    private final SparkMax m_leftLeader = new SparkMax(DriveConstants.leftLeaderID, DriveConstants.motorType);
    private final SparkMax m_leftFollower = new SparkMax(DriveConstants.leftFollowerID, DriveConstants.motorType);
    private final SparkMax m_rightLeader = new SparkMax(DriveConstants.rightLeaderID, DriveConstants.motorType);
    private final SparkMax m_rightFollower = new SparkMax(DriveConstants.rightFollowerID, DriveConstants.motorType); 

    private final DifferentialDrive m_robotDrive = new DifferentialDrive(m_leftLeader::set, m_rightLeader::set);

    private SparkMaxConfig globalConfig = new SparkMaxConfig();
    private SparkMaxConfig rightLeaderConfig = new SparkMaxConfig();
    private SparkMaxConfig rightFollowerConfig = new SparkMaxConfig();
    private SparkMaxConfig leftFollowerConfig = new SparkMaxConfig();

    public DriveTank()
    {
        //Set all motors to stall at the limit and brake on idle
        globalConfig.smartCurrentLimit(DriveConstants.motorStallLimit);
        globalConfig.idleMode(IdleMode.kBrake);

        //Apply global config and inverse right motor
        rightLeaderConfig.apply(globalConfig);
        rightLeaderConfig.inverted(true);

        //Set left follower config and follow left leader
        leftFollowerConfig.apply(globalConfig);
        leftFollowerConfig.follow(DriveConstants.leftLeaderID);

        //set right follower config and follow right leader
        rightFollowerConfig.apply(globalConfig);
        rightFollowerConfig.follow(DriveConstants.rightLeaderID);

        //Set Leader Configs
        m_leftLeader.configure(globalConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        m_rightLeader.configure(rightLeaderConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

        //Set Follower Configs
        m_leftFollower.configure(globalConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        m_rightFollower.configure(globalConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

        SendableRegistry.addChild(m_robotDrive, m_leftLeader);
        SendableRegistry.addChild(m_robotDrive, m_rightLeader);
    }

    public void drivetrain_periodic(XboxController m_driverController)
    {
        m_robotDrive.tankDrive(m_driverController.getLeftY(), m_driverController.getRightY());
    }
}
