package org.firstinspires.ftc.teamcode.Robot.Commands.DrivetrainSubcommands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.teamcode.Robot.Config;
import org.firstinspires.ftc.teamcode.Robot.Subsystems.Drivetrain;

import java.util.function.DoubleSupplier;

public class DriveCommand extends CommandBase  {
        private Drivetrain dt;
        private DoubleSupplier xSupplier;
        private DoubleSupplier ySupplier;
        private DoubleSupplier zSupplier;
        private IMU imu;


    public DriveCommand(Drivetrain dt, DoubleSupplier xSupplier, DoubleSupplier ySupplier, DoubleSupplier zSupplier, IMU imu){
        this.dt = dt;
        this.xSupplier = xSupplier;
        this.ySupplier = ySupplier;
        this.zSupplier = zSupplier;
        this.imu = imu;

        addRequirements(dt);
    }
    @Override
    public void initialize() {
        super.initialize();
    }

    @Override
    public void execute() {

            this.dt.driveFieldCentric(
                    this.xSupplier.getAsDouble()
                    , this.ySupplier.getAsDouble()
                    , this.zSupplier.getAsDouble()
                    , imu.getRobotYawPitchRollAngles().getYaw());

    }


    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);
    }

    @Override
    public boolean isFinished() {
        return super.isFinished();
    }
}
