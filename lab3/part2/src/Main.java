public class Main {
  public static void main(String[] args){
    Animal wolf = new Wolf("wolf",1);
    Animal parrot = new Parrot("parrot",2);

    int age1 = wolf.getAge();
    String name1 = wolf.getName();

    int age2 = parrot.getAge();
    String name2 = parrot.getName();

    Food meat = new Meat();
    Food plant = new Plant();
  }
}
