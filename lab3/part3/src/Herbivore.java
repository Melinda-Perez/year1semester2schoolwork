public abstract class Herbivore extends Animal{

  public Herbivore(String name, int age) {
    super(name, age);
  }

  public void eat(Food plant){
    if(plant instanceof Plant){
      System.out.println("The animal is eating " + plant);
    }else{
      throw new RuntimeException("Herbivore can only eat Plants.");
    }

  }
}
