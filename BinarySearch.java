class BinarySearch {
  public static int binarySearch(int[] arr, int val, int left, int right) {
    int mid = (left + right) / 2;

    if (left > right) {
      return -1;
    }

    if (val < arr[mid]) {
      return binarySearch(arr, val, left, mid-1);
    } else if (val > arr[mid]) {
      return binarySearch(arr, val, mid+1, right);
    } else {
      return mid;
    }
  }

  public static int binarySearchIter(int[] arr, int val, int left, int right) {
    while (left <= right) {
      int mid = (left + right) / 2;
      if (val < arr[mid]) {
        right = mid-1;
      } else if (val > arr[mid]) {
        left = mid+1;
      } else {
        return mid;
      }
    }
    return -1;
  }

  public static void main(String[] args) {
    int[] nums = {1, 3, 5, 6, 7, 9, 11, 15};
    System.out.println(binarySearch(nums, 11, 0, nums.length-1) == 6);
    System.out.println(binarySearchIter(nums, 11, 0, nums.length-1) == 6);
    System.out.println(binarySearch(nums, 8, 0, nums.length-1) == -1);
    System.out.println(binarySearchIter(nums, 8, 0, nums.length-1) == -1);
  }
}
