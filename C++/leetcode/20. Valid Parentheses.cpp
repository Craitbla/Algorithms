// 20. Допустимые скобки

#include <iostream>
#include <stack>
using namespace std;

class Solution {
public:
  bool isValid(string s) {
    std::stack<char> br_stack;
    bool flag = true;
    char c = '\0';
    char last_c = '\0';
    for (int i = 0; i < s.size(); i++) {
      c = s[i];
      if (c == '(' || c == '{' || c == '[') {
        br_stack.push(c);
      } else if (c == ')' || c == '}' || c == ']') {
        if (br_stack.empty()) {
          flag = false;
          break;
        } else {
          last_c = br_stack.top();
          if ((c == ')' && last_c == '(') || (c == '}' && last_c == '{') ||
              (c == ']' && last_c == '[')) {
            br_stack.pop();
          } else {
            flag = false;
            break;
          }
        }
      }
    }
    if (!br_stack.empty()) {
      flag = false;
    }
    return flag;
  }
};

int main(int argc, char const *argv[]) {
  Solution a;
  std::cout << a.isValid("([]{()})");
  return 0;
}