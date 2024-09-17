package org.firstinspires.ftc.teamcode.Pedrio.Vision;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.hardware.limelightvision;

@TeleOp(name = "Limelight", group = "Sensor")
@Disabled
public class LimeLightHelper extends LinearOpMode {
    private LimeLightHelper limelight;

    @Override
    public void runOpMode() throws InterruptedException{

        limelight = hardwareMap.get(LimeLightHelper.class, "limelight")

        telemetry.setMsTransmissionInterval(5);

        limelight.pipelineSwitch(0);

        limelight.start();

        telemetry.addData("||", "Robot Initialized   Press Begin");
        telemetry.update( %.1fC, CPU: %.1f%%, FPS: %d",
        status.getTemp(), status.getCpu(),(int)status.getFps());
        telemetry.addData("Pipeline",);
        waitForStart();

        LLResult result = limelight.getLatestResult();
        if (result != null) {

            double captureLatency = result.getCaptureLatency();
            double targetingLatency = result.getTargetingLatency();
            double parseLatency = result.getParseLatency();

//            List<LLResultTypes.BarcodeResult> barcodeResults = result.getBarcodeResults();
//            for (LLResultTypes.BarcodeResult br : barcodeResults) {
//                telemetry.addData("Barcodes", "Data: %s", br.getData());
//            }

    }


}
