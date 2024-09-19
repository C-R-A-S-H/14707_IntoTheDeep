package org.firstinspires.ftc.teamcode.Robot;

import com.arcrobotics.ftclib.controller.PIDController;

public class Config {
    //this is where all pid controllers and values get initialized
    public static final double LENGTH = 16;
    public static final double WIDTH = 14.358;
    public static PIDController HorizontalController = new PIDController(0.001, 0, 0);
    public static PIDController VerticalController = new PIDController(0.001, 0, 0);





}
