package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class TestMotor extends OpMode {
    DcMotor motor = null;
    // Control Hub, Motor GoBuilda 3/4, port 1
    @Override
    public void init() {
        motor = hardwareMap.dcMotor.get("feeder");
        motor.setDirection(DcMotor.Direction.REVERSE);

        telemetry.addData("Status", "Initialized");
    }

    @Override
    public void loop() {
        motor.setPower(1);
    }
}
