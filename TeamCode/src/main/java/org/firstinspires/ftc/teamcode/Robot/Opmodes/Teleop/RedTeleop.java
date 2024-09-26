package org.firstinspires.ftc.teamcode.Robot.Opmodes.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Robot.Hardware;

@TeleOp(name = "\uD83D\uDFE5 Murphy BLUE TELEOP \uD83D\uDFE5")
public class RedTeleop extends OpMode {
    private Hardware robot = Hardware.getInstance();
    @Override
    public void init() {
        this.robot.Init(hardwareMap);
    }

    @Override
    public void loop() {
        this.robot =  Hardware.getInstance();
        this.robot.Loop();
    }

    public void CreateBindings(){

    }
}