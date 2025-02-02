package org.firstinspires.ftc.teamcode.Robot.Opmodes.Teleop;

import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.ConditionalCommand;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.ParallelCommandGroup;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.arcrobotics.ftclib.command.button.Button;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.command.button.Trigger;
import com.arcrobotics.ftclib.controller.PIDController;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.Robot.Commands.DepositSubCommands.DepositPivotingCommand;
import org.firstinspires.ftc.teamcode.Robot.Commands.DepositSubCommands.VerticalExtensionCommand;
import org.firstinspires.ftc.teamcode.Robot.Commands.DepositSubCommands.VerticalRetractionCommand;
import org.firstinspires.ftc.teamcode.Robot.Commands.IntakeSubCommands.DropDownCommand;
import org.firstinspires.ftc.teamcode.Robot.Commands.IntakeSubCommands.FullExtendCommand;
import org.firstinspires.ftc.teamcode.Robot.Commands.IntakeSubCommands.RetractionCommand;
import org.firstinspires.ftc.teamcode.Robot.Commands.IntakeSubCommands.TeleopExtendoCommand;
import org.firstinspires.ftc.teamcode.Robot.Hardware;
import org.firstinspires.ftc.teamcode.Robot.Subsystems.Enums.DtPoints;
import org.firstinspires.ftc.teamcode.pedroPathing.localization.Pose;
import org.firstinspires.ftc.teamcode.pedroPathing.util.PIDFController;


@TeleOp(name = "semi auto teleop")
public class AutomatedTeleop extends OpMode {

    private Hardware robot = Hardware.getInstance();

    private GamepadEx driver2;
    private GamepadEx driver1;

    private double extensionPower = 0;


    private boolean ScoreSpeciments = false;

    private boolean vertUsingPid = false;
    private boolean horiUsingPid = false;
    private boolean isTransfering = false;
    private boolean scored = false;
    private boolean extendPid = false;

    private DtPoints alignTarget = DtPoints.BasketAlign;
    private boolean wantsToAlign = false;

    private PIDController alignpid = new PIDController(0.85,0,0);

    @Override
    public void init() {
        this.robot.Init(hardwareMap);

        CommandScheduler.getInstance().reset();

        this.driver1 = new GamepadEx(gamepad1);
        this.driver2 = new GamepadEx(gamepad2);
       // this.robot.intake.IntakeUp();
        this.robot.drivetrain.follower.setStartingPose(new Pose(136.5981308411215,33.420560747663544,0));
        this.robot.drivetrain.follower.startTeleopDrive();


        createBindings();
    }

    @Override
    public void loop() {
        this.robot =  Hardware.getInstance();
        this.robot.Loop();

        double turnPower = gamepad1.right_stick_x;

        if(wantsToAlign){
            Pose pose = this.robot.drivetrain.follower.getPose();
            switch (alignTarget){
                case BasketAlign:
                    turnPower = alignpid.calculate(1.90, pose.getHeading());
                    break;
                case LimelightAlign:
                    turnPower = this.robot.ll.lookAtSample();
                    break;
            }
        }


        this.robot.drivetrain.follower.setTeleOpMovementVectors(-gamepad1.left_stick_y, -gamepad1.left_stick_x, -turnPower,true);

        this.robot.drivetrain.follower.update();
        CommandScheduler.getInstance().run();
    }

    public void createBindings(){
        Button ll = new GamepadButton(driver1, GamepadKeys.Button.A).whenHeld(
                    new InstantCommand(() -> this.wantsToAlign = true)
        ).whenReleased(
                new InstantCommand(() -> this.wantsToAlign = false)
        );

        Button switchAlignMode = new GamepadButton(driver1, GamepadKeys.Button.LEFT_BUMPER).toggleWhenPressed(
                new InstantCommand(() -> this.alignTarget = DtPoints.LimelightAlign),
                new InstantCommand(() -> this.alignTarget = DtPoints.BasketAlign)
        );
        Button intake = new GamepadButton(driver2, GamepadKeys.Button.A).whenPressed(
                new SequentialCommandGroup(
                        new InstantCommand( () -> this.robot.intake.SetIntakeVelocity(-1000)),
                        new FullExtendCommand(this.robot.intake,900),
                        new InstantCommand(() -> this.robot.intake.DropDown())

                ));

        Trigger transfer = new Trigger(()-> this.robot.intake.GetSampleIntaked() && !this.scored).whenActive(
                new SequentialCommandGroup(
                        new InstantCommand( () -> this.extendPid = false),
                    new InstantCommand(() -> this.robot.intake.IntakeUp()),
                    new WaitCommand(500),
                    new ConditionalCommand(
                            new VerticalExtensionCommand(this.robot.deposit,350),
                            new VerticalExtensionCommand(this.robot.deposit,700),
                            () -> !this.scored
                    ),
                    new DepositPivotingCommand(this.robot.deposit,0,0,1),
                    new RetractionCommand(this.robot.intake),
                    new InstantCommand( () -> this.robot.deposit.ClawControl(0.1)),

                    new WaitCommand(700),
                        new VerticalRetractionCommand(this.robot.deposit),
                        new InstantCommand( () -> this.robot.deposit.ClawControl(0.3)),
                        new WaitCommand(500),
                        new ParallelCommandGroup(
                            new VerticalExtensionCommand(this.robot.deposit,1200),
                            new DepositPivotingCommand(this.robot.deposit,0.5,0.5,0),
                                new InstantCommand(() -> this.extendPid = true)
                                )
                )

        );

        Button Drop = new GamepadButton(driver2, GamepadKeys.Button.Y).whenPressed(
                new ConditionalCommand(
                        new SequentialCommandGroup(
                                new InstantCommand( () -> this.scored = true),
                                new InstantCommand(() -> this.robot.deposit.ClawControl(0.2))

                        ),
                        new SequentialCommandGroup(
                                new InstantCommand(() -> this.scored = false),
                                new DepositPivotingCommand(this.robot.deposit,0.6,0.6,0.3),
                                new VerticalRetractionCommand(this.robot.deposit),
                                new ConditionalCommand(
                                        new InstantCommand(() -> this.scored = true),
                                        new InstantCommand(() -> this.scored = false),
                                        () -> this.robot.intake.GetSampleIntaked()
                                )

                        ),
                        () -> !this.scored
                )
        );



        Button ScoreModeSwitch = new GamepadButton(driver2, GamepadKeys.Button.LEFT_BUMPER).toggleWhenPressed(
                new InstantCommand( ( ) -> changeScoreMode(true)),
                new InstantCommand(() -> changeScoreMode(false))
        );

        Button reverse = new GamepadButton(driver2, GamepadKeys.Button.X).toggleWhenPressed(
                new InstantCommand( () -> this.robot.intake.SetIntakeVelocity(-1000)),
                new InstantCommand( ()-> this.robot.intake.SetIntakeVelocity(1000))
        );







    }
    private void changeScoreMode(boolean mode){
        this.ScoreSpeciments = mode;
    }
    private void turnOnPidSlides(boolean enable){
        this.horiUsingPid = enable;
    }
    private void isTransferingEnable(boolean enable){
        this.isTransfering = enable;
    }

}

