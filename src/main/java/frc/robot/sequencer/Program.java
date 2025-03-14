package frc.robot.sequencer;

import frc.robot.Subsystem;
import frc.robot.Vector3;

public class Program {

    public Subsystem[] systems = {null}; //System objects that will get triggered
    public int system_count = 0;
    
    public int steps = 0;

    public Vector3[] movements; //movement command for each step of the program
    public int[] ops; //flags to trigger a subsystem
    public int[][] op_params;

    public Program(Vector3[] movements, int[] ops, int[][] op_params) {

        this.movements = movements;
        this.ops = ops;
        this.op_params = op_params;
    }

    public int add_subsystem(Subsystem newSubsystem)
    {
        systems[system_count + 1] = newSubsystem;
        system_count++;
        return system_count;
    }

    public void add_step(Vector3 movement, int op_flag, int[] op_parameters)
    {
        movements[steps] = movement;
        ops[steps] = op_flag;
        for(int i = 0; i < op_parameters.length; i++)
        {
            op_params[steps][i] = op_parameters[i];
        }
        steps++;
    }

    public void add_movement_step(Vector3 movement)
    {
        movements[steps] = movement;
        ops[steps] = 0;
        op_params[steps] = null;
        steps++;
    }

    public void add_op_step(int op_flag, int[] op_parameters)
    {
        movements[steps] = new Vector3(0.0, 0.0, 0.0);
        ops[steps] = op_flag;
        for(int i = 0; i < op_parameters.length; i++)
        {
            op_params[steps][i] = op_parameters[i];
        }
        steps++;
    }

    public void add_no_op_step()
    {
        movements[steps] = new Vector3(0.0, 0.0, 0.0);
        ops[steps] = 0;
        op_params[steps] = null;
        steps++;
    }
    
    public Vector3 get_movement_target(int count)
    {
        return movements[count];
    }

    public void trigger_current_flag(int count)
    {
        int subsystem_flag = ops[count];

        if(subsystem_flag != 0) // zero is no-op
        {
            if(op_params[count] != null)
            {
                systems[subsystem_flag].auto_trigger(op_params[count].length, op_params[count]);
            }
            else
            {
                systems[subsystem_flag].auto_trigger(0, null);
            }
            
        }
        
    }
}
