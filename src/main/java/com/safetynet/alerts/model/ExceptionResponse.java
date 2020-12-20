package com.safetynet.alerts.model;

import java.util.Date;


public class ExceptionResponse {
  private Date timestamp;
  private int status;
  private String error;
  private String message;
  private String path;

  /**
   * Custom wrapper for custom exceptions, and custom handlers.
   *
   * @param ptimestamp Date
   * @param pstatus int
   * @param perror String
   * @param pmessage String
   * @param ppath String
   */
  public ExceptionResponse(Date ptimestamp, int pstatus, String perror, String pmessage,
                           String ppath) {
    timestamp = ptimestamp;
    message = pmessage;
    status = pstatus;
    error = perror;
    path = ppath;
  }

  public Date getTimestamp() {
    return  timestamp;
  }

  public void setTimestamp(Date ptimestamp) {
    timestamp = ptimestamp;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public String getError() {
    return error;
  }

  public void setError(String error) {
    this.error = error;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  @Override
  public String toString() {
    return "ExceptionResponse{" +
            "timestamp=" + timestamp +
            ", status=" + status +
            ", error='" + error + '\'' +
            ", message='" + message + '\'' +
            ", path='" + path + '\'' +
            '}';
  }
}
