package com.jcaa.usersmanagement.infrastructure.adapter.persistence.exception;

public final class PersistenceException extends RuntimeException {

  private static final String MESSAGE_SAVE = "Failed to save %s with ID: '%s'.";
  private static final String MESSAGE_UPDATE = "Failed to update %s with ID: '%s'.";
  private static final String MESSAGE_FIND = "Failed to find %s with ID: '%s'.";
  private static final String MESSAGE_FIND_FIELD = "Failed to find %s with %s: '%s'.";
  private static final String MESSAGE_ALL = "Failed to retrieve all %s.";
  private static final String MESSAGE_DELETE = "Failed to delete %s with ID: '%s'.";
  private static final String MESSAGE_CONNECTION = "Could not establish database connection.";

  private PersistenceException(final String message, final Throwable cause) {
    super(message, cause);
  }

  public static PersistenceException becauseSaveFailed(final String entity, final String id, final Throwable cause) {
    return new PersistenceException(String.format(MESSAGE_SAVE, entity, id), cause);
  }

  public static PersistenceException becauseUpdateFailed(
      final String entity, final String id, final Throwable cause) {
    return new PersistenceException(String.format(MESSAGE_UPDATE, entity, id), cause);
  }

  public static PersistenceException becauseFindByIdFailed(
      final String entity, final String id, final Throwable cause) {
    return new PersistenceException(String.format(MESSAGE_FIND, entity, id), cause);
  }

  public static PersistenceException becauseFindByFieldFailed(
      final String entity, final String field, final String value, final Throwable cause) {
    return new PersistenceException(String.format(MESSAGE_FIND_FIELD, entity, field, value), cause);
  }

  public static PersistenceException becauseFindAllFailed(final String entity, final Throwable cause) {
    return new PersistenceException(String.format(MESSAGE_ALL, entity), cause);
  }

  public static PersistenceException becauseDeleteFailed(
      final String entity, final String id, final Throwable cause) {
    return new PersistenceException(String.format(MESSAGE_DELETE, entity, id), cause);
  }

  public static PersistenceException becauseConnectionFailed(final Throwable cause) {
    return new PersistenceException(MESSAGE_CONNECTION, cause);
  }
}
