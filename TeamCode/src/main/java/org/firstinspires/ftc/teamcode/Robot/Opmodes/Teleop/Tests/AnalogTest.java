package org.firstinspires.ftc.teamcode.Robot.Opmodes.Teleop.Tests;

import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Pedrio.Sensors.AbsoluteAnalogEncoder;
import org.firstinspires.ftc.teamcode.Robot.Config;


@TeleOp(name = "analog test")
public class AnalogTest extends OpMode {
   private AnalogInput RightPose;
   private AnalogInput LeftPose;
    private CRServo DropDownLeft;
    private CRServo DropDownRight;
    private AbsoluteAnalogEncoder left;
    private AbsoluteAnalogEncoder right;
    private double setpoint = 0;
    @Override
    public void init() {
        DropDownRight = hardwareMap.get(CRServo.class,"DropDownRight");
        DropDownLeft = hardwareMap.get(CRServo.class, "DropDownLeft");
       RightPose = hardwareMap.get(AnalogInput.class, "RightPose");
       LeftPose = hardwareMap.get(AnalogInput.class,"LeftPose");

       //this.DropDownLeft.setDirection(DcMotorSimple.Direction.REVERSE);

       left = new AbsoluteAnalogEncoder(LeftPose);
       //left.setInverted(true);
       right = new AbsoluteAnalogEncoder(RightPose);

    }

    @Override
    public void loop() {
        double Rightposition = right.getCurrentPosition();
        double Leftposition = left.getCurrentPosition();

        if (gamepad1.a){
            setpoint = 0;
        }
        if (gamepad1.b){
            setpoint = 5;
        }
        DropDownLeft.setPower(Config.LeftIntakePivotController.calculate(Leftposition, setpoint));
        DropDownRight.setPower(Config.RightIntakePivotController.calculate(Rightposition,setpoint));
        telemetry.addData("right pos",Rightposition);
        telemetry.addData("left pos", Leftposition);
        telemetry.addData("left volt", left.getVoltage());
        telemetry.addData("right volt", right.getVoltage());
        telemetry.update();
    }
}
