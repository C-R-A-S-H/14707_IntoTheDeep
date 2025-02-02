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
import org.firstinspires.ftc.teamcode.Robot.Config;
import org.firstinspires.ftc.teamcode.Robot.Hardware;
import org.firstinspires.ftc.teamcode.pedroPathing.localization.Pose;

@TeleOp(name ="No Auto Teleop")
public class NonAutoTeleop extends OpMode {
    private Hardware robot = Hardware.getInstance();

    private GamepadEx driver2;
    private GamepadEx driver1;

    double turnPower;
    boolean llOn = false;

    private boolean ScoreSpeciments = false;

    @Override
    public void init() {
        Config.isAuto = false;
        this.robot.Init(hardwareMap);

        CommandScheduler.getInstance().reset();

        this.driver1 = new GamepadEx(gamepad1);
        this.driver2 = new GamepadEx(gamepad2);
        //this.robot.intake.IntakeUp();

        this.robot.drivetrain.follower.setStartingPose(new Pose(136.5981308411215,33.420560747663544,0));
        this.robot.drivetrain.follower.startTeleopDrive();


        createBindings();
    }

    @Override
    public void loop() {
        this.robot =  Hardware.getInstance();
        this.robot.Loop();

        if(llOn){
            turnPower = this.robot.ll.lookAtSample();
        }else{
            turnPower = this.driver1.getRightX();
        }

        this.robot.deposit.SetSlidePower(-this.driver2.getRightY());

        this.robot.intake.setSlidePower(this.driver2.getLeftY());

        //this.robot.intake.setServoPower(this.driver2.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER) - this.driver2.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER));

        this.robot.drivetrain.follower.setTeleOpMovementVectors(-gamepad1.left_stick_y, -gamepad1.left_stick_x, -turnPower,true);

        this.robot.drivetrain.follower.update();
        CommandScheduler.getInstance().run();

    }

    public void createBindings(){
        Button stareAtSample = new GamepadButton(driver1, GamepadKeys.Button.A).whenHeld(
               new InstantCommand( ()-> changeLLMode(true))
        ).whenReleased(
                new InstantCommand( ()-> changeLLMode(false))
        );
      //Button class
        Button intake = new GamepadButton(driver2, GamepadKeys.Button.A).toggleWhenPressed(
                new SequentialCommandGroup(
                new InstantCommand(() -> this.robot.intake.DropDown()),
                new InstantCommand( () -> this.robot.intake.SetIntakeVelocity(-2000))
                ),
                new SequentialCommandGroup(
                        new InstantCommand(() -> this.robot.intake.IntakeUp()),
                        new InstantCommand( () -> this.robot.intake.SetIntakeVelocity(0))
                )
        );

        Button highBasket = new GamepadButton(driver2, GamepadKeys.Button.B).toggleWhenPressed(
                new DepositPivotingCommand(this.robot.deposit,0,0,1)
               ,
                new ConditionalCommand(
                        new DepositPivotingCommand(this.robot.deposit, 1,1,0),
                        new DepositPivotingCommand(this.robot.deposit,0.55,0.55,0),
                        () -> ScoreSpeciments
                )

        );

        Button Drop = new GamepadButton(driver2, GamepadKeys.Button.Y).toggleWhenPressed(
                new InstantCommand( () -> this.robot.deposit.ClawControl(0.2)),
                new InstantCommand( () -> this.robot.deposit.ClawControl(.3))

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
private void changeLLMode(boolean val){
        this.llOn = val;
}

}
