// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;

import frc.robot.subsystems.DriveTank;
import frc.robot.subsystems.Roller;

/**
 * This is a demo program showing the use of the DifferentialDrive class, specifically it contains
 * the code necessary to operate a robot with tank drive.
 */
public class Robot extends TimedRobot {

    private final XboxController m_driverController = new XboxController(0);
    private final DriveTank s_mainDrive = new DriveTank();
	private final Roller s_roller = new Roller();

    /** Called once at the beginning of the robot program. */
    public Robot() {
    }

	

    @Override
    public void teleopPeriodic() {
        s_mainDrive.drivetrain_periodic(m_driverController);

		if(m_driverController.getAButton() == true)
		{
			s_roller.set_speed(0.75);
		}
    }
}