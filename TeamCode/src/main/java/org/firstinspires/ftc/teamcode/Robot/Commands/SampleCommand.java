package org.firstinspires.ftc.teamcode.Robot.Commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.Subsystem;

import org.firstinspires.ftc.teamcode.Robot.Subsystems.NumNum.NumNumDrivetrain;

import java.util.function.DoubleSupplier;

public class SampleCommand extends CommandBase {
    private NumNumDrivetrain Subsystem;
    private DoubleSupplier xval;
    private DoubleSupplier yval;
    private DoubleSupplier zval;

    public SampleCommand(NumNumDrivetrain subsystem, DoubleSupplier xval, DoubleSupplier yval, DoubleSupplier zval){
        this.xval = xval;
        this.yval = yval;
        this.zval = zval;
        this.Subsystem = subsystem;
        addRequirements(this.Subsystem);
    }

    @Override
    public void execute() {
        this.Subsystem.driveFieldCentric(this.xval.getAsDouble(), this.yval.getAsDouble(), this.zval.getAsDouble(), this.Subsystem.getRawIMUHeadingDegrees());
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
