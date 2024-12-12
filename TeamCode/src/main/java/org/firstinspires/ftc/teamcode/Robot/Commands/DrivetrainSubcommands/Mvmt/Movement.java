package org.firstinspires.ftc.teamcode.Robot.Commands.DrivetrainSubcommands.Mvmt;

import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Movement {

    private final DcMotorEx frontRight;
    private final DcMotorEx frontLeft;
    private final DcMotorEx backRight;
    private final DcMotorEx backLeft;
    private final AnalogInput otosX;
    private final AnalogInput otosY;
    private final PIDController pidController;

    private double startX, startY; // Starting OTOS readings
    private double currentX, currentY;

    private static final double VOLTS_TO_INCHES = 1.0; // Conversion factor for OTOS voltage to inches (NEEDS ADJUSTMENT)

    public int FLtarg, FRtarg, BLtarg, BRtarg; // Encoder target positions

    public Movement(DcMotorEx fr, DcMotorEx fl, DcMotorEx br, DcMotorEx bl, AnalogInput xSensor, AnalogInput ySensor) {
        frontRight = fr;
        frontLeft = fl;
        backRight = br;
        backLeft = bl;

        otosX = xSensor;
        otosY = ySensor;

        pidController = new PIDController(0.001, 0.00, 0.00); // Adjustments needed

        resetEncoders();
        resetOdometry();
    }

    public void resetEncoders() {
        frontRight.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        frontLeft.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

        frontRight.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        frontLeft.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        backRight.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
    }

    public void resetOdometry() {
        startX = otosX.getVoltage();
        startY = otosY.getVoltage();
    }

    public void moveForward(double power, double inches) {
        move(power, inches, 0);
    }

    public void moveBackward(double power, double inches) {
        move(-power, -inches, 0);
    }

    public void strafeLeft(double power, double inches) {
        move(power, 0, -inches);
    }

    public void strafeRight(double power, double inches) {
        move(power, 0, inches);
    }

    public void turnLeft(double power, double degrees) {
        turn(power, degrees);
    }

    public void turnRight(double power, double degrees) {
        turn(-power, -degrees);
    }

    private void turn(double power, double degrees) {
        int ticks = (int) (degreesToTicks(degrees));

        FLtarg += ticks;
        FRtarg -= ticks;
        BLtarg += ticks;
        BRtarg -= ticks;

        setMotorTargets();
        setMotorPowers(power, -power);
    }

    public void move(double power, double targetInchesX, double targetInchesY) {
        double targetX = startX + (targetInchesX / VOLTS_TO_INCHES);
        double targetY = startY + (targetInchesY / VOLTS_TO_INCHES);

        do {
            currentX = otosX.getVoltage();
            currentY = otosY.getVoltage();

            double errorX = targetX - currentX;
            double errorY = targetY - currentY;

            double correctionX = pidController.calculate(errorX);
            double correctionY = pidController.calculate(errorY);

            double leftPower = power + correctionY - correctionX;
            double rightPower = power + correctionY + correctionX;

            setMotorPowers(leftPower, rightPower);

        } while (Math.abs(targetX - currentX) > 0.1 || Math.abs(targetY - currentY) > 0.1);

        stopMotors();
    }

    public void stopMotors() {
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
    }

    public void setMotorPowers(double leftPower, double rightPower) {
        frontLeft.setPower(leftPower);
        backLeft.setPower(leftPower);
        frontRight.setPower(rightPower);
        backRight.setPower(rightPower);
    }

    public void setMotorTargets() {
        frontLeft.setTargetPosition(FLtarg);
        frontRight.setTargetPosition(FRtarg);
        backLeft.setTargetPosition(BLtarg);
        backRight.setTargetPosition(BRtarg);
    }

    public boolean motorsAreBusy() {
        return frontLeft.isBusy() || frontRight.isBusy() || backLeft.isBusy() || backRight.isBusy();
    }

    public void performPIDAdjustment() {
        // get average error(s) for all of the motors
        int avgError = (Math.abs(frontLeft.getTargetPosition() - frontLeft.getCurrentPosition()) +
                Math.abs(frontRight.getTargetPosition() - frontRight.getCurrentPosition()) +
                Math.abs(backLeft.getTargetPosition() - backLeft.getCurrentPosition()) +
                Math.abs(backRight.getTargetPosition() - backRight.getCurrentPosition())) / 4;

        // get adjustment value
        double adjustment = pidController.calculate(avgError);

        // adjust the motors properly
        frontLeft.setPower(frontLeft.getPower() + adjustment);
        frontRight.setPower(frontRight.getPower() + adjustment);
        backLeft.setPower(backLeft.getPower() + adjustment);
        backRight.setPower(backRight.getPower() + adjustment);
    }

    public void pause(long milliseconds) {
        ElapsedTime timer = new ElapsedTime();
        while (timer.milliseconds() < milliseconds) {
            idle();
        }
    }

    private void idle() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private double degreesToTicks(double degrees) {
        final double TICKS_PER_REV = 1120; // Example value, adjust for your motor
        final double DEGREES_PER_REV = 360.0;
        return (degrees / DEGREES_PER_REV) * TICKS_PER_REV;
    }
}
