package org.firstinspires.ftc.teamcode.JICE.Subsytems;

import com.arcrobotics.ftclib.controller.PIDController;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Pedrio.PedrioSubsystem;

public class Intake extends PedrioSubsystem {
    private MotorEx extension;
    private CRServo intake;

    private PIDController pid = new PIDController(0.0001,0,0);

    public Intake(HardwareMap hmap){
        this.extension = new MotorEx(hmap,"extension");
        this.intake = hmap.get(CRServo.class, "intake");
    }

    public void goToPosition(double wantedPose){
        this.extension.set(pid.calculate(wantedPose,this.extension.getCurrentPosition()));
    }
    public void setExtensionPower(double power){
        this.extension.set(power);
    }
    public void intakeTogglePoop(){
        this.intake.setPower(1);
    }
    public void intakeToggleOn(){
        this.intake.setPower(-1);
    }

    public double getEncoder(){
        return this.extension.getCurrentPosition();
    }
    @Override
    public void init() {

    }

    @Override
    public void periodic() {

    }
}
