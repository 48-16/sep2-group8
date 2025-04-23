package ui.register;

import dtos.auth.RegisterUserRequest;
import javafx.beans.Observable;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import networking.authentication.authenticationClient;
import utils.ErrorPopUp;
import utils.FieldChecker;

public class signUpVIewModel
{
  private final StringProperty firstNameProp = new SimpleStringProperty();
  private final StringProperty lastNameProp = new SimpleStringProperty();
  private final StringProperty emailProp = new SimpleStringProperty();
  private final StringProperty passwordProp = new SimpleStringProperty();

  private final BooleanProperty disableSignUp = new SimpleBooleanProperty(true);
  private final authenticationClient authService;
  private ErrorPopUp message = new ErrorPopUp();

  public signUpVIewModel(authenticationClient authService)
  {
    this.authService = authService;
    firstNameProp.addListener(this::updateSignUpButtonState);
    lastNameProp.addListener(this::updateSignUpButtonState);
    emailProp.addListener(this::updateSignUpButtonState);
    passwordProp.addListener(this::updateSignUpButtonState);
  }
  public void registerUser()
  {

    if (firstNameProp.get() == null || firstNameProp.get().isEmpty())
    {
      message.show("error", "First name cannot be empty");
      return;
    }
    if (lastNameProp.get() == null || lastNameProp.get().isEmpty())
    {
      message.show("error", "Last name cannot be empty");
      return;
    }
    if (emailProp.get() == null || emailProp.get().isEmpty())
    {
      message.show("error", "Email cannot be empty");
      return;
    }
    if (passwordProp.get() == null || passwordProp.get().isEmpty())
    {
      message.show("error", "Password cannot be empty");
      return;
    }

    try
    {
      authService.register(
          new RegisterUserRequest(emailProp.get(), passwordProp.get(),
              firstNameProp.get(), lastNameProp.get()));
      message.show("Success", "Success");
      clearFields();

    }
    catch (Exception e)
    {
      message.show(e);
    }
  }

  private void clearFields()
  {
    emailProp.set("");
    passwordProp.set("");
    firstNameProp.set("");
    lastNameProp.set("");
  }



  public void updateSignUpButtonState(Observable observable)
  {
    boolean shouldDisable = FieldChecker.isNullOrEmpty(emailProp.get())
        || FieldChecker.isNullOrEmpty(passwordProp.get())
        || FieldChecker.isNullOrEmpty(firstNameProp.get())
        || FieldChecker.isNullOrEmpty(lastNameProp.get());

    disableSignUp.set(shouldDisable);
  }

  public StringProperty firstNameProp()
  {
    return firstNameProp;
  }

  public StringProperty lastNameProp()
  {
    return lastNameProp;
  }

  public StringProperty emailProp()
  {
    return emailProp;
  }

  public StringProperty passwordProp()
  {
    return passwordProp;
  }
  public BooleanProperty disableSignUp(){
    return disableSignUp;
  }
}
