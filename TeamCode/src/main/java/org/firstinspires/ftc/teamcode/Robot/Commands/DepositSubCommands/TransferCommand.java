package org.firstinspires.ftc.teamcode.Robot.Commands.DepositSubCommands;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.Robot.Config;
import org.firstinspires.ftc.teamcode.Robot.Subsystems.Deposit;

public class TransferCommand extends SequentialCommandGroup {
    Deposit deposit;
    public TransferCommand(Deposit deposit){
        this.deposit = deposit;
        addRequirements(this.deposit);
    }
    @Override
    public void initialize() {
        addCommands(
                new InstantCommand( () -> this.deposit.ClawControl(Config.ClawClosePose)),
                new WaitCommand(200)
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
        return true;
    }

}
