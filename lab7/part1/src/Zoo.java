public class Zoo {
  public static void main(String [] args){
    Counter counter = new Counter();
    int guests = 0;

    Thread[] gates = new Thread[3];
    int[] guestsAtGate = {100, 200, 300};

    for(int i = 0; i < gates.length; i++){
      guests = guestsAtGate[i];
      gates[i] = new Thread(new Gate(counter,guestsAtGate[i]));
      gates[i].start();
    }

    for(Thread gate: gates){
      try{
        gate.join();
      }catch (InterruptedException e){
        e.printStackTrace();
      }
    }

  }

}
