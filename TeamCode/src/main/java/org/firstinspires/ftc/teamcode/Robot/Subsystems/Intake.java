package org.firstinspires.ftc.teamcode.Robot.Subsystems;

import org.firstinspires.ftc.teamcode.Pedrio.PedrioSubsystem;
import org.firstinspires.ftc.teamcode.Robot.Config;
import org.firstinspires.ftc.teamcode.Robot.Hardware;
import org.firstinspires.ftc.teamcode.Pedrio.BrainRot.PIDControllers;

public class Intake extends PedrioSubsystem {
    private final Hardware robot = Hardware.getInstance();


    public void SetPower(double WantedPower) {
        robot.Intake1.setPower(WantedPower);
        robot.Intake2.setPower(WantedPower);
    }

    public void DropDown(double WantedPos) {
        double Value = Config.DropDownController.calculate(WantedPos, robot.DropDown.getPosition());
        robot.DropDown.setPosition(Value);
    }

    @Override
    public void init() {

    }

    @Override
    public void periodic() {

    }
}
