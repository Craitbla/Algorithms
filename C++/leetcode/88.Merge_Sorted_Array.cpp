#include <iostream>
#include <vector>

using namespace std;

// движение с конца для удобства работы в одном массиве
// три указателя

class Solution {
public:
  void swapIterators(std::vector<int>::iterator &it1,
                     std::vector<int>::iterator &it2,
                     std::vector<int>::iterator &it_res) {
    if (*it1 > *it2) {
      *it_res = *it1;
      it1--;
    } else {
      *it_res = *it2;
      it2--;
    }
    it_res--;
  }

  void merge(vector<int> &nums1, int m, vector<int> &nums2, int n) {
    if (n == 0)
      return;
    if (m == 0) {
      std::copy(nums2.begin(), nums2.end(), nums1.begin());
      return;
    }
    auto it1 = nums1.begin(); // обычно делают через 3 счетчика, а не итератора,
                              // считается безопаснее
    it1 = it1 + (m - 1);
    auto it2 = --nums2.end();
    auto it_res = --nums1.end();

    while (it1 != --nums1.begin() && it2 != --nums2.begin()) {
      swapIterators(it1, it2, it_res);
    }
    if (it2 != --nums2.begin()) {
      std::move(nums2.begin(), ++it2, nums1.begin());
    }
  }
};

int main() {
  Solution solution;
  vector<int> nums1 = {1, 2, 3, 0, 0, 0};
  vector<int> nums2 = {2, 5, 6};

  solution.merge(nums1, 3, nums2, 3);

  std::cout << "Результат: ";
  for (auto it = nums1.begin(); it != nums1.end(); ++it) {
    std::cout << *it << " ";
  }

  return 0;
}