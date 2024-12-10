package org.firstinspires.ftc.teamcode.NUMNUM;

import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "NUMNUM")
public class teleop extends OpMode {

    private MotorEx FLM;
    private MotorEx FRM;
    private MotorEx BLM;
    private MotorEx BRM;

    private MecanumDrive MD;

    @Override
    public void init() {
        this.FLM = new MotorEx(hardwareMap, "FLM");
        this.FRM = new MotorEx(hardwareMap, "FRM");
        this.BLM = new MotorEx(hardwareMap, "BLM");
        this.BRM = new MotorEx(hardwareMap, "BRM");

        MD = new MecanumDrive(this.FRM, this.FRM, this.BLM, this.BRM);
    }

    @Override
    public void loop() {
        this.MD.driveRobotCentric(
                -gamepad1.left_stick_x * 0.8,
                gamepad1.left_stick_y * 0.8,
                -gamepad1.right_stick_x * 0.8
        );
    }
}
