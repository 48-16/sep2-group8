import java.util.Date;

public class Appointment
{
  private String id;
  private Client client; //ig we gonna call it like this
  private Admin admin; // baber who gonna take an appointment
  private AppointmentDate appointmentDate;

  public Appointment(String id, Client client, Admin admin, AppointmentDate appointmentDate) {
    this.id = id;
    this.client = client;
    this.admin = admin;
    this.appointmentDate = appointmentDate;
  }

  public String toString() {
    return "Appointment id" + id + "\n"
        + "Client: " + client + "\n" // for barber and client maybe I should use add name method
        + "Barber: " + admin//idk??
        + "\n"
        + "Date: " + appointmentDate + "\n";
  }


}
