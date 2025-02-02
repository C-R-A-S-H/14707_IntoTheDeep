package org.firstinspires.ftc.teamcode.Robot.Commands.AutoCommands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Pedrio.Vision.LimeLightHelper;
import org.firstinspires.ftc.teamcode.Robot.Subsystems.Drivetrain;

public class LimeLightLineup extends CommandBase {

    private LimeLightHelper ll;
    private Drivetrain dt;
    private double power;
    public LimeLightLineup(LimeLightHelper ll, Drivetrain dt){
        this.ll = ll;
        this.dt = dt;
        this.dt.follower.startTeleopDrive();
    }


    @Override
    public void execute() {
        power = this.ll.lookAtSample();

        this.dt.follower.setTeleOpMovementVectors(0,0,-power);
        super.execute();

    }

    @Override
    public void end(boolean interrupted) {
        this.dt.follower.setTeleOpMovementVectors(0,0,0);
        super.end(interrupted);
    }

    @Override
    public boolean isFinished() {
        return this.ll.LookingAtSample(power);
    }
}
