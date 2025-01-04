package org.firstinspires.ftc.teamcode.Robot.Opmodes.Auto;

import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.ParallelCommandGroup;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Robot.Commands.DepositSubCommands.DepositPivotingCommand;
import org.firstinspires.ftc.teamcode.Robot.Commands.DepositSubCommands.VerticalExtensionCommand;
import org.firstinspires.ftc.teamcode.Robot.Commands.DepositSubCommands.VerticalRetractionCommand;
import org.firstinspires.ftc.teamcode.Robot.Commands.DrivetrainSubcommands.FollowPathCommand;
import org.firstinspires.ftc.teamcode.Robot.Commands.DrivetrainSubcommands.SillyFollowPathCommand;
import org.firstinspires.ftc.teamcode.Robot.Commands.IntakeSubCommands.FullExtendCommand;
import org.firstinspires.ftc.teamcode.Robot.Commands.IntakeSubCommands.RetractionCommand;
import org.firstinspires.ftc.teamcode.Robot.Config;
import org.firstinspires.ftc.teamcode.Robot.Hardware;
import org.firstinspires.ftc.teamcode.pedroPathing.localization.Pose;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.BezierCurve;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.BezierLine;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.Path;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.Point;

@Autonomous(name = "4 sample")
public class fourSample extends OpMode {
    private Hardware robot = Hardware.getInstance();

    private Path scoreSamplePreload;
    private Path firstSamplePickup;
    private Path firstSampleScore;
    private Path secondSamplePickup;
    private Path secondSampleScore;
    @Override
    public void init() {
        this.robot.Init(hardwareMap);
        this.robot.drivetrain.follower.setStartingPose(new Pose(136.5981308411215,33.420560747663544,0));
        this.BuildPaths();
        this.robot.deposit.resetSlideEncoders();
        Config.isAuto = true;


    }

    @Override
    public void start() {
        ScheduleCommands();
        super.start();
    }

    @Override
    public void loop() {
        this.robot.drivetrain.follower.update();
        this.robot =  Hardware.getInstance();
        this.robot.Loop();

        CommandScheduler.getInstance().run();
    }

    private void BuildPaths(){


        scoreSamplePreload = new Path(new BezierCurve(
                new Point(136.598, 33.421, Point.CARTESIAN),
                new Point(132.336, 32.523, Point.CARTESIAN),
                new Point(130.093, 28.486, Point.CARTESIAN),
                new Point(134.579, 21.757, Point.CARTESIAN)
        ));
        firstSamplePickup = new Path(
                new BezierCurve(
                        new Point(134.579, 21.757, Point.CARTESIAN),
                        new Point(135.925, 29.607, Point.CARTESIAN),
                        new Point(126.280, 26.692, Point.CARTESIAN)
                )
        );
        firstSampleScore = new Path(
                new BezierCurve(
                        new Point(126.280, 26.692, Point.CARTESIAN),
                        new Point(130.542, 24.897, Point.CARTESIAN),
                        new Point(133.458, 20.860, Point.CARTESIAN)
                )
        );

        secondSamplePickup = new Path(
                new BezierCurve(
                        new Point(133.458, 20.860, Point.CARTESIAN),
                        new Point(132.112, 23.551, Point.CARTESIAN),
                        new Point(126.505, 21.981, Point.CARTESIAN)
                )
        );
        secondSampleScore = new Path(
                new BezierCurve(
                        new Point(126.505, 21.981, Point.CARTESIAN),
                        new Point(130.093, 22.654, Point.CARTESIAN),
                        new Point(130.766, 18.841, Point.CARTESIAN)
                )
        );
        scoreSamplePreload.setReversed(true);
        firstSampleScore.setReversed(true);
        secondSampleScore.setReversed(true);

        this.robot.drivetrain.follower.setMaxPower(0.6);

        this.robot.drivetrain.follower.pathBuilder().addPath(scoreSamplePreload).setReversed(true);
        this.robot.drivetrain.follower.pathBuilder().addPath(firstSamplePickup);
        this.robot.drivetrain.follower.pathBuilder().addPath(firstSampleScore).setReversed(true);
        this.robot.drivetrain.follower.pathBuilder().addPath(secondSamplePickup);
        this.robot.drivetrain.follower.pathBuilder().addPath(secondSampleScore).setReversed(true);


    }
    private void ScheduleCommands(){
        CommandScheduler.getInstance().schedule(
                new SequentialCommandGroup(
                        new InstantCommand( () -> this.robot.deposit.ClawControl(0.3)),
                new ParallelCommandGroup(
                        new SillyFollowPathCommand(this.robot.drivetrain,scoreSamplePreload,true),
                        new VerticalExtensionCommand(this.robot.deposit,1090),
                        new DepositPivotingCommand(this.robot.deposit,0.5,0.5,0)
                ),
                        new WaitCommand(300),
                        new InstantCommand( () -> this.robot.deposit.ClawControl(0)),
                        new WaitCommand(200),
                        new DepositPivotingCommand(this.robot.deposit,0,0,1),
                        new VerticalRetractionCommand(this.robot.deposit),
                        new FollowPathCommand(this.firstSamplePickup,this.robot.drivetrain),
                        new FullExtendCommand(this.robot.intake),
                        new WaitCommand(500),
                        new RetractionCommand(this.robot.intake),
                        new WaitCommand(1000),
                        new InstantCommand( () -> this.robot.deposit.ClawControl(0.3)),
                        new ParallelCommandGroup(
                                new SillyFollowPathCommand(this.robot.drivetrain,firstSampleScore,true),
                                new VerticalExtensionCommand(this.robot.deposit,1090),
                                new DepositPivotingCommand(this.robot.deposit,0.5,0.5,0)
                        ), new WaitCommand(300),
                        new InstantCommand( () -> this.robot.deposit.ClawControl(0)),
                        new WaitCommand(700),
                        new DepositPivotingCommand(this.robot.deposit,0,0,1),
                        new VerticalRetractionCommand(this.robot.deposit),
                        new FollowPathCommand(this.secondSamplePickup,this.robot.drivetrain),
                        new FullExtendCommand(this.robot.intake),
                        new WaitCommand(500),
                        new RetractionCommand(this.robot.intake),
                        new WaitCommand(1000),
                        new ParallelCommandGroup(
                                new SillyFollowPathCommand(this.robot.drivetrain,secondSampleScore,true),
                                new VerticalExtensionCommand(this.robot.deposit,1090),
                                new DepositPivotingCommand(this.robot.deposit,0.5,0.5,0)
                        ),
                        new WaitCommand(1000),
                        new DepositPivotingCommand(this.robot.deposit,0,0,1),
                        new VerticalRetractionCommand(this.robot.deposit)
                )
        );
    }
}
