package org.firstinspires.ftc.teamcode.Robot.Commands.DrivetrainSubcommands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Robot.Subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.Path;

public class SillyFollowPathCommand extends CommandBase {
    private Drivetrain dt;
    private Path path;
    private boolean reversed;
    public SillyFollowPathCommand(Drivetrain dt, Path path, boolean reversed) {
        this.dt = dt;
        this.path = path;
        this.reversed = reversed;
    }

    @Override
    public void initialize() {
        if(this.reversed){
            this.path.setReversed(true);
            this.dt.follower.followPath(this.path,true);
        }else {
            this.dt.follower.followPath(this.path,true);
        }
    }


    @Override
    public boolean isFinished() {
        return this.path.isAtParametricEnd();
    }
}
