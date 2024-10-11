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
    private LimeLightHelper ll;
    private double ea;
    public FullExtendCommand(Intake intake, LimeLightHelper ll){
        this.intake = intake;
        this.ll = ll;
        addRequirements(this.intake,this.ll);
    }
    @Override
    public void initialize() {
        this.intake.DropDown();
    }

    @Override
    public void execute() {
        ea = ll.getDistanceFromSample(ll.getColorData().get(0));
        this.intake.intakeState = IntakeState.EXTENDING;
        this.intake.ExtendLimelight(ea);
    }

    @Override
    public void end(boolean interrupted) {
        this.intake.intakeState = IntakeState.EXTENDED;

        super.end(interrupted);
    }

    @Override
    public boolean isFinished() {
        return this.intake.tolerance(this.intake.HorizontalEncTicks, ea - 0.5,ea + 0.5);
    }
}
