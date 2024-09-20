package org.firstinspires.ftc.teamcode.Pedrio.Vision;


import android.support.v4.os.IResultReceiver;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;

import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
import org.firstinspires.ftc.teamcode.Pedrio.PedrioSubsystem;
import org.firstinspires.ftc.teamcode.Robot.Hardware;
import org.firstinspires.ftc.teamcode.pedroPathing.localization.Pose;


public class LimeLightHelper extends PedrioSubsystem {
    private final Hardware robot = Hardware.getInstance();




    public void PipelineSwitch(int index) {
        robot.limelight3A.pipelineSwitch(index);



    }
    public Pose3D GetPosition(){
        return robot.limelight3A.getLatestResult().getBotpose();


    }


    @Override
    public void init() {
        robot.limelight3A.start();
    }

    @Override
    public void periodic() {

    }


}
