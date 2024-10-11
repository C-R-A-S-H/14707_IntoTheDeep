package org.firstinspires.ftc.teamcode.Robot.Opmodes.Teleop.Tests;

import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.IMU;

@TeleOp(name = "drivetrain test")
public class DrivetrainTest extends OpMode {
    private MotorEx frontLeft;
    private MotorEx frontRight;
    private MotorEx backLeft;
    private MotorEx backRight;
    private IMU imu;
    private MecanumDrive drive;
    @Override
    public void init() {
        this.frontLeft = new MotorEx(hardwareMap,"frontLeft");
        this.frontRight = new MotorEx(hardwareMap, "frontRight");
        this.backLeft = new MotorEx(hardwareMap, "backLeft");
        this.backRight = new MotorEx(hardwareMap, "backRight");

        this.imu = hardwareMap.get(IMU.class, "imu");

        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.RIGHT,
                RevHubOrientationOnRobot.UsbFacingDirection.UP));
        // Without this, the REV Hub's orientation is assumed to be logo up / USB forward
        this.imu.initialize(parameters);

        drive = new MecanumDrive(this.frontLeft, this.frontRight, this.backLeft, this.backRight);


    }

    @Override
    public void loop() {
            drive.driveRobotCentric(
                    -gamepad1.left_stick_x,
                    gamepad1.left_stick_y,
                    -gamepad1.right_stick_x

            );
    }
}
