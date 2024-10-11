package org.firstinspires.ftc.teamcode.Robot.Commands.HangCommands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Robot.Config;
import org.firstinspires.ftc.teamcode.Robot.Subsystems.Deposit;

public class PrepFirstStageCommand extends CommandBase {
    Deposit slides;
    public PrepFirstStageCommand(Deposit slides){
        this.slides = slides;
        addRequirements(this.slides);
    }
    @Override
    public void initialize() {
        super.initialize();
    }

    @Override
    public void execute() {
       this.slides.SetSlidePose(Config.FirstStagePrepSetpoint);
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
