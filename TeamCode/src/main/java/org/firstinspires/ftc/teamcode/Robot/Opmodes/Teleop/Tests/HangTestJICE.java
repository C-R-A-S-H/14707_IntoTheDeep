package org.firstinspires.ftc.teamcode.Robot.Opmodes.Teleop.Tests;

import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "hangtest")
public class HangTestJICE extends OpMode {
    private MotorEx hang;
    @Override
    public void init() {
        hang = new MotorEx(hardwareMap,"h");
    }

    @Override
    public void loop() {
            hang.set(gamepad1.left_stick_y);
    }
}
