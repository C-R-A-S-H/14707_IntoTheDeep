package org.firstinspires.ftc.teamcode.Robot.Opmodes.Teleop.Tests;

import com.arcrobotics.ftclib.controller.PIDController;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Robot.Config;

@TeleOp(name = "ExtensionTest")
public class ExtensionTest extends OpMode {
    private PIDController pid = Config.HorizontalController;

    private MotorEx Extension;
    private MotorEx IntakeMotor;
    private Servo DropDownLeft;
    private Servo DropDownRight;
    double setpoint = 0;
    @Override
    public void init() {
        this.Extension = new MotorEx(hardwareMap,"HsSlide");
        this.Extension.setInverted(true);
        this.Extension.resetEncoder();


        this.IntakeMotor = new MotorEx(hardwareMap,"IntakeMotor");
        this.IntakeMotor.setInverted(true);

    }

    @Override
    public void loop() {


        if(gamepad1.a){
            setpoint = 900;
            //this.DropDownLeft.setPosition(1);
            //this.DropDownRight.setPosition(1);
            //this.IntakeMotor.setVelocity(2000);
        }
        if (gamepad1.b){
            setpoint = -50;
            //this.DropDownLeft.setPosition(0);
            //this.DropDownRight.setPosition(0);
            //this.IntakeMotor.setVelocity(0);
        }



        this.Extension.set(pid.calculate(this.Extension.getCurrentPosition(),setpoint));
        telemetry.addData("encoder", this.Extension.getCurrentPosition());
    }
}
