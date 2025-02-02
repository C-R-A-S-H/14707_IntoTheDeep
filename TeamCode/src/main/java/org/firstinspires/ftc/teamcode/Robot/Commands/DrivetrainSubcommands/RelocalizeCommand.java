package org.firstinspires.ftc.teamcode.Robot.Commands.DrivetrainSubcommands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.geometry.Pose2d;

import org.firstinspires.ftc.teamcode.Pedrio.Vision.LimeLightHelper;
import org.firstinspires.ftc.teamcode.Robot.Config;
import org.firstinspires.ftc.teamcode.Robot.Subsystems.Drivetrain;

public class RelocalizeCommand extends CommandBase {
    private Drivetrain dt;
    private LimeLightHelper ll;

    public RelocalizeCommand(Drivetrain dt, LimeLightHelper ll){
        this.dt = dt;
        this.ll = ll;
        addRequirements(this.dt);
    }
    @Override
    public void initialize() {
        //this.ll.updateHeading(this.dt.getRawIMUHeadingDegrees());
//        if(this.ll.checkForValidTags()) {
//            Pose2d pose = this.ll.getPose();
//            Config.llPose = pose;
//        }
    }

    @Override
    public void execute() {
        super.execute();
    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
