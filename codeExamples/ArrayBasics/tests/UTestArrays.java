import org.junit.Test;

public class UTestArrays {

  @Test
  public void test_dynArray_01() {
    int m = 4;
    int a[] = new int[m];

    for (int i = 0; i < a.length; i++) {
      a[i] = i * i;
    }

    for (int i = 0; i < a.length; i++) {
      System.out.printf("a[%d]=%d\n", i, a[i]);
    }
  }

  @Test
  public void test_dynArray_02() {
    int m = 4;
    int a[] = getArray(m);

    for (int i = 0; i < a.length; i++) {
      a[i] = i * i;
    }

    for (int i = 0; i < a.length; i++) {
      System.out.printf("a[%d]=%d\n", i, a[i]);
    }
  }

  @Test
  public void test_multiDimArray_01() {
    // intMatrix[3][2] = new int[][]{{1,2},{3,4}};
    int intMatrix[][] = new int[][] { { 1, 2 }, { 3, 4, 5 }, { 6, 7 } };

    for (int i = 0; i < intMatrix.length; i++) {
      System.out.printf("Zeile %d:\n", i);
      for (int j = 0; j < intMatrix[i].length; j++) {
        System.out.printf("intMatrix[%d][%d]=%d\n", i, j, intMatrix[i][j]);
      }
    }
  }

  private int[] getArray(int n) {
    return new int[n];
  }

}
