// Sum a list of integers
// Purpose: show that java syntax and C syntax are very similar.
//
// Build:
//   javac SumList.java
//
// Usage:
//   java SumList
//

class SumList {
  public static void main(String args[]) {

    int list[] = new int[] { 1, 2, 3, 4, 5, 6 };

    int i;
    int sum = 0;
    for (i = 0; i < 6; i++) {
      sum += list[i];
    }

    System.out.printf("Summe ist %d\n", sum);
  }
}
