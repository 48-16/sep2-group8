package dtos.user;

public class UserDataDto
{
  private static UserDataDto currentUser;

  public boolean admin(){
    return true; //check if user admin
  }
  public UserDataDto currentUser(){
    return currentUser;
  }
  public String id()
  {
    return null;//getter for user id;
  }
}
