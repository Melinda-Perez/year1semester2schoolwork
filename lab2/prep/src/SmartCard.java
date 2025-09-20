public class SmartCard {
  private String owner;
  private Boolean status = false;

  public SmartCard(String name){
    owner = name;
  }

  public String getOwner(){
    return owner;
  }

  public Boolean isStaff(){
    return status;
  }

  public void setStaff(Boolean status1){
    status = status1;
  }
}
