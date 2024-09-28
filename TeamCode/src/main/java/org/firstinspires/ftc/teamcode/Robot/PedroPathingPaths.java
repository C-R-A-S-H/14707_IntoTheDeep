package org.firstinspires.ftc.teamcode.Robot;

import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.BezierCurve;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.Path;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.PathBuilder;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.Point;

public class PedroPathingPaths {
    private BezierCurve auto_path = new BezierCurve(
          new Point(16.156, 129.249, Point.CARTESIAN),
          new Point(30.205, 106.771, Point.CARTESIAN),
          new Point(69.541, 126.439, Point.CARTESIAN),
          new Point(69.073, 96.468, Point.CARTESIAN)
        );


    public void BuildPaths(BezierCurve bezierCurve){
        PathBuilder builder = new PathBuilder();

        builder.addPath(bezierCurve);

    }
}
