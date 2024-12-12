package org.firstinspires.ftc.teamcode.Robot.Opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.teamcode.Robot.Commands.DrivetrainSubcommands.Mvmt.Movement;
import org.firstinspires.ftc.teamcode.Robot.Subsystems.Deposit;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.AnalogInput;

@Autonomous(name = "BlueNet", group = "Blue")
public class BlueNet extends OpMode {

    public DcMotorEx frontRight, frontLeft, backRight, backLeft;
    public AnalogInput otosX, otosY;
    private Movement mvm;
    private Deposit depositSubsystem;

    @Override
    public void init() {
        // Initialize motors
        frontRight = hardwareMap.get(DcMotorEx.class, "FrontRight");
        frontLeft = hardwareMap.get(DcMotorEx.class, "FrontLeft");
        backRight = hardwareMap.get(DcMotorEx.class, "BackRight");
        backLeft = hardwareMap.get(DcMotorEx.class, "BackLeft");

        // Initialize Lazer thingy
        otosX = hardwareMap.get(AnalogInput.class, "OTOS_X");
        otosY = hardwareMap.get(AnalogInput.class, "OTOS_Y");

        // Initialize Movement file
        mvm = new Movement(frontRight, frontLeft, backRight, backLeft, otosX, otosY);

        // Initialize Deposit Subsystem
        depositSubsystem = new Deposit(hardwareMap);

        // Reset encoders
        mvm.resetEncoders();
    }

    @Override
    public void start() {
        // start auto
        mvm.moveForward(0.5, 32.4);  // F 2.7ft
        mvm.pause(200);

        mvm.turnLeft(0.3, 180);
        mvm.pause(500);

        //Clip preloaded spec
        depositSubsystem.SetSlidePose(10);  // Set to desired position (Ex: 10 encoder ticks)
        depositSubsystem.SetServoPoses(0.5, 0.5, 0.5);  // Set pivot positions (range 0 to 1)
        depositSubsystem.ClawControl(0.8);  // Claw position (1 = open | 0 = close)
        if (depositSubsystem.SlideAtPoint()) {
            // Code to execute when the slide reaches the desired position
        }


        mvm.turnRight(0.3, -180);
        mvm.pause(500);

        mvm.strafeLeft(0.5, 43.2);  // SL 3.6ft
        mvm.pause(500);

        //Intake to outtake

        mvm.turnLeft(0.3, 10);
        mvm.pause(500);

        // Push out to net

        mvm.turnRight(0.3, -10);
        mvm.pause(500);

        mvm.strafeLeft(0.5, 2.4); // SL 0.2ft
        mvm.pause(500);

        //Intake to outtake

        mvm.turnLeft(0.3, 7);
        mvm.pause(500);

        // Push out to net

        mvm.turnRight(0.3, -7);
        mvm.pause(500);

        mvm.strafeLeft(0.5, 3.0); // SL 0.25ft
        mvm.pause(500);

        //Intake to outtake

        // Push out to net

        mvm.strafeRight(0.5, 21.6);  // SR 1.8ft
        mvm.pause(500);

        mvm.moveForward(0.5, 21.6); //  F 1.8ft
        mvm.pause(500);

        mvm.turnLeft(0.3, 90);
        mvm.pause(500);

        mvm.moveBackward(0.5, 3.6); //  B 0.3ft
        mvm.pause(500);

        // Stick out and touch hang bar
    }

    @Override
    public void loop() {
        depositSubsystem.periodic();
        if (mvm.motorsAreBusy()) {
            mvm.performPIDAdjustment();
        } else {
            mvm.stopMotors();
        }
        telemetry.addData("OTOS X Voltage: ", otosX.getVoltage());
        telemetry.addData("OTOS Y Voltage: ", otosY.getVoltage());
        telemetry.addData("FL Target: ", mvm.FLtarg);
        telemetry.addData("FL Current: ", frontLeft.getCurrentPosition());
        telemetry.addData("FR Target: ", mvm.FRtarg);
        telemetry.addData("FR Current: ", frontRight.getCurrentPosition());
        telemetry.addData("BL Target: ", mvm.BLtarg);
        telemetry.addData("BL Current: ", backLeft.getCurrentPosition());
        telemetry.addData("BR Target: ", mvm.BRtarg);
        telemetry.addData("BR Current: ", backRight.getCurrentPosition());
        telemetry.update();
    }
}
