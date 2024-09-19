package org.firstinspires.ftc.teamcode.Robot.Subsystems;

import org.firstinspires.ftc.teamcode.Pedrio.PedrioSubsystem;
import org.firstinspires.ftc.teamcode.Robot.Config;
import org.firstinspires.ftc.teamcode.Robot.Hardware;

public class HorizontalSlide extends PedrioSubsystem {
    private final Hardware robot = Hardware.getInstance();

    public void SetPower(double WantedPower) {
        robot.HsSlide.set(WantedPower);
        robot.HsSlide.getCurrentPosition();
    }

    public void setPosValue(double WantedPos) {
        double Value = Config.HorizontalController.calculate(WantedPos, robot.HsSlide.getCurrentPosition());
        robot.HsSlide.set(Value);
    }

    @Override
    public void init() {

    }

    @Override
    public void periodic() {

    }
}