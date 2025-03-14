package frc.robot.subsystems;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;


import frc.robot.Subsystem;
import frc.robot.Constants.DriveConstants;

import com.pathplanner.lib.config.RobotConfig;
import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.controllers.PPLTVController;

public class DriveMech extends SubsystemBase{
    
    private final SparkMax motorLeftFront = new SparkMax(DriveConstants.leftLeaderID, DriveConstants.motorType);
    private final SparkMax motorLeftBack = new SparkMax(DriveConstants.leftFollowerID, DriveConstants.motorType);
    private final SparkMax motorRightFront = new SparkMax(DriveConstants.rightLeaderID, DriveConstants.motorType);
    private final SparkMax motorRightBack = new SparkMax(DriveConstants.rightFollowerID, DriveConstants.motorType);

    public RelativeEncoder encoderLF = motorLeftFront.getEncoder();
    public RelativeEncoder encoderLB = motorLeftBack.getEncoder();
    public RelativeEncoder encoderRF = motorRightFront.getEncoder();
    public RelativeEncoder encoderRB = motorRightBack.getEncoder();

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

        // Load the RobotConfig from the GUI settings. You should probably
        // store this in your Constants file
        /*RobotConfig config;
        try{
        config = RobotConfig.fromGUISettings();
        } catch (Exception e) {
        // Handle exception as needed
        e.printStackTrace();
        }

        // Configure AutoBuilder last
        AutoBuilder.configure(
                this::getPose, // Robot pose supplier
                this::resetPose, // Method to reset odometry (will be called if your auto has a starting pose)
                this::getRobotRelativeSpeeds, // ChassisSpeeds supplier. MUST BE ROBOT RELATIVE
                (speeds, feedforwards) -> driveRobotRelative(speeds), // Method that will drive the robot given ROBOT RELATIVE ChassisSpeeds. Also optionally outputs individual module feedforwards
                new PPLTVController(0.02), // PPLTVController is the built in path following controller for differential drive trains
                config, // The robot configuration
                () -> {
                // Boolean supplier that controls when the path will be mirrored for the red alliance
                // This will flip the path being followed to the red side of the field.
                // THE ORIGIN WILL REMAIN ON THE BLUE SIDE

                var alliance = DriverStation.getAlliance();
                if (alliance.isPresent()) {
                    return alliance.get() == DriverStation.Alliance.Red;
                }
                return false;
                },
                this // Reference to this subsystem to set requirements
            );*/
    }

    public void drivetrain_periodic(XboxController m_driverController)
    {
        // https://gm0.org/en/latest/docs/software/tutorials/mecanum-drive.html

        double y = -m_driverController.getLeftY();
        double x = m_driverController.getLeftX() * 1.25;
        double rx = m_driverController.getRightX();

        if(-DriveConstants.deadzone < y && y < DriveConstants.deadzone){y = 0;}

        if(-DriveConstants.deadzone < x && x < DriveConstants.deadzone){x = 0;}

        if(-DriveConstants.deadzone < rx && rx < DriveConstants.deadzone){rx = 0;}

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

    public void set_speed(double speed_forward, double speed_strafe, double speed_rotation)
    {
        double factor = Math.max(Math.abs(speed_forward) + Math.abs(speed_strafe) + Math.abs(speed_rotation), 1);

        double frontLeftPow = (speed_forward + speed_strafe + speed_rotation) / factor;
        double backLeftPow = (speed_forward - speed_strafe + speed_rotation) / factor;
        double frontRightPow = (speed_forward - speed_strafe - speed_rotation) / factor;
        double backRightPow = (speed_forward + speed_strafe - speed_rotation) / factor;

        motorLeftFront.set(frontLeftPow);
        motorLeftBack.set(backLeftPow);
        motorRightFront.set(frontRightPow);
        motorRightBack.set(backRightPow);
    }

}
