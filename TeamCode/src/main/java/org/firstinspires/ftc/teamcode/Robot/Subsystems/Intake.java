package org.firstinspires.ftc.teamcode.Robot.Subsystems;

import com.arcrobotics.ftclib.controller.PIDController;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorRangeSensor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.Pedrio.PedrioSubsystem;
import org.firstinspires.ftc.teamcode.Robot.Config;
import org.firstinspires.ftc.teamcode.Robot.Hardware;
import org.firstinspires.ftc.teamcode.Robot.Subsystems.Enums.IntakeState;

public class Intake extends PedrioSubsystem {
    private final Hardware robot = Hardware.getInstance();

    private MotorEx IntakeMotor;
    private MotorEx HsSlide;

    public CRServo DropDownLeft;
    public CRServo  DropDownRight;

    public ColorRangeSensor sensor;
    public DistanceSensor distanceSensor;


    public double setpoint;

    private double intakePivotSetpoint = 355;

    private PIDController DropPid = new PIDController(0.01,0,0);

    public Intake(HardwareMap hmap){
        this.HsSlide = new MotorEx(hmap, "HsSlide");
        this.IntakeMotor = new MotorEx(hmap,"IntakeMotor");
        this.DropDownLeft = hmap.get(CRServo.class, "DropDownLeft");
        this.DropDownRight = hmap.get(CRServo.class,"DropDownRight");

        this.DropDownLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        this.sensor = hmap.get(ColorRangeSensor.class,"s");
        this.distanceSensor = hmap.get(DistanceSensor.class,"d");

        //this.LeftServoPose = hmap.get(AnalogInput.class,"LeftPose");
        //this.RightServoPose = hmap.get(AnalogInput.class, "RightPose");

        this.HsSlide.setInverted(true);
    }

    public PIDController getDropPid(){
        return this.DropPid;
    }

    public boolean GetSampleIntaked(){
        return this.sensor.getLightDetected() > 0.3;
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

    public void IntakeToAutoPose(){
        this.intakePivotSetpoint = 355;
    }
    public void DropDown() {
        this.intakePivotSetpoint = 10;
        this.intakeState = IntakeState.EXTENDING;
    }
    public void IntakeUp(){
        this.intakePivotSetpoint = 355;
        this.intakeState = IntakeState.RETRACTING;
    }

    public void setServoPower(double power){
        this.DropDownLeft.setPower(-power);
        this.DropDownRight.setPower(-power);
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

    public boolean SlideAtPoint(){
        return this.tolerance(this.HsSlide.getCurrentPosition(), -2,2);
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

        //double Rightposition = RightServoPose.getVoltage() / 3.3 * 360;
        //double Leftposition = LeftServoPose.getVoltage() / 3.3 * 360;

        //this.DropDownLeft.setPower(Config.LeftIntakePivotController.calculate(Math.abs(Leftposition),this.intakePivotSetpoint));
        //this.DropDownRight.setPower(Config.RightIntakePivotController.calculate(Math.abs(Rightposition),this.intakePivotSetpoint));


    }

    public boolean tolerance(double value,double min,double max){
        return value >= min && value <= max;
    }
    private double averageError(double x, double y, double turn){
        return (x + y + turn) / 3;
    }
}
