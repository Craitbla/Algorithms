#include <iostream>
#include <unordered_set>
#include <vector>

using namespace std;

class Solution {
public:
  bool containsDuplicate(vector<int> &nums) {
    unordered_set<int> mySet;
    mySet.reserve(nums.size()); // это хорошо
    for (int i = 0; i < nums.size(); i++) {
      if (mySet.insert(nums[i]).second == false) { // это тоже хорошо
        return true; // это допустимо, но если нужна безопасность, то конечно
                     // один выход из функции
      }
    }
    return false;
  }
};

int main(int argc, char const *argv[]) {
  Solution sol;
  vector<int> vec = {1, 2, 3, 3, 4};
  cout << sol.containsDuplicate(vec);
  return 0;
}
