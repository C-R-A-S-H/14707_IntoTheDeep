package org.firstinspires.ftc.teamcode.Robot.Commands.IntakeSubCommands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Pedrio.Sensors.BeamBreak;
import org.firstinspires.ftc.teamcode.Robot.Subsystems.Enums.IntakeState;
import org.firstinspires.ftc.teamcode.Robot.Subsystems.Intake;

public class IntakeActivationCommand extends CommandBase {
    private Intake intake;
    private BeamBreak bb;
    public IntakeActivationCommand(Intake intake, BeamBreak bb){
        this.intake = intake;
        this.bb = bb;
        addRequirements(this.intake);
    }

    @Override
    public void initialize() {
        this.intake.SetPower(0.6);
        this.intake.intakeState = IntakeState.INTAKING;
    }

    @Override
    public void execute() {
        super.execute();
    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);
    }

    @Override
    public boolean isFinished() {
        return this.bb.getTriggered();
    }
}
