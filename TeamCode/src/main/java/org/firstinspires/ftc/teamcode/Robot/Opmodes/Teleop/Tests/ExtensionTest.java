package org.firstinspires.ftc.teamcode.Robot.Opmodes.Teleop.Tests;

import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "ExtensionTest")
public class ExtensionTest extends OpMode {

    private MotorEx Extension;
    @Override
    public void init() {
        this.Extension = new MotorEx(hardwareMap,"ex");
    }

    @Override
    public void loop() {
        this.Extension.set(gamepad1.left_stick_y);
    }
}
