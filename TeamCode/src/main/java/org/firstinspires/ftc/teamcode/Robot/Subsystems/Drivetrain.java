package org.firstinspires.ftc.teamcode.Robot.Subsystems;

import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.geometry.Pose2d;
import com.arcrobotics.ftclib.geometry.Rotation2d;
import com.arcrobotics.ftclib.geometry.Translation2d;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.arcrobotics.ftclib.kinematics.wpilibkinematics.ChassisSpeeds;
import com.arcrobotics.ftclib.kinematics.wpilibkinematics.MecanumDriveKinematics;
import com.qualcomm.hardware.sparkfun.SparkFunOTOS;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.Pedrio.DataFusing.RegularFusing;
import org.firstinspires.ftc.teamcode.Pedrio.PedrioSubsystem;
import org.firstinspires.ftc.teamcode.Robot.Config;
import org.firstinspires.ftc.teamcode.Robot.Hardware;
import org.firstinspires.ftc.teamcode.pedroPathing.follower.Follower;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;

import java.util.ArrayList;


public class Drivetrain extends PedrioSubsystem {
    private final Hardware robot = Hardware.getInstance();

   public Follower follower;

    public Drivetrain(HardwareMap hmap){

        this.follower = new Follower(hmap);

    }

    private RegularFusing dataFuser = new RegularFusing();

    private final MecanumDriveKinematics kine = new MecanumDriveKinematics(
            new Translation2d(Config.LENGTH / 2, Config.WIDTH / 2),
            new Translation2d(Config.LENGTH / 2, -Config.WIDTH / 2),
            new Translation2d(-Config.LENGTH / 2, Config.WIDTH / 2),
            new Translation2d(-Config.LENGTH / 2, -Config.WIDTH / 2)
    );



    @Override
    public void init() {
    }

    @Override
    public void periodic() {

    }
}

