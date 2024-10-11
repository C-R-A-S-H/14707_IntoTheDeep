package org.firstinspires.ftc.teamcode.Robot.Commands.HangCommands;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.Robot.Subsystems.Deposit;

public class HangCommand extends SequentialCommandGroup {
    private Deposit deposit;
    public HangCommand(Deposit deposit){
        this.deposit = deposit;
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
    public void end(boolean interrupted) {
        super.end(interrupted);
    }

    @Override
    public boolean isFinished() {
        return super.isFinished();
    }
}
