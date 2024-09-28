package org.firstinspires.ftc.teamcode.Robot.Commands.AutoTeleopSubCommands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Robot.Config;
import org.firstinspires.ftc.teamcode.Robot.Hardware;

public class AutoTeleop extends CommandBase {
    private Hardware robot;
    public AutoTeleop(Hardware robot){
        this.robot = robot;
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
        return Config.AutoScoring;
    }
}
