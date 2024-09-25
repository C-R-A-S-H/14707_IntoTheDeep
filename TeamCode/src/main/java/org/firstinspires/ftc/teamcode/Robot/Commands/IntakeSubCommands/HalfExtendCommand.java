package org.firstinspires.ftc.teamcode.Robot.Commands.IntakeSubCommands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Pedrio.Vision.LimeLightHelper;
import org.firstinspires.ftc.teamcode.Robot.Config;
import org.firstinspires.ftc.teamcode.Robot.Subsystems.Intake;

public class HalfExtendCommand extends CommandBase {
    private Intake intake;
    private LimeLightHelper ll;

    public HalfExtendCommand(Intake intake, LimeLightHelper ll){
        this.intake = intake;
        this.ll = ll;

        addRequirements(this.intake,this.ll);
    }
    @Override
    public void initialize() {
        this.intake.SetPower(0);
    }

    @Override
    public void execute() {
        this.intake.SetSlidePos(Config.HalfExtendedSlideEncPos);
        this.intake.IntakeUp();
    }

    @Override
    public boolean isFinished() {
        return this.intake.tolerance(this.intake.HorizontalEncTicks * Config.HorizontalSlideTicksToInches, Config.HalfExtendedSlideEncPos - 0.5,Config.HalfExtendedSlideEncPos + 0.5);
    }
}
