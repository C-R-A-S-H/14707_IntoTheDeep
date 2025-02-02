package org.firstinspires.ftc.teamcode.Robot.Commands.DepositSubCommands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Pedrio.Sensors.MagLimitSwitch;
import org.firstinspires.ftc.teamcode.Robot.Subsystems.Deposit;

public class VerticalRetractionCommand extends CommandBase {
    private Deposit deposit;


    public VerticalRetractionCommand(Deposit deposit){
        this.deposit = deposit;

    }

    @Override
    public void initialize() {
        super.initialize();
    }

    @Override
    public void execute() {
        this.deposit.SetSlidePower(-0.6);
    }

    @Override
    public void end(boolean interrupted) {
        this.deposit.SetSlidePower(0);
        this.deposit.resetSlideEncoders();
    }

    @Override
    public boolean isFinished() {
        return this.deposit.AverageSlideEncoderPose < 0;
    }
}
