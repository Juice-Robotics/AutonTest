package org.firstinspires.ftc.teamcode.teleop;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.exception.RobotCoreException;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.VoltageSensor;

import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;
import org.firstinspires.ftc.teamcode.lib.Component;
import org.firstinspires.ftc.teamcode.lib.Levels;
import org.firstinspires.ftc.teamcode.lib.Motor;
import org.firstinspires.ftc.teamcode.subsystems.slides.Slides;

@TeleOp(group = "competition")
public class SlidesAndDrive extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {


        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        DcMotorEx slides1 = (DcMotorEx) hardwareMap.dcMotor.get("slides1");
        DcMotorEx slides2 = (DcMotorEx) hardwareMap.dcMotor.get("slides2");

        slides1.setDirection(DcMotorSimple.Direction.REVERSE);


        Gamepad previousGamepad1 = new Gamepad();

        waitForStart();
        if (isStopRequested()) return;

        while (opModeIsActive() && !isStopRequested()) {

            if (gamepad1.left_bumper) {
                slides1.setPower(-1);
                slides2.setPower(-1);
            }

                telemetry.addData("slide1 current", slides1.getCurrent(CurrentUnit.AMPS));
                telemetry.addData("slides pos: ", slides1.getCurrentPosition());
                telemetry.addData("slide2 current", slides2.getCurrent(CurrentUnit.AMPS));
                telemetry.update();
        }
    }
}

