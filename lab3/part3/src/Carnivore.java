public abstract class Carnivore extends Animal{

  public Carnivore(String name, int age) {
    super(name, age);
  }

  public void eat(Food meat){
    if(meat instanceof Meat) {
      System.out.println("The animal is eating " + meat);
    }else{
      throw new RuntimeException("Carnivore can only eat Meat.");
    }
  }
}
