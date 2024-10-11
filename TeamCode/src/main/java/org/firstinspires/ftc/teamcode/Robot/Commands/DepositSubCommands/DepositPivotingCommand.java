package org.firstinspires.ftc.teamcode.Robot.Commands.DepositSubCommands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Robot.Subsystems.Deposit;

public class DepositPivotingCommand extends CommandBase {
    Deposit deposit;
    double PivotALeft;
    double PivotARight;
    double PivotB;
    public DepositPivotingCommand(Deposit deposit, double PivotALeft,double PivotARight,double PivotB){
        this.PivotALeft = PivotALeft;
        this.PivotARight = PivotARight;
        this.PivotB = PivotB;
        this.deposit = deposit;
        addRequirements(this.deposit);
    }

    @Override
    public void initialize() {
        this.deposit.SetServoPoses(this.PivotALeft,this.PivotARight,this.PivotB);

    }

    @Override
    public void execute() {
        this.deposit.SetServoPoses(this.PivotALeft,this.PivotARight,this.PivotB);
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
