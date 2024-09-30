package org.firstinspires.ftc.teamcode.Robot.Commands.IntakeSubCommands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Pedrio.Sensors.BeamBreak;
import org.firstinspires.ftc.teamcode.Robot.Subsystems.Enums.IntakeState;
import org.firstinspires.ftc.teamcode.Robot.Subsystems.Intake;

public class IntakeActivationCommand extends CommandBase {
    private Intake intake;
    private BeamBreak TransferBeamBreak;
    public IntakeActivationCommand(Intake intake, BeamBreak TransferBeamBreak){
        this.intake = intake;
        this.TransferBeamBreak = TransferBeamBreak;
        addRequirements(this.intake);
    }

    @Override
    public void initialize() {
        this.intake.SetPower(100);
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
        return this.TransferBeamBreak.getTriggered();
    }
}
