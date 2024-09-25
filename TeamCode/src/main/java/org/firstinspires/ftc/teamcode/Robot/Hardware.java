package org.firstinspires.ftc.teamcode.Robot;

import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.hardware.sparkfun.SparkFunOTOS;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Pedrio.PedrioSubsystem;
import org.firstinspires.ftc.teamcode.Pedrio.Sensors.BeamBreak;
import org.firstinspires.ftc.teamcode.Pedrio.Sensors.MagLimitSwitch;
import org.firstinspires.ftc.teamcode.Pedrio.Vision.LimeLightHelper;
import org.firstinspires.ftc.teamcode.Robot.Subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.Robot.Subsystems.Intake;
import org.firstinspires.ftc.teamcode.Robot.Subsystems.NumNum.NumNumDrivetrain;

import java.util.ArrayList;
import java.util.List;


public class Hardware {

    private static Hardware instance = null;

    public MotorEx FlMotor;
    public MotorEx FrMotor;
    public MotorEx BlMotor;
    public MotorEx BrMotor;

    public MotorEx HsSlide;
    public MotorEx AVSlide;
    public MotorEx BVSlide;

    public CRServo Intake1;
    public CRServo Intake2;

    public Servo DropDown;

    public Limelight3A limelight3A;

    public BeamBreak intakeBeamBreak;
    public MagLimitSwitch horizontalLimitSwitch;

    public IMU imu;
    public HardwareMap hmap;
    public SparkFunOTOS otos;

    public Drivetrain drivetrain;
    public Intake intake;
    public LimeLightHelper ll;

    private List<PedrioSubsystem> subsystems = new ArrayList<>();
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
        this.AVSlide = new MotorEx(hmap, "VsSlide");

        this.Intake1 = hmap.get(CRServo.class,"Intake1");
        this.Intake2 = hmap.get(CRServo.class,"Intake2");

        this.DropDown = hmap.get(Servo.class, "DropDown");

        this.limelight3A = hmap.get(Limelight3A.class,"ll");

        this.intakeBeamBreak = new BeamBreak(hmap,"intakeBeamBreak");
        this.horizontalLimitSwitch = new MagLimitSwitch(hmap,"horizontalLimitLimitSwitch");

        this.drivetrain = new Drivetrain();



        this.subsystems.add(this.drivetrain);
        this.subsystems.add(this.intake);

        //this.otos = hmap.get(SparkFunOTOS.class, "otos");
        this.imu = hmap.get(IMU.class, "imu");

        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.BACKWARD));
        // Without this, the REV Hub's orientation is assumed to be logo up / USB forward
        this.imu.initialize(parameters);

        for(PedrioSubsystem Subsystem : subsystems){
            Subsystem.init();
        }

    }
    public void Loop(){
        for(PedrioSubsystem Subsystem : subsystems){
            Subsystem.init();
        }
    }

}
