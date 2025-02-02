package org.firstinspires.ftc.teamcode.Robot.Opmodes.Auto;

import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.ParallelCommandGroup;
import com.arcrobotics.ftclib.command.ParallelRaceGroup;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.arcrobotics.ftclib.command.WaitUntilCommand;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.Robot.Commands.AutoCommands.LimeLightLineup;
import org.firstinspires.ftc.teamcode.Robot.Commands.DepositSubCommands.DepositPivotingCommand;
import org.firstinspires.ftc.teamcode.Robot.Commands.DepositSubCommands.VerticalExtensionCommand;
import org.firstinspires.ftc.teamcode.Robot.Commands.DepositSubCommands.VerticalRetractionCommand;
import org.firstinspires.ftc.teamcode.Robot.Commands.DrivetrainSubcommands.FollowPathCommand;
import org.firstinspires.ftc.teamcode.Robot.Commands.DrivetrainSubcommands.SillyFollowPathCommand;
import org.firstinspires.ftc.teamcode.Robot.Commands.IntakeSubCommands.DropDownCommand;
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
    private Path thirdSamplePickup;
    private Path thirdSampleScore;

    double pose = 61;
    double power = 0;
    boolean loopStarted = false;

    private SequentialCommandGroup intake;
    @Override
    public void init() {
        this.robot.Init(hardwareMap);
        this.robot.drivetrain.follower.setStartingPose(new Pose(136.5981308411215,33.420560747663544,0));
        this.BuildPaths();
        this.robot.deposit.resetSlideEncoders();
        Config.isAuto = true;
        CommandScheduler.getInstance().reset();


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
        telemetry.addData("intaked",this.robot.intake.GetSampleIntaked());
         telemetry.update();


        CommandScheduler.getInstance().run();
    }

    private void BuildPaths(){


        scoreSamplePreload = new Path(new BezierCurve(
                new Point(136.598, 33.421, Point.CARTESIAN),
                new Point(132.336, 32.523, Point.CARTESIAN),
                new Point(130.093, 28.486, Point.CARTESIAN),
                new Point(133.907, 20.411, Point.CARTESIAN)
        ));
        firstSamplePickup = new Path(
                new BezierCurve(
                        new Point(133.907, 20.411, Point.CARTESIAN),
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
       /* intake = new SequentialCommandGroup(
                //new DepositPivotingCommand(this.robot.deposit,0.6,0.6,0),
                //new VerticalExtensionCommand(this.robot.deposit,450),
                //new SequentialCommandGroup(

                 //       new LimeLightLineup(this.robot.ll,this.robot.drivetrain),
                //        new WaitUntilCommand(() -> this.robot.ll.SampleInSight())
               // )
              //  ,
                new FullExtendCommand(this.robot.intake,10),
                new DropDownCommand(this.robot.intake, 61),
                new FullExtendCommand(this.robot.intake,20),
                new WaitUntilCommand( () -> this.robot.intake.GetSampleIntaked()),

                new DropDownCommand(this.robot.intake, 127),
                new InstantCommand( () -> this.robot.intake.SetPower(0)),
                new RetractionCommand(this.robot.intake),
                new DepositPivotingCommand(this.robot.deposit,0,0,1),
                new WaitCommand(200),
                new VerticalRetractionCommand(this.robot.deposit),
                new InstantCommand( () -> this.robot.deposit.ClawControl(0.3)),
                new WaitCommand(300)
                //new VerticalExtensionCommand(this.robot.deposit,500),
                //new DepositPivotingCommand(this.robot.deposit,0.6,0.6,0)
        );

        */

        CommandScheduler.getInstance().schedule(
                new SequentialCommandGroup(
                        new InstantCommand( () -> this.robot.deposit.ClawControl(0.3)),
                new ParallelCommandGroup(
                        new SillyFollowPathCommand(this.robot.drivetrain,scoreSamplePreload,true),
                        new VerticalExtensionCommand(this.robot.deposit,1100),
                        new DepositPivotingCommand(this.robot.deposit,0.6,0.6,0)
                ),
                        new WaitCommand(300),
                        new InstantCommand( () -> this.robot.deposit.ClawControl(0.1)),
                        new WaitCommand(500),
                        new DepositPivotingCommand(this.robot.deposit,0.3,0.3,1),


                        new FollowPathCommand(this.firstSamplePickup,this.robot.drivetrain),


                        new FullExtendCommand(this.robot.intake,600),
                        new InstantCommand( () -> this.robot.intake.DropDown()),
                        new WaitCommand(600),
                        //new DropDownCommand(this.robot.intake, 61),
                       // new DropDownCommand(this.robot.intake, 61),
                        new FullExtendCommand(this.robot.intake,1200),
                        new ParallelRaceGroup(
                            new WaitUntilCommand( () -> this.robot.intake.GetSampleIntaked()),
                                new WaitCommand(1000).andThen(
                                        new InstantCommand( () -> this.robot.intake.SetPower(500)).andThen(
                                                new WaitCommand(200)
                                        ).andThen(
                                                new InstantCommand( () -> this.robot.intake.setIntakePower(-2000))
                                        )
                                )
                                ),

                        new DropDownCommand(this.robot.intake, 130),
                        // new InstantCommand(()-> this.SetServoPose(130)),
                        new InstantCommand( () -> this.robot.intake.SetPower(0)),
                        new WaitUntilCommand(() -> this.robot.intake.distanceSensor.getDistance(DistanceUnit.MM) > 125),

                        new RetractionCommand(this.robot.intake),
                        //new FullExtendCommand(this.robot.intake,0),
                        new DepositPivotingCommand(this.robot.deposit,0,0,1),
                        new InstantCommand( () -> this.robot.deposit.ClawControl(0)),
                        new WaitCommand(700),
                        //new VerticalRetractionCommand(this.robot.deposit),
                        new VerticalExtensionCommand(this.robot.deposit,100),
                        new WaitCommand(500),
                        new VerticalExtensionCommand(this.robot.deposit,10),
                        new InstantCommand( () -> this.robot.deposit.ClawControl(0.3)),
                        new WaitCommand(1000),
                        new VerticalExtensionCommand(this.robot.deposit,500),
                        new DepositPivotingCommand(this.robot.deposit,0.6,0.6,0),
                        new WaitCommand(300),
                        new ParallelCommandGroup(
                                new SillyFollowPathCommand(this.robot.drivetrain,firstSampleScore,true),
                                new VerticalExtensionCommand(this.robot.deposit,1090),
                                new DepositPivotingCommand(this.robot.deposit,0.5,0.5,0)
                        ),
                        new InstantCommand( () -> this.robot.deposit.ClawControl(0.1)),
                        new WaitCommand(200),
                        new DepositPivotingCommand(this.robot.deposit,0.3,0.3,1),
                        //new VerticalRetractionCommand(this.robot.deposit),
                        new FollowPathCommand(this.secondSamplePickup,this.robot.drivetrain),
                        new FullExtendCommand(this.robot.intake,15),

                        new DropDownCommand(this.robot.intake, 61) ,

                        new WaitCommand(1000),

                        new WaitUntilCommand( () -> this.robot.intake.GetSampleIntaked()),

                        new DropDownCommand(this.robot.intake, 130),
                        // new InstantCommand(()-> this.SetServoPose(130)),
                        new InstantCommand( () -> this.robot.intake.SetPower(0)),
                        //new WaitUntilCommand(() -> this.robot.intake.distanceSensor.getDistance(DistanceUnit.MM) > 120),

                        new RetractionCommand(this.robot.intake),
                        new FullExtendCommand(this.robot.intake,0),
                        new DepositPivotingCommand(this.robot.deposit,0,0,1),
                        new InstantCommand( () -> this.robot.deposit.ClawControl(0)),
                        //new WaitCommand(1000),
                        //new VerticalRetractionCommand(this.robot.deposit),
                        //new InstantCommand( () -> this.robot.deposit.ClawControl(0.3)),
                        new VerticalRetractionCommand(this.robot.deposit),
                        new WaitCommand(1000),
                        new VerticalExtensionCommand(this.robot.deposit,500),
                        new DepositPivotingCommand(this.robot.deposit,0.6,0.6,0),
                        new WaitCommand(300),
                        new ParallelCommandGroup(
                                new SillyFollowPathCommand(this.robot.drivetrain,secondSampleScore,true),
                                new VerticalExtensionCommand(this.robot.deposit,1090),
                                new DepositPivotingCommand(this.robot.deposit,0.5,0.5,0)
                        ),
                        new WaitCommand(100),
                        new InstantCommand( () -> this.robot.deposit.ClawControl(0.1)),
                        new WaitCommand(200),
                        new DepositPivotingCommand(this.robot.deposit,0.3,0.3,1),
                        new VerticalRetractionCommand(this.robot.deposit)




                )
        );



    }
    public void SetServoPose(double pose){
        this.pose = pose;
    }
}
