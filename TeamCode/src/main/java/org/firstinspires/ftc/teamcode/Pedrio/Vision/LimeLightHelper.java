package org.firstinspires.ftc.teamcode.Pedrio.Vision;


import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;

import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
import org.firstinspires.ftc.teamcode.Pedrio.PedrioSubsystem;


public class LimeLightHelper extends PedrioSubsystem {
    private Limelight3A ll;
    public LimeLightHelper(Limelight3A ll) {
        this.ll = ll;
    }

    public void PipelineSwitch(int index) {
        this.ll.pipelineSwitch(index);



    }
    public Pose3D GetPosition(){
        return this.ll.getLatestResult().getBotpose();
    }

    public LLResult getData(){
        return this.ll.getLatestResult();
    }

    @Override
    public void init() {
        this.ll.start();
    }

    @Override
    public void periodic() {

    }


}
