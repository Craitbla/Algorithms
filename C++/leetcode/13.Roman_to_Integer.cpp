class Solution {
public:
  int singleRomanSwitchCase(char c) {
    switch (c) {
    case 'I':
      return 1;
    case 'V':
      return 5;
    case 'X':
      return 10;
    case 'L':
      return 50;
    case 'C':
      return 100;
    case 'D':
      return 500;
    case 'M':
      return 1000;
    }
    return 0;
  };

  int doubleRomanSwitchCase(char cur, char next) { // 0 если ничего из этого
    int num = 0;
    if (cur == 'I') {
      if (next == 'V')
        num = 4;
      if (next == 'X')
        num = 9;
    } else if (cur == 'X') {
      if (next == 'L')
        num = 40;
      if (next == 'C')
        num = 90;
    } else if (cur == 'C') {
      if (next == 'D')
        num = 400;
      if (next == 'M')
        num = 900;
    }
    return num;
  };

  int romanToInt(string s) {
    int res = 0, temp = 0;
    if (s.size() == 1) {
      return singleRomanSwitchCase(s[0]);
    }

    char cur, next;
    for (int i = 0; i < s.size(); i++) {
      cur = s[i];
      next = s[i + 1];
      temp = doubleRomanSwitchCase(cur, next);
      if (temp) {
        res += temp;
        i++;
      } else {
        res += singleRomanSwitchCase(cur);
      }
    }
    if (next!=s[s.size() - 1] && next!='\0')
      res += singleRomanSwitchCase(s[s.size() - 1]);

    return res;
  };
};