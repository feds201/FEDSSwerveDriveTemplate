package frc.robot.commands.auton;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.arm2.RotateArm2Position;
import frc.robot.commands.claw.OuttakeCone;
import frc.robot.commands.sensor.DepthAlign;
import frc.robot.commands.sensor.StrafeAlign;
import frc.robot.constants.ArmConstants;
import frc.robot.constants.AutoConstants;
import frc.robot.constants.VisionConstants;
import frc.robot.subsystems.ArmSubsystem5;
import frc.robot.subsystems.ClawSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.LimelightSubsystem;
import frc.robot.subsystems.SwerveSubsystem;
import frc.robot.subsystems.WheelSubsystem;


public class TestAuton extends SequentialCommandGroup{
    
    public TestAuton(SwerveSubsystem s_swerve, LimelightSubsystem s_limelight, ArmSubsystem5 s_arm, IntakeSubsystem s_intake, WheelSubsystem s_wheels, ClawSubsystem s_claw){

        addRequirements(s_swerve);
        addRequirements(s_limelight);

        addCommands(
            new ParallelRaceGroup(new WaitCommand(0.5), new StrafeAlign(s_swerve, s_limelight, 0)),
            new ParallelCommandGroup(
                new RotateArm2Position(s_arm, ArmConstants.kArmPutHigh),
                new SequentialCommandGroup(
                    new WaitCommand(2.0), 
                    new ParallelRaceGroup(
                        new WaitCommand(1),
                        new DepthAlign(s_swerve, s_limelight, VisionConstants.kDepthAlignmentDistance)))),
            new ParallelCommandGroup(
                new RotateArm2Position(s_arm, AutoConstants.kArmAutonPos),
                new OuttakeCone(s_claw),
                new SequentialCommandGroup(
                    new WaitCommand(1.5)),
                    new DepthAlign(s_swerve, s_limelight, VisionConstants.kRetreatDistance)),
                new ParallelDeadlineGroup(
                    new WaitCommand(1.5), 
                    new RotateArm2Position(s_arm, ArmConstants.kArmHome))
        );
            //new ParallelDeadlineGroup(
                /*new WaitCommand(1.6), 
                new RunCommand(
                () -> {
                  // Robot.motionMode = MotionMode.NULL;
                  s_swerve.setModuleStates(
                      new SwerveModuleState[] {
                          new SwerveModuleState(1.1, Rotation2d.fromDegrees(90)),
                          new SwerveModuleState(1.1, Rotation2d.fromDegrees(90)),
                          new SwerveModuleState(1.1, Rotation2d.fromDegrees(90)),
                          new SwerveModuleState(1.1, Rotation2d.fromDegrees(90))
                      });
                }
            )),*/
            //new ParallelRaceGroup(new WaitCommand(0.5), new StrafeAlign(s_swerve, s_limelight, AutoConstants.kMarker1)),
           // new WaitCommand(15)
        // );
    }
    
}
