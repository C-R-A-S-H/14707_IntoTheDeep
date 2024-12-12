package org.firstinspires.ftc.teamcode.Robot.Opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.teamcode.Robot.Commands.DrivetrainSubcommands.Mvmt.Movement;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.arcrobotics.ftclib.controller.PIDController;

@Autonomous(name = "BlueNet AUTO", group = "Blue")
public class BlueNet extends OpMode {

    private static final double KP = 0.001;
    private static final double KI = 0.001;
    private static final double KD = 0.001;

    private static final double CPR = 537.6; //Counts per revolution
    private static final double WDI = 4.0; // Wheel Diamater in inches
    private static final double CPI = CPR / (WDI * Math.PI); // Counts per inch

    private int FLtarg, FRtarg, BLtarg, BRtarg;
    private Movement movement;

    @Override
    public void init() {
        // Initialize motors
        DcMotorEx frontRight = hardwareMap.get(DcMotorEx.class, "FrontRight");
        DcMotorEx frontLeft = hardwareMap.get(DcMotorEx.class, "FrontLeft");
        DcMotorEx backRight = hardwareMap.get(DcMotorEx.class, "BackRight");
        DcMotorEx backLeft = hardwareMap.get(DcMotorEx.class, "BackLeft");

        // Initialize Movement
        movement = new Movement(frontLeft, frontRight, backLeft, backRight);

        // Initialize PID Controller
        PIDController pidController = new PIDController(KP, KI, KD);

        // Reset encoders
        movement.resetEncoders();
    }


    @Override
    public void start() {
        // Forward 2.7 ft
        movement.moveForward(0.5, 32.4);
        movement.pause(501);

        // Turn 180° left
        movement.turnLeft(0.3, 180);
        movement.pause(500);

        // Clip preloaded spec
        // LOGIC PLACEHOLDER

        // Turn -180° right
        movement.turnRight(0.3, 180);
        movement.pause(500);

        // Strafe left 3.6 ft
        movement.strafeLeft(0.5, 43.2);
        movement.pause(500);

        // Intake to outtake
        // LOGIC PLACEHOLDER

        // Turn 10° left
        movement.turnLeft(0.3, 10);
        movement.pause(500);

        // Push out to net
        // LOGIC PLACEHOLDER

        // Turn -10° right
        movement.turnRight(0.3, 10);
        movement.pause(500);

        // Strafe left 0.2 ft
        movement.strafeLeft(0.5, 2.4);
        movement.pause(500);

        // Intake to outtake
        // LOGIC PLACEHOLDER

        // Turn 7° left
        movement.turnLeft(0.3, 7);
        movement.pause(500);

        // Push out to net
        // LOGIC PLACEHOLDER

        // Turn -7° right
        movement.turnRight(0.3, 7);
        movement.pause(500);

        // Strafe left 0.25 ft
        movement.strafeLeft(0.5, 3.0);
        movement.pause(500);

        // Intake to outtake
        // LOGIC PLACEHOLDER

        // Push out to net
        // LOGIC PLACEHOLDER

        // Strafe right 1.8 ft
        movement.strafeRight(0.5, 21.6);
        movement.pause(500);

        // Forward 1.8 ft
        movement.moveForward(0.5, 21.6);
        movement.pause(500);

        // Turn 90° left
        movement.turnLeft(0.3, 90);
        movement.pause(500);

        // Backward 0.3 ft
        movement.moveBackward(0.5, 3.6);
        movement.pause(500);

        // Stick out to touch bar
        // LOGIC PLACEHOLDER
    }

    @Override
    public void loop() {
        if (movement.motorsAreBusy()) {
            movement.performPIDAdjustment();
        } else {
            movement.stopMotors();
        }

        telemetry.addData("FL Target", FLtarg);
        telemetry.addData("FR Target", FRtarg);
        telemetry.addData("BL Target", BLtarg);
        telemetry.addData("BR Target", BRtarg);
        telemetry.update();
    }


}
