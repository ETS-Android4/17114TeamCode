package org.firstinspires.ftc.teamcode.OpModes;




import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp (name = "Dug")
public class Dug extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        // Declare our motors
        // Make sure your ID's match your configuration
        DcMotor leftFront = hardwareMap.dcMotor.get("leftFront");
        DcMotor leftRear = hardwareMap.dcMotor.get("leftRear");
        DcMotor rightFront = hardwareMap.dcMotor.get("rightFront");
        DcMotor rightRear = hardwareMap.dcMotor.get("rightRear");
        DcMotor duckMotor = hardwareMap.dcMotor.get("duckMotor");
        DcMotor liftMotor = hardwareMap.dcMotor.get("liftMotor");
        Servo boxServo = hardwareMap.servo.get("boxServo");
        DcMotor intakeMotor = hardwareMap.dcMotor.get("intakeMotor");
        // Reverse the right side motors
        // Reverse left motors if you are using NeveRests
        rightFront.setDirection(DcMotorSimple.Direction.REVERSE);
        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
        leftRear.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
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

            liftMotor.setPower(gamepad2.left_trigger - gamepad2.right_trigger);
            intakeMotor.setPower(gamepad1.left_trigger - gamepad1.right_trigger);

            if (gamepad2.a) {
                boxServo.setPosition(0.6);
            }
            else if(gamepad2.y) {
                boxServo.setPosition(1);
            }
            else {
                boxServo.setPosition(1);
            }

            if (gamepad2.x) {
                duckMotor.setPower(.5);
            }
            else if(gamepad2.b) {
                duckMotor.setPower(-.5);
            }
            else {
                duckMotor.setPower(0);
            }

            if(gamepad2.x) {
                duckMotor.setPower(duckMotor.getPower() + .0025);
            } else if (gamepad2.b) {
                duckMotor.setPower(duckMotor.getPower() - .0025);
            }else {
                duckMotor.setPower(0);
            }

            if (duckMotor.getPower() > .8) {
                duckMotor.setPower(.8);
            } else if (duckMotor.getPower() < -.8) {
                duckMotor.setPower(-.8);
            }

            if (gamepad1.right_bumper) {
                intakeMotor.setPower(.8);
            }
            else if(gamepad1.left_bumper) {
                intakeMotor.setPower(-.8);
            }
            else {
                intakeMotor.setPower(0);
            }

        }
    }

    //private void move(int a, double b) {

    //}
}