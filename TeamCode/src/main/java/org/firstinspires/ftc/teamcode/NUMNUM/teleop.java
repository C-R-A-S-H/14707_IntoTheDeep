package org.firstinspires.ftc.teamcode.NUMNUM;

import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.IMU;

@TeleOp(name = "NUMNUM")
public class teleop extends OpMode {

    private MotorEx FLM;
    private MotorEx FRM;
    private MotorEx BLM;
    private MotorEx BRM;

    private MotorEx LS;
    private MotorEx RS;

    private MecanumDrive MD;

    private IMU imu;

    @Override
    public void init() {
        this.FLM = new MotorEx(hardwareMap, "FLM");
        this.FRM = new MotorEx(hardwareMap, "FRM");
        this.BLM = new MotorEx(hardwareMap, "BLM");
        this.BRM = new MotorEx(hardwareMap, "BRM");

        this.LS = new MotorEx(hardwareMap, "LS");
        this.RS = new MotorEx(hardwareMap, "RS");

        MD = new MecanumDrive(this.FLM, this.FRM, this.BLM, this.BRM);

        this.imu = hardwareMap.get(IMU.class, "imu");

        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.BACKWARD));
        // Without this, the REV Hub's orientation is assumed to be logo up / USB forward
        this.imu.initialize(parameters);
    }

    @Override
    public void loop() {
        this.MD.driveRobotCentric(
                -gamepad1.left_stick_x * 0.8,
                gamepad1.left_stick_y * 0.8,
                -gamepad1.right_stick_x * 0.8
        );

        double power = gamepad2.right_stick_x;

        this.LS.set(power);
        this.RS.set(power)
        ;
    }
}
