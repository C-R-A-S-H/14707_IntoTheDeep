package org.firstinspires.ftc.teamcode.Robot.Commands.IntakeSubCommands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.Robot.Subsystems.Intake;

public class DropDownCommand extends CommandBase {

    private Intake intake;
    private double pose;
    public DropDownCommand( Intake intake,double pose){
        this.intake = intake;
        this.pose = pose;

    }

    @Override
    public void execute() {
        //double power = this.intake.getDropPid().calculate(this.pose,this.intake.distanceSensor.getDistance(DistanceUnit.MM));
        if(this.pose < 50) {
            this.intake.DropDown();
        }else{
            this.intake.IntakeUp();
        }
        //this.intake.setServoPower(power);
    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);
        this.intake.setServoPower(0);
    }

    @Override
    public boolean isFinished() {
        return tolerance(this.intake.distanceSensor.getDistance(DistanceUnit.MM), pose - 2, pose + 2);
    }

    private boolean tolerance(double value,double min,double max){
        return value >= min && value <= max;
    }
}
