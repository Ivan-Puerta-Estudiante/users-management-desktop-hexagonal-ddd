package com.jcaa.usersmanagement.infrastructure.config;

import com.jcaa.usersmanagement.application.service.GetAllAppointmentsService;
import com.jcaa.usersmanagement.application.port.in.*;
import com.jcaa.usersmanagement.application.service.*;
import com.jcaa.usersmanagement.infrastructure.adapter.email.JavaMailEmailSenderAdapter;
import com.jcaa.usersmanagement.infrastructure.adapter.email.SmtpConfig;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.config.DatabaseConfig;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.config.DatabaseConnectionFactory;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.repository.AppointmentRepositoryMySQL;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.repository.UserRepositoryMySQL;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.AppointmentController;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.UserController;

import java.sql.Connection;
import jakarta.validation.Validator;

public final class DependencyContainer {

  private static final String DB_HOST = "db.host";
  private static final String DB_PORT = "db.port";
  private static final String DB_NAME = "db.name";
  private static final String DB_USER = "db.username";
  private static final String DB_PASSWORD = "db.password";

  private static final String SMTP_HOST = "smtp.host";
  private static final String SMTP_PORT = "smtp.port";
  private static final String SMTP_USER = "smtp.username";
  private static final String SMTP_PASSWORD = "smtp.password";
  private static final String SMTP_FROM = "smtp.from.address";
  private static final String SMTP_FROM_NAME = "smtp.from.name";

  private final UserController userController;
  private final AppointmentController appointmentController;

  public DependencyContainer() {
    final AppProperties properties = new AppProperties();

    final Connection connection = buildDatabaseConnection(properties);
    final UserRepositoryMySQL userRepository = new UserRepositoryMySQL(connection);
    final AppointmentRepositoryMySQL appointmentRepository = new AppointmentRepositoryMySQL(connection);

    final JavaMailEmailSenderAdapter emailSender =
        new JavaMailEmailSenderAdapter(buildSmtpConfig(properties));
    final EmailNotificationService emailNotification = new EmailNotificationService(emailSender);

    // Construir Validator para las validaciones en la capa de aplicación
    final Validator validator = ValidatorProvider.buildValidator();

    //USER USE CASES

    final CreateUserUseCase createUserUseCase =
        new CreateUserService(userRepository, userRepository, emailNotification, validator);
    final UpdateUserUseCase updateUserUseCase =
        new UpdateUserService(userRepository, userRepository, userRepository, emailNotification, validator);
    final DeleteUserUseCase deleteUserUseCase =
        new DeleteUserService(userRepository, userRepository, validator);
    final GetUserByIdUseCase getUserByIdUseCase = new GetUserByIdService(userRepository, validator);
    final GetAllUsersUseCase getAllUsersUseCase = new GetAllUsersService(userRepository);
    final LoginUseCase loginUseCase = new LoginService(userRepository, validator);

    // APPOINTMENT USE CASES

    final CreateAppointmentUseCase createAppointmentUseCase =
            new CreateAppointmentService(appointmentRepository, validator);
    final UpdateAppointmentUseCase updateAppointmentUseCase =
            new UpdateAppointmentService(appointmentRepository, appointmentRepository, validator);
    final DeleteAppointmentUseCase deleteAppointmentUseCase =
            new DeleteAppointmentService(appointmentRepository, appointmentRepository, validator);
    final GetAppointmentByIdUseCase getAppointmentByIdUseCase =
            new GetAppointmentByIdService(appointmentRepository, validator);
    final GetAllAppointmentsUseCase getAllAppointmentsUseCase =
            new GetAllAppointmentsService(appointmentRepository);


    this.userController =
        new UserController(
            createUserUseCase,
            updateUserUseCase,
            deleteUserUseCase,
            getUserByIdUseCase,
            getAllUsersUseCase,
            loginUseCase);

    this.appointmentController =
            new AppointmentController(
                    createAppointmentUseCase,
                    updateAppointmentUseCase,
                    deleteAppointmentUseCase,
                    getAppointmentByIdUseCase,
                    getAllAppointmentsUseCase

            );



  }

  public UserController userController() {
    return userController;
  }
  public AppointmentController appointmentController() {
    return appointmentController;
  }

  private static Connection buildDatabaseConnection(final AppProperties properties) {
    final DatabaseConfig config =
        new DatabaseConfig(
            properties.get(DB_HOST),
            properties.getInt(DB_PORT),
            properties.get(DB_NAME),
            properties.get(DB_USER),
            properties.get(DB_PASSWORD));
    return DatabaseConnectionFactory.createConnection(config);
  }

  private static SmtpConfig buildSmtpConfig(final AppProperties properties) {
    return new SmtpConfig(
        properties.get(SMTP_HOST),
        properties.getInt(SMTP_PORT),
        properties.get(SMTP_USER),
        properties.get(SMTP_PASSWORD),
        properties.get(SMTP_FROM),
        properties.get(SMTP_FROM_NAME));
  }
}
