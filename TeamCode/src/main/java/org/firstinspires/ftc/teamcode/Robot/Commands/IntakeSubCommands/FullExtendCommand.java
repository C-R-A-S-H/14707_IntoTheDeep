package org.firstinspires.ftc.teamcode.Robot.Commands.IntakeSubCommands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Pedrio.Vision.LimeLightHelper;
import org.firstinspires.ftc.teamcode.Robot.Config;
import org.firstinspires.ftc.teamcode.Robot.Subsystems.Enums.IntakeState;
import org.firstinspires.ftc.teamcode.Robot.Subsystems.Intake;

import java.util.ArrayList;
import java.util.List;

public class FullExtendCommand extends CommandBase {
    private Intake intake;
    private double pose;
    public FullExtendCommand(Intake intake, double pose){
        this.intake = intake;
        this.pose = pose;

    }
    @Override
    public void initialize() {
        this.intake.SetPower(-2000);
    }

    @Override
    public void execute() {
        //ea = ll.getDistanceFromSample(ll.getColorData().get(0));

        this.intake.intakeState = IntakeState.EXTENDING;
        this.intake.SetSlidePos(pose);


    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);
    }

    @Override
    public boolean isFinished() {
        return this.intake.SlideAtPoint(); // this.intake.tolerance(this.intake.HorizontalEncTicks, Config.FullyExtendedSlideEncPos - 3,Config.FullyExtendedSlideEncPos + 3);
    }
}
