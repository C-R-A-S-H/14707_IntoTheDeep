package org.firstinspires.ftc.teamcode.Robot.Opmodes.Teleop;

import com.arcrobotics.ftclib.command.CommandScheduler;
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

import org.firstinspires.ftc.teamcode.Robot.Commands.DrivetrainSubcommands.DriveCommand;
import org.firstinspires.ftc.teamcode.Robot.Commands.IntakeSubCommands.IntakeActivationCommand;
import org.firstinspires.ftc.teamcode.Robot.Commands.IntakeSubCommands.RetractionVoltageCommand;
import org.firstinspires.ftc.teamcode.Robot.Commands.IntakeSubCommands.TeleopExtendoCommand;
import org.firstinspires.ftc.teamcode.Robot.Config;
import org.firstinspires.ftc.teamcode.Robot.Hardware;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

@TeleOp(name ="No Auto Teleop")
public class NonAutoTeleop extends OpMode {
    //private Hardware robot = Hardware.getInstance();


    private MotorEx frontLeft;
    private MotorEx frontRight;
    private MotorEx backLeft;
    private MotorEx backRight;

    private MecanumDrive drive;

    private Servo Arm;
    private Servo Wrist;
    private Servo DepositClaw;

    public MotorEx SlidesA;
    public MotorEx SlidesB;
    @Override
    public void init() {
        //this.robot.Init(hardwareMap);


        this.frontLeft = new MotorEx(hardwareMap,"FrontLeft");
        this.frontRight = new MotorEx(hardwareMap,"FrontRight");
        this.backLeft = new MotorEx(hardwareMap,"BackLeft");
        this.backRight = new MotorEx(hardwareMap,"BackRight");

        this.SlidesA = new MotorEx(hardwareMap,"SlideA");
        this.SlidesB = new MotorEx(hardwareMap, "SlideB");

        this.Arm = hardwareMap.get(Servo.class,"Arm");
        this.Wrist = hardwareMap.get(Servo.class, "Wrist");
        this.DepositClaw = hardwareMap.get(Servo.class,"Claw");


        drive = new MecanumDrive(this.frontLeft, this.frontRight, this.backLeft, this.backRight);

        // CommandScheduler.getInstance().setDefaultCommand(this.robot.drivetrain,new DriveCommand(this.robot.drivetrain,xSupplier,ySupplier,zSupplier)); //add drive command
    }

    @Override
    public void loop() {

        this.drive.driveRobotCentric(
                -gamepad1.left_stick_x * 0.8,
                gamepad1.left_stick_y * 0.8,
                -gamepad1.right_stick_x * 0.8);

        double power = gamepad2.left_stick_y;

        this.SlidesA.set(power);
        this.SlidesB.set(power);

        if(gamepad2.a){
            Wrist.setPosition(0.5);
            Arm.setPosition(0.2);
        }else if(gamepad2.b){
            Wrist.setPosition(0.8);
            Arm.setPosition(-1);
        }else
        if(gamepad2.x){
            DepositClaw.setPosition(1);
        }else
        if(gamepad2.y){
            DepositClaw.setPosition(-1);
        }


    }

    public void createBindings(){
        /*Button Intake = (new GamepadButton(driver1, GamepadKeys.Button.A).whenHeld(
                new TeleopExtendoCommand(this.robot.intake).alongWith(
                        new IntakeActivationCommand(this.robot.intake, this.robot.transferColorSensor).andThen(new RetractionVoltageCommand(this.robot.intake))
                )
        ).whenReleased(new RetractionVoltageCommand(this.robot.intake)));

         */

    }

}
