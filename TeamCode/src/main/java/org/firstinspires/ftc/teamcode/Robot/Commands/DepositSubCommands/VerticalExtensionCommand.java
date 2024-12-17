package org.firstinspires.ftc.teamcode.Robot.Commands.DepositSubCommands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Robot.Subsystems.Deposit;

public class VerticalExtensionCommand extends CommandBase {
    private Deposit deposit;
    private double pose;
    public VerticalExtensionCommand(Deposit deposit, double pose){
        this.deposit = deposit;
        this.pose = pose;

    }
    @Override
    public void initialize() {
        super.initialize();
    }

    @Override
    public void execute() {
        this.deposit.SetSlidePose(this.pose);
    }

    @Override
    public void end(boolean interrupted) {

       super.end(interrupted);
    }

    @Override
    public boolean isFinished() {
        return this.deposit.SlideAtPoint(this.pose);
    }
}
