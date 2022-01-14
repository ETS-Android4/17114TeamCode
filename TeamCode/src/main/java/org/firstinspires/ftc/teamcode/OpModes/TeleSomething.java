package org.firstinspires.ftc.teamcode.OpModes;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.linearOpMode;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
@TeleOp
public class TeleSomething extends LinearOpMode {

    private DcMotor rightFront;
    private DcMotor rightRear;
    private DcMotor leftFront;
    private DcMotor leftRear;
    private DcMotor duckMotor;
    private DcMotor liftMotor;
    private CRServo boxServo;

    @Override
    public void runOpMode() throws InterruptedException {
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        leftRear = hardwareMap.get(DcMotor.class, "leftRear");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        rightRear = hardwareMap.get(DcMotor.class, "rightRear");
        boxServo = hardwareMap.get(CRServo.class, "boxServo");
        DcMotor duckMotor = hardwareMap.dcMotor.get("duckMotor");
        DcMotor liftMotor = hardwareMap.dcMotor.get("liftMotor");
        rightFront.setDirection(DcMotorSimple.Direction.REVERSE);
        rightRear.setDirection(DcMotorSimple.Direction.REVERSE);
        liftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
      //  telemetry.addData("Say", "Hello Driver")
        waitForStart();
      while(opModeIsActive())
            telemetry.addData("Lift Encoder", liftMotor.getCurrentPosition());
        telemetry.addData("Lift Encoder", liftMotor.getCurrentPosition());
        telemetry.addData("Lift Encoder", liftMotor.getCurrentPosition());

            telemetry.update();
        }
    }
