package org.firstinspires.ftc.teamcode.Robot.Subsystems.NumNum;

//import static org.firstinspires.ftc.teamcode.Robot.Opmodes.NumNum.NumNumTest.BlMotor;
//import static org.firstinspires.ftc.teamcode.Robot.Opmodes.NumNum.NumNumTest.BrMotor;
//import static org.firstinspires.ftc.teamcode.Robot.Opmodes.NumNum.NumNumTest.FlMotor;
///import static org.firstinspires.ftc.teamcode.Robot.Opmodes.NumNum.NumNumTest.FrMotor;

//import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.geometry.Translation2d;
//import com.arcrobotics.ftclib.kinematics.wpilibkinematics.ChassisSpeeds;
import com.arcrobotics.ftclib.kinematics.wpilibkinematics.MecanumDriveKinematics;

//import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.Pedrio.PedrioSubsystem;
//import org.firstinspires.ftc.teamcode.Robot.Hardware;


public class NumNumDrivetrain extends PedrioSubsystem {

    //private final Hardware robot = Hardware.getInstance();
    //protected MecanumDrive drive = new MecanumDrive(FlMotor, FrMotor, BlMotor, BrMotor);
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

    public void drive(double x, double y, double turn) {
        //drive.driveRobotCentric(x, y, turn);
    }




    @Override
    public void init() {

    }

    @Override
    public void periodic() {

    }

}