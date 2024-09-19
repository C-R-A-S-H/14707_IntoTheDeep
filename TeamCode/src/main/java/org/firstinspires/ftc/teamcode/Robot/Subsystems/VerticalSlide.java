package org.firstinspires.ftc.teamcode.Robot.Subsystems;

import org.firstinspires.ftc.teamcode.Robot.Config;
import org.firstinspires.ftc.teamcode.Robot.Hardware;

public class VerticalSlide {
    private final Hardware robot = Hardware.getInstance();

    public void SetPower(double WantedPower) {
        robot.VsSlide.set(WantedPower);
        robot.VsSlide.getCurrentPosition();
    }
    public void setPosValue(double WantedPos) {
        double Value = Config.VerticalController.calculate(WantedPos, robot.VsSlide.getCurrentPosition());
        robot.VsSlide.set(Value);
    }
}