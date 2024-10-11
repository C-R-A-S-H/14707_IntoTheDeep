package org.firstinspires.ftc.teamcode.Robot.Commands.AutoTeleopSubCommands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Robot.Subsystems.NumNum.NumNumDrivetrain;

import java.util.function.BooleanSupplier;

public class AutoCycleBasketCommand extends CommandBase {
    NumNumDrivetrain dt;
    BooleanSupplier cancelKey;
    public AutoCycleBasketCommand(NumNumDrivetrain dt, BooleanSupplier cancelKey){
        this.dt = dt;
        this.cancelKey = cancelKey;
        addRequirements(this.dt);
    }
    @Override
    public boolean isFinished() {
        return cancelKey.getAsBoolean();
    }

    @Override
    public void execute() {
        super.execute();
    }

    @Override
    public void initialize() {
        super.initialize();
    }
}
