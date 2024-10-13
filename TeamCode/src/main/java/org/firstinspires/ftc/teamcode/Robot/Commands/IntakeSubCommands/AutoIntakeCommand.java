package org.firstinspires.ftc.teamcode.Robot.Commands.IntakeSubCommands;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;

import org.firstinspires.ftc.teamcode.Pedrio.Sensors.MagLimitSwitch;
import org.firstinspires.ftc.teamcode.Pedrio.Vision.LimeLightHelper;
import org.firstinspires.ftc.teamcode.Robot.Config;
import org.firstinspires.ftc.teamcode.Robot.Subsystems.Intake;

public class AutoIntakeCommand extends SequentialCommandGroup {
    Intake intake;
    LimeLightHelper ll;
    NormalizedColorSensor transferColorSensor;
    MagLimitSwitch magLimitSwitch;

    public AutoIntakeCommand(Intake intake, LimeLightHelper ll, NormalizedColorSensor transferColorSensor, MagLimitSwitch magLimitSwitch){
        this.intake = intake;
        this.ll = ll;
        this.transferColorSensor = transferColorSensor;
        this.magLimitSwitch = magLimitSwitch;

        addRequirements(this.intake,this.ll);

    }
    @Override
    public void initialize() {
        addCommands(
                new HalfExtendCommand(this.intake),
                new FullExtendCommand(this.intake,this.ll),
                new IntakeActivationCommand(this.intake,this.transferColorSensor)
        );
    }

    @Override
    public void execute() {
        super.execute();
    }

    @Override
    public void end(boolean interrupted) {
        addCommands(new RetractionCommand(this.intake, this.magLimitSwitch));

    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
