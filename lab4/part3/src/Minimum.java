public class Minimum<T extends Comparable<T>> implements TreeMin<T>{

  @Override
  public T minimum(Node<T> node) {
    if(node == null){
      throw new IllegalArgumentException("Node must not be null");
    }

    if(node instanceof Leaf<T>){
      return(((Leaf<T>) node).getValue());
    }

    if(node instanceof Branch){
      Branch<T> branch = (Branch<T>) node;
      T leftMin = minimum(branch.getLeft());
      T rightMin = minimum(branch.getRight());

      return (leftMin.compareTo(rightMin) < 0 ? leftMin:rightMin);
    }

    throw new IllegalArgumentException("Node must be either a leaf or a branch.");
  }
}
