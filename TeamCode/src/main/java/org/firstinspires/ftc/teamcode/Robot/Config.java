package org.firstinspires.ftc.teamcode.Robot;

import com.arcrobotics.ftclib.controller.PIDController;
import com.arcrobotics.ftclib.geometry.Pose2d;
import com.qualcomm.hardware.limelightvision.Limelight3A;

import org.firstinspires.ftc.teamcode.Robot.Subsystems.Enums.ScoringModes;
import org.firstinspires.ftc.teamcode.Robot.Subsystems.Enums.WantedColor;

import java.sql.Array;
import java.util.ArrayList;

//@com.acmerobotics.dashboard.config.Config
public class Config {
    //this is where all pid controllers and values get initialized

    //robot values
    public static final double LENGTH = 16;
    public static final double WIDTH = 14.358;
    public static double LLHeight = 3;
    public static double LLAngle = 0;
    public static double SamepleHeight = 1.5;
    public static double HorizontalSlideTicksToInches = 10.3;

    //pid controllers and ff
    public static PIDController HorizontalController = new PIDController(0.3, 0, 0);
    public static PIDController VerticalController = new PIDController(0.001, 0, 0);

    public static double HorizontalSlideFF = -0.1;
    public static double VerticalSlideFF = 0.1;
    //public static PIDController DropDownController = new PIDController(0.001, 0, 0);

    //setpoints
    public static double DropDownPoseLeft = 1;
    public static double DropDownPoseRight = 1;
    public static double IntakeUpPoseLeft = 0;
    public static double IntakeUpPoseRight = 0;

    public static double FullyExtendedSlideEncPos = 900;
    public static double HalfExtendedSlideEncPos = 500;
    public static double RetractedSlideEncPos = -50;

    public static double LeftATransferPose = 0;
    public static double RightATransferPose = 0;
    public static double LeftAScoreBasketPose = 0;
    public static double RightAScoreBasketPose = 0;

    public static double ClawPivotTransferPose = 0;
    public static double ClawPivotScoreBasketPose = 0;

    public static double ClawClosePose = 0;
    public static double ClawOpenPose = 0;

    public static double HighBasketSlideSetpoint = 1200;
    public static double LowBasketSlideSetpoint = 800;

    public static double HighBarSlideSetpoint = 1000;

    public static double FirstStagePrepSetpoint = 900;
    public static double FirstStageHangSetpoint = 0;

    public static double HorizontalSlideTeleopSetpoint = 1000;

    public static double TolerantDistanceFromSample = 2;

    //during match stuff
    public static double ExtensionCount = 0;
    public static double BeamBroken = 1;
    public static double BeamNotBroken = 0;
    public static boolean AutoScoring = false;
    //CHANGE THESE BEFORE A MATCH or make the code change them idk
    public static boolean isAuto = true;
    public static boolean AllianceIsBlue = true;//false for red

    public static ScoringModes scoringMode = ScoringModes.HighBar;
    public static WantedColor wantedColor = WantedColor.BLUE;

    //color sensor values
    public static double[] RedSampleRGB = {100,0,0};
    public static double[] BlueSampleRGB = {0,0,100};
    public static double[] YellowSampleRGB = {0,50,50};

    public static int BlueSampleColor = 0;
    public static int RedSampleColor = 1;
    public static int YellowSampleColor = 2;

    //robot pose
    public static Pose2d llPose;
    public static Pose2d RobotPose;
    public static double robot_heading;




}
