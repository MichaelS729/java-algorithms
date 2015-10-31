import java.util.Arrays;

class MergeSort {
  public static int[] merge(int[] a, int[] b) {
    int i = 0;
    int j = 0;
    int k = 0;
    int[] sorted = new int[a.length + b.length];
    while ((i < a.length) && (j < b.length)) {
      if (a[i] <= b[j]) {
        sorted[k] = a[i];
        i++;
      } else {
        sorted[k] = b[j];
        j++;
      }
      k++;
    }
    while (i < a.length) {
      sorted[k] = a[i];
      i++;
      k++;
    }
    while (j < b.length) {
      sorted[k] = b[j];
      j++;
      k++;
    }
    return sorted;
  }

  public static int[] mergesort(int[] arr) {
    if (arr.length == 1) {
      int[] new_arr = {arr[0]};
      return new_arr;
    }
    int mid = arr.length / 2;
    int i = 0;
    int[] left = new int[mid];
    int[] right = new int[arr.length - mid];

    int j = 0;
    while (i < mid) {
      left[j] = arr[i];
      i++;
      j++;
    }

    j = 0;
    while (i < arr.length) {
      right[j] = arr[i];
      i++;
      j++;
    }

    return merge(mergesort(left), mergesort(right));
  }

  public static void main(String[] args) {
    int[] nums1 = {5, 4, 2, 3, 1, 6, 8, 7, 9, 10};
    System.out.println(Arrays.toString(mergesort(nums1)));
    int[] nums2 = {5, 4, 2, 3, 1, 6, 8, 7, 10};
    System.out.println(Arrays.toString(mergesort(nums2)));
  }
}
