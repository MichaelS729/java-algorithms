import java.util.Arrays;

class MergeSort {

  public static void mergesort(int[] arr) {
    mergesort(arr, 0, arr.length - 1);
  }

  private static void mergesort(int[] arr, int start, int end) {
    if (start < end) {
      int mid = (start + end) / 2;
      mergesort(arr, start, mid);
      mergesort(arr, mid + 1, end);
      merge(arr, start, mid, end);
    }
  }

  private static void merge(int[] arr, int start, int mid, int end) {
    int[] helper = new int[arr.length];

    for (int i = start; i <= end; i++) {
      helper[i] = arr[i];
    }

    int index1 = start;
    int index2 = mid + 1;
    int curr = start;
    while ((index1 <= mid) && (index2 <= end)) {
      if (helper[index1] <= helper[index2]) {
        arr[curr] = helper[index1];
        index1++;
      } else {
        arr[curr] = helper[index2];
        index2++;
      }
      curr++;
    }

    while (index1 <= mid) {
      arr[curr] = helper[index1];
      index1++;
      curr++;
    }
  }

  public static void main(String[] args) {
    int[] nums1 = {5, 4, 2, 3, 1, 6, 8, 7, 9, 10};
    mergesort(nums1);
    System.out.println(Arrays.toString(nums1));
    int[] nums2 = {5, 4, 2, 3, 1, 6, 8, 7, 10};
    mergesort(nums2);
    System.out.println(Arrays.toString(nums2));
    int[] nums3 = {10};
    mergesort(nums3);
    System.out.println(Arrays.toString(nums3));
    int[] empty = new int[0];
    mergesort(empty);
    System.out.println(Arrays.toString(empty));
  }
}
