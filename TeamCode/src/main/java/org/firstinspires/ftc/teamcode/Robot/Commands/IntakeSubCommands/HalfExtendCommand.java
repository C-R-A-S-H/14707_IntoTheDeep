package org.firstinspires.ftc.teamcode.Robot.Commands.IntakeSubCommands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Pedrio.Vision.LimeLightHelper;
import org.firstinspires.ftc.teamcode.Robot.Subsystems.Intake;

public class HalfExtendCommand extends CommandBase {
    private Intake intake;
    private LimeLightHelper ll;
    private double ea;
    public HalfExtendCommand(Intake intake, LimeLightHelper ll, double ExtensionAmount){
        this.intake = intake;
        this.ll = ll;
        this.ea = ExtensionAmount;
        addRequirements(this.intake,this.ll);
    }
    @Override
    public void initialize() {
        this.intake.SetPower(0);
    }

    @Override
    public void execute() {
        this.intake.SetSlidePos(this.ea);
        this.intake.IntakeUp();
    }

    @Override
    public boolean isFinished() {
        return super.isFinished();
    }
}
