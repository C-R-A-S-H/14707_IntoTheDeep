package org.firstinspires.ftc.teamcode.JICE.Commands.BaseCommands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.JICE.Subsytems.Pivot;

public class ArmToPosistionCommand extends CommandBase {
    private Pivot pivot;
    private double pose;

    public ArmToPosistionCommand(Pivot pivot, double pos){
        this.pivot = pivot;
        this.pose = pos;
        addRequirements(this.pivot);
    }
    @Override
    public void initialize() {
        super.initialize();
    }

    @Override
    public void execute() {
        this.pivot.goToPosition(pose);
    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);
    }

    @Override
    public boolean isFinished() {
        return tolerance(this.pivot.getEncoder(),-5,5);
    }

    public boolean tolerance(double value,double min,double max){
        return value >= min && value <= max;
    }

}
