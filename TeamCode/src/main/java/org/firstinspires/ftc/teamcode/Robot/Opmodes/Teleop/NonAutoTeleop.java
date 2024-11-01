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
    private Hardware robot = Hardware.getInstance();

    private DoubleSupplier xSupplier = this::getX;
    private DoubleSupplier ySupplier = this::getY;
    private DoubleSupplier zSupplier = this::getTurn;

    @Override
    public void init() {
        this.robot.Init(hardwareMap);



        CommandScheduler.getInstance().setDefaultCommand(this.robot.drivetrain,new DriveCommand(this.robot.drivetrain,xSupplier,ySupplier,zSupplier)); //add drive command
    }

    @Override
    public void loop() {
        this.robot =  Hardware.getInstance();
        this.robot.Loop();


    }

    public void createBindings(){
      //Button class
        
    }

    public double getX(){
        return gamepad1.left_stick_x;
    }
    public double getY(){
        return gamepad1.left_stick_y;
    }
    public double getTurn(){
        return gamepad1.right_stick_x;
    }

}
