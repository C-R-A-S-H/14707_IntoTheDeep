package org.firstinspires.ftc.teamcode.JICE.Subsytems;

import com.arcrobotics.ftclib.controller.PIDController;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDCoefficients;

import org.firstinspires.ftc.teamcode.Pedrio.PedrioSubsystem;

public class Pivot extends PedrioSubsystem {
    private MotorEx pivot;

    private PIDController pid = new PIDController(0.0001,0,0);

    public Pivot(HardwareMap hmap){
        pivot = new MotorEx(hmap,"pivot");
    }

    public void goToPosition(double position){
        this.pivot.set(pid.calculate(position,this.pivot.getCurrentPosition()));
    }
    public void rawPower(double power){
        this.pivot.set(power);
    }


    public double getEncoder(){
        return this.pivot.getCurrentPosition();
    }
    @Override
    public void init() {

    }

    @Override
    public void periodic() {

    }
}
