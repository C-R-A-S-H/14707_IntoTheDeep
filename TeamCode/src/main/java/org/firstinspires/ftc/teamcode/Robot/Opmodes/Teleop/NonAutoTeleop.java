package org.firstinspires.ftc.teamcode.Robot.Opmodes.Teleop;

import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.ConditionalCommand;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.arcrobotics.ftclib.command.button.Button;
import com.arcrobotics.ftclib.command.button.Trigger;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.I2cAddr;

import org.firstinspires.ftc.teamcode.Robot.Commands.DrivetrainSubcommands.DriveCommand;
import org.firstinspires.ftc.teamcode.Robot.Commands.IntakeSubCommands.FullExtendCommand;
import org.firstinspires.ftc.teamcode.Robot.Commands.IntakeSubCommands.HalfExtendCommand;
import org.firstinspires.ftc.teamcode.Robot.Commands.IntakeSubCommands.IntakeActivationCommand;
import org.firstinspires.ftc.teamcode.Robot.Commands.IntakeSubCommands.RetractionCommand;
import org.firstinspires.ftc.teamcode.Robot.Config;
import org.firstinspires.ftc.teamcode.Robot.Hardware;

import java.util.function.DoubleSupplier;

@TeleOp(name ="No Auto Teleop")
public class NonAutoTeleop extends OpMode {
    private Hardware robot = Hardware.getInstance();

    private GamepadEx driver1 = new GamepadEx(gamepad1);
    private GamepadEx driver2 = new GamepadEx(gamepad2);


    private FullExtendCommand fullExtendCommand = new FullExtendCommand(this.robot.intake,this.robot.ll);
    private HalfExtendCommand halfExtendCommand = new HalfExtendCommand(this.robot.intake);
    private IntakeActivationCommand intakeActivationCommand = new IntakeActivationCommand(this.robot.intake,this.robot.intakeBeamBreak);
    private RetractionCommand retractionCommand = new RetractionCommand(this.robot.intake,this.robot.horizontalLimitSwitch);


    private DoubleSupplier xSupplier;
    private DoubleSupplier ySupplier;
    private DoubleSupplier zSupplier;
    @Override
    public void init() {
        this.robot.Init(hardwareMap);
    }

    @Override
    public void loop() {
        this.robot =  Hardware.getInstance();
        this.robot.Loop();
        //CommandScheduler.getInstance().setDefaultCommand(this.robot.drivetrain,); //add drive command

        if (Config.AutoScoring){

        }else{

        }
    }

    public void createBindings(){
            driver1.getGamepadButton(GamepadKeys.Button.B).whenHeld(new SequentialCommandGroup(
                halfExtendCommand,
                    new ConditionalCommand(fullExtendCommand,null,( () -> !this.robot.ll.getColorData().isEmpty())).andThen(
                            intakeActivationCommand
                    )
            )).whenReleased(
                    retractionCommand
            );

    }


}
