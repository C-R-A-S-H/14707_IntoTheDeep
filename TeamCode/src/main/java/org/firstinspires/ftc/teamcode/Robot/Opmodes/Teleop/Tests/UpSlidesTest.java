package org.firstinspires.ftc.teamcode.Robot.Opmodes.Teleop.Tests;

import com.arcrobotics.ftclib.controller.PIDController;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "LinearSlideTest")
public class UpSlidesTest extends OpMode {
    private PIDController pid = new PIDController(0.01,0,0);
    public static double p = 0.01;
    public static double d = 0;
    private MotorEx SlideA;
    private MotorEx SlideB;
    double setpoint = 0;
    @Override
    public void init() {
        this.SlideA = new MotorEx(hardwareMap,"LeftVSlide");
        this.SlideB = new MotorEx(hardwareMap,"RightVSlide");

        this.SlideB.setInverted(true);
        this.SlideA.setInverted(true);

        this.SlideA.resetEncoder();
        this.SlideB.resetEncoder();
    }

    @Override
    public void loop() {
        this.pid.setPID(p,0,d);

        if(gamepad1.a){
            setpoint = 800;
        }
        if (gamepad1.b){
            setpoint = 0;
        }
        double pose = (this.SlideA.getCurrentPosition() + this.SlideB.getCurrentPosition()) / 2;
        //double power = pid.calculate(pose, setpoint);
        this.SlideA.set(gamepad1.left_stick_y);
        this.SlideB.set(gamepad1.left_stick_y);




        telemetry.addData("encoder", pose);
    }
}
