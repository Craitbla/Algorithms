#include <iostream>
#include <unordered_map>

using namespace std;

// круто, класс, свое, но не понятное и даже не совсем рабочее

class Solution { // ощущение что ну прям вайб код - вайб код, не верю что это
                 // самое правильное решение, мудрено и хешмапы нет
public:
  void printMap(unordered_map<char, unsigned int> &myMap) {
    for (std::pair<char, unsigned int> p : myMap)
      std::cout << p.first << " " << p.second << "\n";
  }
  int lengthOfLongestSubstring(string s) {
    if (s.length() <= 1) {
      return s.length();
    }
    unsigned int counterMax = 0;
    std::unordered_map<char, unsigned int> indexesMap;
    int curIndex = 0;
    char checkingChar = '\0';
    while (s[curIndex] != '\0') {
      // for (size_t i = 0; i < 5; i++) {

      std::cout << "до \n";
      printMap(indexesMap);
      std::cout << "\n\n\n";

      for (size_t i = curIndex; i < s.length(); i++) {
        checkingChar = s[i];
        if (indexesMap.count(checkingChar)) { // найден
          for (size_t j = curIndex; j < indexesMap[checkingChar]; j++) {
            indexesMap.erase(s[j]);
            std::cout << "уд!!!!!!!!!!!!!" << s[j] << "\n";
          }
          indexesMap[checkingChar] = i;

          if (i - curIndex > counterMax) {
            counterMax = i - curIndex;
          }
          curIndex = i + 1;
          break;
        } else { // не найден
          indexesMap[s[i]] = i;
        }
        std::cout << "во время " << i << "\n";
        printMap(indexesMap);
        std::cout << "\n\n\n";
      }
    }
    if (indexesMap.size() > counterMax) {
      counterMax = indexesMap.size();
    }
    return counterMax;
  }
};

int main() {
  Solution solution;

  std::cout << solution.lengthOfLongestSubstring("vabweacdagsdf") << "\n";

  // std::unordered_map<char, unsigned int> indexesMap;
  // indexesMap['a'] = 0;
  // indexesMap.erase('d');
  // solution.printMap(indexesMap);

  // std::cout << solution.lengthOfLongestSubstring("") << "\n";
  // std::cout << solution.lengthOfLongestSubstring("a") << "\n";
  // std::cout << solution.lengthOfLongestSubstring("ab") << "\n";
  // std::cout << solution.lengthOfLongestSubstring("aa") << "\n";
  // std::cout << solution.lengthOfLongestSubstring("abcabcbb") << "\n";
  // std::cout << solution.lengthOfLongestSubstring("bbbbb") << "\n";
  // std::cout << solution.lengthOfLongestSubstring("pwwkew") << "\n";

  return 0;
}