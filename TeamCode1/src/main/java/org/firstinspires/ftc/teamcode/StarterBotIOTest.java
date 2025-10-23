package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class StarterBotIOTest extends OpMode {
    boolean isButtonPressed_A;
    boolean leftBump;
    boolean rightBump;

    double leftStickX;
    double leftStickY;

    double rightStickX;

    double rightStickY;

    DcMotor launcher;

    DcMotor right_drive;
    DcMotor left_drive;

    CRServo rightFeeder;
    CRServo leftFeeder;
//    final double feedTimeSecs = 0.20;
//    // Takes this amt of time to run one shot
//    private ElapsedTime runtime = null;

    @Override
    public void init() {
        launcher = hardwareMap.dcMotor.get("launcher");
        right_drive = hardwareMap.dcMotor.get("right_drive");
        left_drive = hardwareMap.dcMotor.get("left_drive");
        rightFeeder = hardwareMap.crservo.get("right_feeder");
        leftFeeder = hardwareMap.crservo.get("left_feeder");

        left_drive.setDirection(DcMotor.Direction.REVERSE);
        right_drive.setDirection(DcMotor.Direction.FORWARD);
        leftFeeder.setDirection(DcMotor.Direction.REVERSE);
        rightFeeder.setDirection(DcMotor.Direction.FORWARD);

    }

    @Override
    public void loop() {
        isButtonPressed_A = gamepad1.a;
        leftBump = gamepad1.left_bumper;
        rightBump = gamepad1.right_bumper;
        rightStickX = gamepad1.right_stick_x;
        rightStickY = gamepad1.right_stick_y;
        leftStickX = gamepad1.left_stick_x;
        leftStickY = gamepad1.left_stick_y;

        left_drive.setPower(-leftStickY);
        right_drive.setPower(rightStickY);

        if (isButtonPressed_A) {
            launcher.setPower(1);
        } else {
            launcher.setPower(0);
        }

        if (leftBump) {
            rightFeeder.setPower(0.25);
            leftFeeder.setPower(0.25);
        } else {
            rightFeeder.setPower(0);
            leftFeeder.setPower(0);
        }

        if (rightBump) {
            right_drive.setPower(0.25);
            left_drive.setPower(0.25);
        } else {
            right_drive.setPower(0);
            left_drive.setPower(0);
        }
    }

    public void launch(boolean isLaunch) {
        double timeMil = System.currentTimeMillis();
        if (isLaunch) {
            leftFeeder.setPower(1);
            rightFeeder.setPower(1);
            launcher.setPower(1);

            if (System.currentTimeMillis() - timeMil >= 1000) {
                leftFeeder.setPower(0);
                rightFeeder.setPower(0);
                launcher.setPower(0);
            }
        } else {
            // If we don't want to launch, we set everything to 0
            leftFeeder.setPower(0);
            rightFeeder.setPower(0);
            launcher.setPower(0);
        }
    }
}