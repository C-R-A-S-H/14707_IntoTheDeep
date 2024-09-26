package org.firstinspires.ftc.teamcode.Robot.Subsystems;

import org.firstinspires.ftc.teamcode.Pedrio.PedrioSubsystem;
import org.firstinspires.ftc.teamcode.Robot.Hardware;

public class BeamBreak extends PedrioSubsystem {

    public boolean isBroken(){
        if(Hardware.getInstance().BeamBreak.getVoltage() < 10){
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
