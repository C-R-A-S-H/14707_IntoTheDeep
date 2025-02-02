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


    public AutoIntakeCommand(Intake intake, LimeLightHelper ll, NormalizedColorSensor transferColorSensor){
        this.intake = intake;
        this.ll = ll;
        this.transferColorSensor = transferColorSensor;


    }
    @Override
    public void initialize() {
        addCommands(
                new FullExtendCommand(this.intake, Config.FullyExtendedSlideEncPos),
                new IntakeActivationCommand(this.intake,this.transferColorSensor)
        );
    }

    @Override
    public void execute() {
        super.execute();
    }

    @Override
    public void end(boolean interrupted) {
        addCommands(new RetractionCommand(this.intake));

    }


    @Override
    public boolean isFinished() {
        return true;
    }
}
