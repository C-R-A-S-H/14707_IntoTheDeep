package org.firstinspires.ftc.teamcode.NUMNUM;

import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

public class Hardware {

    public static Hardware instance = null;
    private HardwareMap hmap;
    public MotorEx FLM;
    public MotorEx FRM;
    public MotorEx BLM;
    public MotorEx BRM;

    public MotorEx LS;
    public MotorEx RS;

    public IMU imu;

    public MecanumDrive drive;

    public static Hardware getInstance(){
        if(instance == null) {

            instance = new Hardware();
        }
        return instance;
    }

    public void init(HardwareMap hmap) {
        this.hmap = hmap;
        this.FLM=new MotorEx(hmap, "FLM");
        this.FRM=new MotorEx(hmap,"FRM");
        this.BLM=new MotorEx(hmap,"BLM");
        this.BRM=new MotorEx(hmap,"BRM");

        this.LS=new MotorEx(hmap,"LS");
        this.RS=new MotorEx(hmap,"RS");

        this.imu = hmap.get(IMU.class, "imu");

        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.BACKWARD));
        // Without this, the REV Hub's orientation is assumed to be logo up / USB forward
        this.imu.initialize(parameters);

        drive = new MecanumDrive(FLM, FRM, BLM, BRM);

    }

    public void drive(double x, double y, double z) {
        drive.driveRobotCentric(x, y, z);
    }

    public void loop(){

    }

}
