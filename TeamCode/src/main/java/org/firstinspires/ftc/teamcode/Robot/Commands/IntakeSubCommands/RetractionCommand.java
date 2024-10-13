package org.firstinspires.ftc.teamcode.Robot.Commands.IntakeSubCommands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Pedrio.Sensors.MagLimitSwitch;
import org.firstinspires.ftc.teamcode.Robot.Subsystems.Enums.IntakeState;
import org.firstinspires.ftc.teamcode.Robot.Subsystems.Intake;

public class RetractionCommand extends CommandBase {
    private Intake intake;
    private MagLimitSwitch magLimitSwitch;
    public RetractionCommand(Intake intake, MagLimitSwitch magLimitSwitch){
        this.intake = intake;
        this.magLimitSwitch = magLimitSwitch;
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
        return this.magLimitSwitch.getAtLimit();
    }
}
