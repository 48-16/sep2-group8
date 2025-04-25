package dtos.apointment;

import java.time.LocalDate;

public class bookAppointmentRequest
{
  private final String userId;
  private final LocalDate date;
  private final String timeSlot;

  public bookAppointmentRequest(String userId, LocalDate date, String timeSlot) {
    this.userId = userId;
    this.date = date;
    this.timeSlot = timeSlot;
  }
  
  public String getUserId() { return userId; }
  public LocalDate getDate() { return date; }
  public String getTimeSlot() { return timeSlot; }
}
