package org.firstinspires.ftc.teamcode;

import android.annotation.SuppressLint;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp
public class RobotCode extends OpMode {
    // Listing out stuff we'll need later, have to set any values to null or else will give error
    DcMotor leftBackPar;
    DcMotor leftFront;
    DcMotor rightBack;
    DcMotor rightFrontPerp;
    DcMotor leftFeeder;
    DcMotor rightFeeder;
    DcMotor launcher;
    boolean is_A_Pressed;
    boolean is_Y_Pressed;
    boolean is_X_Pressed;
    boolean is_B_Pressed;
    boolean leftBump;
    boolean rightBump;
    boolean leftStickDown;
    boolean rightStickDown;
    double rightStickX;
    double rightStickY;
    double leftStickX;
    double leftStickY;

    ElapsedTime timer = new ElapsedTime();
    enum State {IDLE, LAUNCHING}

    @SuppressLint("DefaultLocale")
    @Override
    public void init() {
        // Mapping to motors and gamepad
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        rightFrontPerp = hardwareMap.get(DcMotor.class, "rightFrontPerp");
        leftBackPar = hardwareMap.get(DcMotor.class, "leftBackPar");
        rightBack = hardwareMap.get(DcMotor.class, "rightBack");
        leftBump = gamepad1.left_bumper;
        rightBump = gamepad1.right_bumper;
        rightStickX = gamepad1.right_stick_x;
        rightStickY = gamepad1.right_stick_y;
        leftStickX = gamepad1.left_stick_x;
        leftStickY = gamepad1.left_stick_y;

        // Motor behavior brake
        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFrontPerp.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftBackPar.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Motor direction
        leftFront.setDirection(DcMotor.Direction.REVERSE);
        leftBackPar.setDirection(DcMotor.Direction.REVERSE);
        rightFrontPerp.setDirection(DcMotor.Direction.FORWARD);
        rightBack.setDirection(DcMotor.Direction.FORWARD);

        telemetry.addData("Status", "Initialized");
    }

    @Override
    public void loop() {
        // check if we've changed from !pressed to pressed
        boolean isNewButtonPressA = !is_A_Pressed && gamepad1.a;

        // set new a button state
        is_A_Pressed = gamepad1.a;

        // Setting up values for driving forwards, laterally, and turning
        double drive = -gamepad1.left_stick_y;
        double turn = gamepad1.right_stick_x;
        double lateral = gamepad1.left_stick_x;
        double leftFrontPower = drive + lateral + turn;
        double rightFrontPower = drive - lateral - turn;
        double leftBackPower = drive - lateral + turn;
        double rightBackPower = drive + lateral - turn;
        // Give motors the power values from above
        leftFront.setPower(leftFrontPower);
        leftBackPar.setPower(leftBackPower);
        rightFrontPerp.setPower(rightFrontPower);
        rightBack.setPower(rightBackPower);

        if (leftBump) {
            leftFront.setPower(0.5);
            leftBackPar.setPower(0.5);
            rightFrontPerp.setPower(0.5);
            rightBack.setPower(0.5);
        }

        if (isNewButtonPressA) {
            // start timer
            timer.reset();
        }

        if (is_A_Pressed) {
            // Use time from earlier to keep checking against the present time, if 1 second has passed stop launching
//            if (timer.seconds() >= 1.0) {
//                is_A_Pressed = false;
//            } else {
//                launch(true);
//            }

        }

    }

    // sets button states
    private void checkButtons() {

    }

    public void launch(boolean isLaunch) {
        // TODO: Set DcMotor motor directions, may need to debug/find a better method for keeping track of time
        if (isLaunch) {
            launcher.setPower(1);
            leftFeeder.setPower(1);
            rightFeeder.setPower(1);
        }
    }
}
