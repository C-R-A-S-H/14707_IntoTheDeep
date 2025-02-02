package org.firstinspires.ftc.teamcode.Robot.Opmodes.Auto;

import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.InstantCommand;
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
import org.firstinspires.ftc.teamcode.Robot.Commands.IntakeSubCommands.DropDownCommand;
import org.firstinspires.ftc.teamcode.Robot.Commands.IntakeSubCommands.FullExtendCommand;
import org.firstinspires.ftc.teamcode.Robot.Commands.IntakeSubCommands.RetractionCommand;
import org.firstinspires.ftc.teamcode.Robot.Hardware;
import org.firstinspires.ftc.teamcode.pedroPathing.localization.Pose;

@Autonomous(name = "LL auto test")
public class LimeLightIntakeTest extends OpMode {
    private Hardware robot = Hardware.getInstance();

    double pose;
    double power = 0;

    @Override
    public void init() {
        this.robot.Init(hardwareMap);
        this.robot.drivetrain.follower.setStartingPose(new Pose(135.9,80.74766355140187,0));
        this.BuildPaths();
        CommandScheduler.getInstance().reset();

        pose = this.robot.intake.distanceSensor.getDistance(DistanceUnit.MM);

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

        //double power = this.robot.intake.getDropPid().calculate(this.pose,this.robot.intake.distanceSensor.getDistance(DistanceUnit.MM));
        //this.robot.intake.setServoPower(power);
        CommandScheduler.getInstance().run();
    }

    private void BuildPaths(){

    }
    private void ScheduleCommands(){
        CommandScheduler.getInstance().schedule(
            new SequentialCommandGroup(
                    new DepositPivotingCommand(this.robot.deposit,0.6,0.6,0),
                    new VerticalExtensionCommand(this.robot.deposit,450),
                    new SequentialCommandGroup(
                            new LimeLightLineup(this.robot.ll,this.robot.drivetrain),
                            new WaitUntilCommand(() -> this.robot.ll.SampleInSight())
                    )
                    ,
                    //new InstantCommand(()-> this.SetServoPose(61)),
                    new FullExtendCommand(this.robot.intake,15),

                    new DropDownCommand(this.robot.intake, 61) ,

                    new WaitCommand(1000),

                    new WaitUntilCommand( () -> this.robot.intake.GetSampleIntaked()),

                    new DropDownCommand(this.robot.intake, 130),
                   // new InstantCommand(()-> this.SetServoPose(130)),
                    new InstantCommand( () -> this.robot.intake.SetPower(0)),
                    new WaitUntilCommand(() -> this.robot.intake.distanceSensor.getDistance(DistanceUnit.MM) > 120),
                    new RetractionCommand(this.robot.intake),
                    new FullExtendCommand(this.robot.intake,0),
                    new DepositPivotingCommand(this.robot.deposit,0,0,1),
                    new InstantCommand( () -> this.robot.deposit.ClawControl(0)),
                    new WaitCommand(1000),
                    new VerticalRetractionCommand(this.robot.deposit),
                    new InstantCommand( () -> this.robot.deposit.ClawControl(0.3)),
                    new WaitCommand(1000),
                    new VerticalExtensionCommand(this.robot.deposit,500),
                    new DepositPivotingCommand(this.robot.deposit,0.6,0.6,0)

            )
        );
    }

    public void SetServoPose(double pose){
        this.pose = pose;
    }
}
