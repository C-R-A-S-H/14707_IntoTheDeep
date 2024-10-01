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

    public static double DropDownPoseLeft = 0.1;
    public static double DropDownPoseRight = -0.1;
    public static double IntakeUpPoseLeft = 0;
    public static double IntakeUpPoseRight = 0;

    public static double HorizontalSlideFF = 0.1;
    public static double VerticalSlideFF = 0.1;

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

    public static boolean AutoScoring = false;

    public static double TolerantDistanceFromSample = 2;

    public static boolean isAuto = true;

    public static int BlueSampleColor = 0;
    public static int RedSampleColor = 1;
    public static int YellowSampleColor = 2;

    public static boolean AllianceIsBlue = true;//false for red




}
