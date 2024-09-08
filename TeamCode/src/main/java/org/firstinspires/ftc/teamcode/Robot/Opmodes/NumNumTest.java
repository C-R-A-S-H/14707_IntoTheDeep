package org.firstinspires.ftc.teamcode.Robot.Opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.Robot.Hardware;

@TeleOp(name = "NumNumTestBed")
public class NumNumTest extends OpMode {
    private Hardware robot = Hardware.getInstance();
    private Gamepad driver = new Gamepad();

    @Override
    public void init() {
        driver.setGamepadId(0);
        robot.Init(hardwareMap);

    }

    @Override
    public void loop() {
        this.robot =  Hardware.getInstance();

        this.robot.drivetrain.driveFieldCentric(-gamepad1.left_stick_x,
                gamepad1.left_stick_y,
                -gamepad1.right_stick_x,
                this.robot.imu.getRobotYawPitchRollAngles().getYaw());

        if (gamepad1.a){
            this.robot.imu.resetYaw();
        }

        telemetry.addData("ODO pose", this.robot.drivetrain.getPose());
    }
}
