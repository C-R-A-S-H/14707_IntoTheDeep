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
        super.initialize();
    }

    @Override
    public void execute() {
        this.intake.setSlidePower(-0.6);
        this.intake.intakeState = IntakeState.RETRACTING;
    }

    @Override
    public void end(boolean interrupted) {
        this.intake.setSlidePower(0);
        this.intake.resetEncoders();

    }

    @Override
    public boolean isFinished() {
        return this.magLimitSwitch.getAtLimit();
    }
}
