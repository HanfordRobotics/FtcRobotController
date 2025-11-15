package org.firstinspires.ftc.teamcode;

import android.annotation.SuppressLint;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp
public class RobotCode extends OpMode {
    // Listing out stuff we'll need later, have to set any values to null or else will give error
    DcMotor leftBackPar;
    DcMotor leftFront;
    DcMotor rightBack;
    DcMotor rightFrontPerp;
    DcMotor feeder;
    DcMotor feeder2; // This is the 2nd ball from the intake
    DcMotor launcher;
    DcMotor launcher2;
    Servo servo;
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

    enum State {IDLE, INTAKING, LAUNCHING, STOPPING}

    boolean prevA = false;

    State state = State.IDLE;


    @SuppressLint("DefaultLocale")
    @Override
    public void init() {
        // Mapping to motors and gamepad
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        rightFrontPerp = hardwareMap.get(DcMotor.class, "rightFrontPerp");
        leftBackPar = hardwareMap.get(DcMotor.class, "leftBackPar");
        rightBack = hardwareMap.get(DcMotor.class, "rightBack");
        feeder = hardwareMap.get(DcMotor.class, "feeder");
        feeder2 = hardwareMap.get(DcMotor.class, "feeder2");
        launcher = hardwareMap.get(DcMotor.class, "launcher");
        launcher2 = hardwareMap.get(DcMotor.class, "launcher2");
        servo = hardwareMap.get(Servo.class, "servo");

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
        launcher2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        launcher.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        // Motor direction
        leftFront.setDirection(DcMotor.Direction.FORWARD);
        leftBackPar.setDirection(DcMotor.Direction.REVERSE);
        rightFrontPerp.setDirection(DcMotor.Direction.FORWARD);
        rightBack.setDirection(DcMotor.Direction.FORWARD);
        feeder2.setDirection(DcMotor.Direction.REVERSE);
        feeder.setDirection(DcMotor.Direction.FORWARD);

        telemetry.addData("Status", "Initialized");


    }

    @Override
    public void loop() {
        // check if we've changed from !pressed to pressed
        //is_A_Pressed = !prevA && gamepad1.a;

        // set new a button state
        // prevA = gamepad1.a;

        // Setting up values for driving forwards, laterally, and turning
        double drive = -gamepad1.left_stick_y;
        double turn = gamepad1.right_stick_x;
        double lateral = gamepad1.left_stick_x;
        double leftFrontPower = (drive + lateral + turn)*(1-gamepad1.left_trigger);
        double rightFrontPower = (drive - lateral - turn)*(1-gamepad1.left_trigger);
        double leftBackPower = (drive - lateral + turn)*(1-gamepad1.left_trigger);
        double rightBackPower = (drive + lateral - turn)*(1-gamepad1.left_trigger);
        // Give motors the power values from above
        leftFront.setPower(leftFrontPower);
        leftBackPar.setPower(leftBackPower);
        rightFrontPerp.setPower(rightFrontPower);
        rightBack.setPower(rightBackPower);


        if (gamepad1.a) {
            //state = State.LAUNCHING;
            launcher.setPower(-.1);
            launcher2.setPower(.1);
            feeder.setPower(-1.0);
            feeder2.setPower(-1.0);
            //servo.setPosition(1.0);
            timer.reset();
        }
        if (gamepad1.b) {
            // state = State.STOPPING;
            launcher.setPower(0.0);
            launcher2.setPower(0.0);
            feeder.setPower(0.0);
            feeder2.setPower(0.0);
        }
        if (gamepad1.x) {
            //state = State.INTAKING;
            launcher.setPower(0);
            launcher2.setPower(0);
            feeder2.setPower(-1.0);
        }
        if (gamepad1.y) {
                launcher.setPower(0);
                launcher2.setPower(0);
                feeder.setPower(-1.0);
            }

            // Servo control hub at port 0
            //switch (state) {
            //  case LAUNCHING:
            //    launcher.setPower(-.1);
            //  launcher2.setPower(.1);
            //feeder.setPower(-1.0);
            // feeder2.setPower(-1.0);
            //servo.setPosition(1.0);
            //timer.reset();
            //break;
            //case STOPPING:
            //  launcher.setPower(0.0);
            // launcher2.setPower(0.0);
            // feeder.setPower(0.0);
            // feeder2.setPower(0.0);
            //servo.setPosition(0.5);
            // case IDLE:
            //    break;
            //   case INTAKING:
            //     launcher.setPower(0);
            //    launcher2.setPower(0);
            //   feeder.setPower(-1.0);
            //  feeder2.setPower(-1.0);
        }
        //TODO: set servo pos correctly
        //if (is_Y_Pressed) {
//            servo.setPosition(1.0);
        //is_Y_Pressed = false;
        //} else {
        //servo.setPosition(-0.5);
    }
    //hey grace this is going to spin all motors at .5 power so the robot will just move forward really slow
    //if (leftBump) {
    //leftFront.setPower(0.5);
    // leftBackPar.setPower(0.5);
    // rightFrontPerp.setPower(0.5);
    // rightBack.setPower(0.5);


//    public void launch(boolean isLaunch) {
//        // TODO: Set DcMotor motor directions, may need to debug/find a better method for keeping track of time
//        if (isLaunch) {
//            launcher.setPower(1);
//            feeder.setPower(1);
//        }
//    }
