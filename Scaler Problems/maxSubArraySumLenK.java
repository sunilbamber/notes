/** 17-Mar-25
  Problem: Find max sub array sum of length k
**/

public class MyClass {
  public static void main(String args[]) {
    int k=2;
    int[] arr = {1,2,3,4,5};
    int max = maxSubArraySumLenK(arr,k);
    System.out.println(max);
  }
  
  static int maxSubArraySumLenK(int arr[], int k) {
      int max = Integer.MIN_VALUE;
      int sum=0;
      for(int i=0;i<k;i++) {
          sum += arr[i];
      }
      max = max<sum ? sum : max;
      int i=1, j=i+k-1;
      while(j<arr.length) {
          sum = sum - arr[i-1] +arr[j];
          max = max<sum ? sum : max;
          i++;j++;
      }
      return max;
  }
}
