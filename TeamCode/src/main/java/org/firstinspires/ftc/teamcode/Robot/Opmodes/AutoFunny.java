package org.firstinspires.ftc.teamcode.Robot.Opmodes;


import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "park")
public class AutoFunny extends OpMode {
    private MotorEx frontLeft;
    private MotorEx frontRight;
    private MotorEx backLeft;
    private MotorEx backRight;

    private MecanumDrive drive;

    private boolean timerStart = false;

    private ElapsedTime time = new ElapsedTime();
    @Override
    public void init() {
        this.frontLeft = new MotorEx(hardwareMap,"FrontLeft");
        this.frontRight = new MotorEx(hardwareMap,"FrontRight");
        this.backLeft = new MotorEx(hardwareMap,"BackLeft");
        this.backRight = new MotorEx(hardwareMap,"BackRight");

        drive = new MecanumDrive(this.frontLeft, this.frontRight, this.backLeft, this.backRight);

        time.reset();
    }

    @Override
    public void loop() {
        if(!timerStart){
            timerStart = true;
            time.reset();
        }

       if(time.time() < 2){
           drive.driveRobotCentric(-0.5,0,0);
       }else {
           drive.driveRobotCentric(0, 0, 0);
       }
    }
}
