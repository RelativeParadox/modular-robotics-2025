// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.subsystems.DriveMech;
import frc.robot.subsystems.Roller;

/**
 * This is a demo program showing the use of the DifferentialDrive class, specifically it contains
 * the code necessary to operate a robot with tank drive.
 */
public class Robot extends TimedRobot {

    private final XboxController m_driverController = new XboxController(0);
    private final DriveMech s_mainDrive = new DriveMech();
	private final Roller s_roller = new Roller(); 
    //private Sequencer auto_sequencer = new Sequencer(null, null);

    private double start_time = 0;
    private double delta_time = 0;

    /** Called once at the beginning of the robot program. */
    public Robot() {

        //Enable Roborio Camera
        CameraServer.startAutomaticCapture();

        //limelight config
        LimelightHelpers.setPipelineIndex("", 0);
        //LimelightHelpers.

        //Sendable Chooser

        //Setup Sequencer

        //auto_sequencer = new Sequencer(Constants.test_program, new Subsystem[]{null, s_roller});
    }

    @Override
    public void autonomousInit() {
        start_time = System.currentTimeMillis();
    }

	@Override
    public void autonomousPeriodic() {
        delta_time = System.currentTimeMillis() - start_time;

        if(delta_time <= 1000){
            s_mainDrive.set_speed(0.25, 0.0, 0.0);
        }
        else
        {
            s_mainDrive.set_speed(0.0, 0.0, 0.0);
        }
    }

    @Override
    public void teleopPeriodic() {  
        s_mainDrive.drivetrain_periodic(m_driverController);

		if(m_driverController.getAButton() == true) 
		{
			s_roller.set_speed(-0.20);
		}
        else
        {
            s_roller.set_speed(0.0);
        }
    }
}