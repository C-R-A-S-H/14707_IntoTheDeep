package org.firstinspires.ftc.teamcode.Robot.Subsystems;

import org.firstinspires.ftc.teamcode.Pedrio.PedrioSubsystem;
import org.firstinspires.ftc.teamcode.Robot.Config;
import org.firstinspires.ftc.teamcode.Robot.Hardware;

public class VerticalSlide extends PedrioSubsystem {
    private final Hardware robot = Hardware.getInstance();

    public void SetPower(double WantedPower) {
        robot.AVSlide.set(WantedPower);
        robot.BVSlide.set(WantedPower);
    }

    public void setPosValue(double WantedPos) {
        double PositionAverage = (double) (robot.AVSlide.getCurrentPosition() + robot.BVSlide.getCurrentPosition()) /2;
        double Value = Config.VerticalController.calculate(WantedPos, PositionAverage);

        SetPower(Value);
    }

    @Override
    public void init() {

    }

    @Override
    public void periodic() {

    }
}