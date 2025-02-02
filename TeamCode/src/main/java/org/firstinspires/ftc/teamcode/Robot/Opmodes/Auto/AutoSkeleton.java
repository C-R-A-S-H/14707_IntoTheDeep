package org.firstinspires.ftc.teamcode.Robot.Opmodes.Auto;

import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Robot.Commands.DepositSubCommands.DepositPivotingCommand;
import org.firstinspires.ftc.teamcode.Robot.Commands.DepositSubCommands.VerticalExtensionCommand;
import org.firstinspires.ftc.teamcode.Robot.Commands.DepositSubCommands.VerticalRetractionCommand;
import org.firstinspires.ftc.teamcode.Robot.Commands.DrivetrainSubcommands.SillyFollowPathCommand;
import org.firstinspires.ftc.teamcode.Robot.Hardware;
import org.firstinspires.ftc.teamcode.Robot.Subsystems.Intake;
import org.firstinspires.ftc.teamcode.pedroPathing.localization.Pose;
import org.firstinspires.ftc.teamcode.pedroPathing.localization.PoseUpdater;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.BezierCurve;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.Path;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.Point;

@Autonomous(name = "RAS")
public class AutoSkeleton extends OpMode {
    private Hardware robot = Hardware.getInstance();

    private Path one;
    private Path two;
    private Path three;
    private Path four;
    private Telemetry telemetryA;
    private PoseUpdater poseUpdater;


    @Override
    public void init() {                        // Initialize robot
        this.robot.Init(hardwareMap);
        this.robot.drivetrain.follower.setStartingPose(new Pose(7.7,85.2,180));
        this.BuildPaths();

    }

    @Override
    public void start() {           // Starts the Auto functions, going through the "ScheduleCommands()"
        ScheduleCommands();
        super.start();
    }

    @Override
    public void loop() {            // Constantly running during Auto
        this.robot.drivetrain.follower.update();
        telemetryA.addData("x", poseUpdater.getPose().getX());  // Prints out current LazerX position
        telemetryA.addData("y", poseUpdater.getPose().getY());  // Prints out current LazerY position
        telemetryA.addData("heading", poseUpdater.getPose().getHeading());  // Prints out current heading pos
        telemetryA.addData("total heading", poseUpdater.getTotalHeading());
        telemetry.update();
        this.robot =  Hardware.getInstance();
        this.robot.Loop();
        CommandScheduler.getInstance().run();
    }

    private void BuildPaths(){      // Set Paths here
        one = new Path(new BezierCurve(
                new Point(7.7, 85.2, Point.CARTESIAN),
                new Point(14, 85.1, Point.CARTESIAN),
                new Point(22.1, 76.6, Point.CARTESIAN),
                new Point(38.3, 77, Point.CARTESIAN)
        ));
        this.one.setReversed(true);
        this.robot.drivetrain.follower.pathBuilder().addPath(one).setReversed(true);

        two = new Path(new BezierCurve(
                // LINE POINTS HERE
        ));
        this.two.setReversed(true);
        this.robot.drivetrain.follower.pathBuilder().addPath(two).setReversed(true);

        three = new Path(new BezierCurve(
                // LINE POINTS HERE
        ));
        this.three.setReversed(true);
        this.robot.drivetrain.follower.pathBuilder().addPath(three).setReversed(true);

        four = new Path(new BezierCurve(
                // LINE POINTS HERE
        ));
        this.four.setReversed(true);
        this.robot.drivetrain.follower.pathBuilder().addPath(four).setReversed(true);






    }
    private void ScheduleCommands() {
        CommandScheduler.getInstance().schedule(
                new SequentialCommandGroup(
                        new SillyFollowPathCommand(this.robot.drivetrain, this.one, true)


                        // Auto Commands to follow go here
                )
        );
    }
}
