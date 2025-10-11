package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class testMotor extends OpMode {
    DcMotor motor = null;

    @Override
    public void init() {
        motor = hardwareMap.dcMotor.get("launcher");
        motor.setDirection(DcMotor.Direction.REVERSE);

        telemetry.addData("Status", "Initialized");
    }

    @Override
    public void loop() {
        motor.setPower(0.5);
    }
}
