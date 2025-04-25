package dtos.apointment;

import java.time.LocalDate;

public class appointmentDto
{
  private final String userId;
  private final LocalDate date;
  private final String timeSlot;
  private final String status;

  public appointmentDto(String userId, LocalDate date, String timeSlot, String status) {
    this.userId = userId;
    this.date = date;
    this.timeSlot = timeSlot;
    this.status = status;
  }

  public String getUserId() { return userId; }
  public LocalDate getDate() { return date; }
  public String getTimeSlot() { return timeSlot; }
  public String getStatus() { return status; }
}

