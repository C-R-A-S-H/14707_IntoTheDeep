package org.firstinspires.ftc.teamcode.Robot.Commands.IntakeSubCommands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Pedrio.Sensors.ZLColorSensor;
import org.firstinspires.ftc.teamcode.Robot.Config;
import org.firstinspires.ftc.teamcode.Robot.Subsystems.Enums.IntakeState;
import org.firstinspires.ftc.teamcode.Robot.Subsystems.Intake;

import java.util.function.DoubleSupplier;

public class TeleopExtendoCommand extends CommandBase {
    private Intake intake;
    private ZLColorSensor colorSensor;
    public TeleopExtendoCommand(Intake intake, ZLColorSensor colorSensor){
        this.intake = intake;
        this.colorSensor = colorSensor;
    }

    @Override
    public void initialize() {
        this.intake.DropDown();
    }

    @Override
    public void execute() {
        if(this.intake.intakeState != IntakeState.RETRACTING) {
           this.intake.SetSlidePos(Config.HorizontalSlideTeleopSetpoint);
        }
    }

    @Override
    public void end(boolean interrupted) {
        this.intake.setSlideVelocity(0);
        super.end(interrupted);
    }

    @Override
    public boolean isFinished() {
        return this.intake.tolerance(intake.HorizontalEncTicks, Config.HorizontalSlideTeleopSetpoint - 10, Config.HorizontalSlideTeleopSetpoint + 10);
    }
}
