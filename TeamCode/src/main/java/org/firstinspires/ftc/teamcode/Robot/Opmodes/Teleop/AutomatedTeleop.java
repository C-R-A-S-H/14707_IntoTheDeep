package org.firstinspires.ftc.teamcode.Robot.Opmodes.Teleop;

import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.ConditionalCommand;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.button.Button;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Robot.Commands.DepositSubCommands.DepositPivotingCommand;
import org.firstinspires.ftc.teamcode.Robot.Hardware;

@TeleOp(name = "semi auto teleop")
public class AutomatedTeleop extends OpMode {
    private Hardware robot = Hardware.getInstance();

    private GamepadEx driver2;
    private GamepadEx driver1;

    private double extensionPower = 0;

    private boolean ScoreSpeciments = false;

    private boolean usingPid = false;

    @Override
    public void init() {
        this.robot.Init(hardwareMap);

        CommandScheduler.getInstance().cancelAll();

        this.driver1 = new GamepadEx(gamepad1);
        this.driver2 = new GamepadEx(gamepad2);
        this.robot.intake.IntakeUp();

        this.robot.drivetrain.follower.startTeleopDrive();


        createBindings();
    }

    @Override
    public void loop() {
        this.robot =  Hardware.getInstance();
        this.robot.Loop();

        this.extensionPower = this.driver2.getLeftY();

        if (!usingPid){
            this.robot.deposit.SetSlidePower(this.driver2.getRightY());
        }


        this.robot.drivetrain.follower.setTeleOpMovementVectors(-gamepad1.left_stick_y, -gamepad1.left_stick_x, -gamepad1.right_stick_x);

        this.robot.drivetrain.follower.update();
        CommandScheduler.getInstance().run();
    }

    public void createBindings(){
        Button intake = new GamepadButton(driver2, GamepadKeys.Button.A).toggleWhenPressed(
                new SequentialCommandGroup(
                        new InstantCommand(() -> this.robot.intake.DropDown()),
                        new InstantCommand( () -> this.robot.intake.SetIntakeVelocity(-1000))
                ),
                new SequentialCommandGroup(
                        new InstantCommand(() -> this.robot.intake.IntakeUp()),
                        new InstantCommand( () -> this.robot.intake.SetIntakeVelocity(-1000))
                )
        );

        Button highBasket = new GamepadButton(driver2, GamepadKeys.Button.B).toggleWhenPressed(
                new DepositPivotingCommand(this.robot.deposit,0,0,1)
                ,
                new ConditionalCommand(
                        new DepositPivotingCommand(this.robot.deposit, 1,1,0),
                        new DepositPivotingCommand(this.robot.deposit,0.5,0.5,0),
                        () -> ScoreSpeciments
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
}

