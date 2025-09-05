#include <iostream>
#include <unordered_set>

using namespace std;

class Solution {
public:
  int lengthOfLongestSubstring(string s) {
    if (s.length() <= 1) {
      return s.length();
    }
    std::unordered_set<char> charSet;
    int leftIndex = 0, rightIndex = 0;
    int maxLen = 0, tmpLen = 0;
    while (s[rightIndex] != '\0') {
      if (charSet.insert(s[rightIndex]).second == true) {
        tmpLen++;
      } else {
        while (s[leftIndex] != s[rightIndex]) {
          charSet.erase(s[leftIndex]);
          leftIndex++;
          tmpLen--;
        }
        leftIndex++;
      }
      if (maxLen < tmpLen) {
        maxLen = tmpLen;
      }
      rightIndex++;
    }
    return maxLen;
  }
};

int main() {
  Solution solution;

  std::cout << solution.lengthOfLongestSubstring("vabweacdagsdf") << "\n";

  std::cout << solution.lengthOfLongestSubstring("") << "\n";
  std::cout << solution.lengthOfLongestSubstring("a") << "\n";
  std::cout << solution.lengthOfLongestSubstring("ab") << "\n";
  std::cout << solution.lengthOfLongestSubstring("aa") << "\n";
  std::cout << solution.lengthOfLongestSubstring("abcabcbb") << "\n";
  std::cout << solution.lengthOfLongestSubstring("bbbbb") << "\n";
  std::cout << solution.lengthOfLongestSubstring("pwwkew") << "\n";

  return 0;
}