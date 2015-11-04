import java.util.Arrays;

public class QuickAlgorithms {

  /**
   * Sorts the array ARR in ascending order.
   */
  public static void quicksort(int[] arr) {
    quicksort(arr, 0, arr.length - 1);
  }

  private static void quicksort(int[] arr, int start, int end) {
    if (start < end) {
      int index = partition(arr, pivotIndex(start, end), start, end);
      quicksort(arr, start, index-1);
      quicksort(arr, index+1, end);
    }
  }

  /**
   * Selects the K-th smallest element in the array ARR.
   * Assume 0 < K <= ARR.LENGTH.
   *
   * WARNING: Array will be altered after algorithm completes.
   */
  public static int quickselect(int[] arr, int k) {
    return quickselect(arr, k, 0, arr.length - 1);
  }

  private static int quickselect(int[] arr, int k, int start, int end) {
    int index = partition(arr, pivotIndex(start, end), start, end);
    if (k-1 == index) {
      return arr[index];
    } else if (k-1 < index) {
      return quickselect(arr, k, start, index-1);
    } else {
      return quickselect(arr, k, index+1, end);
    }
  }

  private static int partition(int[] arr, int pivInd, int start, int end) {
    int pivVal = arr[pivInd];
    arr[pivInd] = arr[end];
    arr[end] = pivVal;

    int left = start;
    int right = end-1;

    while (left < right) {
      while (arr[left] < pivVal) {
        left++;
      }
      while ((arr[right] > pivVal) && (right > start)) {
        right--;  
      }

      if (left < right) {
        int tmp = arr[left];
        arr[left] = arr[right];
        arr[right] = tmp;
        left++;
        right--;
      }
    }

    while (arr[left] < pivVal) {
      left++;
    }

    arr[end] = arr[left];
    arr[left] = pivVal;
    return left;
  }

  private static int pivotIndex(int start, int end) {
    return start + (int)((end - start + 1) * Math.random());
  }

  /**
   * Finds the median value of the array using Quickselect.
   */
  public static double medianSimple(int[] arr) {
    int mid = arr.length / 2;
    if (arr.length % 2 == 0) {
      return (quickselect(arr, mid, 0, arr.length - 1) + quickselect(arr, mid+1, 0, arr.length - 1)) / (double) 2;
    } else {
      return quickselect(arr, mid+1, 0, arr.length - 1);
    }
  }

  /**
   * Finds the median value of the array using Median of Medians.
   */
  public static int median(int[] arr) {
    return median(arr, arr.length/2 + 1, 0, arr.length - 1);
  }

  public static int median(int[] arr, int k, int start, int end) {
    int index = partition(arr, pivotIndexMedian(arr, start, end), start, end);
    if (k-1 == index) {
      return arr[index];
    } else if (k-1 < index) {
      return median(arr, k, start, index-1);
    } else {
      return median(arr, k, index+1, end);
    }
  }

  /**
   * Breaks array into groups of 5 and computes the median of each,
   * then recursive computes true median.
   */
  private static int pivotIndexMedian(int[] arr, int start, int end) {
    if (end - start < 5) {
      return partition(arr, pivotIndex(start, end), start, end);
    }
    int i = start;
    while (i <= end) {
      int groupEnd = i + 4;
      if (groupEnd > end) {
        groupEnd = end;
      }

      int median5Index = findMedian5Index(arr, start, end);
      
      int tmp = arr[median5Index];
      arr[median5Index] = arr[start + (i-start) / 5];
      arr[start + (i-start) / 5] = tmp;
      i = groupEnd + 1;
    }

    // (right - left) / 5 / 2 == (right - left) / 10
    return median(arr, start + (end - start) / 10 + 1, start, start + (int)Math.ceil((end - start) / 5) - 1);
  }

  private static int findMedian5Index(int[] arr, int start, int end) {
    for (int i = start + 1; i <= end ; i++) {
      for (int j = i; j > start && arr[j-1] > arr[j]; j--) {
        int tmp = arr[j-1];
        arr[j-1] = arr[j];
        arr[j] = tmp;
      }
    }
    return (start + end + 1) / 2;
  }

  public static void main(String[] args) {
    int[] nums1 = {5, 4, 2, 5, 3, 5, 1, 6, 8, 7, 9, 10, 5};
    System.out.println("3rd smallest element should be 3: " + Integer.toString(quickselect(nums1, 3)));
    quicksort(nums1);
    System.out.println(Arrays.toString(nums1));
    int[] nums2 = {5, 4, 2, 5, 3, 5, 1, 6, 8, 7, 10, 5};
    System.out.println("6rd smallest element should be 5: " + Integer.toString(quickselect(nums2, 6)));
    quicksort(nums2);
    System.out.println(Arrays.toString(nums2));

    int[] nums3 = {1, 5, 3, 6, 2};
    System.out.println("Median value should be 3.0: " + Double.toString(medianSimple(nums3)));
    System.out.println("Median from Median of Medians should be 3: " + Integer.toString(median(nums3)));

    int[] nums4 = {1, 5, 3, 6, 2, 8};
    System.out.println("Median value should be 4.0: " + Double.toString(medianSimple(nums3)));
    System.out.println("Median from Median of Medians should be 3 or 5: " + Integer.toString(median(nums4)));
  }
}
