package org.firstinspires.ftc.teamcode.Robot.Subsystems;

import org.firstinspires.ftc.teamcode.Pedrio.PedrioSubsystem;
import org.firstinspires.ftc.teamcode.Robot.Config;
import org.firstinspires.ftc.teamcode.Robot.Hardware;
import org.firstinspires.ftc.teamcode.Robot.Subsystems.Enums.DepositStates;
import org.firstinspires.ftc.teamcode.Robot.Subsystems.Enums.IntakeState;

public class Deposit extends PedrioSubsystem {
    private final Hardware robot = Hardware.getInstance();

    public DepositStates depositStates = DepositStates.ScoringBasketsHigh;
    public void SetSlidePower(double WantedPower) {
        robot.AVSlide.set(WantedPower);
        robot.BVSlide.set(WantedPower);
    }

    public void SetSlidePose(double WantedPos) {
        double PositionAverage = (double) (robot.AVSlide.getCurrentPosition() + robot.BVSlide.getCurrentPosition()) / 2;
        double Value = Config.VerticalController.calculate(WantedPos, PositionAverage);

        double FF = Config.HorizontalSlideFF;
        //s.intakeState == IntakeState.RETRACTING){
       //     FF  = -FF;
       // }

        SetSlidePower(Value);
    }
    
    public void SetServoPoses(double LeftPivot1,double RightPivot1,double Pivot2,double claw){
        this.robot.DepositPivot1Left.setPosition(LeftPivot1);
        this.robot.DepositPivot1Right.setPosition(RightPivot1);
        this.robot.DepositPivot2.setPosition(Pivot2);
        this.robot.DepositClaw.setPosition(claw);
    }
    
    @Override
    public void init() {

    }

    @Override
    public void periodic() {

    }
}
