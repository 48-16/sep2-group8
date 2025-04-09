import java.util.ArrayList;

public class AppointmentList
{
  private ArrayList<Appointment> appointments;

  public AppointmentList() {
    appointments = new ArrayList<>();
  }

  public void addAppointment(Appointment appointment) {
    appointments.add(appointment);
  }

  public void cancelAppointment(Appointment appointment) {
    appointments.remove(appointment);
  }


}
