#include <cmath>
#include <iostream>
#include <numeric>
#include <vector>

using namespace std;

// если вектор, то удаление дорогое, лучше делать замену
// i в for это удобный счетчик, который можно использовать вместо
// последовательности целых чисел, напрример здесь заменяется [0,1,2,3,4,5] на
// [0,0,0,0,0,0] и работа с числами возможна через i

class Solution {
public:
  int countPrimes(int n) {
    if (n <= 2) {
      return 0;
    }
    std::vector<bool> vec(n);
    int endNum = std::round(std::sqrt(n));
    for (int i = 2; i <= endNum; i++) { // умножаемое
      for (int j = 2; j <= n / i; j++) {
        if (i * j < vec.size()) {
          vec[i * j] = true;
        }
      }
      // std::cout << "пром результат: ";
      // for (bool o : vec) {
      //   std::cout << o << " ";
      // }
      // std::cout << "\n";
    }

    return n - std::accumulate(vec.begin(), vec.end(), 0) - 2;
  }
};
int main() {
  Solution solution;

  std::cout << solution.countPrimes(14);

  return 0;
}