package org.firstinspires.ftc.teamcode.Robot.Commands.IntakeSubCommands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Pedrio.Sensors.MagLimitSwitch;
import org.firstinspires.ftc.teamcode.Robot.Config;
import org.firstinspires.ftc.teamcode.Robot.Subsystems.Enums.IntakeState;
import org.firstinspires.ftc.teamcode.Robot.Subsystems.Intake;

public class RetractionCommand extends CommandBase {
    private Intake intake;

    public RetractionCommand(Intake intake){
        this.intake = intake;
    }
    @Override
    public void initialize() {
        this.intake.IntakeUp();

    }

    @Override
    public void execute() {

       // this.intake.intakeState = IntakeState.RETRACTING;
        this.intake.SetSlidePos(Config.RetractedSlideEncPos);

    }

    @Override
    public void end(boolean interrupted) {
        this.intake.setSlidePower(-0.1);

    }

    @Override
    public boolean isFinished() {
        return this.intake.tolerance(this.intake.GetSlidePos(), Config.RetractedSlideEncPos - 1,Config.RetractedSlideEncPos + 1);
    }
}
