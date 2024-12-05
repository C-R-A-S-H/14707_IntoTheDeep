
package org.firstinspires.ftc.teamcode.Pedrio.DiffySwerve;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.hardware.bosch.BHI260IMU;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

@Config
public class Hardware {

    private static Hardware instance;


    public MotorEx leftModuleMotorA;
    public  MotorEx leftModuleMotorB;

    public MotorEx rightModuleMotorA;
    public MotorEx rightModuleMotorB;

    public IMU imu;

    public static Hardware getInstance(){
        if(instance == null){
            instance = new Hardware();
        }
        return instance;
    }

    public void init(HardwareMap hardwareMap){
        //initalize motors
        leftModuleMotorA = new MotorEx(hardwareMap,"leftModuleMotorA");
        leftModuleMotorB = new MotorEx(hardwareMap, "leftModuleMotorB");

        rightModuleMotorA = new MotorEx(hardwareMap, "rightModuleMotorA");
        rightModuleMotorB = new MotorEx(hardwareMap, "rightModuleMotorB");

        imu = hardwareMap.get(IMU.class, "imu");

        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.BACKWARD));
        // Without this, the REV Hub's orientation is assumed to be logo up / USB forward
        imu.initialize(parameters);


    }
    public double getHeading(){
        return imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES);
    }
}
