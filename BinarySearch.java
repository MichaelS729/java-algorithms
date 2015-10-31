class BinarySearch {
  public static int BinarySearch(int[] arr, int val, int left, int right) {
    int mid = (left + right) / 2;

    if (left > right) {
      return -1;
    }

    if (val < arr[mid]) {
      return BinarySearch(arr, val, left, mid-1);
    } else if (val > arr[mid]) {
      return BinarySearch(arr, val, mid+1, right);
    } else {
      return mid;
    }
  }

  public static void main(String[] args) {
    int[] nums = {1, 3, 5, 6, 7, 9, 11, 15};
    System.out.println(BinarySearch(nums, 11, 0, nums.length-1) == 6);
    System.out.println(BinarySearch(nums, 8, 0, nums.length-1) == -1);
  }
}
