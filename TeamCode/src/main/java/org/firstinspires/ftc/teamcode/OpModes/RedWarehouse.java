package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous

public class RedWarehouse extends LinearOpMode {
    // Declare our motors
    // Make sure your ID's match your configuration
    private DcMotor rightFront;
    private DcMotor rightRear;
    private DcMotor leftFront;
    private DcMotor leftRear;
    private DcMotor duckMotor;
    private DcMotor liftMotor;

    @Override

    public void runOpMode() {
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        leftRear = hardwareMap.get(DcMotor.class, "leftRear");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        rightRear = hardwareMap.get(DcMotor.class, "rightRear");
        DcMotor duckMotor = hardwareMap.dcMotor.get("duckMotor");
        DcMotor liftMotor = hardwareMap.dcMotor.get("liftMotor");
        rightFront.setDirection(DcMotorSimple.Direction.REVERSE);
        rightRear.setDirection(DcMotorSimple.Direction.REVERSE);
        liftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        waitForStart();

        while (opModeIsActive()) {
          move(-1500,-1500,-1500,-1500);//forward
            sleep(500);
         // move(1000,1000,1000,1000);//backward
            move(-500,-500,500,500);
            move(-950,-950,-950,-950);
            move(-500,-500,500,500);
            sleep(250000);


            /*rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            rightRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            leftRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            rightFront.setTargetPosition(3000);
            rightRear.setTargetPosition(3000);
            leftFront.setTargetPosition(-3000);
            leftRear.setTargetPosition(-3000);

            rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightRear.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            leftRear.setMode(DcMotor.RunMode.RUN_TO_POSITION);



            rightRear.setPower(0.6);
            rightFront.setPower(0.6);
            leftFront.setPower(0.6);
            leftRear.setPower(0.6);

           // leftFront.setPower(leftFront/ Math.abs(.6));
            //leftRear.setPower(lb/(Math.abs(lb)));

            while(leftFront.isBusy() && leftRear.isBusy() && rightFront.isBusy() && rightRear.isBusy()){
                sleep(50);

            }
            rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            rightRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            leftRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            rightRear.setPower(0);
            leftFront.setPower(0);
            rightFront.setPower(0);
            leftRear.setPower(0);
*/


        }

    }


    //----------------------------encoder-----------------
    public void move(int rf, int rb, int lf, int lb) {
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        rightFront.setTargetPosition(rf);
        rightRear.setTargetPosition(rb);
        leftFront.setTargetPosition(lf);
        leftRear.setTargetPosition(lb);

        rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightRear.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftRear.setMode(DcMotor.RunMode.RUN_TO_POSITION);


        rightRear.setPower(0.6);
        rightFront.setPower(0.6);

        leftFront.setPower(0.6);
        leftRear.setPower(0.6);

        while (leftFront.isBusy() && leftRear.isBusy() && rightFront.isBusy() && rightRear.isBusy()) {
            sleep(50);

        }
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        rightRear.setPower(0);
        leftFront.setPower(0);
        rightFront.setPower(0);
        leftRear.setPower(0);


    }
    //===============================lift====================




    //----------------------Lift------------
    public void liftup(int encod){


        liftMotor.setTargetPosition(encod);

        liftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        liftMotor.setPower(0.6);

        }




    public void liftdown(int encod){


        liftMotor.setTargetPosition(encod);

        liftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        liftMotor.setPower(-0.6);
        while (liftMotor.isBusy()){
            sleep(50);
        }


        liftMotor.setPower(0); //Might need to come out if lift  is falling

    }
}

