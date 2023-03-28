package frc.robot.commands.auton;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.intake.ReverseIntakeWheels;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.SwerveSubsystem;
import frc.robot.subsystems.WheelSubsystem;

public class cubeBalance extends SequentialCommandGroup{
    private final WheelSubsystem s_wheels;
    private final SwerveSubsystem s_swerve;
    private final IntakeSubsystem s_intake;

    public cubeBalance(WheelSubsystem s_wheels, SwerveSubsystem s_swerve, IntakeSubsystem s_intake){
        this.s_wheels = s_wheels;
        this.s_swerve = s_swerve;
        this.s_intake = s_intake;
        addRequirements(this.s_wheels);
        addRequirements(this.s_swerve);
        addRequirements(this.s_intake);

        addCommands(
            new ReverseIntakeWheels(s_wheels, 0.6), 
            new ParallelDeadlineGroup(
                new WaitCommand(3.2), 
                new RunCommand(
                () -> {
                  // Robot.motionMode = MotionMode.NULL;
                  this.s_swerve.setModuleStates(
                      new SwerveModuleState[] {
                          new SwerveModuleState(-1.8, Rotation2d.fromDegrees(0)),
                          new SwerveModuleState(-1.8, Rotation2d.fromDegrees(0)),
                          new SwerveModuleState(-1.8, Rotation2d.fromDegrees(0)),
                          new SwerveModuleState(-1.8, Rotation2d.fromDegrees(0))
                      });
                }
            )),
            new ParallelDeadlineGroup(
                new WaitCommand(1), 
                new RunCommand(
                () -> {
                    // Robot.motionMode = MotionMode.NULL;
                    this.s_swerve.setModuleStates(
                        new SwerveModuleState[] {
                            new SwerveModuleState(0, Rotation2d.fromDegrees(0)),
                            new SwerveModuleState(0, Rotation2d.fromDegrees(0)),
                            new SwerveModuleState(0, Rotation2d.fromDegrees(0)),
                            new SwerveModuleState(0, Rotation2d.fromDegrees(0))
                        });
                }))
            ,
            new ParallelDeadlineGroup(new WaitCommand(2), new RunCommand(
                () -> {
                    // Robot.motionMode = MotionMode.NULL;
                    this.s_swerve.setModuleStates(
                        new SwerveModuleState[] {
                            new SwerveModuleState(1.8, Rotation2d.fromDegrees(0)),
                            new SwerveModuleState(1.8, Rotation2d.fromDegrees(0)),
                            new SwerveModuleState(1.8, Rotation2d.fromDegrees(0)),
                            new SwerveModuleState(1.8, Rotation2d.fromDegrees(0))
                        });
                })),
                new ParallelDeadlineGroup(new WaitCommand(1), new RunCommand(
                () -> {
                    // Robot.motionMode = MotionMode.NULL;
                    this.s_swerve.setModuleStates(
                        new SwerveModuleState[] {
                            new SwerveModuleState(0, Rotation2d.fromDegrees(0)),
                            new SwerveModuleState(0, Rotation2d.fromDegrees(0)),
                            new SwerveModuleState(0, Rotation2d.fromDegrees(0)),
                            new SwerveModuleState(0, Rotation2d.fromDegrees(0))
                        });
                })),
            new WaitCommand(15)
        );
    }
    
}
