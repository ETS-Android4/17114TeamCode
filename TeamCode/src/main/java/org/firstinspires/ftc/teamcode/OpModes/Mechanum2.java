package org.firstinspires.ftc.teamcode.OpModes;




import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class Mechanum2 extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        // Declare our motors
        // Make sure your ID's match your configuration
        DcMotor leftFront = hardwareMap.dcMotor.get("leftFront");
        DcMotor leftRear = hardwareMap.dcMotor.get("leftRear");
        DcMotor rightFront = hardwareMap.dcMotor.get("rightFront");
        DcMotor rightRear = hardwareMap.dcMotor.get("rightRear");
        CRServo liftMotor = hardwareMap.crservo.get("liftMotor");
        CRServo intakeWheel = hardwareMap.crservo.get("intakeWheel");
        CRServo duckMotor = hardwareMap.crservo.get("duckMotor");
        // Reverse the right side motors
        // Reverse left motors if you are using NeveRests
        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
        leftRear.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            double y = -gamepad1.right_stick_y; // Remember, this is reversed!
            double x = gamepad1.right_stick_x * 1.1; // Counteract imperfect strafing
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

            liftMotor.setPower(gamepad1.left_trigger - gamepad1.right_trigger);

            if (gamepad1.a) {
                intakeWheel.setPower(1);
            }
            else {
                intakeWheel.setPower(0);
            }
            if (gamepad1.y) {
                intakeWheel.setPower(-1);
            }
            else {
                intakeWheel.setPower(0);
            }
            if (gamepad1.x) {
                duckMotor.setPower(1);
            }
            else {
                duckMotor.setPower(0);
            }
            if (gamepad1.b) {
                duckMotor.setPower(-1);
            }
            else {
                duckMotor.setPower(0);
            }
        }
    }
}