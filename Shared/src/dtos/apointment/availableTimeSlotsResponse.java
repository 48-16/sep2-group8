package dtos.apointment;

import java.time.LocalDate;

public class availableTimeSlotsResponse
{
  private final LocalDate date;
  private final boolean[] availableSlots;

  public availableTimeSlotsResponse(LocalDate date, boolean[] availableSlots) {
    this.date = date;
    this.availableSlots = availableSlots;
  }

  public LocalDate getDate() { return date; }
  public boolean[] getAvailableSlots() { return availableSlots; }
}
