package org.firstinspires.ftc.teamcode.Robot.Commands.AutoCommands;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.arcrobotics.ftclib.geometry.Pose2d;
import com.arcrobotics.ftclib.geometry.Rotation2d;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;

import org.firstinspires.ftc.teamcode.Pedrio.Sensors.MagLimitSwitch;
import org.firstinspires.ftc.teamcode.Pedrio.Vision.LimeLightHelper;
import org.firstinspires.ftc.teamcode.Robot.Commands.DepositSubCommands.PreScoreCommand;
import org.firstinspires.ftc.teamcode.Robot.Commands.DepositSubCommands.ScoreCommand;
import org.firstinspires.ftc.teamcode.Robot.Commands.DepositSubCommands.VerticalExtensionCommand;
import org.firstinspires.ftc.teamcode.Robot.Commands.DrivetrainSubcommands.FollowPathCommand;
import org.firstinspires.ftc.teamcode.Robot.Config;
import org.firstinspires.ftc.teamcode.Robot.Hardware;
import org.firstinspires.ftc.teamcode.Robot.Subsystems.Deposit;
import org.firstinspires.ftc.teamcode.Robot.Subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.Robot.Subsystems.Intake;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.BezierCurve;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.BezierLine;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.Point;

public class AutoBlueBasketCommand extends SequentialCommandGroup {
    private Drivetrain dt;
    private Intake intake;
    private Deposit deposit;
    private MagLimitSwitch verticalMagSwitch;

    private NormalizedColorSensor colorSensor;
    private LimeLightHelper ll;

    public AutoBlueBasketCommand(Drivetrain dt, Intake intake, Deposit deposit) {
        this.dt = dt;
        this.intake = intake;
        this.deposit = deposit;

    }

    @Override
    public void initialize() {
        Config.RobotPose = new Pose2d(7.024,80.078,new Rotation2d(180));
        addCommands(
                new FollowPathCommand(new BezierCurve(
                        // start to pre score speciment
                        new Point(7.024, 80.078, Point.CARTESIAN),
                        new Point(21.307, 80.078, Point.CARTESIAN),
                        new Point(34.654, 76.800, Point.CARTESIAN)),this.dt)
                        //.alongWith(
                         //   new PreScoreCommand(this.deposit)
               // )
                ,
                new FollowPathCommand( // after pre score moving to the bar score after
                        new BezierLine(
                                new Point(34.654, 76.800, Point.CARTESIAN),
                                new Point(40.741, 76.800, Point.CARTESIAN)
                        ), this.dt)
                ,new WaitCommand(100)
                //,
                //new ScoreCommand(this.deposit)


        );
    }

    @Override
    public void execute() {
        //this.ll.updateHeading(this.dt.getRawIMUHeadingDegrees());
    }



    @Override
    public boolean isFinished() {
        return false;
    }
}
/*paths imported from generate

 builder
      .addPath(
        // start to pre score speciment
        new BezierCurve(
          new Point(7.024, 80.078, Point.CARTESIAN),
          new Point(21.307, 80.078, Point.CARTESIAN),
          new Point(34.654, 76.800, Point.CARTESIAN)
        )
      )
      .setTangentialHeadingInterpolation()
      .setReversed(true)
      .addPath(
        // after pre score moving to the bar score after
        new BezierLine(
          new Point(34.654, 76.800, Point.CARTESIAN),
          new Point(40.741, 76.800, Point.CARTESIAN)
        )
      )
      .setTangentialHeadingInterpolation()
      .setReversed(true)
      .addPath(
        // go to first sample intake command after
        new BezierCurve(
          new Point(40.741, 76.800, Point.CARTESIAN),
          new Point(26.224, 90.849, Point.CARTESIAN),
          new Point(41.912, 100.215, Point.CARTESIAN),
          new Point(44.254, 112.390, Point.CARTESIAN)
        )
      )
      .setTangentialHeadingInterpolation()
      .addPath(
        // going to basket after run transfer commands then score commands
        new BezierCurve(
          new Point(44.254, 112.390, Point.CARTESIAN),
          new Point(28.098, 116.137, Point.CARTESIAN),
          new Point(14.985, 129.717, Point.CARTESIAN)
        )
      )
      .setTangentialHeadingInterpolation()
      .setReversed(true)
      .addPath(
        // middle sample after run intake command
        new BezierCurve(
          new Point(14.985, 129.717, Point.CARTESIAN),
          new Point(25.288, 125.737, Point.CARTESIAN),
          new Point(34.420, 127.844, Point.CARTESIAN)
        )
      )
      .setTangentialHeadingInterpolation()
      .addPath(
        // back to basket after run transfer then score commands
        new BezierCurve(
          new Point(34.420, 127.844, Point.CARTESIAN),
          new Point(25.054, 126.205, Point.CARTESIAN),
          new Point(15.922, 131.824, Point.CARTESIAN)
        )
      )
      .setTangentialHeadingInterpolation()
      .setReversed(true)
      .addPath(
        // top sample run intake command after
        new BezierCurve(
          new Point(15.922, 131.824, Point.CARTESIAN),
          new Point(39.337, 124.800, Point.CARTESIAN),
          new Point(47.298, 123.629, Point.CARTESIAN),
          new Point(46.361, 132.059, Point.CARTESIAN)
        )
      )
      .setTangentialHeadingInterpolation()
      .addPath(
        // back to basket for 3rd sample run transfer and score commands after
        new BezierCurve(
          new Point(46.361, 132.059, Point.CARTESIAN),
          new Point(29.034, 118.712, Point.CARTESIAN),
          new Point(15.220, 130.654, Point.CARTESIAN)
        )
      )
      .setTangentialHeadingInterpolation()
      .setReversed(true)
      .addPath(
        // go to park run slide commands after
        new BezierCurve(
          new Point(15.220, 130.654, Point.CARTESIAN),
          new Point(59.473, 120.351, Point.CARTESIAN),
          new Point(59.005, 98.341, Point.CARTESIAN)
        )
      )
      .setTangentialHeadingInterpolation()
      .setReversed(true)
      .addPath(
        // back up to tier 1 park run slides down after
        new BezierLine(
          new Point(59.005, 98.341, Point.CARTESIAN),
          new Point(59.005, 92.722, Point.CARTESIAN)
        )
      )
      .setTangentialHeadingInterpolation()
      .setReversed(true);



 */

