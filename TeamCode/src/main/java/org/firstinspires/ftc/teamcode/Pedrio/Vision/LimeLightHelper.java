package org.firstinspires.ftc.teamcode.Pedrio.Vision;


import com.qualcomm.hardware.limelightvision.LLResultTypes;

import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
import org.firstinspires.ftc.teamcode.Pedrio.PedrioSubsystem;
import org.firstinspires.ftc.teamcode.Robot.Hardware;

import java.util.Collection;
import java.util.List;


public class LimeLightHelper extends PedrioSubsystem {
    private final Hardware robot = Hardware.getInstance();




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
        robot.limelight3A.start();
    }

    @Override
    public void periodic() {

    }


}
