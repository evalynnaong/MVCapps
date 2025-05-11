package smartbox.components;

import smartbox.*;

public class Stack extends Component implements IStack {

private java.util.Stack<Double> stack;

public Stack() {
    super();
    stack = new java.util.Stack<Double>();
}

    public void push(Double num) {
        stack.push(num);
    }

    public void pop() {
        stack.pop();
    }

    public Double top() {
        Double top = stack.peek();
        return top;
    }

    public void clear() {
        stack.removeAllElements();
    }

    public Boolean isEmpty() {
        return stack.isEmpty();
    }


}
