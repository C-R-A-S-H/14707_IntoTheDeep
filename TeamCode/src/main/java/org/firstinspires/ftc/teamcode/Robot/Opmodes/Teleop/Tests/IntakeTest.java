package org.firstinspires.ftc.teamcode.Robot.Opmodes.Teleop.Tests;

import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "intake test")
public class IntakeTest extends OpMode {
    private MotorEx intake;
    @Override
    public void init() {
        intake = new MotorEx(hardwareMap, "intakeMotor");
    }

    @Override
    public void loop() {
        intake.setVelocity(gamepad1.left_stick_y * 2000);
    }
}
