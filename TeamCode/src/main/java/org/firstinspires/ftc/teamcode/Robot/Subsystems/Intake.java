package org.firstinspires.ftc.teamcode.Robot.Subsystems;

import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;
import org.firstinspires.ftc.teamcode.Pedrio.PedrioSubsystem;
import org.firstinspires.ftc.teamcode.Robot.Config;
import org.firstinspires.ftc.teamcode.Robot.Hardware;
import org.firstinspires.ftc.teamcode.Robot.Subsystems.Enums.IntakeState;

public class Intake extends PedrioSubsystem {
    private final Hardware robot = Hardware.getInstance();

    private MotorEx IntakeMotor;
    private MotorEx HsSlide;

    public Servo DropDownLeft;
    public Servo DropDownRight;

    public double setpoint;

    public Intake(HardwareMap hmap){
        this.HsSlide = new MotorEx(hmap, "HsSlide");
        this.IntakeMotor = new MotorEx(hmap,"IntakeMotor");
        this.DropDownLeft = hmap.get(Servo.class, "DropDownLeft");
        this.DropDownRight = hmap.get(Servo.class,"DropDownRight");

        this.HsSlide.setInverted(true);
    }

    public IntakeState intakeState = IntakeState.DISABLED;
    public double HorizontalEncTicks = 0;

    public void SetPower(double velocity) {
        this.IntakeMotor.setVelocity(velocity);
    }

    public void setSlideVelocity(double power){
        this.HsSlide.setVelocity(power);
    }

    public void setSlidePower(double power){this.HsSlide.set(power);}
    public void setIntakePower(double power){this.HsSlide.set(power);}


    public void SetIntakeVelocity(double velocity){this.IntakeMotor.setVelocity(velocity);}

    public double getExtensionVoltage(){
        return this.HsSlide.motorEx.getCurrentAlert(CurrentUnit.AMPS);
    }

    public void DropDown() {
        this.DropDownLeft.setPosition(Config.DropDownPoseLeft);
        this.DropDownRight.setPosition(Config.DropDownPoseRight);
        this.intakeState = IntakeState.EXTENDING;
    }
    public void IntakeUp(){
        this.DropDownLeft.setPosition(Config.IntakeUpPoseLeft);
        this.DropDownRight.setPosition(Config.IntakeUpPoseRight);
        this.intakeState = IntakeState.RETRACTING;
    }

    public void SetSlidePos(double WantedPos){
        //this.setpoint = WantedPos;
        double Value = Config.HorizontalController.calculate(this.HsSlide.getCurrentPosition(),WantedPos);
        //double FF = Config.HorizontalSlideFF;
        //if (this.intakeState == IntakeState.RETRACTING || this.intakeState == IntakeState.AT_ZERO){
         //   FF  = -FF;
        //}
        this.HsSlide.set(Value);
    }


    public void ExtendLimelight(double distance){
        double Value = Config.HorizontalController.calculate(Config.TolerantDistanceFromSample,distance);
        double FF = Config.HorizontalSlideFF;
        if (this.intakeState == IntakeState.RETRACTING){
            FF  = -FF;
        }
        this.HsSlide.set(Value + FF);

    }
    public double GetSlidePos(){
        return this.HsSlide.getCurrentPosition();
    }
    public void resetEncoders(){
        this.HsSlide.resetEncoder();
    }

    @Override
    public void init() {

    }

    @Override
    public void periodic() {
       // SetSlidePos(setpoint);
        this.HorizontalEncTicks = this.HsSlide.getCurrentPosition();
    }

    public boolean tolerance(double value,double min,double max){
        return value >= min && value <= max;
    }
    private double averageError(double x, double y, double turn){
        return (x + y + turn) / 3;
    }
}
