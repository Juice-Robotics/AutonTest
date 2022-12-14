package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.lib.*;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.VoltageSensor;

// IMPORT SUBSYSTEMS
import org.firstinspires.ftc.teamcode.subsystems.claw.Claw;
import org.firstinspires.ftc.teamcode.subsystems.slides.Slides;
import org.firstinspires.ftc.teamcode.subsystems.v4b.V4B;


public class Robot {

    // SUBSYSTEM DECLARATIONS
    public Component[] components;
    public Claw claw;
    public Slides slides;
    public V4B v4b;

    // STATE VARS
    // example: clawToggled = false;


    // POSE
//    public Pose robotPose = new Pose(
//        Math.PI/2, Math.PI/2, 0.0,
//        7.41830709, -7.41830709, 0.5748031,
//        0.5748031, 0.5748031, 3.75,
//        0.6968503935
//    );

    // AUTON CONSTANTS
    public SampleMecanumDrive drive;
    boolean auton;



    public Robot(HardwareMap map, boolean auton){
        this.auton = auton;

        this.drive = new SampleMecanumDrive(map);

        this.components = new Component[]{
                new Motor(3, "leftRear", map, true),          //0 left odometer
                new Motor(2, "rightRear", map, false),        //1 right odometer
                new Motor(1, "leftFront", map, true),         //2 middle odometer
                new Motor(0, "rightFront", map, false),       //3

                new Motor(1, "slides1", map, true),           //4
                new Motor(2, "slides2", map, true),           //5

                new StepperServo(0, "v4bServo1", map),        //6
                new StepperServo(1, "v4bServo2", map),        //7

                new StepperServo(0, "clawXServo1", map),      //8
                new StepperServo(1, "clawXServo2", map),      //9

                new StepperServo(0, "clawYServo", map),       //10

                new StepperServo(1, "clawServo", map),        //11
        };

        VoltageSensor voltageSensor = map.voltageSensor.iterator().next();

        // INIT SUBSYSTEMS
        this.claw = new Claw((StepperServo) components[11], (StepperServo) components[8], (StepperServo) components[9], (StepperServo) components[10]);
        this.slides = new Slides((Motor) components[4], (Motor) components[5], voltageSensor);
        this.v4b = new V4B((StepperServo) components[6], (StepperServo) components[7]);
    }


    // CONTROL FUNCTIONS

    //CLAW
    public void toggleClaw(boolean x) {
        this.claw.toggle();
    }

    public void startClawX(boolean direction) {
        this.claw.startXRotation(direction);
    }

    public void startClawY(boolean direction) {
        this.claw.startYRotation(direction);
    }

    public void resetClawRotation(boolean b) {
        this.claw.resetRotation(Axis.ALL);
    }

    public void update() {
        slides.update();
    }


    // SLIDES + V4B + CLAW PRESETS
    public void groundPreset(boolean pad_down) {
        this.slides.runToPreset(Levels.GROUND);
        this.claw.setXRotation(15);
        try {
            Thread.sleep(100);
        } catch (Exception e) {}
        this.v4b.runToPreset(Levels.GROUND);
        this.claw.setYRotation(0);
    }

    public void lowPreset(boolean pad_left) {
        this.slides.runToPreset(Levels.LOW);
        this.v4b.runToPreset(Levels.LOW);
        this.claw.setYRotation(140);
        this.claw.setXRotation(195);
    }

    public void mediumPreset(boolean pad_right) {
        this.slides.runToPreset(Levels.MEDIUM);
        this.v4b.runToPreset(Levels.MEDIUM);
        this.claw.setYRotation(140);
        this.claw.setXRotation(195);

    }

    public void highPreset(boolean pad_up) {
        this.slides.runToPreset(Levels.HIGH);
        this.v4b.runToPreset(Levels.HIGH);
        this.claw.setYRotation(140);
        this.claw.setXRotation(185);
    }

    public void resetAllServos() {
        this.v4b.setAngle(10);
        this.claw.setXRotation(13);
        this.claw.clawY.servo.setPosition(0);
    }


    //DRIVE
    public void setDrivePower(double x, double y, double rx) {
        double powerFrontLeft = y + x + rx;
        double powerFrontRight = y - x - rx;
        double powerBackLeft = (y - x + rx) * -1;
        double powerBackRight = (y + x - rx) * -1;

        if (Math.abs(powerFrontLeft) > 1 || Math.abs(powerBackLeft) > 1 ||
                Math.abs(powerFrontRight) > 1 || Math.abs(powerBackRight) > 1) {
            // Find the largest power
            double max;
            max = Math.max(Math.abs(powerFrontLeft), Math.abs(powerBackLeft));
            max = Math.max(Math.abs(powerFrontRight), max);
            max = Math.max(Math.abs(powerBackRight), max);

            // Divide everything by max (it's positive so we don't need to worry
            // about signs)
            powerFrontLeft /= max;
            powerBackLeft /= max;
            powerFrontRight /= max;
            powerBackRight /= max;
        }
        Motor backLeft = (Motor) components[0];
        Motor backRight = (Motor) components[1];
        Motor frontLeft = (Motor) components[2];
        Motor frontRight = (Motor) components[3];
        frontLeft.setSpeed((float)powerFrontLeft);
        frontRight.setSpeed((float)powerFrontRight);
        backLeft.setSpeed(-(float)powerBackLeft);
        backRight.setSpeed(-(float)powerBackRight);
    }
}
