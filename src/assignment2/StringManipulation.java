package assignment2;

/**
 * Class that implements a handful of String methods
 * @author rtp32
 */
public class StringManipulation {

    /**
     * converts a string of infix operators to postfix
     * @param infix an infix expression
     * @return the postfix equivalent to input expression
     */
    public static String toPostfix(String infix) {

        StringBuilder out = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < infix.length(); i++) {  // iterates through input string
            char token = infix.charAt(i);
            if (token >= '0' && token <= '9') {  // if it is a number...
                while (i < infix.length() && (infix.charAt(i) >= '0' && infix.charAt(i) <= '9')) {
                    out.append(infix.charAt(i));  // put it in the output string
                    i++;
                }
                out.append(' ');
            }
            else if (token == '+' || token == '-') {  // if it is a low-precedence operator
                while (stack.peek() != null &&
                        (stack.peek() == '+' || stack.peek() == '-' || stack.peek() == '*' || stack.peek() == '/')) {
                    out.append(stack.pop());
                    out.append(' ');
                }
                stack.push(token);
            } else if (token == '/' || token == '*') {  // high precedence operator
                while (stack.peek() != null && (stack.peek() == '*' || stack.peek() == '/')) {
                    out.append(stack.pop());
                    out.append(' ');
                }
                stack.push(token);
            } else if (token == '(')
                stack.push(token);
            else if (token == ')') {  // looks for beginning of parenthesis
                while (stack.peek() != null && stack.peek() != '(') {
                    out.append(stack.pop());
                    out.append(' ');
                }
                stack.pop();
            }
        }

        while (stack.peek() != null) {  // outputs rest of stack
            out.append(stack.pop());
            out.append(' ');
        }
        
        if (out.charAt(out.length() - 1) == ' ')  // deletes last space of string
            out.deleteCharAt(out.length() - 1);

        return out.toString();

    }


    /**
     * evaluates a postfix expression
     * @param postfix expression to be evaluated
     * @return the result of the expression
     */
    public static int result(String postfix) {
        Stack<Integer> stack = new Stack<>();
        StringBuilder temp = new StringBuilder();
        int total = 0;

        for (int i = 0; i < postfix.length(); i++) {  // iterates through input
            char token = postfix.charAt(i);
            if (token >= '0' && token <= '9') {  // if it is a numher...
                while (i < postfix.length() && (postfix.charAt(i)>= '0' && postfix.charAt(i) <= '9')) {
                    temp.append(postfix.charAt(i));  // make sure it gets the full number ex. 26, 2137
                    i++;
                }
                i--;
                stack.push(Integer.parseInt(temp.toString()));
                temp.setLength(0);
            } else {
                switch(token) {  // deals with operators
                    case '+':
                        stack.push(stack.pop() + stack.pop());
                        break;
                    case '-':
                        stack.push((-1 * stack.pop()) + stack.pop());  // subtraction had to be "backwards"
                        break;
                    case '*':
                        stack.push(stack.pop() * stack.pop());
                        break;
                    case '/':
                        stack.push(stack.pop() / stack.pop());
                        break;
                }
            }
        }

        if (stack.peek() != null)
            return stack.pop();  // returns last remaining value of stack

        return 0;  // default return statement

    }


    /**
     * returns the smallest number from a given string
     * @param number the string to be evaluated
     * @param n number of digits to be deleted
     * @return the smallest number after digits are deleted
     */
    public static String smallestNumber(String number, int n) {
        Stack<Character> stack = new Stack<>();
        int deletions = n;

        for (int i = 0; i < number.length(); i++) {  // assure front digit is smaller than the current digit
            while (stack.peek() != null && number.charAt(i) < stack.peek() && deletions > 0) {
                stack.pop();
                deletions--;
            }

            stack.push(number.charAt(i));
        }

        while (deletions > 0) {
            stack.pop();
            deletions--;
        }

        StringBuilder builder = new StringBuilder();
        while (stack.peek() != null)  // pop rest of stack
            builder.append(stack.pop());

        builder.reverse();

        while (builder.length() > 0 && builder.charAt(0) == '0')  // deletes leading zeroes
            builder.deleteCharAt(0);

        return builder.length() == 0 ? "0" : builder.toString();

    }


    /**
     * turns an input string into an "unbelievable string" as described in the assignment
     * @param s input string
     * @return "unbelievable string"
     */
    public static String unbelievableString(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {  // iterates through string
            if (stack.peek() != null && (stack.peek() == s.charAt(i) + ('A' - 'a') || stack.peek() == s.charAt(i) - ('A' - 'a'))) {
                stack.pop();  // pops if there are two back-to-back letters of different cases
            }
            else
                stack.push(s.charAt(i));  // pushes otherwise
        }

        StringBuilder builder = new StringBuilder();
        while (stack.peek() != null)
            builder.append(stack.pop());  // appends string to stack
        builder.reverse();

        return builder.toString();
    }


}
