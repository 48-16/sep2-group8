import java.time.LocalDateTime;

public class AppointmentDate
{
  private LocalDateTime localDateTime;

  public AppointmentDate(int year, int month, int day, int hour, int minute) {
    this.localDateTime = LocalDateTime.of(year,month,day,hour,minute);
  }
}
