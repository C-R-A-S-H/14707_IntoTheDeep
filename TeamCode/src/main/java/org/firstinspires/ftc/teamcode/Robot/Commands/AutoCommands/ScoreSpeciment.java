package org.firstinspires.ftc.teamcode.Robot.Commands.AutoCommands;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.Robot.Commands.DepositSubCommands.DepositPivotingCommand;
import org.firstinspires.ftc.teamcode.Robot.Commands.DepositSubCommands.VerticalExtensionCommand;
import org.firstinspires.ftc.teamcode.Robot.Commands.DepositSubCommands.VerticalRetractionCommand;
import org.firstinspires.ftc.teamcode.Robot.Subsystems.Deposit;

public class ScoreSpeciment extends SequentialCommandGroup {
    private Deposit deposit;

    public ScoreSpeciment(Deposit deposit){
        this.deposit = deposit;
        addCommands(
                new WaitCommand(300),
                new VerticalExtensionCommand(this.deposit,100),
                new DepositPivotingCommand(this.deposit, 0.45,0.45,0),
                new WaitCommand(1000),
                new VerticalRetractionCommand(this.deposit).alongWith(
                        new InstantCommand( () -> this.deposit.ClawControl(0.2))
                ),
                new InstantCommand( () -> this.deposit.ClawControl(0)),
                new DepositPivotingCommand(this.deposit,0,0,0.96)
        );
    }

}
