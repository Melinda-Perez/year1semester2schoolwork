import java.util.Iterator;

public class Main {
  public static void main(String[]args){
    UserGroup userGroup = new UserGroup();
    userGroup.printUsernames();

    UserGroup administrators = new UserGroup();
    Iterator<User> iterator = administrators.getUserIterator();
    while(iterator.hasNext()){
      if(iterator.next().getUserType() == "admin"){
        administrators.users.add(iterator.next());
      }
    }
    administrators.printUsernames();
    administrators.users.get(administrators.users.size()-1).setUserType("user");

  }
}
