package org.firstinspires.ftc.teamcode.Robot.Subsystems;

import org.firstinspires.ftc.teamcode.Pedrio.PedrioSubsystem;
import org.firstinspires.ftc.teamcode.Robot.Hardware;

public class BeamBreak extends PedrioSubsystem {

    public boolean isBroken1(){
        if(Hardware.getInstance().BeamBreak1.getVoltage() < 10){
            return true;
        }else{
            return false;
        }
    }

    public boolean isBroken2(){
        if(Hardware.getInstance().BeamBreak2.getVoltage() < 10){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void init() {

    }

    @Override
    public void periodic() {

    }
}
