package org.firstinspires.ftc.teamcode.Robot.Opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.Robot.Hardware;

@TeleOp(name = "NumNumTestBed")
public class NumNumTest extends OpMode {
    private Hardware robot = Hardware.getInstance();
    static final double TRACKWIDTH = 16;
    static final double CENTER_WHEEL_OFFSET = 2.4;

    @Override
    public void init() {
        this.robot.Init(hardwareMap);

    }

    @Override
    public void loop() {
        this.robot =  Hardware.getInstance();
        this.robot.Loop();

        this.robot.drivetrain.driveFieldCentric(-gamepad1.left_stick_x,
                gamepad1.left_stick_y,
                -gamepad1.right_stick_x,
                this.robot.imu.getRobotYawPitchRollAngles().getYaw());

        if (gamepad1.a){
            this.robot.imu.resetYaw();
        }


    }
}
