package org.firstinspires.ftc.teamcode.Pedrio.Vision;


import com.arcrobotics.ftclib.controller.PIDController;
import com.arcrobotics.ftclib.geometry.Pose2d;
import com.arcrobotics.ftclib.geometry.Rotation2d;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.hardware.limelightvision.Limelight3A;

import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
import org.firstinspires.ftc.teamcode.Pedrio.PedrioSubsystem;
import org.firstinspires.ftc.teamcode.Robot.Config;
import org.firstinspires.ftc.teamcode.Robot.Hardware;

import java.util.ArrayList;
import java.util.List;


public class LimeLightHelper extends PedrioSubsystem {
    private final Hardware robot = Hardware.getInstance();
    private double heading;

    private PIDController LignUpPid = new PIDController(0.015,0,0);


    public void PipelineSwitch(int index) {
        this.robot.limelight3A.pipelineSwitch(index);
    }
    public Pose3D GetPosition(){
        return this.robot.limelight3A.getLatestResult().getBotpose();
    }

    public Limelight3A getLL(){
        return this.robot.limelight3A;
    }
    public List<List<Double>> getColorData(){
        LLResult data = this.robot.limelight3A.getLatestResult();
        List<List<Double>> colorData = new ArrayList<>();
        List<LLResultTypes.ColorResult> colorResults = data.getColorResults();
        if(!colorResults.isEmpty()) {
            for (LLResultTypes.ColorResult cr : colorResults) {
                List<Double> SigmaList = new ArrayList<>();
                SigmaList.add(cr.getTargetXDegrees());
                SigmaList.add(cr.getTargetYDegrees());
                SigmaList.add(cr.getTargetArea());
                colorData.add(SigmaList);
            }
        }
        return  colorData;
    }

    public double lookAtSample() {
        double power = 0;
        LLResult result = this.robot.limelight3A.getLatestResult();
        if (result != null) {
            if (result.isValid()) {
                List<LLResultTypes.ColorResult> colorResults = result.getColorResults();
                power = LignUpPid.calculate(0, colorResults.get(0).getTargetXDegrees());

            }

        }
        return power;
    }

    public boolean SampleInSight(){
        LLResult result = this.robot.limelight3A.getLatestResult();
        if (result != null) {
            if (result.isValid()) {
                return true;


            }

        }
        return false;
    }

    public boolean LookingAtSample(double powerVal){
        LLResult result = this.robot.limelight3A.getLatestResult();
        if (result != null) {
            if (result.isValid()) {
                List<LLResultTypes.ColorResult> colorResults = result.getColorResults();
                return tolerance(colorResults.get(0).getTargetXDegrees(), -0.1,0.1);


            }

        }
        return false;
    }
    private boolean tolerance(double value,double min,double max){
        return value >= min && value <= max;
    }
    public double getDistanceFromSample(List<Double> sample){
        double offset = sample.get(1);

        double angleToGoalDegrees = Config.LLAngle + offset;
        double angleToGoalRadians = angleToGoalDegrees * (3.14159 / 180.0);

        double distanceFromLimelightToGoalInches = (Config.SamepleHeight - Config.LLHeight) / Math.tan(angleToGoalRadians);

        return distanceFromLimelightToGoalInches;

    }

    public boolean checkForValidTags(){
        return this.robot.limelight3A.getLatestResult().getBotposeTagCount() >= 1;
    }

    public void updateHeading(double heading){
        this.robot.limelight3A.updateRobotOrientation(heading);
        this.heading = heading;
    }

    public Pose2d getPose(){
        Pose3D pose = this.robot.limelight3A.getLatestResult().getBotpose_MT2();

        return new Pose2d(
                pose.getPosition().x,
                pose.getPosition().y,
                new Rotation2d(this.heading)
        );
    }

    @Override
    public void init() {
        this.robot.limelight3A.start();
        this.robot.limelight3A.pipelineSwitch(0);
    }

    @Override
    public void periodic() {

    }


}
