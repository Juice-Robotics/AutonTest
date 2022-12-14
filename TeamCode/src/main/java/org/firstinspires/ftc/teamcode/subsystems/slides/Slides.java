package org.firstinspires.ftc.teamcode.subsystems.slides;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.VoltageSensor;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.lib.Levels;
import org.firstinspires.ftc.teamcode.lib.Motor;

public class Slides {
    private PIDController controller1;
    private PIDController controller2;

    public double p = 0.009, i = 0.01, d = 0.0005;
    public double f = -0.003;
    double voltageCompensation;

    public int target = 0;
    public Levels currentLevel = Levels.ZERO;
    private final double ticks_in_degrees = 700 / 180.0;
    public double power1;
    public double power2;

    public Motor slides1;
    public Motor slides2;
    public VoltageSensor voltageSensor;

    // TARGETS IN NEGATIVE
    public int zeroTarget = -10;
    public int groundTarget = -10;
    public int lowTarget = -250;
    public int midTarget = -700;
    public int highTarget = -1090;


    public Slides(Motor slides1, Motor slides2, VoltageSensor voltageSensor) {
        this.slides1 = slides1;
        this.slides2 = slides2;
        this.voltageSensor = voltageSensor;

        controller1 = new PIDController(p, i , d);
        controller2 = new PIDController(p, i , d);
        slides1.motor.setDirection(DcMotorSimple.Direction.REVERSE);
    }


    public void update() {
        int slides1Pos = slides1.motor.getCurrentPosition();
//        int slides2Pos = slides2.motor.getCurrentPosition();

        double pid1 = controller1.calculate(slides1Pos, target);
//        double pid2 = controller2.calculate(slides2Pos, target);
        double ff = Math.cos(Math.toRadians(target / ticks_in_degrees)) * f;

        voltageCompensation = 13.2 / voltageSensor.getVoltage();
        power1 = (pid1 + ff) * voltageCompensation;
//        power2 = pid2 + ff;

        if (target == groundTarget){
            slides1.motor.setPower(power1*0.3);
            slides2.motor.setPower(-power1*0.3);
        }
        else {
            slides1.motor.setPower(power1);
            slides2.motor.setPower(-power1);
        }
    }

    public void runToPosition(int ticks) {
        target = ticks;
    }

    public void runToPreset(Levels level) {
//        switch (level) {
//            case ZERO:
//                target = zeroTarget;
//                currentLevel = level;
//            case GROUND:
//                target = groundTarget;
//                currentLevel = level;
//            case LOW:
//                target = lowTarget;
//                currentLevel = level;
//            case MEDIUM:
//                target = midTarget;
//                currentLevel = level;
//            case HIGH:
               /*target = lowTarget;
               currentLevel = level;*/
//        }
        if (level == Levels.ZERO) {
            target = zeroTarget;
            currentLevel = level;
        } else if (level == Levels.GROUND) {
            target = groundTarget;
            currentLevel = level;
        } else if (level == Levels.LOW) {
            target = lowTarget;
            currentLevel = level;
        } else if (level == Levels.MEDIUM) {
            target = midTarget;
            currentLevel = level;
        } else if (level == Levels.HIGH) {
            target = highTarget;
            currentLevel = level;
        }
    }


    public void resetAllEncoders(){
        slides1.resetEncoder();
        slides2.resetEncoder();
    }

}
