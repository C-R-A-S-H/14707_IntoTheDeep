package org.firstinspires.ftc.teamcode.Robot.Commands.DepositSubCommands;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import org.firstinspires.ftc.teamcode.Robot.Subsystems.Deposit;

public class SpecimentHangSequence extends SequentialCommandGroup {
    private Deposit deposit;
    public SpecimentHangSequence(Deposit deposit){
        this.deposit = deposit;
        addRequirements(this.deposit);
    }

    @Override
    public void initialize() {
        addCommands(
                new InstantCommand( () -> this.deposit.SetServoPoses(0,0,0))
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
