package org.firstinspires.ftc.teamcode.JICE.Commands.BaseCommands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.JICE.Subsytems.Intake;

public class ExtensionToPositionCommand extends CommandBase {
    private Intake intake;
    private double pose;
    public ExtensionToPositionCommand(Intake intake, double pose){
        this.intake = intake;
        this.pose = pose;
        addRequirements(this.intake);
    }
    @Override
    public void initialize() {
        super.initialize();
    }

    @Override
    public void execute() {
        this.intake.goToPosition(this.pose);
    }

    @Override
    public void end(boolean interrupted) {
        this.intake.setExtensionPower(0);
        super.end(interrupted);
    }

    @Override
    public boolean isFinished() {
        return tolerance(this.intake.getEncoder(), -5,5);
    }
    public boolean tolerance(double value,double min,double max){
        return value >= min && value <= max;
    }
}
