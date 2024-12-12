package org.firstinspires.ftc.teamcode.Robot.Subsystems;

import com.arcrobotics.ftclib.hardware.motors.CRServo;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Pedrio.PedrioSubsystem;
import org.firstinspires.ftc.teamcode.Robot.Config;
import org.firstinspires.ftc.teamcode.Robot.Hardware;
import org.firstinspires.ftc.teamcode.Robot.Subsystems.Enums.DepositStates;

public class Deposit extends PedrioSubsystem {


    public double LeftSlidePose = 0;
    public double RightSlidePose = 0;

    public double AverageSlideEncoderPose = 0;

    public DepositStates depositStates = DepositStates.ScoringBasketsHigh;

    private Servo DepositPivot1Left;
    private Servo DepositPivot1Right;
    private Servo DepositPivot2;//on the right
    private Servo DepositClaw;



    public MotorEx LeftVSlide;
    public MotorEx RightVSlide;

    public Deposit(HardwareMap hmap){

        this.LeftVSlide = new MotorEx(hmap, "LeftVSlide");
        this.RightVSlide = new MotorEx(hmap,"RightVSlide");

        this.DepositClaw = hmap.get(Servo.class,"DepositClaw");
        this.DepositPivot1Left = hmap.get(Servo.class,"DepositPivot1Left");
        this.DepositPivot1Right = hmap.get(Servo.class,"DepositPivot1Right");

        this.DepositPivot1Left.setDirection(Servo.Direction.FORWARD);
        this.DepositPivot1Right.setDirection(Servo.Direction.REVERSE);
        this.DepositPivot2 = hmap.get(Servo.class,"DepositPivot2");

        this.LeftVSlide.setInverted(true);
        this.RightVSlide.setInverted(true);
    }

    public void SetSlidePower(double WantedPower) {
        this.LeftVSlide.set(WantedPower);
        this.RightVSlide.set(WantedPower);

    }

    public void SetSlidePose(double WantedPos) {
        double PositionAverage = (double) (this.LeftVSlide.getCurrentPosition() + this.RightVSlide.getCurrentPosition()) / 2;
        double Value = Config.VerticalController.calculate(PositionAverage, WantedPos);

        SetSlidePower(Value);
    }
    
    public void SetServoPoses(double LeftPivot1,double RightPivot1,double Pivot2){
        this.DepositPivot1Left.setPosition(LeftPivot1);
        this.DepositPivot1Right.setPosition(RightPivot1);
        this.DepositPivot2.setPosition(Pivot2);

    }
    public void ClawControl(double claw){
        this.DepositClaw.setPosition(claw);
    }

    public boolean SlideAtPoint(){
        return tolerance(averageError(LeftSlidePose, RightSlidePose), -5,5);
    }
    public void resetSlideEncoders(){
        this.LeftVSlide.resetEncoder();
        this.RightVSlide.resetEncoder();
    }

    private boolean tolerance(double value,double min,double max){
        return value >= min && value <= max;
    }
    private double averageError(double a, double b){
        return (a + b) / 2;
    }
    @Override
    public void init() {
    }

    @Override
    public void periodic() {
        AverageSlideEncoderPose = averageError(this.LeftVSlide.getCurrentPosition(), this.RightVSlide.getCurrentPosition());
    }
}
