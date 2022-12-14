package org.firstinspires.ftc.teamcode.teleop;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.exception.RobotCoreException;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Robot;

@TeleOp(group = "competition")
public class TeleOpMain extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        // Initialize your own robot class
        Robot robot = new Robot(hardwareMap,false);

        double x;
        double y;
        double rx;

        Gamepad previousGamepad1 = new Gamepad();
        Gamepad previousGamepad2 = new Gamepad();

        ElapsedTime timer;
        timer = new ElapsedTime();

        boolean isOpen = false;

        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        waitForStart();
        if (isStopRequested()) return;

        while (opModeIsActive() && !isStopRequested()) {

            if (gamepad1.dpad_up) {
                robot.slides.resetAllEncoders();
            }

            //DRIVE
            if (gamepad1.right_trigger > 0.1){
                x = gamepad1.left_stick_x*(1-0.66*gamepad1.right_trigger);
                y = -gamepad1.left_stick_y*(1-0.66*gamepad1.right_trigger);
                rx = gamepad1.right_stick_x*(1-0.66*gamepad1.right_trigger);

            } else{
                x = -gamepad1.left_stick_x;
                y = -gamepad1.left_stick_y;
                rx = gamepad1.right_stick_x;
            }
            robot.setDrivePower(-x, y, rx);

            //PRESETS
            if (gamepad1.right_bumper)
                robot.groundPreset(gamepad1.dpad_down);
            if (gamepad1.dpad_left)
                robot.lowPreset(gamepad1.dpad_left);
            if (gamepad1.dpad_right)
                robot.mediumPreset(gamepad1.dpad_right);
            if (gamepad1.left_bumper)
                robot.highPreset(gamepad1.left_bumper);

            //CLAW
            if (gamepad1.cross)
                if(timer.milliseconds()>500) {
                    isOpen = !isOpen;
                    if (isOpen){
                        robot.claw.setPositionClaw(0.8);
                        timer.reset();
                    }
                    else {
                        robot.claw.setPositionClaw(0.5);
                        timer.reset();
                    }
                }
            if (gamepad2.left_stick_y > 0.2) {
                robot.startClawX(true);
            } else if (gamepad2.left_stick_y < -0.2) {
                robot.startClawX(false);
            }

            if (gamepad1.circle) {
                robot.resetAllServos();
            }

            if (gamepad2.right_stick_x > 0.2) {
                robot.startClawY(true);
            } else if (gamepad2.right_stick_x < -0.2) {
                robot.startClawY(false);
            }

//            if (gamepad2.b && !previousGamepad2.b)
//                robot.resetClawRotation(gamepad2.b);
//            if (gamepad1.)

            robot.slides.update();
            telemetry.addData("v4b position target: ", robot.v4b.getAngle());
            telemetry.addData("v4b1 position: ", robot.v4b.v4b1.servo.getPosition());
            telemetry.addData("v4b2 position: ", robot.v4b.v4b2.servo.getPosition());
            telemetry.addData("x pressed?", gamepad1.cross);
            telemetry.addData("claw status: ", robot.claw.state);
            telemetry.addData("claw: ", robot.claw.claw.servo.getPosition());
            telemetry.addData("d left? ", gamepad1.dpad_left);
            telemetry.addData("slides target: ", robot.slides.target);
            telemetry.addData("slides pos: ", robot.slides.slides1.motor.getCurrentPosition());
            telemetry.addData("slides power", robot.slides.power1);
            telemetry.addData("slides level: ", robot.slides.currentLevel);
            telemetry.addData("voltage: ", robot.slides.voltageSensor.getVoltage());
            telemetry.update();

            try {
                previousGamepad1.copy(gamepad1);
                previousGamepad2.copy(gamepad2);
            } catch (RobotCoreException e) {

            }
        }
    }
}