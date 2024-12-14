package org.firstinspires.ftc.teamcode.Robot.Opmodes.Teleop.Tests;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "DepositTestServo")
public class DepositTest extends OpMode {
    private Servo DepositPivot1Left;
    private Servo DepositPivot1Right;
    private Servo DepositPivot2;//on the right
    private Servo DepositClaw;
    @Override
    public void init() {
        this.DepositClaw = hardwareMap.get(Servo.class,"DepositClaw");
        this.DepositPivot1Left = hardwareMap.get(Servo.class,"DepositPivot1Left");
        this.DepositPivot1Right = hardwareMap.get(Servo.class,"DepositPivot1Right");
        this.DepositPivot1Left.setDirection(Servo.Direction.FORWARD);
        this.DepositPivot1Right.setDirection(Servo.Direction.REVERSE);
        this.DepositPivot2 = hardwareMap.get(Servo.class,"DepositPivot2");
    }

    @Override
    public void loop() {
        if(gamepad1.a){
            SetServoPoses(0,0,1); //picking up pose 0 0 1
        }
        if (gamepad1.b) {
            SetServoPoses(1,1,0); // 1 1 0
        }


      //  if(gamepad1.a){
      //      ClawControl(0);
       // }
       // if(gamepad1.b){
       //     ClawControl(0.33);
       // }

    }
    public void SetServoPoses(double LeftPivot1,double RightPivot1,double Pivot2){
        this.DepositPivot1Left.setPosition(LeftPivot1);
        this.DepositPivot1Right.setPosition(RightPivot1);
        this.DepositPivot2.setPosition(Pivot2);

    }
    public void ClawControl(double claw){
        this.DepositClaw.setPosition(claw);
    }

}
