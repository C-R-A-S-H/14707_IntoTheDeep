package org.firstinspires.ftc.teamcode.Pedrio.PointToPoint;

import com.qualcomm.hardware.sparkfun.SparkFunOTOS;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;


public class TrajectoryAuto extends OpMode {
    private DcMotor frontLeftMotor;
    private DcMotor backLeftMotor ;
    private DcMotor frontRightMotor;
    private DcMotor backRightMotor;
    private SparkFunOTOS otos;

    private final ZLPIDFController xController = new ZLPIDFController(0.001,0,0,0);
    private final ZLPIDFController yController = new ZLPIDFController(0.001,0,0,0);
    private final ZLPIDFController turnController = new ZLPIDFController(0.001,0,0,0);



    @Override
    public void init() {
        DcMotor frontLeftMotor = hardwareMap.dcMotor.get("frontLeftMotor");
        DcMotor backLeftMotor = hardwareMap.dcMotor.get("backLeftMotor");
        DcMotor frontRightMotor = hardwareMap.dcMotor.get("frontRightMotor");
        DcMotor backRightMotor = hardwareMap.dcMotor.get("backRightMotor");

        otos =hardwareMap.get(SparkFunOTOS.class,"otos");
    }

    @Override
    public void loop() {

    }

    public void drive(double x, double y, double rx, double heading){
        double botHeading = otos.getPosition().h;

        // Rotate the movement direction counter to the bot's rotation
        double rotX = x * Math.cos(-botHeading) - y * Math.sin(-botHeading);
        double rotY = x * Math.sin(-botHeading) + y * Math.cos(-botHeading);

        rotX = rotX * 1.1;  // Counteract imperfect strafing

        // Denominator is the largest motor power (absolute value) or 1
        // This ensures all the powers maintain the same ratio,
        // but only if at least one is out of the range [-1, 1]
        double denominator = Math.max(Math.abs(rotY) + Math.abs(rotX) + Math.abs(rx), 1);
        double frontLeftPower = (rotY + rotX + rx) / denominator;
        double backLeftPower = (rotY - rotX + rx) / denominator;
        double frontRightPower = (rotY - rotX - rx) / denominator;
        double backRightPower = (rotY + rotX - rx) / denominator;

        frontLeftMotor.setPower(frontLeftPower);
        backLeftMotor.setPower(backLeftPower);
        frontRightMotor.setPower(frontRightPower);
        backRightMotor.setPower(backRightPower);
    }

    public boolean goToPoint(double wantedX, double wantedY, double wantedHeading){
        SparkFunOTOS.Pose2D currentPose = otos.getPosition();
        double xPower = xController.calculate(wantedX,currentPose.x);
        double yPower = yController.calculate(wantedY, currentPose.y);
        double turnPower = turnController.calculate(wantedHeading, currentPose.h);

        drive(xPower, yPower,turnPower,currentPose.h);

        double AverageVelocity =  averageError(otos.getVelocity().x, otos.getVelocity().y, otos.getVelocity().h);


        return tolerance(AverageVelocity, AverageVelocity - 0.1, AverageVelocity + 0.1 );

    }

    private boolean tolerance(double value,double min,double max){
        return value >= min && value <= max;
    }
    private double averageError(double x, double y, double turn){
        return (x + y + turn) / 3;
    }

}
