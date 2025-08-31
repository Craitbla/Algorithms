#include <cmath>
#include <iostream>
#include <numeric>
#include <vector>

// без переноса+перенос, сумматор, конкатенация не нужна ////////////////

// Сдвиг отрицательных чисел влево (<<) в C++ является неопределенным
// поведением. Преобразование в unsigned гарантирует корректный сдвиг, так как:

// unsigned использует модульную арифметику.

// Сдвиг влево для unsigned определен четко (биты сдвигаются, а выходящие за
// разрядную сетку отбрасываются).
using namespace std;

class Solution {
public:
  int getSum(int a, int b) {
    int tmp;
    while (b != 0) {
      tmp = (unsigned)(a & b) << 1;
      a = a ^ b;
      b = tmp;
    }
    return a;
  }
};
int main() {
  Solution solution;

  std::cout << solution.getSum(2, 5) << "\n";
  std::cout << solution.getSum(-2, 5) << "\n";
  std::cout << solution.getSum(-2, -5) << "\n";
  std::cout << solution.getSum(0, 5) << "\n";
  std::cout << solution.getSum(-5, 0) << "\n";
  std::cout << solution.getSum(100000, 5) << "\n";

  // должно быть

  //  7
  //  3
  //  -7
  //  5
  // -5
  //  100005

  return 0;
}