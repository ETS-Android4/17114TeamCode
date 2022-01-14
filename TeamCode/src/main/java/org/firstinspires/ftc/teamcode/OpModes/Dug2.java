package org.firstinspires.ftc.teamcode.OpModes;



import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp (name = "Dug2")
public class Dug2 extends OpMode {
    DcMotor leftFront;
    DcMotor leftRear;
    DcMotor rightFront;
    DcMotor rightRear;
    DcMotor duckMotor;
    DcMotor liftMotor;
    Servo boxServo;
    DcMotor intakeMotor;


    @Override
    public void init() {
        leftFront = hardwareMap.dcMotor.get("leftFront");
        leftRear = hardwareMap.dcMotor.get("leftRear");
        rightFront = hardwareMap.dcMotor.get("rightFront");
        rightRear = hardwareMap.dcMotor.get("rightRear");
        duckMotor = hardwareMap.dcMotor.get("duckMotor");
        liftMotor = hardwareMap.dcMotor.get("liftMotor");
        boxServo = hardwareMap.servo.get("boxServo");
        intakeMotor = hardwareMap.dcMotor.get("intakeMotor");

        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
        leftRear.setDirection(DcMotorSimple.Direction.REVERSE);

        liftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        liftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        boxServo.setPosition(1);
        telemetry.addData("Say", ":p");
    }

    @Override
    public void loop() {
        double y = gamepad1.right_stick_y;
        double x = -gamepad1.right_stick_x * 1.1; // Counteract imperfect strafing
        double rx = gamepad1.left_stick_x;

        // Denominator is the largest motor power (absolute value) or 1
        // This ensures all the powers maintain the same ratio, but only when
        // at least one is out of the range [-1, 1]
        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
        double frontLeftPower = (y + x + rx) / denominator;
        double backLeftPower = (y - x + rx) / denominator;
        double frontRightPower = (y - x - rx) / denominator;
        double backRightPower = (y + x - rx) / denominator;

        leftFront.setPower(frontLeftPower);
        leftRear.setPower(backLeftPower);
        rightFront.setPower(frontRightPower);
        rightRear.setPower(backRightPower);

        //liftMotor.setPower(gamepad2.left_trigger - gamepad2.right_trigger);
        intakeMotor.setPower(gamepad1.left_trigger - gamepad1.right_trigger);

        if (gamepad2.x) {
            boxServo.setPosition(0.6);
        }
        //else if (gamepad2.y) {
        //    boxServo.setPosition(0.6;
        //}
        else {
            boxServo.setPosition(1);
        }

        /*
        if (gamepad1.x) {
            duckMotor.setPower(.5);
        }
        else if(gamepad1.b) {
            duckMotor.setPower(-.5);
        }
        else {
            duckMotor.setPower(0);
        }
        */

        if (gamepad1.x) {
            duckMotor.setPower(duckMotor.getPower() + .0025);
        } else if (gamepad1.b) {
            duckMotor.setPower(duckMotor.getPower() - .0025);
        } else {
            duckMotor.setPower(0);
        }

        if (duckMotor.getPower() > .8) {
            duckMotor.setPower(.8);
        } else if (duckMotor.getPower() < -.8) {
            duckMotor.setPower(-.8);
        }

        if (gamepad2.b) {
            move(-1800);
        } else if (gamepad2.a) {
            move(2000);
            boxServo.setPosition(1);
        }

        if (gamepad1.right_bumper) {
            intakeMotor.setPower(.8);
        } else if (gamepad1.left_bumper) {
            intakeMotor.setPower(-.8);
        } else {
            intakeMotor.setPower(0);
        }

    }

    private void move(int a) {
        liftMotor.setTargetPosition(a);
        liftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        liftMotor.setPower(1);
    }
}