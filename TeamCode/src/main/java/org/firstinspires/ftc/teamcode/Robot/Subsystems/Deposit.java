package org.firstinspires.ftc.teamcode.Robot.Subsystems;

import org.firstinspires.ftc.teamcode.Pedrio.PedrioSubsystem;
import org.firstinspires.ftc.teamcode.Robot.Config;
import org.firstinspires.ftc.teamcode.Robot.Hardware;
import org.firstinspires.ftc.teamcode.Robot.Subsystems.Enums.DepositStates;

public class Deposit extends PedrioSubsystem {
    private final Hardware robot = Hardware.getInstance();


    public double LeftSlidePose = 0;
    public double RightSlidePose = 0;

    public double AverageSlideEncoderPose = 0;

    public DepositStates depositStates = DepositStates.ScoringBasketsHigh;

    public void SetSlidePower(double WantedPower) {
        robot.LeftVSlide.set(WantedPower);
        robot.RightVSlide.set(WantedPower);
    }

    public void SetSlidePose(double WantedPos) {
        double PositionAverage = (double) (robot.LeftVSlide.getCurrentPosition() + robot.RightVSlide.getCurrentPosition()) / 2;
        double Value = Config.VerticalController.calculate(WantedPos, PositionAverage);
        double FF = Config.VerticalSlideFF;

        if(depositStates == DepositStates.Retracting){
         FF  = -FF;
        }

        SetSlidePower(Value + FF);
    }
    
    public void SetServoPoses(double LeftPivot1,double RightPivot1,double Pivot2){
        this.robot.DepositPivot1Left.setPosition(LeftPivot1);
        this.robot.DepositPivot1Right.setPosition(RightPivot1);
        this.robot.DepositPivot2.setPosition(Pivot2);

    }
    public void ClawControl(double claw){
        this.robot.DepositClaw.setPosition(claw);
    }

    public boolean SlideAtPoint(){
        return tolerance(averageError(LeftSlidePose, RightSlidePose), -5,5);
    }
    public void resetSlideEncoders(){
        this.robot.LeftVSlide.resetEncoder();
        this.robot.RightVSlide.resetEncoder();
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
        AverageSlideEncoderPose = averageError(this.robot.LeftVSlide.getCurrentPosition(), this.robot.RightVSlide.getCurrentPosition());
    }
}
