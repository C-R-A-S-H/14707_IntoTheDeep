package org.firstinspires.ftc.teamcode.Robot.Opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.teamcode.Robot.Commands.DrivetrainSubcommands.Mvmt.Movement;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.AnalogInput;

@Autonomous(name = "BlueNet", group = "Blue")
public class BlueNet extends OpMode {

    public DcMotorEx frontRight, frontLeft, backRight, backLeft;
    public AnalogInput otosX, otosY;
    private Movement mvm;

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

        // Reset encoders
        mvm.resetEncoders();
    }

    @Override
    public void start() {
        // do Auto start stuff
        mvm.moveForward(0.5, 32.4);
        mvm.pause(200);

        mvm.turnLeft(0.3, 180);
        mvm.pause(500);

        mvm.turnRight(0.3, 180);
        mvm.pause(500);

        mvm.strafeLeft(0.5, 43.2);
        mvm.pause(500);

        mvm.turnLeft(0.3, 10);
        mvm.pause(500);

        mvm.turnRight(0.3, 10);
        mvm.pause(500);

        mvm.strafeLeft(0.5, 2.4);
        mvm.pause(500);

        mvm.turnLeft(0.3, 7);
        mvm.pause(500);

        mvm.turnRight(0.3, 7);
        mvm.pause(500);

        mvm.strafeLeft(0.5, 3.0);
        mvm.pause(500);

        mvm.strafeRight(0.5, 21.6);
        mvm.pause(500);

        mvm.moveForward(0.5, 21.6);
        mvm.pause(500);

        mvm.turnLeft(0.3, 90);
        mvm.pause(500);

        mvm.moveBackward(0.5, 3.6);
        mvm.pause(500);
    }

    @Override
    public void loop() {
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
