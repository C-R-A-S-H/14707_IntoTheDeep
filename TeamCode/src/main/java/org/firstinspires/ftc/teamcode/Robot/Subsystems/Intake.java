package org.firstinspires.ftc.teamcode.Robot.Subsystems;

import org.firstinspires.ftc.teamcode.Pedrio.PedrioSubsystem;
import org.firstinspires.ftc.teamcode.Robot.Config;
import org.firstinspires.ftc.teamcode.Robot.Hardware;
import org.firstinspires.ftc.teamcode.Robot.Subsystems.Enums.IntakeState;

public class Intake extends PedrioSubsystem {
    private final Hardware robot = Hardware.getInstance();
    public IntakeState intakeState = IntakeState.DISABLED;
    public double HorizontalEncTicks = this.robot.HsSlide.getCurrentPosition();

    public void SetPower(double velocity) {
        robot.IntakeMotor.setVelocity(velocity);
    }

    public void setSlideVelocity(double power){
        this.robot.HsSlide.setVelocity(power);
    }

    public void DropDown() {
        robot.DropDownLeft.setPosition(Config.DropDownPoseLeft);
        robot.DropDownRight.setPosition(Config.DropDownPoseRight);
        this.intakeState = IntakeState.EXTENDING;
    }
    public void IntakeUp(){
        robot.DropDownLeft.setPosition(Config.IntakeUpPoseLeft);
        robot.DropDownRight.setPosition(Config.IntakeUpPoseRight);
        this.intakeState = IntakeState.RETRACTING;
    }

    public void SetSlidePos(double WantedPos) {
        double Value = Config.HorizontalController.calculate(WantedPos, HorizontalEncTicks * Config.HorizontalSlideTicksToInches);
        double FF = Config.HorizontalSlideFF;
        if (this.intakeState == IntakeState.RETRACTING){
            FF  = -FF;
        }
        robot.HsSlide.set(Value + FF);
    }
    public void ExtendLimelight(double distance){
        double Value = Config.HorizontalController.calculate(Config.TolerantDistanceFromSample,distance);
        double FF = Config.HorizontalSlideFF;
        if (this.intakeState == IntakeState.RETRACTING){
            FF  = -FF;
        }
        robot.HsSlide.set(Value + FF);

    }

    public void resetEncoders(){
        this.robot.HsSlide.resetEncoder();
    }

    @Override
    public void init() {

    }

    @Override
    public void periodic() {
        HorizontalEncTicks = robot.HsSlide.getCurrentPosition();
    }

    public boolean tolerance(double value,double min,double max){
        return value >= min && value <= max;
    }
    private double averageError(double x, double y, double turn){
        return (x + y + turn) / 3;
    }
}
