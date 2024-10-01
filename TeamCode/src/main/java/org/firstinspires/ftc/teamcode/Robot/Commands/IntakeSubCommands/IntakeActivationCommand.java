package org.firstinspires.ftc.teamcode.Robot.Commands.IntakeSubCommands;

import android.graphics.Color;

import com.arcrobotics.ftclib.command.CommandBase;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;

import org.firstinspires.ftc.teamcode.Pedrio.Sensors.BeamBreak;
import org.firstinspires.ftc.teamcode.Robot.Config;
import org.firstinspires.ftc.teamcode.Robot.Subsystems.Enums.IntakeState;
import org.firstinspires.ftc.teamcode.Robot.Subsystems.Intake;

public class IntakeActivationCommand extends CommandBase {
    private Intake intake;
    private NormalizedColorSensor transferColorSensor;
    public IntakeActivationCommand(Intake intake, NormalizedColorSensor transferColorSensor){
        this.intake = intake;
        this.transferColorSensor = transferColorSensor;
        addRequirements(this.intake);
    }

    @Override
    public void initialize() {
        this.intake.SetPower(400);
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
        return checkForSample();
    }

    public boolean checkForSample(){
        if(Config.AllianceIsBlue){
            return (this.transferColorSensor.getNormalizedColors().toColor() == Config.BlueSampleColor || this.transferColorSensor.getNormalizedColors().toColor() == Config.YellowSampleColor);
        }
        return (this.transferColorSensor.getNormalizedColors().toColor() == Config.RedSampleColor || this.transferColorSensor.getNormalizedColors().toColor() == Config.YellowSampleColor);
    }
}
