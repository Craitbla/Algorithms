#include <algorithm>
#include <iostream>
#include <vector>

// Вставка элементов (insert)
// Сложность: Амортизированная O(1)
// В лучшем случае: O(1) (когда есть зарезервированная память)
// В худшем случае: O(n) (когда требуется перераспределение памяти и копирование
// всех элементов)

class Solution {
public:
  void printVec(std::vector<int> &nums) {
    for (int n : nums) {
      std::cout << n << " ";
    }
    std::cout << "\n";
  }

  // вар 1
  // 1) создание нового вектора с O(1)
  // 2) перемещение через move в начальный вектор, да, памяти в 2 раза больше
  // нужно, но зато move по идее тоже O(1)

  //   void rotate(std::vector<int> &nums, int k) {
  //     if (k == 0) {
  //       return;
  //     }
  //     k %= nums.size();
  //     std::vector<int> newNums;
  //     auto numsSeparatorIt = nums.begin() + (nums.size() - k);
  //     newNums.insert(newNums.end(), numsSeparatorIt, nums.end());
  //     newNums.insert(newNums.end(), nums.begin(), numsSeparatorIt);
  //     nums = std::move(newNums);
  //   }

  // вар 2
  // 1) reverse

  void rotate(std::vector<int> &nums, int k) {
    if (k == 0) {
      return;
    }
    k %= nums.size();
    auto numsSeparatorIt = nums.begin() + k;
    std::reverse(nums.begin(), nums.end());
    std::reverse(nums.begin(), numsSeparatorIt);
    std::reverse(numsSeparatorIt, nums.end());
  }
};

int main(int argc, char const *argv[]) {
  Solution solution;
  std::vector<int> nums = {0, 1, 2, 3, 4, 5};
  solution.rotate(nums, 2);
  solution.printVec(nums);
  return 0;
}
