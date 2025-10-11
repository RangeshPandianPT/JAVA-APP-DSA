import java.util.*;

public class InfixToPostfixSimple {

    static int precedence(char ch) {
        switch (ch) {
            case '^': return 3;
            case '*':
            case '/': return 2;
            case '+':
            case '-': return 1;
            default: return -1;
        }
    }

    static String infixToPostfix(String expr) {
        StringBuilder result = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < expr.length(); i++) {
            char c = expr.charAt(i);

            if (Character.isLetterOrDigit(c)) {
                result.append(c).append(' ');
            } 
            else if (c == '(') {
                stack.push(c);
            } 
            else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    result.append(stack.pop()).append(' ');
                }
                stack.pop(); // remove '('
            } 
            else if (c == ' ') {
                continue; // skip spaces
            } 
            else { // Operator
                while (!stack.isEmpty() && precedence(stack.peek()) >= precedence(c)) {
                    result.append(stack.pop()).append(' ');
                }
                stack.push(c);
            }
        }

        while (!stack.isEmpty()) {
            result.append(stack.pop()).append(' ');
        }

        return result.toString().trim();
    }

    static double evaluatePostfix(String postfix, Map<Character, Double> values) {
        Stack<Double> stack = new Stack<>();
        String[] tokens = postfix.split("\\s+");

        for (String token : tokens) {
            char c = token.charAt(0);

            if (Character.isLetterOrDigit(c) && token.length() == 1) {
                stack.push(values.get(c));
            } else {
                double b = stack.pop();
                double a = stack.pop();

                switch (c) {
                    case '+': stack.push(a + b); break;
                    case '-': stack.push(a - b); break;
                    case '*': stack.push(a * b); break;
                    case '/': stack.push(a / b); break;
                    case '^': stack.push(Math.pow(a, b)); break;
                }
            }
        }
        return stack.pop();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Infix Expression: A * (B + C) / D");
        String expr1 = "A * (B + C) / D";
        String postfix1 = infixToPostfix(expr1);
        System.out.println("Postfix Expression: " + postfix1);

        Map<Character, Double> values1 = new HashMap<>();
        values1.put('A', 6.0);
        values1.put('B', 2.0);
        values1.put('C', 3.0);
        values1.put('D', 5.0);

        double result1 = evaluatePostfix(postfix1, values1);
        System.out.println("Value: " + result1);
        System.out.println();

        System.out.println("Infix Expression: A ^ B ^ C");
        String expr2 = "A ^ B ^ C";
        String postfix2 = infixToPostfix(expr2);
        System.out.println("Postfix Expression: " + postfix2);

        Map<Character, Double> values2 = new HashMap<>();
        values2.put('A', 2.0);
        values2.put('B', 3.0);
        values2.put('C', 2.0);

        double result2 = evaluatePostfix(postfix2, values2);
        System.out.println("Value: " + result2);
    }
}
