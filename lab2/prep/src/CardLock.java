public class CardLock {
  private SmartCard lastCard;
  private Boolean isUnlocked = false;
  private Boolean access = false;

  public void swipeCard(SmartCard last){
    lastCard = last;
  }


  public SmartCard getLastCardSeen(){
    return lastCard;
  }

  public Boolean isUnlocked(){

    if(lastCard.isStaff() == true || access == true){
      isUnlocked = true;
    }
    return isUnlocked;
  }

  public Boolean toggleStudentAccess(){
    access = (!access);
    return access;
  }

}
