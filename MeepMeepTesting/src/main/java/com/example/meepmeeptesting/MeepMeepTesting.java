package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);
        Pose2d startPose = new Pose2d(in(91), in(160), rad(-90));
        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                .setDimensions(16.2,14)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(73.173, 73.173, 6.776136290775701, Math.toRadians(314.75385135135133), 11)

                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(startPose)
                                .forward(50)
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
                                .setReversed(true)
                                .splineTo(new Vector2d(60,12.5), 0)
                                .setReversed(false)
                                .splineTo(new Vector2d(31,5), 179.6)
                                .build()
                );

        meepMeep.setBackground(MeepMeep.Background.FIELD_POWERPLAY_OFFICIAL)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
    public static double rad(double degrees) {
        return Math.toRadians(degrees);
    }

    public static double in(double centimeters) {
        return centimeters * 0.3837008;
    }
}

//    Pose2d startPose = new Pose2d(in(91), in(-160), rad(90));
//    RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
//            .setDimensions(16.2,14)
//            // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
//            .setConstraints(73.173, 73.173, 6.776136290775701, Math.toRadians(314.75385135135133), 11)
//
//            .followTrajectorySequence(drive ->
//                    drive.trajectorySequenceBuilder(startPose)
//                            .forward(50)
//                            .splineTo(new Vector2d(31,-5), 134.5)
//                            .setReversed(true)
//                            .splineTo(new Vector2d(60,-12), 0)
//                            .setReversed(false)
//                            .splineTo(new Vector2d(31,-5), 134.5)
//                            .setReversed(true)
//                            .splineTo(new Vector2d(60,-12), 0)
//                            .setReversed(false)
//                            .splineTo(new Vector2d(31,-5), 134.5)
//                            .setReversed(true)
//                            .splineTo(new Vector2d(60,-12), 0)
//                            .setReversed(false)
//                            .splineTo(new Vector2d(31,-5), 134.5)
//                            .setReversed(true)
//                            .splineTo(new Vector2d(60,-12), 0)
//                            .setReversed(false)
//                            .splineTo(new Vector2d(31,-5), 134.5)
//                            .setReversed(true)
//                            .splineTo(new Vector2d(60,-12), 0)
//                            .setReversed(false)
//                            .splineTo(new Vector2d(31,-5), 134.5)
//                            .build()
//            );