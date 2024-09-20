package org.firstinspires.ftc.teamcode.Robot.Commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Robot.Subsystems.Intake;

public class IntakeCommand extends CommandBase {
    private Intake subsystem;
    private Intake DropDown;
    private double power;
    public IntakeCommand(Intake subsystem, double power){
        this.subsystem = subsystem;
        this.power = power;
        addRequirements(this.subsystem);
    }
    public void DropDownCommand(Intake subsystem, double power){
        this.DropDown = subsystem;
        this.power = power;
        addRequirements(this.DropDown);
    }
}
