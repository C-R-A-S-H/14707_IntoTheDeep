package org.firstinspires.ftc.teamcode.Robot.Subsystems;

import org.firstinspires.ftc.teamcode.Robot.Hardware;

public class HorizontalSlide {
    private final Hardware robot = Hardware.getInstance();

    public double SetPower(double WantedPower) {

        WantedPower = 0.5;

        return  WantedPower;
    }
}