package org.firstinspires.ftc.teamcode.Robot.Commands.DrivetrainSubcommands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Robot.Subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.PathChain;


public class FollowPathChainCommand extends CommandBase {
    private Drivetrain dt;
    private PathChain path;
    private boolean holdingEnd;
    public FollowPathChainCommand(Drivetrain dt, PathChain path, boolean holdEnd){
        this.dt = dt;
        this.path = path;

        this.holdingEnd = holdEnd;

    }

    @Override
    public void initialize() {
        this.dt.follower.followPath(this.path,this.holdingEnd);
    }


    @Override
    public boolean isFinished() {
        return this.path.getPath(this.path.size() - 1).isAtParametricEnd();
    }
}
