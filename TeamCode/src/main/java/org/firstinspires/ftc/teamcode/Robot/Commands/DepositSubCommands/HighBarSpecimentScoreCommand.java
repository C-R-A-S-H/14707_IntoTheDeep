package org.firstinspires.ftc.teamcode.Robot.Commands.DepositSubCommands;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.Robot.Config;
import org.firstinspires.ftc.teamcode.Robot.Subsystems.Deposit;

public class HighBarSpecimentScoreCommand extends SequentialCommandGroup  {
    private Deposit deposit;
    public HighBarSpecimentScoreCommand(Deposit deposit){
        this.deposit = deposit;
        addRequirements(this.deposit);
    }

    @Override
    public void initialize() {
        addCommands(new VerticalExtensionCommand(this.deposit, Config.HighBarSlideSetpoint)

                );
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

