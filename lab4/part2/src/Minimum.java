public class Minimum<T extends Comparable<T>> implements ArrayMin<T>{
  public T minimum(T[] array){
    if(array == null || array.length == 0){
      throw new IllegalArgumentException("Array must not be empty");
    }
    return minimumAux(0,array);
  }

  private T minimumAux(int index,T[] array){
    if(index == array.length-1){
      return array[index];
    }

    T minOfRest = minimumAux(index + 1,array);

    return (array[index].compareTo(minOfRest) < 0 ? array[index]:minOfRest);
  }
}
