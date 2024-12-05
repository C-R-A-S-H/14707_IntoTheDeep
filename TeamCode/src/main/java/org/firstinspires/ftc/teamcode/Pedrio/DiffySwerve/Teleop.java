
package org.firstinspires.ftc.teamcode.Pedrio.DiffySwerve;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;




@TeleOp(name = "Diffy-Swerve-Test")
public class Teleop extends OpMode {

    private Drivetrain swerve = new Drivetrain();

    private Hardware robot = Hardware.getInstance();

    @Override
    public void init() {
        this.robot.init(hardwareMap);
        this.swerve.init();
    }

    @Override
    public void loop() {
        this.robot =  Hardware.getInstance();

        telemetry.addData("swerve",swerve.drive(
                gamepad1.left_stick_x,
                gamepad1.left_stick_y,
                gamepad1.right_stick_x
        ));
        if(gamepad1.a){
            this.robot.imu.resetYaw();
        }

        telemetry.addData("LeftModuleMotor A ticks = ",this.robot.leftModuleMotorA.getCurrentPosition());
        telemetry.addData("LeftModuleMotor B ticks = ",this.robot.leftModuleMotorB.getCurrentPosition());

        telemetry.addData("leftModule Heading", this.swerve.leftModule.getModuleHeading());
        telemetry.addData("leftModule Distance", this.swerve.leftModule.getModuleWheelDistance());

    }
}
