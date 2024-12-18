package org.firstinspires.ftc.teamcode.Robot.Commands.DepositSubCommands;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.Robot.Config;
import org.firstinspires.ftc.teamcode.Robot.Subsystems.Deposit;

public class LowBasketCommand extends SequentialCommandGroup {
    private Deposit deposit;
    public LowBasketCommand(Deposit deposit){
        this.deposit = deposit;
        addRequirements(this.deposit);
    }
    @Override
    public void initialize() {
        addCommands(
                new VerticalExtensionCommand(this.deposit, Config.LowBasketSlideSetpoint).alongWith(
                        new DepositPivotingCommand(this.deposit, Config.LeftAScoreBasketPose, Config.RightAScoreBasketPose, Config.ClawPivotScoreBasketPose))
        );
    }

    @Override
    public void execute() {

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
