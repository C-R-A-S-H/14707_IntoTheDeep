package org.firstinspires.ftc.teamcode.Robot.Subsystems;

import com.arcrobotics.ftclib.command.OdometrySubsystem;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.geometry.Pose2d;
import com.arcrobotics.ftclib.geometry.Rotation2d;
import com.arcrobotics.ftclib.geometry.Translation2d;
import com.arcrobotics.ftclib.kinematics.HolonomicOdometry;
import com.arcrobotics.ftclib.kinematics.wpilibkinematics.ChassisSpeeds;
import com.arcrobotics.ftclib.kinematics.wpilibkinematics.MecanumDriveKinematics;
import com.qualcomm.hardware.sparkfun.SparkFunOTOS;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.Pedrio.PedrioSubsystem;
import org.firstinspires.ftc.teamcode.Robot.Hardware;

import java.util.ArrayList;


public class NumNumTestBed extends PedrioSubsystem {

    private final Hardware robot = Hardware.getInstance();
    public MecanumDrive drive = new MecanumDrive(robot.FlMotor, robot.FrMotor, robot.BlMotor, robot.BrMotor);
    private double LENGTH = 14;
    private double WIDTH = 14;
    static final double TRACKWIDTH = 16;
    static final double CENTER_WHEEL_OFFSET = 2.4;

    private final MecanumDriveKinematics kine = new MecanumDriveKinematics(
            new Translation2d(LENGTH / 2, WIDTH / 2),
            new Translation2d(LENGTH / 2, -WIDTH / 2),
            new Translation2d(-LENGTH / 2, WIDTH / 2),
            new Translation2d(-LENGTH / 2, -WIDTH / 2)
    );

    public void driveFieldCentric(double x, double y, double turn, double gyroAngle) {
        drive.driveFieldCentric(x, y, turn, gyroAngle);
    }

    public void driveFieldCentricWithWheelSpeeds(ChassisSpeeds speeds) {
        drive.driveFieldCentric(speeds.vxMetersPerSecond, speeds.vyMetersPerSecond, speeds.omegaRadiansPerSecond, getRawIMUHeadingDegrees());
    }

    public double getRawIMUHeadingDegrees() {
        return robot.imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES);
    }

    public double getRawIMUHeadingRadians() {
        return robot.imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);
    }


    @Override
    public void init() {

    }

    @Override
    public void periodic() {

    }

}