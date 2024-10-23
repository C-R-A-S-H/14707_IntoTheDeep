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
//import org.firstinspires.ftc.teamcode.pedroPathing.localization.Pose;

import java.util.List;

@TeleOp(name = "NumNumTestBed")
public class NumNumTest extends OpMode {
    //private Hardware robot = Hardware.getInstance();
    static final double TRACKWIDTH = 16;
    static final double CENTER_WHEEL_OFFSET = 2.4;
    //private Pose currentPose;

    private Motor FlMotor;
    private Motor FrMotor;
    private Motor BlMotor;
    private Motor BrMotor;

    private MecanumDrive drive;

    private PIDController x_pid = new PIDController(1,0,0);
    private PIDController y_pid = new PIDController(1,0,0);
    private PIDController turn_pid = new PIDController(1,0,0);

    public IMU imu;

    public Limelight3A limelight;

    private LimeLightHelper helper;
    private NumNumDrivetrain dt = new NumNumDrivetrain();

    int wantedId = 2;
    double yaw = 0;



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
        this.limelight.pipelineSwitch(4);
        //\
        this.limelight.start();

        this.drive = new MecanumDrive(FlMotor, FrMotor, BlMotor, BrMotor);


    }

    @Override
    public void loop() {

        LLResult data = this.limelight.getLatestResult();
        if(data != null) {
           if(data.isValid()) {
              List<LLResultTypes.DetectorResult> results = data.getDetectorResults();

              for(LLResultTypes.DetectorResult sample: results){
                  if(sample.getClassId() == wantedId){
                      yaw = sample.getTargetXDegrees();
                  }
              }
               telemetry.addData("Closest Red Sample", yaw);
            }else{
               telemetry.addData("Data isnt valid",""  );
           }
        }else{
            telemetry.addData("Data Null ", "");
        }

        drive.driveRobotCentric(gamepad1.left_stick_x,gamepad1.left_stick_y,turn_pid.calculate(0,yaw));
        telemetry.update();
    }
}
