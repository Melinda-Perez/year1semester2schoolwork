import java.util.ArrayList;
import java.util.Iterator;

public class UserGroup {
  ArrayList<User> users;
  public UserGroup(){
    users = new ArrayList<>();
  }

  public ArrayList<User> getUsers(){
    return users;
  }

  public void addSampleData(){
    User user1 = new User("user1","user","a");
    User user2 = new User("user2","editor","b");
    User user3 = new User("user3","admin","c");
    User user4 = new User("user4","user","d");
    User user5 = new User("user5","editor","e");
    User user6 = new User("user6","admin","f");
    User user7 = new User("user7","user","g");
    User user8 = new User("user8","editor","h");
    User user9 = new User("user9","admin","i");
    User user10 = new User("user10","user","j");

    users.add(user1);
    users.add(user2);
    users.add(user3);
    users.add(user4);
    users.add(user5);
    users.add(user6);
    users.add(user7);
    users.add(user8);
    users.add(user9);
    users.add(user10);
  }

  public User getUser(int index){
    return users.get(index);
  }

  public void printUsernames(){
    for(User user: users){
      String username = user.getUsername();
      String userType = user.getUserType();
      System.out.println(username + " " + userType);
    }
  }

  public void removeFirstUser(){
    users.remove(0);
  }

  public void removeLastUser(){
    users.remove(users.size()-1);
  }

  public void removeUser(String username){
    Iterator<User> iterator = users.iterator();
    while(iterator.hasNext()){
      User user = iterator.next();
      if(user.getUsername() == username){
        iterator.remove();
      }
    }
  }

  public Iterator<User> getUserIterator(){
    return users.iterator();
  }
}
