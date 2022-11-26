package org.firstinspires.ftc.teamcode.auton;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.lib.Levels;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

@Config
@Autonomous(group = "drive")

public class BlueRight extends LinearOpMode {

    Robot robot;

    @Override
    public void runOpMode() throws InterruptedException {

        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        Pose2d startPose = new Pose2d(in(91), in(160), rad(-90));

        TrajectorySequence trajectory = drive.trajectorySequenceBuilder(startPose)
                .forward(50)
                .splineTo(new Vector2d(31,5), 179.6)
                .addDisplacementMarker(() -> {
                    robot.highPreset(true);
                    robot.claw.toggle();
                    sleep(500);
                    robot.groundPreset(true);
                })
                .setReversed(true)
                .splineTo(new Vector2d(60,12.5), 0)
                .setReversed(false)
                .splineTo(new Vector2d(31,5), 179.6)
                .setReversed(true)
                .splineTo(new Vector2d(60,12.5), 0)
                .setReversed(false)
                .splineTo(new Vector2d(31,5), 179.6)
                .setReversed(true)
                .splineTo(new Vector2d(60,12.5), 0)
                .setReversed(false)
                .splineTo(new Vector2d(31,5), 179.6)
                .setReversed(true)
                .splineTo(new Vector2d(60,12.5), 0)
                .setReversed(false)
                .splineTo(new Vector2d(31,5), 179.6)
                .setReversed(true)
                .splineTo(new Vector2d(60,12.5), 0)
                .setReversed(false)
                .splineTo(new Vector2d(31,5), 179.6)
                .build();

        waitForStart();

        if (isStopRequested()) return;

        drive.followTrajectorySequence(trajectory);

        while (!isStopRequested() && opModeIsActive()) ;
    }

    public static double rad(double degrees) {
        return Math.toRadians(degrees);
    }

    public static double in(double centimeters) {
        return centimeters * 0.3837008;
    }

}
