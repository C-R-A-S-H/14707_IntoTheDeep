package org.firstinspires.ftc.teamcode.NUMNUM;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "NUMNUM")
public class teleop extends OpMode {

    private static Hardware robot = Hardware.getInstance();

    @Override
    public void init() {
        robot = robot.getInstance();
        robot.init(hardwareMap);
    }

    @Override
    public void loop() {
        robot.loop();
        this.robot.drive(
                -gamepad1.left_stick_x * 0.8,
                gamepad1.left_stick_y * 0.8,
                -gamepad1.right_stick_x * 0.8
        );

        double power = gamepad2.right_stick_x;

        this.robot.LS.set(power);
        this.robot.RS.set(power);
    }
}
