/*package org.firstinspires.ftc.teamcode.Pedrio.PointToPoint;

import static java.lang.Thread.sleep;


import com.qualcomm.hardware.sparkfun.SparkFunOTOS;

import org.firstinspires.ftc.teamcode.Robot.Subsystems.Drivetrain;

import java.util.List;

public class Trajectory {
    private final ZLPIDFController xController = new ZLPIDFController(0.001,0,0,0);
    private final ZLPIDFController yController = new ZLPIDFController(0.001,0,0,0);
    private final ZLPIDFController turnController = new ZLPIDFController(0.001,0,0,0);

    double xPose;
    double yPose;
    double heading;

    private List<Point> trajectory;

    public Trajectory(List<Point> trajectory, double xPose,double yPose,double heading){
        this.trajectory = trajectory;


    }

    public Point getPoint(int currentIndex){
        return this.trajectory.get(currentIndex);
    }



    public void follow() throws InterruptedException {
        double x;
        double y;
        double turn;
        for(int i = 0; i <= this.trajectory.size(); i++){
            while(true) {
                x = xController.calculate(otos.getPosition().x , trajectory.get(i).getPose().getX());
                y = yController.calculate(otos.getPosition().y, trajectory.get(i).getPose().getY());
                turn = turnController.calculate(otos.getPosition().h, trajectory.get(i).getPose().getHeading());

                drivetrain.driveFieldCentric(x, y, turn, drivetrain.getRawIMUHeadingDegrees());

                double val = averageError(x,y,turn);
                if(tolerance(val,val - 0.5, val + 0.5)){
                    break;
                }
            }
        }

    }
    private boolean tolerance(double value,double min,double max){
        return value >= min && value <= max;
    }
    private double averageError(double x, double y, double turn){
        return (x + y + turn) / 3;
    }

}


 */
