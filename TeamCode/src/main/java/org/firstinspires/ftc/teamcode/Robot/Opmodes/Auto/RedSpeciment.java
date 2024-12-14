package org.firstinspires.ftc.teamcode.Robot.Opmodes.Auto;

import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Robot.Commands.DepositSubCommands.DepositPivotingCommand;
import org.firstinspires.ftc.teamcode.Robot.Commands.DepositSubCommands.VerticalExtensionCommand;
import org.firstinspires.ftc.teamcode.Robot.Commands.DepositSubCommands.VerticalRetractionCommand;
import org.firstinspires.ftc.teamcode.Robot.Commands.DrivetrainSubcommands.SillyFollowPathCommand;
import org.firstinspires.ftc.teamcode.Robot.Hardware;
import org.firstinspires.ftc.teamcode.pedroPathing.localization.Pose;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.BezierCurve;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.Path;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.Point;

@Autonomous(name = "RedSpeciment")
public class RedSpeciment extends OpMode {
    private Hardware robot = Hardware.getInstance();

    private Path scorePreload;
    private Path barToHuman;
    @Override
    public void init() {
        this.robot.Init(hardwareMap);
        this.robot.drivetrain.follower.setStartingPose(new Pose(135.9,80.74766355140187,0));
        this.buildPaths();

        this.robot.deposit.ClawControl(.45);
        this.robot.deposit.SetServoPoses(0.5,0.5,0.5 );

        scheduleCommands();
    }

    @Override
    public void loop() {
        this.robot.drivetrain.follower.update();
        this.robot =  Hardware.getInstance();
        this.robot.Loop();



        CommandScheduler.getInstance().run();
    }

    public void buildPaths(){
        scorePreload = new Path(new BezierCurve(
                new Point(136.374, 80.748, Point.CARTESIAN),
                new Point(120.673, 80.748, Point.CARTESIAN),
                new Point(122.467, 73.794, Point.CARTESIAN),
                new Point(103.402, 76.486, Point.CARTESIAN)
        ));

        barToHuman = new Path( new BezierCurve(
                new Point(103.402, 76.486, Point.CARTESIAN),
                new Point(111.925, 120.000, Point.CARTESIAN),
                new Point(135.477, 119.327, Point.CARTESIAN)
        ));

        this.scorePreload.setReversed(true);

        this.barToHuman.setReversed(true);

        this.robot.drivetrain.follower.pathBuilder().addPath(scorePreload).setReversed(true);

        this.robot.drivetrain.follower.pathBuilder().addPath(barToHuman).setReversed(true);




    }

    public void scheduleCommands(){
        CommandScheduler.getInstance().schedule(
                new SequentialCommandGroup(
                       new SillyFollowPathCommand(
                                this.robot.drivetrain,this.scorePreload,true
                       ),-
                        new VerticalExtensionCommand(this.robot.deposit,400),
                        new DepositPivotingCommand(this.robot.deposit, 1,1,0),
                        new WaitCommand(500),
                        new VerticalExtensionCommand(this.robot.deposit, 0),
                        new InstantCommand(() -> this.robot.deposit.ClawControl(0))

                )

        );
    }


}
