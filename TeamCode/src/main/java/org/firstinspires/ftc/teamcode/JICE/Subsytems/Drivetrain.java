package org.firstinspires.ftc.teamcode.JICE.Subsytems;

import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Pedrio.PedrioSubsystem;
import org.firstinspires.ftc.teamcode.pedroPathing.follower.Follower;

public class Drivetrain extends PedrioSubsystem {

    private MotorEx fl_motor;
    private MotorEx fr_motor;
    private MotorEx bl_motor;
    private MotorEx br_motor;

    private MecanumDrive drive;

    public Follower follower;

    public Drivetrain(HardwareMap hmap){
        fl_motor = new MotorEx(hmap,"fl");
        fr_motor = new MotorEx(hmap,"fr");
        bl_motor = new MotorEx(hmap,"bl");
        br_motor = new MotorEx(hmap,"br");

        follower = new Follower(hmap);
        drive = new MecanumDrive(fl_motor,fr_motor,bl_motor,br_motor);

    }

    public void driveRobotCentric(double x, double y, double turn){
        drive.driveRobotCentric(x,y,turn);
    }

    @Override
    public void init() {

    }

    @Override
    public void periodic() {

    }
}
