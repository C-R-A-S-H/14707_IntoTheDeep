package org.firstinspires.ftc.teamcode.Robot.Commands.IntakeSubCommands;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;

import org.firstinspires.ftc.teamcode.Pedrio.Sensors.MagLimitSwitch;
import org.firstinspires.ftc.teamcode.Robot.Subsystems.Intake;

public class TeleopIntakeCommand extends SequentialCommandGroup {
    private Intake intake;
    private NormalizedColorSensor colorSensor;
    private MagLimitSwitch magLimitSwitch;

    public TeleopIntakeCommand(Intake intake, NormalizedColorSensor colorSensor, MagLimitSwitch magLimitSwitch){
        this.intake = intake;
        this.colorSensor = colorSensor;
        this.magLimitSwitch = magLimitSwitch;
        addRequirements(this.intake);
    }
    @Override
    public void initialize() {
        addCommands(
                new TeleopExtendoCommand(this.intake),
                new IntakeActivationCommand(this.intake,this.colorSensor)
        );
    }

    @Override
    public void execute() {
        super.execute();
    }

    @Override
    public void end(boolean interrupted) {
        addCommands(
                new RetractionCommand(this.intake, this.magLimitSwitch)
        );
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
