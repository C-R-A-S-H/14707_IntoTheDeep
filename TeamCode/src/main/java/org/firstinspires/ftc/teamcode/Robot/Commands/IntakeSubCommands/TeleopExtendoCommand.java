package org.firstinspires.ftc.teamcode.Robot.Commands.IntakeSubCommands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Pedrio.Sensors.ZLColorSensor;
import org.firstinspires.ftc.teamcode.Robot.Config;
import org.firstinspires.ftc.teamcode.Robot.Subsystems.Enums.IntakeState;
import org.firstinspires.ftc.teamcode.Robot.Subsystems.Intake;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

public class TeleopExtendoCommand extends CommandBase {
    private Intake intake;
    private DoubleSupplier val;
    private BooleanSupplier pid;
    public TeleopExtendoCommand(Intake intake, DoubleSupplier val, BooleanSupplier pid){
        this.intake = intake;
        this.val = val;
        this.pid = pid;
    }



    @Override
    public void execute() {
        if (!this.pid.getAsBoolean()) {
            this.intake.SetSlidePos(val.getAsDouble() * 900);
        }

    }

    @Override
    public void end(boolean interrupted) {

        super.end(interrupted);
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
