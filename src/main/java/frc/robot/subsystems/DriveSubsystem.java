/* 
* Kevin Hwang
* Lasted edited on 10/26/2021
* Makes robot move using Mecanum wheels.
*/

package frc.robot.subsystems;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import java.util.function.Supplier;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.drive.MecanumDrive;




public class DriveSubsystem extends SubsystemBase{
    private Supplier<Double> multiplier;

    // Left
    private final WPI_VictorSPX frontLeft = new WPI_VictorSPX(Constants.FRONT_LEFT_ID);
    private final WPI_VictorSPX rearLeft = new WPI_VictorSPX(Constants.REAR_LEFT_ID);

    // Right 
    private final WPI_VictorSPX frontRight = new WPI_VictorSPX(Constants.FRONT_RIGHT_ID);
    private final WPI_VictorSPX rearRight = new WPI_VictorSPX(Constants.REAR_RIGHT_ID);

    public final MecanumDrive driveTrain;

    public DriveSubsystem(Supplier<Double> multiplier){
        frontRight.setInverted(true);
        rearRight.setInverted(true);
        frontLeft.setInverted(false);
        rearLeft.setInverted(false);

        this.multiplier = multiplier;

        driveTrain = new MecanumDrive(frontLeft, rearLeft, frontRight, rearRight);
    }

    public void drive(double ySpeed, double xSpeed,double zRotation){
        // ySpeed, xSpeed, and zRotation are all supplier values from the joystick object
        driveTrain.driveCartesian(
            -ySpeed*multiplier.get().doubleValue(),
            xSpeed*multiplier.get().doubleValue(),
            zRotation*multiplier.get().doubleValue()
        );
    }
    
}
