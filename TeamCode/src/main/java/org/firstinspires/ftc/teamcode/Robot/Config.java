package org.firstinspires.ftc.teamcode.Robot;

import com.arcrobotics.ftclib.controller.PIDController;

@com.acmerobotics.dashboard.config.Config
public class Config {
    //this is where all pid controllers and values get initialized
    public static final double LENGTH = 16;
    public static final double WIDTH = 14.358;
    public static PIDController HorizontalController = new PIDController(0.001, 0, 0);
    public static PIDController VerticalController = new PIDController(0.001, 0, 0);
    //public static PIDController DropDownController = new PIDController(0.001, 0, 0);
    public static double DropDownPose = -1;
    public static double IntakeUpPose = 0;

    public static double SlideFF = 0.1;

    public static double FullyExtendedSlideEncPos = 200;
    public static double HalfExtendedSlideEncPos = 100;
    public static double RetractedSlideEncPos = 0;

    public static double ExtensionCount = 0;

    public static double LLHeight = 3;
    public static double LLAngle = 0;

    public static double SamepleHeight = 1.5;

    public static double HorizontalSlideTicksToInches = 10.3;

    public static double BeamBroken = 1;
    public static double BeamNotBroken = 0;



}
