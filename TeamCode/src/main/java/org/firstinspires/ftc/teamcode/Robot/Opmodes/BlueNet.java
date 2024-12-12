package org.firstinspires.ftc.teamcode.Robot.Opmodes;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.teamcode.Robot.Commands.DrivetrainSubcommands.Mvmt.Movement;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@Autonomous(name = "BlueNet AUTO", group = "Blue")
public class BlueNet extends OpMode {

    public DcMotorEx frontRight, frontLeft, backRight, backLeft;

    private Movement mvm;

    @Override
    public void init() {
        // Initialize motors
        frontRight = hardwareMap.get(DcMotorEx.class, "FrontRight");
        frontLeft = hardwareMap.get(DcMotorEx.class, "FrontLeft");
        backRight = hardwareMap.get(DcMotorEx.class, "BackRight");
        backLeft = hardwareMap.get(DcMotorEx.class, "BackLeft");

        // Initialize Movement
        mvm = new Movement();


        // Reset encoders
        mvm.resetEncoders();
    }


    @Override
    public void start() {
        // Forward 2.7 ft
        mvm.moveForward(0.5, 32.4);
        mvm.pause(200);

        // Turn 180° left
        mvm.turnLeft(0.3, 180);
        mvm.pause(500);

        // Clip preloaded spec
        // LOGIC PLACEHOLDER

        // Turn -180° right
        mvm.turnRight(0.3, 180);
        mvm.pause(500);

        // Strafe left 3.6 ft
        mvm.strafeLeft(0.5, 43.2);
        mvm.pause(500);

        // Intake to outtake
        // LOGIC PLACEHOLDER

        // Turn 10° left
        mvm.turnLeft(0.3, 10);
        mvm.pause(500);

        // Push out to net
        // LOGIC PLACEHOLDER

        // Turn -10° right
        mvm.turnRight(0.3, 10);
        mvm.pause(500);

        // Strafe left 0.2 ft
        mvm.strafeLeft(0.5, 2.4);
        mvm.pause(500);

        // Intake to outtake
        // LOGIC PLACEHOLDER

        // Turn 7° left
        mvm.turnLeft(0.3, 7);
        mvm.pause(500);

        // Push out to net
        // LOGIC PLACEHOLDER

        // Turn -7° right
        mvm.turnRight(0.3, 7);
        mvm.pause(500);

        // Strafe left 0.25 ft
        mvm.strafeLeft(0.5, 3.0);
        mvm.pause(500);

        // Intake to outtake
        // LOGIC PLACEHOLDER

        // Push out to net
        // LOGIC PLACEHOLDER

        // Strafe right 1.8 ft
        mvm.strafeRight(0.5, 21.6);
        mvm.pause(500);

        // Forward 1.8 ft
        mvm.moveForward(0.5, 21.6);
        mvm.pause(500);

        // Turn 90° left
        mvm.turnLeft(0.3, 90);
        mvm.pause(500);

        // Backward 0.3 ft
        mvm.moveBackward(0.5, 3.6);
        mvm.pause(500);

        // Stick out to touch bar
        // LOGIC PLACEHOLDER
    }

    @Override
    public void loop() {
        if (mvm.motorsAreBusy()) {
            mvm.performPIDAdjustment();
        } else {
            mvm.stopMotors();
        }
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
