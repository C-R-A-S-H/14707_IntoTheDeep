package org.firstinspires.ftc.teamcode.Robot.Opmodes.Teleop;

import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.arcrobotics.ftclib.command.button.Button;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.command.button.Trigger;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Robot.Commands.DepositSubCommands.DepositPivotingCommand;
import org.firstinspires.ftc.teamcode.Robot.Commands.DepositSubCommands.TransferCommand;
import org.firstinspires.ftc.teamcode.Robot.Commands.DepositSubCommands.VerticalExtensionCommand;
import org.firstinspires.ftc.teamcode.Robot.Commands.DrivetrainSubcommands.DriveCommand;
import org.firstinspires.ftc.teamcode.Robot.Commands.IntakeSubCommands.AutoIntakeCommand;
import org.firstinspires.ftc.teamcode.Robot.Commands.IntakeSubCommands.FullExtendCommand;
import org.firstinspires.ftc.teamcode.Robot.Commands.IntakeSubCommands.IntakeActivationCommand;
import org.firstinspires.ftc.teamcode.Robot.Commands.IntakeSubCommands.RetractionCommand;
import org.firstinspires.ftc.teamcode.Robot.Commands.IntakeSubCommands.RetractionVoltageCommand;
import org.firstinspires.ftc.teamcode.Robot.Commands.IntakeSubCommands.TeleopExtendoCommand;
import org.firstinspires.ftc.teamcode.Robot.Config;
import org.firstinspires.ftc.teamcode.Robot.Hardware;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

@TeleOp(name ="No Auto Teleop")
public class NonAutoTeleop extends OpMode {
    private Hardware robot = Hardware.getInstance();

    private GamepadEx driver2;

    private MotorEx frontLeft;
    private MotorEx frontRight;
    private MotorEx backLeft;
    private MotorEx backRight;

    private MecanumDrive drive;

    @Override
    public void init() {
        this.robot.Init(hardwareMap);

        this.driver2 = new GamepadEx(gamepad2);

        this.frontLeft = new MotorEx(hardwareMap,"FrontLeft");
        this.frontRight = new MotorEx(hardwareMap, "FrontRight");
        this.backLeft = new MotorEx(hardwareMap, "BackLeft");
        this.backRight = new MotorEx(hardwareMap, "BackRight");

        drive = new MecanumDrive(this.frontLeft, this.frontRight, this.backLeft, this.backRight);


        createBindings();
    }

    @Override
    public void loop() {
        this.robot =  Hardware.getInstance();
        this.robot.Loop();

        this.robot.deposit.SetSlidePower(this.driver2.getLeftY());

        this.drive.driveRobotCentric(
                -gamepad1.left_stick_x,
                gamepad1.left_stick_y,
                -gamepad1.right_stick_x
        );
        CommandScheduler.getInstance().run();

    }

    public void createBindings(){
      //Button class
        Button intake = new GamepadButton(driver2, GamepadKeys.Button.A).whenHeld(
                new FullExtendCommand(this.robot.intake)
        ).whenReleased(new RetractionCommand(this.robot.intake));

        Button highBasket = new GamepadButton(driver2, GamepadKeys.Button.B).toggleWhenPressed(
                new DepositPivotingCommand(this.robot.deposit, 0.4,.25,0),
                new DepositPivotingCommand(this.robot.deposit,0,0,1)
        );

        Button Drop = new GamepadButton(driver2, GamepadKeys.Button.Y).toggleWhenPressed(
                new InstantCommand( () -> this.robot.deposit.ClawControl(0)),
                new InstantCommand( () -> this.robot.deposit.ClawControl(.35))
        );

        Button reverse = new GamepadButton(driver2, GamepadKeys.Button.X).toggleWhenPressed(
                new InstantCommand( () -> this.robot.intake.SetIntakeVelocity(-1000)),
                new InstantCommand( ()-> this.robot.intake.SetIntakeVelocity(2000))
        );
                //new FullExtendCommand(this.robot.intake)


        
    }



}
