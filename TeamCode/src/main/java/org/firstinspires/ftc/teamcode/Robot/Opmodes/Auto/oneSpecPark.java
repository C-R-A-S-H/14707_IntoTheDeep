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
public class oneSpecPark extends OpMode {
    private Hardware robot = Hardware.getInstance();

    private Path scorePreload;
    private Path barToHuman;
    @Override
    public void init() {
        this.robot.Init(hardwareMap);
        this.robot.drivetrain.follower.setStartingPose(new Pose(135.9,80.74766355140187,0));
        this.buildPaths();



        scheduleCommands();
    }

    @Override
    public void start() {
        this.robot.deposit.ClawControl(.35);

        this.robot.intake.setSlidePower(-0.2);
        this.robot.deposit.SetServoPoses(0.5,0.5,0.5 );
        this.robot.intake.IntakeToAutoPose();
        super.start();
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
                new Point(133.682, 82.318, Point.CARTESIAN),
                new Point(128.075, 82.318, Point.CARTESIAN),
                new Point(130.318, 77.383, Point.CARTESIAN),
                new Point(117.981, 77.832, Point.CARTESIAN)
        ));

        barToHuman = new Path( new BezierCurve(
                new Point(117.981, 77.832, Point.CARTESIAN),
                new Point(133.234, 89.047, Point.CARTESIAN),
                new Point(131.664, 124.486, Point.CARTESIAN)
        ));

        this.scorePreload.setReversed(true);

        this.barToHuman.setReversed(false);

        this.robot.drivetrain.follower.pathBuilder().addPath(scorePreload).setReversed(true);

        this.robot.drivetrain.follower.pathBuilder().addPath(barToHuman).setReversed(false);




    }

    public void scheduleCommands(){
        CommandScheduler.getInstance().schedule(
                new SequentialCommandGroup(

                       new SillyFollowPathCommand(
                                this.robot.drivetrain,this.scorePreload,true
                       )
                        ,new InstantCommand( () -> this.robot.deposit.SetServoPoses(0.5,0.5,0.5 )),
                       new InstantCommand( () ->  this.robot.intake.IntakeToAutoPose())
                       ,

                        new WaitCommand(300),
                        new VerticalExtensionCommand(this.robot.deposit,100),
                        new DepositPivotingCommand(this.robot.deposit, 0.45,0.45,0),
                        new WaitCommand(2000),
                        new VerticalRetractionCommand(this.robot.deposit).alongWith(
                        new InstantCommand( () -> this.robot.deposit.ClawControl(0.2))
                        ),
                        new InstantCommand( () -> this.robot.deposit.ClawControl(0)),
                        new DepositPivotingCommand(this.robot.deposit,0,0,0.96),
                        new SillyFollowPathCommand(
                                this.robot.drivetrain, this.barToHuman, false
                        )

                )

        );
    }


}
