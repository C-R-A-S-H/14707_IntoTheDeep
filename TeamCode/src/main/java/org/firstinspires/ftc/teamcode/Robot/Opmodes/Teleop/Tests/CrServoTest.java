package org.firstinspires.ftc.teamcode.Robot.Opmodes.Teleop.Tests;

import com.arcrobotics.ftclib.hardware.motors.CRServo;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "CrServoTest")
public class CrServoTest extends OpMode {
    private Servo servo;
    private Servo left;
    private Servo right;
    @Override
    public void init() {
        servo = hardwareMap.get(Servo.class,"i");
        left = hardwareMap.get(Servo.class,"left");
        right = hardwareMap.get(Servo.class, "right");

        left.setDirection(Servo.Direction.REVERSE);
    }

    @Override
    public void loop() {


    }
}
