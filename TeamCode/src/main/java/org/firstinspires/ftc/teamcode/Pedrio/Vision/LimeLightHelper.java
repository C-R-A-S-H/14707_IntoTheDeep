package org.firstinspires.ftc.teamcode.Pedrio.Vision;



import com.arcrobotics.ftclib.controller.PIDController;
import com.arcrobotics.ftclib.geometry.Pose2d;
import com.arcrobotics.ftclib.geometry.Rotation2d;
import com.qualcomm.hardware.limelightvision.LLResult;

import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.hardware.limelightvision.Limelight3A;

import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
import org.firstinspires.ftc.teamcode.Pedrio.PedrioSubsystem;
import org.firstinspires.ftc.teamcode.Robot.Hardware;

import java.util.Collection;
import java.util.List;


public class LimeLightHelper extends PedrioSubsystem {
    private final Hardware robot = Hardware.getInstance();

    private PIDController LignUpPid = new PIDController(0.017,0,0);




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


    public void PipelineSwitch(int index) {
        robot.limelight3A.pipelineSwitch(index);




    }
    public Pose3D GetPosition(){
        return robot.limelight3A.getLatestResult().getBotpose();


    }
    public List<LLResultTypes.FiducialResult> GetIDResults() {
        return robot.limelight3A.getLatestResult().getFiducialResults();
    }

    public Collection<LLResultTypes.BarcodeResult> GetBarResults() {
        return robot.limelight3A.getLatestResult().getBarcodeResults();
    }

    public Collection<LLResultTypes.ClassifierResult> GetClassifierResults(){
        return robot.limelight3A.getLatestResult().getClassifierResults();
    }





    @Override
    public void init() {

        this.robot.limelight3A.start();
        this.robot.limelight3A.pipelineSwitch(0);
        robot.limelight3A.start();

    }

    @Override
    public void periodic() {

    }


}
