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
        robot = new Robot(hardwareMap, true);
        Pose2d startPose = new Pose2d(in(91), in(160), rad(90));
        drive.setPoseEstimate(startPose);

        TrajectorySequence trajectory = drive.trajectorySequenceBuilder(startPose)
                .addTemporalMarker(1, () -> {
                    robot.highPreset(true);
                    robot.update();
                })
                .back(50)
                .setReversed(true)
                .splineTo(new Vector2d(31,5), 179.6)
                .addTemporalMarker(3, () -> {
                    robot.claw.toggle();
                    robot.groundPreset(true);
                    robot.update();
                })
                .setReversed(false)
                .splineTo(new Vector2d(58,10), 0)
                .setReversed(true)
                .addTemporalMarker(5, () -> {
                    robot.highPreset(true);
                    robot.update();
                    robot.claw.toggle();
                })
                .splineTo(new Vector2d(31,5), 179.6)
                .addTemporalMarker(7, () -> {
                    robot.claw.toggle();
                    robot.groundPreset(true);
                    robot.update();
                })
                .setReversed(false)
                .splineTo(new Vector2d(58,10), 0)
                .setReversed(true)
                .addTemporalMarker(10, () -> {
                    robot.highPreset(true);
                    robot.update();
                    robot.claw.toggle();
                })
                .splineTo(new Vector2d(31,5), 179.6)
                .addTemporalMarker(14, () -> {
                    robot.claw.toggle();
                    robot.groundPreset(true);
                    robot.update();
                })
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

