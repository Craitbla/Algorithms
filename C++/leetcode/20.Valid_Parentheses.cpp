// 20. Допустимые скобки

#include <iostream>
#include <map>
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

// с использованем map

class Solution {
public:
  bool isValid(string s) {
    std::map<char, char> br_map;
    br_map['('] = ')';
    br_map['{'] = '}';
    br_map['['] = ']';
    std::stack<char> br_stack;
    for (int i = 0; i < s.size(); i++) {
      if (br_map.find(s[i]) != br_map.end()) {
        br_stack.push(s[i]);
      } else {
        if (br_stack.empty()) {
          br_stack.push(s[i]);
          break;
        } else {
          if (br_map[br_stack.top()] == s[i]) {
            br_stack.pop();
          } 
          else {
            break;
          }
        }
      }
    }
    return br_stack.empty();
  }
};

int main(int argc, char const *argv[]) {
  Solution a;
  std::cout << a.isValid("(){[]}");
  return 0;
}