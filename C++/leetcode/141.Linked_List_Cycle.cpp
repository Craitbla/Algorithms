#include <array>
#include <iostream>
#include <vector>
using namespace std;

// Медленный и быстрый указатели для проверки на цикл

struct ListNode {
  int val;
  ListNode *next;
  ListNode(int x) : val(x), next(NULL) {}
};

// первый
class Solution {
public:
  bool hasCycle(ListNode *head) {
    bool flag = false;
    if (head != nullptr) {
      if (head->next != nullptr) {

        ListNode *cur_ptr = head;
        vector<ListNode *> list;

        while (cur_ptr->next != nullptr && flag != true) {
          list.push_back(cur_ptr);
          for (int i = 0; i < list.size() - 1; i++) {
            if (cur_ptr->next == list[i]) {
              flag = true;
            }
          }
          cur_ptr = cur_ptr->next;
        }
      }
    }
    return flag;
  }
};

int main(int argc, char const *argv[]) {
  ListNode a(1);
  ListNode b(2);
  ListNode c(1);
  ListNode d(2);

  a.next = &b;
  b.next = &c;
  c.next = &d;
  d.next = &b;

  Solution sol;
  std::cout << sol.hasCycle(&a);
  return 0;
}