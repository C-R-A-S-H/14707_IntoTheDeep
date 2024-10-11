package org.firstinspires.ftc.teamcode.Robot.Commands.DepositSubCommands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Pedrio.Sensors.MagLimitSwitch;
import org.firstinspires.ftc.teamcode.Robot.Subsystems.Deposit;

public class VerticalRetractionCommand extends CommandBase {
    private Deposit deposit;
    private MagLimitSwitch magLimitSwitch;

    public VerticalRetractionCommand(Deposit deposit, MagLimitSwitch magLimitSwitch){
        this.deposit = deposit;
        this.magLimitSwitch = magLimitSwitch;
        addRequirements(this.deposit);
    }

    @Override
    public void initialize() {
        super.initialize();
    }

    @Override
    public void execute() {
        if(this.deposit.AverageSlideEncoderPose < 300){
            this.deposit.SetSlidePower(-0.3);

        }else{
            this.deposit.SetSlidePower(-0.7);
        }
    }

    @Override
    public void end(boolean interrupted) {
        this.deposit.SetSlidePower(0);
        this.deposit.resetSlideEncoders();
    }

    @Override
    public boolean isFinished() {
        return this.magLimitSwitch.getAtLimit();
    }
}
