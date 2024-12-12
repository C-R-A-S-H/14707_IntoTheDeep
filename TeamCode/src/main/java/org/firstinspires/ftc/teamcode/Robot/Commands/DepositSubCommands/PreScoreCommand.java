package org.firstinspires.ftc.teamcode.Robot.Commands.DepositSubCommands;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.Pedrio.Sensors.MagLimitSwitch;
import org.firstinspires.ftc.teamcode.Robot.Config;
import org.firstinspires.ftc.teamcode.Robot.Subsystems.Deposit;

public class PreScoreCommand extends SequentialCommandGroup {
    private Deposit deposit;
    public PreScoreCommand(Deposit deposit){
        this.deposit = deposit;

    }
    @Override
    public void initialize() {
        super.initialize();
    }

    @Override
    public void execute() {
        switch(Config.scoringMode){
            case HighBar:
                break;
            case LowBar:
                break;
            case HighBaskets:
                addCommands(new HighBasketCommand(this.deposit));
            case LowBaskets:
                addCommands(new LowBasketCommand(this.deposit));
        }
    }

    @Override
    public void end(boolean interrupted) {
        /*addCommands(
                new InstantCommand( () -> this.deposit.ClawControl(Config.ClawOpenPose)),
                new WaitCommand(300),
                new DepositPivotingCommand(this.deposit,Config.LeftATransferPose, Config.RightATransferPose, Config.ClawPivotTransferPose),
                new WaitCommand(200),
                new VerticalRetractionCommand(this.deposit,this.magLimitSwitch)
        );

         */
        super.end(interrupted);
    }

    @Override
    public boolean isFinished() {
        return true;
    }

}
