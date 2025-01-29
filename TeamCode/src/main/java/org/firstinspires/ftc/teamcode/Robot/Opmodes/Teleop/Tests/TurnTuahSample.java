package org.firstinspires.ftc.teamcode.Robot.Opmodes.Teleop.Tests;

import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Pedrio.Vision.LimeLightHelper;
import org.firstinspires.ftc.teamcode.Robot.Hardware;

import java.util.ArrayList;
import java.util.List;

@TeleOp(name = "Limelight line up")
public class TurnTuahSample extends OpMode {
    private Hardware robot = Hardware.getInstance();
    private double power;
    private LimeLightHelper ll;
    private PIDController pid = new PIDController(0.015,0,0);
    @Override
    public void init() {
        this.robot.Init(hardwareMap);

        CommandScheduler.getInstance().cancelAll();

        this.robot.drivetrain.follower.startTeleopDrive();
        ll = new LimeLightHelper();
        ll.init();

    }

    @Override
    public void loop() {
        this.robot =  Hardware.getInstance();
        this.robot.Loop();
        LLResult result = this.ll.getLL().getLatestResult();
        if (result != null) {
            if (result.isValid()) {
                List<LLResultTypes.ColorResult> colorResults = result.getColorResults();
                power =pid.calculate(0,colorResults.get(0).getTargetXDegrees());

            }else{
                power = 0;
            }

        }

        this.robot.drivetrain.follower.setTeleOpMovementVectors(0,0, -power );
        this.robot.drivetrain.follower.update();
    }
}
