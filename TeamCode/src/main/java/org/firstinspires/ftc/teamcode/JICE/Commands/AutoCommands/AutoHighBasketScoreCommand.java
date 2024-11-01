package org.firstinspires.ftc.teamcode.JICE.Commands.AutoCommands;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.ParallelCommandGroup;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.JICE.Commands.BaseCommands.ArmToPosistionCommand;
import org.firstinspires.ftc.teamcode.JICE.Commands.BaseCommands.ExtensionToPositionCommand;
import org.firstinspires.ftc.teamcode.JICE.JiceConfig;
import org.firstinspires.ftc.teamcode.JICE.Subsytems.Intake;
import org.firstinspires.ftc.teamcode.JICE.Subsytems.Pivot;

public class AutoHighBasketScoreCommand extends SequentialCommandGroup {
    private Intake intake;
    private Pivot pivot;

    public AutoHighBasketScoreCommand(Intake intake, Pivot pivot){
        this.intake = intake;
        this.pivot = pivot;
        addRequirements(this.intake, this.pivot);
    }
    @Override
    public void initialize() {
        addCommands( new ParallelCommandGroup(new  ArmToPosistionCommand(this.pivot, JiceConfig.PivotHighBasketSetpoint),
            new ExtensionToPositionCommand(this.intake, JiceConfig.ExtensionHighBasketSetpoint))
        , new InstantCommand(this.intake::intakeTogglePoop)
                , new WaitCommand(300)
        );

    }

    @Override
    public void execute() {
        super.execute();
    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);
        this.intake.intakeToggleOn();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
