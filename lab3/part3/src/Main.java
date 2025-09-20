public class Main {
  public static void main(String[] args){
    Animal wolf = new Wolf("wolf",1);
    Animal parrot = new Parrot("parrot",2);
    Animal cow = new Cow("cow",3);

    int age1 = wolf.getAge();
    String name1 = wolf.getName();

    int age2 = parrot.getAge();
    String name2 = parrot.getName();

    Food meat = new Meat("meat");
    Food plant = new Plant("plant");

    wolf.makeNoise();
    parrot.makeNoise();

    wolf.eat(meat);
    parrot.eat(meat);
    cow.eat(plant);
  }
}
