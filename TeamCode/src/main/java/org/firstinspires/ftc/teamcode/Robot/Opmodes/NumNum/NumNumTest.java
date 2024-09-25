package org.firstinspires.ftc.teamcode.Robot.Opmodes.NumNum;

import com.arcrobotics.ftclib.controller.PIDController;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.teamcode.Pedrio.Vision.LimeLightHelper;
import org.firstinspires.ftc.teamcode.Robot.Hardware;
import org.firstinspires.ftc.teamcode.Robot.Subsystems.NumNum.NumNumDrivetrain;
import org.firstinspires.ftc.teamcode.pedroPathing.localization.Pose;

import java.util.List;

@TeleOp(name = "NumNumTestBed")
public class NumNumTest extends OpMode {
    //private Hardware robot = Hardware.getInstance();
    static final double TRACKWIDTH = 16;
    static final double CENTER_WHEEL_OFFSET = 2.4;
    private Pose currentPose;

    public Motor FlMotor;
    public Motor FrMotor;
    public Motor BlMotor;
    public Motor BrMotor;

    MecanumDrive drive;

    private PIDController x_pid = new PIDController(1,0,0);
    private PIDController y_pid = new PIDController(1,0,0);
    private PIDController turn_pid = new PIDController(1,0,0);

    public IMU imu;

    public Limelight3A limelight;

    public LimeLightHelper helper;
    public NumNumDrivetrain dt = new NumNumDrivetrain();




    @Override
    public void init() {
        //this.robot.Init(hardwareMap);
        this.FlMotor = new Motor(hardwareMap, "FrontLeft");
        this.FrMotor = new Motor(hardwareMap, "FrontRight");
        this.BrMotor = new Motor(hardwareMap, "BackRight");
        this.BlMotor = new Motor(hardwareMap, "BackLeft");

        this.limelight = hardwareMap.get(Limelight3A.class, "ll");

        this.imu = hardwareMap.get(IMU.class, "imu");

        //aprilTag = AprilTagProcessor.easyCreateWithDefaults();
        //visionPortal = VisionPortal.easyCreateWithDefaults(
        //hmap.get(WebcamName.class, "Webcam 1"), aprilTag);
        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.BACKWARD));
        // Without this, the REV Hub's orientation is assumed to be logo up / USB forward
        this.imu.initialize(parameters);
       telemetry.setMsTransmissionInterval(11);
        this.limelight.pipelineSwitch(0);
        //\
        this.limelight.start();

        drive = new MecanumDrive(FlMotor, FrMotor, BlMotor, BrMotor);


    }

    @Override
    public void loop() {
        LLResult data = this.limelight.getLatestResult();
        if(data != null) {
           if(data.isValid()) {
              List<LLResultTypes.FiducialResult> results = data.getFiducialResults();
                drive.driveRobotCentric(0,0,turn_pid.calculate(14, results.get(0).getCameraPoseTargetSpace().getOrientation().getYaw()));
                telemetry.addData("target stuff: ",results.get(0).getCameraPoseTargetSpace());
            }else{
               telemetry.addData("Data isnt valid",""  );
           }
        }else{
            telemetry.addData("Data Null ", "");
        }

        telemetry.update();
    }
}
