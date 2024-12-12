package org.firstinspires.ftc.teamcode.Robot.Commands.DrivetrainSubcommands.Mvmt;
import org.firstinspires.ftc.teamcode.Robot.Subsystems.*;
import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.DcMotorEx;


public class Movement extends CommandBase {

    public DcMotorEx frontRight, frontLeft, backRight, backLeft;
    public PIDController pidController;

    private static final double KP = 0.001;
    private static final double KI = 0;
    private static final double KD = 0;

    private static final double CPR = 537.6; //Counts per revolution
    private static final double WDI = 4.0; // Wheel Diamater in inches
    private static final double CPI = CPR / (WDI * Math.PI); // Counts per inch

    private int FLtarg, FRtarg, BLtarg, BRtarg;
    private double Powertarg;

    public Movement(DcMotorEx frontLeft, DcMotorEx frontRight, DcMotorEx backLeft, DcMotorEx backRight) {
    }


    public void moveForward(double power, double inches) {
        startMove(power, inches, inches, inches, inches);
    }

    public void moveBackward(double power, double inches) {
        startMove(power, -inches, -inches, -inches, -inches);
    }

    public void strafeLeft(double power, double inches) {
        startMove(power, -inches, inches, inches, -inches);
    }

    public void strafeRight(double power, double inches) {
        startMove(power, inches, -inches, -inches, inches);
    }

    public void turnRight(double power, double degrees) {
        double turnInches = degrees / 360 * (Math.PI * WDI);
        startMove(power, -turnInches, turnInches, -turnInches, turnInches);
    }

    public void turnLeft(double power, double degrees) {
        double turnInches = degrees / 360 * (Math.PI * WDI);
        startMove(power, turnInches, -turnInches, turnInches, -turnInches);
    }

    private void startMove(double power, double frontLeftInches, double frontRightInches, double backLeftInches, double backRightInches) {
        FLtarg = frontLeft.getCurrentPosition() + (int) (frontLeftInches * CPI);
        FRtarg = frontRight.getCurrentPosition() + (int) (frontRightInches * CPI);
        BLtarg = backLeft.getCurrentPosition() + (int) (backLeftInches * CPI);
        BRtarg = backRight.getCurrentPosition() + (int) (backRightInches * CPI);

        setTargetPositions();
        runToPositionMode();

        Powertarg = power;
        setMotorPowers(Powertarg);
    }

    public void pause(double milliseconds) {
        long pauseEndTime = System.currentTimeMillis() + (long) milliseconds;
    }

    public void performPIDAdjustment() {
        int currentPos = (frontLeft.getCurrentPosition() + frontRight.getCurrentPosition() + backLeft.getCurrentPosition() + backRight.getCurrentPosition()) / 4;
        int targetPos = (FLtarg + FRtarg + BLtarg + BRtarg) / 4;
        double error = targetPos - currentPos;
        double correction = pidController.calculate(error);

        setMotorPowers(Powertarg + correction);
    }

    public boolean motorsAreBusy() {
        return frontLeft.isBusy() && frontRight.isBusy() && backLeft.isBusy() && backRight.isBusy();
    }

    public void stopMotors() {
        setMotorPowers(0);
        runUsingEncoders();
    }

    public void resetEncoders() {
        frontRight.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        frontLeft.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
    }

    private void setTargetPositions() {
        frontLeft.setTargetPosition(FLtarg);
        frontRight.setTargetPosition(FRtarg);
        backLeft.setTargetPosition(BLtarg);
        backRight.setTargetPosition(BRtarg);
    }

    private void runToPositionMode() {
        frontLeft.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        backRight.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
    }

    private void runUsingEncoders() {
        frontLeft.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
    }

    private void setMotorPowers(double power) {
        frontLeft.setPower(power);
        frontRight.setPower(power);
        backLeft.setPower(power);
        backRight.setPower(power);
    }
}
