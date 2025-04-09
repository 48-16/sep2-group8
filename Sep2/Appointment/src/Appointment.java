import java.util.Date;

public class Appointment
{
  private String id;
  private Client client; //ig we gonna call it like this
  private Admin admin; // baber who gonna take an appointment
  private AppointmentDate appointmentDate;
  private double price;// should we do price for appointment or on place?

  public Appointment(String id, Client client, Admin admin, AppointmentDate appointmentDate, double price) {
    this.id = id;
    this.client = client;
    this.admin = admin;
    this.appointmentDate = appointmentDate;
    this.price = price;
  }

  public String toString() {
    return "Appointment id" + id + "\n"
            + "Client: " + client + "\n" // for barber and client maybe I should use add name method
            + "Barber: " + admin//idk??
            + "\n"
            + "Date: " + appointmentDate + "\n"
            +"Price: " + price + "\n";
  }


}
