package org.firstinspires.ftc.teamcode.Robot;

import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.hardware.sparkfun.SparkFunOTOS;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Robot.Subsystems.NumNum.NumNumDrivetrain;
import org.firstinspires.ftc.teamcode.pedroPathing.localization.localizers.ThreeWheelIMULocalizer;


public class Hardware {

    private static Hardware instance = null;

    public MotorEx FlMotor;
    public MotorEx FrMotor;
    public MotorEx BlMotor;
    public MotorEx BrMotor;

    public MotorEx HsSlide;

    public Limelight3A limelight3A;


    public IMU imu;
    public HardwareMap hmap;
    public SparkFunOTOS otos;

    //public AprilTagProcessor aprilTag;
    //public VisionPortal visionPortal;

    public NumNumDrivetrain drivetrain;

    public static Hardware getInstance(){
        if(instance == null){
            instance = new Hardware();
        }
        return instance;
    }

    public void Init(final HardwareMap hmap){
        this.hmap = hmap;
        this.FlMotor = new MotorEx(hmap,"FrontLeft");
        this.FrMotor = new MotorEx(hmap,"FrontRight");
        this.BlMotor = new MotorEx(hmap,"BackLeft");
        this.BrMotor = new MotorEx(hmap,"BackRight");
        this.HsSlide = new MotorEx(hmap, "HsSlide");

        this.limelight3A = hmap.get(Limelight3A.class,"LL");

        this.drivetrain = new NumNumDrivetrain();

        this.drivetrain.init();
        //this.otos = hmap.get(SparkFunOTOS.class, "otos");
        this.imu = hmap.get(IMU.class, "imu");

        //aprilTag = AprilTagProcessor.easyCreateWithDefaults();
        //visionPortal = VisionPortal.easyCreateWithDefaults(
                //hmap.get(WebcamName.class, "Webcam 1"), aprilTag);
        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.BACKWARD));
        // Without this, the REV Hub's orientation is assumed to be logo up / USB forward
        this.imu.initialize(parameters);

    }
    public void Loop(){
        this.drivetrain.periodic();
    }

}
