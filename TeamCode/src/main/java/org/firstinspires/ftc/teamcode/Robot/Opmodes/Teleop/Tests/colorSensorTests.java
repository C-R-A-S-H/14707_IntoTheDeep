package org.firstinspires.ftc.teamcode.Robot.Opmodes.Teleop.Tests;

import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorRangeSensor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@TeleOp(name = "color test")
public class colorSensorTests extends OpMode {
    private ColorRangeSensor sensor;
    private DistanceSensor distanceSensor;


    private CRServo left;
    private CRServo right;

    private PIDController pid = new PIDController(0.01,0,0);

    private double setpoint = 66;

    
    @Override
    public void init() {
        this.sensor = hardwareMap.get(ColorRangeSensor.class,"s");
        this.distanceSensor = hardwareMap.get(DistanceSensor.class,"d");

        this.left = hardwareMap.get(CRServo.class, "DropDownLeft");
        this.right = hardwareMap.get(CRServo.class,"DropDownRight");

        this.left.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    @Override
    public void loop() {
        telemetry.addData(String.valueOf( this.sensor.getDistance(DistanceUnit.MM)),"distance from sample");
        telemetry.addData(String.valueOf( this.sensor.getLightDetected()),"color");

        telemetry.addData(String.valueOf(this.distanceSensor.getDistance(DistanceUnit.MM)), "drop down");//61drop down 127 up

        double power = pid.calculate(setpoint,this.distanceSensor.getDistance(DistanceUnit.MM));

        if(gamepad1.a){
            setpoint = 61;
        }
        if(gamepad1.b){
            setpoint = 127;
        }
        this.left.setPower(-power);
        this.right.setPower(-power);
        telemetry.update();
    }
}
