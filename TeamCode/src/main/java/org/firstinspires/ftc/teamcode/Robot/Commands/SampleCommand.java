package org.firstinspires.ftc.teamcode.Robot.Commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.Subsystem;

public class SampleCommand extends CommandBase {
    private Subsystem m_subsystem;
    public SampleCommand(Subsystem subsystem){
        this.m_subsystem = subsystem;
        addRequirements(subsystem);
    }
    @Override
    public void initialize() {
        super.initialize();
    }

    @Override
    public void execute() {
        super.execute();
    }

    @Override
    public boolean isFinished() {
        return super.isFinished();
    }
}
