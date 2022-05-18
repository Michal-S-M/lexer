package com.company;
import java.util.*;

public class Interpreter {

    private final ArrayList<Token> infixExpr;
    private final Map<String, Double> variables = new HashMap<>();
    private int iterator;
    private Token cur;
    private boolean transCondition;

    public Interpreter(ArrayList<Token> infixExpr) {
        this.infixExpr = infixExpr;
        cur = infixExpr.get(0);
        iterator = 0;
        transCondition = false;
        for (; iterator < infixExpr.size(); iterator++) {
            cur = infixExpr.get(iterator);
            switch (cur.type) {
                case "ASSIGN_OP" -> interpret_value("ENDL");
                case "_if_" -> interpret_if();
                case "WHILE" -> interpret_while();
                case "DO" -> interpret_do_while();
                case "_for_" -> interpret_for();
                case "PRINT" -> interpret_print();
            }
        }
        System.out.println(this.variables);
    }

    private void interpret_value(String trans) {
        int indexVar = iterator - 1;
        int startExpr = iterator + 1;

        while (!trans.equals(cur.type)) {
            if ("ENDL".equals(cur.type)) {
                double rez = calc(toPostfix(infixExpr, startExpr, iterator));
                variables.put(infixExpr.get(indexVar).value, rez);
                //System.out.println("infixExpr.get(indexVar).value: "+infixExpr.get(indexVar).value); ///////////////////
                indexVar = iterator + 1;
                startExpr = iterator + 3;
            }
            iterator++;
            //System.out.println("iterator: "+iterator);                                               ///////////////////
            cur = infixExpr.get(iterator);
        }

        double rez = calc(toPostfix(infixExpr, startExpr, iterator));
        variables.put(infixExpr.get(indexVar).value, rez);
    }

    private void interpret_if() {
        interpret_condition();
        if (!transCondition) {
            while (!"ENDL".equals(cur.type)) {
                if ("ELSE".equals(cur.type)) {
                    break;
                }
                iterator++;
                cur = infixExpr.get(iterator);
            }
        } else {
            iterator++;
            interpret_value("ELSE");
            while (!"ENDL".equals(cur.type)) {
                iterator++;
                cur = infixExpr.get(iterator);
            }
        }
    }

    private int operationPriority(Token op) {
        return switch (op.value) {
            case "(" -> 0;
            case "+", "-" -> 1;
            case "*", "/" -> 2;
            default -> throw new IllegalArgumentException("Illegal value: " + op.value);
        };
    }

    private double execute(Token op, double first, double second) {
        return switch (op.value) {
            case "+" -> first + second;
            case "-" -> first - second;
            case "*" -> first * second;
            case "/" -> first / second;
            default -> throw new IllegalArgumentException("Illegal value: " + op.value);
        };
    }

    private boolean compare(Token comp, double first, double second) {
        return switch (comp.value) {
            case ">" -> Double.compare(first, second) == 1;
            case "~" -> Double.compare(first, second) == 0;
            case "<" -> Double.compare(first, second) == -1;
            case "!=" -> Double.compare(first, second) != 0;
            default -> throw new IllegalArgumentException("Illegal value: " + comp.value);
        };
    }

    private void interpret_condition() {
        int first_argument_index = iterator + 2;
        int comparison_op_index = iterator + 3;
        int second_argument_index = iterator + 4;
        Token s = infixExpr.get(second_argument_index);
        double first = variables.get(infixExpr.get(first_argument_index).value);
        double second = switch (s.type) {
            case "DIGIT" -> Double.parseDouble(s.value);
            case "VAR" -> variables.get(s.value);
            default ->  0.0;
        };

        iterator += 6;
        cur = infixExpr.get(iterator);

        transCondition = compare(infixExpr.get(comparison_op_index), first, second);
    }

    private void interpret_while() {
        int start_iteration = iterator;
        interpret_condition();
        while (transCondition) {
            iterator++;
            interpret_value("ENDL");
            iterator = start_iteration;
            cur = infixExpr.get(iterator);
            interpret_condition();
        }
        while (!"ENDL".equals(cur.type)) {
            iterator++;
            cur = infixExpr.get(iterator);
        }
    }

    private void interpret_do_while() {
        iterator += 2;
        cur = infixExpr.get(iterator);
        int start_iteration = iterator;
        do {
            interpret_value("WHILE");
            interpret_condition();
            iterator = start_iteration;
            cur = infixExpr.get(iterator);
        } while (transCondition);

        while (!"ENDL".equals(cur.type)) {
            iterator++;
            cur = infixExpr.get(iterator);
        }
    }

    private void interpret_for() {
        iterator += 3;
        cur = infixExpr.get(iterator);
        interpret_value("DIV");

        iterator--;
        int condition = iterator;
        interpret_condition();

        int indexAfterFor = iterator + 1;
        while (transCondition) {
            while (!"R_BC".equals(cur.type)) {
                iterator++;
                cur = infixExpr.get(iterator);
            }
            iterator += 2;
            cur = infixExpr.get(iterator);
            interpret_value("ENDL");

            iterator = indexAfterFor;
            cur = infixExpr.get(iterator);
            interpret_value("R_BC");

            iterator = condition;
            interpret_condition();
        }

        while (!"ENDL".equals(cur.type)) {
            iterator++;
            cur = infixExpr.get(iterator);
        }
    }

    private void interpret_print() {
        iterator++;
        cur = infixExpr.get(iterator);
        if ("L_BC".equals(cur.type)) {
            Token c = infixExpr.get(iterator + 1);
            switch (c.type) {
                case "DIGIT" -> System.out.println(Double.parseDouble(c.value));
                case "VAR" -> System.out.println(variables.get(c.value));
            }
            iterator += 2;
        } else {
            System.out.println(variables);
        }
    }

    private ArrayList<Token> toPostfix(ArrayList<Token> infixExpr, int start, int end) {
        ArrayList<Token> postfixExpr = new ArrayList<>();
        Stack<Token> stack = new Stack<>();
        for (int i = start; i < end; i++) {
            Token c = infixExpr.get(i);
            switch (c.type) {
                case "DIGIT", "VAR" -> postfixExpr.add(c);
                case "L_BC" -> stack.push(c);
                case "R_BC" -> {
                    while (stack.size() > 0 && !"L_BC".equals(stack.peek().type))
                        postfixExpr.add(stack.pop());
                    stack.pop();
                }
                case "OP" -> {
                    Token op = c;
                    while (stack.size() > 0 && (operationPriority(stack.peek()) >= operationPriority(op))) {
                        postfixExpr.add(stack.pop());
                    }
                    stack.push(c);
                }
            }
        }

        while (!stack.isEmpty()) {
            postfixExpr.add(stack.pop());
        }

        return postfixExpr;
    }

    private double calc(ArrayList<Token> postfixExpr) {
        Stack<Double> locals = new Stack<>();
        for (int i = 0; i < postfixExpr.size(); i++) {
            Token c = postfixExpr.get(i);
            switch (c.type) {
                case "DIGIT" -> locals.push(Double.parseDouble(c.value));
                case "VAR" -> locals.push(variables.get(c.value));
                case "OP" -> {
                    double second = locals.size() > 0 ? locals.pop() : 0,
                            first = locals.size() > 0 ? locals.pop() : 0;
                    locals.push(execute(c, first, second));
                }
            }
        }
        return locals.pop();
    }
}