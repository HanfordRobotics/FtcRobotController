// imports
package org.firstinspires.ftc.teamcode;
import android.annotation.SuppressLint;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;


@TeleOp
public class GraceTraining extends OpMode {
    // have to set them to null or program will give red squiggly lines
    private ElapsedTime runtime = null;
    private DcMotor leftFront = null;
    private DcMotor leftBackPar = null;
    private DcMotor rightFrontPerp = null;
    private DcMotor rightBack = null;

    @SuppressLint("DefaultLocale")
    @Override
    public void init() {
        runtime = new ElapsedTime();
        telemetry.addData("Status", "Initialized");

        // named Perp and Par for later Roadrunner
        // Mapping motors to names
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        rightFrontPerp = hardwareMap.get(DcMotor.class, "rightFrontPerp");
        leftBackPar = hardwareMap.get(DcMotor.class,"leftBackPar");
        rightBack = hardwareMap.get(DcMotor.class,"rightBack");

        leftFront.setDirection(DcMotor.Direction.FORWARD);
        leftBackPar.setDirection(DcMotor.Direction.FORWARD);
        rightFrontPerp.setDirection(DcMotor.Direction.REVERSE);
        rightBack.setDirection(DcMotor.Direction.REVERSE);

        telemetry.addData("Runtime: ", runtime);
    }

    @Override
    public void start() {
        runtime.reset();
    }

    @Override
    public void loop() {
        double drive = -gamepad1.left_stick_y;
        double turn  =  gamepad1.right_stick_x;
        double leftPower = Range.clip(drive + turn, -1.0, 1.0);
        double rightPower = Range.clip(drive - turn, -1.0, 1.0);

        leftFront.setPower(leftPower);
        leftBackPar.setPower(leftPower);
        rightFrontPerp.setPower(rightPower);
        rightBack.setPower(rightPower);

        telemetry.addData("Left Power: ", leftPower);
        telemetry.addData("Right Power: ", rightPower);
    }

    @Override
    public void stop() {
        // Runs once when driver hits 'stop'
    }
}
