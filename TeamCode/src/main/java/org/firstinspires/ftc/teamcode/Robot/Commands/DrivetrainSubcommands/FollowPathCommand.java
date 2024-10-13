package org.firstinspires.ftc.teamcode.Robot.Commands.DrivetrainSubcommands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Robot.Subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.BezierCurve;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.Path;

public class FollowPathCommand extends CommandBase {
    Path path = null;
    BezierCurve bezierCurve = null;
    Drivetrain dt;
    private boolean UsingPath = false;

    public FollowPathCommand(Path path, Drivetrain dt){
        this.path = path;
        this.dt = dt;
        UsingPath = true;
        addRequirements(this.dt);
    }

    public FollowPathCommand(BezierCurve bezierCurve, Drivetrain dt){
        this.bezierCurve = bezierCurve;
        this.dt = dt;
        addRequirements(this.dt);
    }

    @Override
    public void initialize() {
        if(UsingPath){
            this.dt.follower.followPath(path);
        }else{
            this.dt.follower.followPath(new Path(this.bezierCurve));
        }
    }

    @Override
    public void execute() {
        super.execute();
    }


    @Override
    public boolean isFinished() {
        return this.dt.follower.atParametricEnd();
    }
}
