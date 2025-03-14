package frc.robot.sequencer;

import frc.robot.Subsystem;
import frc.robot.Vector3;

public class Sequencer {

    private Program current_program;
    private int counter = 0;
    private boolean halt = false;

    private Vector3 distance_target;
    private Vector3 distance_progress;
    private double dead_zone = 0.1;

    /*private double max_wheel_power = 0.25;
    private double begin_easing_distance = 1.0;
    private double easing_constant = 3; 
    private double min_wheel_power = 0.1;

    private double last_rot_lf = 0.0;
    private double last_rot_lb = 0.0;
    private double last_rot_rf = 0.0;
    private double last_rot_rb = 0.0;*/

    //private double wheel_radius = 0.5; //6 inch == 0.5 feet
    //private double wheel_circ = 2.0 * Math.PI * wheel_radius;
     
    public Sequencer(Program new_program, Subsystem[] systems) {
        //init Code here
        this.current_program = new_program;
        this.current_program.systems = systems;
    }

    public void restart_program()
    {
        //zero counter, distance_progress, last_rots

        //set distance_target to target counter 0

        //set halt flag to false
        halt = false;
    }

    public void resume_program()
    {
        //set halt flag to false
        halt = false;
    }

    public void interupt_program()
    {
        //set speeds to zero

        //set halt flag to true
        halt = true;

    }

    public void periodic()
    {
        if(!halt)
        {
            Vector3 current_error = distance_progress.subtract(distance_target);

            if(current_error.x > dead_zone && current_error.y > dead_zone && current_error.y > dead_zone)
            {
                //calc wheel distance from positions
                //delta_pos = last_pos - current_pos;
                //distance = delta_pos * wheel_circ;

                //determine movement type and calculate average distance based on given formula

                //based on movement type update distance moved

                //update saved wheel positions for next time

                //distance to target is target_delta = target_distance - current_distance

                //set new speed vector, include easing based off distance magnitude
                //target_delta.normalised() is the proportional
                //use to set each as a proportion of the max speed
            }
            else
            {
                //set speeds to zero

                //process the subsystem trigger


                //We reached our goal, progress the counter and get the next target
                counter++;
                distance_target = current_program.get_movement_target(counter);
            }
        }

    }
    
}
