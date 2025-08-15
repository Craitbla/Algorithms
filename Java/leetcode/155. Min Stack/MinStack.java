//в Java нельзя использовать примитивные типы (такие как int, double, char и т. д.) 
//в качестве параметров обобщённых типов (ArrayList, HashSet, HashMap и др.).

//iterator.next()	Возвращает текущий элемент и при этом еще и переходит к следующему

//Всегда используйте .equals() для сравнения объектов (кроме примитивов). Это для сравнения значений
//Если работаете с примитивами (int, long), == безопасен.

//!!!не строить логику на сравнении ссылок, потому что здесь кеширование есть, по итогу надо работать именно с значениями

import java.util.Stack;
import java.lang.Integer;

class MinStack {
    private Stack<Integer> generalStack = new Stack<>();
    private Stack<Integer> minStack = new Stack<>();

    public static void main(String[] args) {

        MinStack obj = new MinStack();
        int param_1, param_2, param_3;
        obj.push(0);
        obj.push(1);
        obj.push(0);
        param_1 = obj.getMin();
        obj.pop();
        param_2 = obj.getMin();
        obj.pop();
        param_3 = obj.getMin();
        System.out.println(param_1 + " " + param_2 + " " + param_3);
    }

    public void push(int val) {
        generalStack.push(val);
        if (minStack.empty() || val <= minStack.peek()) { // =
            minStack.push(val);
        }
    }

    public void pop() {
        if (generalStack.peek().equals(minStack.peek())) { // eq
            minStack.pop();
        }
        generalStack.pop();
    }

    public int top() {
        return generalStack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}

// import java.util.ArrayList;
// import java.util.Iterator;
// import java.lang.Integer;

// class MinStack { //реализовала через лист, не поняла задание

// public ArrayList<Integer> stack = new ArrayList<>();

// public void push(int val) {
// stack.add(0, val);
// }

// public void pop() {
// stack.remove(0);
// }

// public int top() {
// return stack.get(0);
// }

// public int getMin() {
// int minNum = top();
// int cur = 0;
// Iterator<Integer> it = stack.iterator();
// while (it.hasNext()) {
// cur = it.next();
// if (minNum > cur) {
// minNum = cur;
// }
// }
// return minNum;
// }
// }
