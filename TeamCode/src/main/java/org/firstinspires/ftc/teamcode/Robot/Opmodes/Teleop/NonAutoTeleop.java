package org.firstinspires.ftc.teamcode.Robot.Opmodes.Teleop;

import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.ConditionalCommand;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.button.Button;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Robot.Commands.DepositSubCommands.DepositPivotingCommand;
import org.firstinspires.ftc.teamcode.Robot.Hardware;

@TeleOp(name ="No Auto Teleop")
public class NonAutoTeleop extends OpMode {
    private Hardware robot = Hardware.getInstance();

    private GamepadEx driver2;
    private GamepadEx driver1;

    private MotorEx frontLeft;
    private MotorEx frontRight;
    private MotorEx backLeft;
    private MotorEx backRight;

    private MecanumDrive drive;

    private boolean ScoreSpeciments = false;

    @Override
    public void init() {
        this.robot.Init(hardwareMap);

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

        this.robot.deposit.SetSlidePower(-this.driver2.getRightY());

        this.robot.intake.setSlidePower(this.driver2.getLeftY());

        this.robot.drivetrain.follower.setTeleOpMovementVectors(-gamepad1.left_stick_y, -gamepad1.left_stick_x, -gamepad1.right_stick_x);

        this.robot.drivetrain.follower.update();
        CommandScheduler.getInstance().run();

    }

    public void createBindings(){
      //Button class
        Button intake = new GamepadButton(driver2, GamepadKeys.Button.A).toggleWhenPressed(
                new SequentialCommandGroup(
                new InstantCommand(() -> this.robot.intake.DropDown()),
                new InstantCommand( () -> this.robot.intake.SetIntakeVelocity(-2000))
                ),
                new SequentialCommandGroup(
                        new InstantCommand(() -> this.robot.intake.IntakeUp()),
                        new InstantCommand( () -> this.robot.intake.SetIntakeVelocity(-2000))
                )
        );

        Button highBasket = new GamepadButton(driver2, GamepadKeys.Button.B).toggleWhenPressed(
                new DepositPivotingCommand(this.robot.deposit,0.05,0.05,1)
               ,
                new ConditionalCommand(
                        new DepositPivotingCommand(this.robot.deposit, 1,1,0),
                        new DepositPivotingCommand(this.robot.deposit,0.5,0.5,0),
                        () -> ScoreSpeciments
                )

        );

        Button Drop = new GamepadButton(driver2, GamepadKeys.Button.Y).toggleWhenPressed(
                new InstantCommand( () -> this.robot.deposit.ClawControl(0)),
                new InstantCommand( () -> this.robot.deposit.ClawControl(.37))

        );

        Button reverse = new GamepadButton(driver2, GamepadKeys.Button.X).toggleWhenPressed(
                new InstantCommand( () -> this.robot.intake.SetIntakeVelocity(-2000)),
                new InstantCommand( ()-> this.robot.intake.SetIntakeVelocity(2000))
        );
        Button turnoff = new GamepadButton(driver1, GamepadKeys.Button.A).whenPressed(
                new InstantCommand( () -> this.robot.intake.setIntakePower(0))
        );

        Button ScoreModeSwitch = new GamepadButton(driver2, GamepadKeys.Button.LEFT_BUMPER).toggleWhenPressed(
                new InstantCommand( ( ) -> changeScoreMode(true)),
                new InstantCommand(() -> changeScoreMode(false))
        );


                //new FullExtendCommand(this.robot.intake)


        
    }

private void changeScoreMode(boolean mode){
        this.ScoreSpeciments = mode;
}

}
