package org.firstinspires.ftc.teamcode.Robot;

import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.hardware.sparkfun.SparkFunOTOS;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Pedrio.PedrioSubsystem;
import org.firstinspires.ftc.teamcode.Pedrio.Sensors.BeamBreak;
import org.firstinspires.ftc.teamcode.Pedrio.Sensors.MagLimitSwitch;
import org.firstinspires.ftc.teamcode.Pedrio.Vision.LimeLightHelper;
import org.firstinspires.ftc.teamcode.Robot.Subsystems.Deposit;
import org.firstinspires.ftc.teamcode.Robot.Subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.Robot.Subsystems.Intake;
import org.firstinspires.ftc.teamcode.pedroPathing.follower.Follower;

import java.util.ArrayList;
import java.util.List;


public class Hardware {

    private static Hardware instance = null;

    public Limelight3A limelight3A;


    //public BeamBreak intakeBeamBreak;
    public NormalizedColorSensor transferColorSensor;

    //public MagLimitSwitch horizontalLimitSwitch;
    //public MagLimitSwitch verticalLimitSwitch;

    //public IMU imu;
    //public SparkFunOTOS otos;

    public Drivetrain drivetrain;
    public Intake intake;
    public Deposit deposit;
    public LimeLightHelper ll;

    private List<PedrioSubsystem> subsystems = new ArrayList<>();
    public static Hardware getInstance(){
        if(instance == null){
            instance = new Hardware();
        }
        return instance;
    }

    public void Init(final HardwareMap hmap){

        this.limelight3A = hmap.get(Limelight3A.class,"ll");

        //this.intakeBeamBreak = new BeamBreak(hmap,"intakeBeamBreak");
        //this.transferColorSensor = hmap.get(NormalizedColorSensor.class, "transferColorSensor");

        //this.horizontalLimitSwitch = new MagLimitSwitch(hmap,"horizontalLimitLimitSwitch");
        //this.verticalLimitSwitch = new MagLimitSwitch(hmap,"verticalLimitSwitch");

        this.drivetrain = new Drivetrain(hmap);
        this.intake = new Intake(hmap);
        this.deposit = new Deposit(hmap);

        this.subsystems.add(this.drivetrain);
        this.subsystems.add(this.intake);
        this.subsystems.add(this.deposit);

        this.ll = new LimeLightHelper();


        //this.otos = hmap.get(SparkFunOTOS.class, "otos");
        //this.imu = hmap.get(IMU.class, "imu");

        /*IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.BACKWARD));
        // Without this, the REV Hub's orientation is assumed to be logo up / USB forward
        this.imu.initialize(parameters);

         */

        for(PedrioSubsystem Subsystem : this.subsystems){
            Subsystem.init();
        }
        this.ll.init();


    }
    public void Loop(){
        for(PedrioSubsystem Subsystem : this.subsystems){
            Subsystem.periodic();
        }
    }

}
