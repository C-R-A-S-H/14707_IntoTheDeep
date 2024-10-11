package org.firstinspires.ftc.teamcode.Robot.Commands.DepositSubCommands;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.Pedrio.Sensors.MagLimitSwitch;
import org.firstinspires.ftc.teamcode.Robot.Config;
import org.firstinspires.ftc.teamcode.Robot.Subsystems.Deposit;

public class ScoreCommand extends SequentialCommandGroup {
    private Deposit deposit;
    private MagLimitSwitch magLimitSwitch;
    public ScoreCommand(Deposit deposit, MagLimitSwitch magLimitSwitch){
        this.deposit = deposit;
        this.magLimitSwitch = magLimitSwitch;
        addRequirements(this.deposit);
    }
    @Override
    public void initialize() {
        addCommands(
                new InstantCommand( () -> this.deposit.ClawControl(Config.ClawOpenPose)),
                new WaitCommand(300),
                new DepositPivotingCommand(this.deposit,Config.LeftATransferPose, Config.RightATransferPose, Config.ClawPivotTransferPose),
                new WaitCommand(200),
                new VerticalRetractionCommand(this.deposit,this.magLimitSwitch)
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
