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
        this.intake.SetPower(-2000);
    }

    @Override
    public void execute() {
        this.intake.intakeState = IntakeState.RETRACTING;
        this.intake.SetSlidePos(Config.RetractedSlideEncPos);

    }

    @Override
    public void end(boolean interrupted) {

        this.intake.IntakeUp();
        this.intake.SetPower(-2000);
        if (!interrupted){
            this.intake.resetEncoders();
        }

    }

    @Override
    public boolean isFinished() {
        return this.intake.tolerance(this.intake.HorizontalEncTicks, Config.RetractedSlideEncPos - 3,Config.RetractedSlideEncPos + 3);
    }
}
