package org.firstinspires.ftc.teamcode.Robot.Commands.IntakeSubCommands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Robot.Subsystems.Enums.IntakeState;
import org.firstinspires.ftc.teamcode.Robot.Subsystems.Intake;

public class RetractionVoltageCommand extends CommandBase {
    private Intake intake;
    public RetractionVoltageCommand(Intake intake){
        this.intake = intake;
        addRequirements(this.intake);
    }

    @Override
    public void initialize() {
        this.intake.IntakeUp();
        this.intake.SetPower(-2000);
    }

    @Override
    public void execute() {
        this.intake.intakeState = IntakeState.RETRACTING;
        if(this.intake.HorizontalEncTicks < 300){
            this.intake.setSlideVelocity(-0.3 * 2000);
        }else{
            this.intake.setSlideVelocity(-0.7 * 2000);
        }
    }

    @Override
    public void end(boolean interrupted) {
        this.intake.setSlideVelocity(0);
        this.intake.resetEncoders();
    }

    @Override
    public boolean isFinished() {
       // return this.intake.getExtensionVoltage() > 4;
        return this.intake.HorizontalEncTicks <= 0;
    }
}
