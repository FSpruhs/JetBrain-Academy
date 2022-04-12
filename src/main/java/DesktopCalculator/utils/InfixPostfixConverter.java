package main.java.DesktopCalculator.utils;


import java.util.ArrayList;
import java.util.Stack;

public class InfixPostfixConverter {

    private static int precedence(String str){
        switch (str){
            case "+":
            case "-":
                return 1;
            case "*":
            case "/":
                return 2;
            case "^":
                return 3;
        }
        return -1;
    }

    public static ArrayList<String> inFixToPostFix(String[] inFix) {
        ArrayList<String> result = new ArrayList<>();
        Stack<String> stack = new Stack<>();
        for (String str : inFix) {
            if (precedence(str) > 0) {
                while (!stack.isEmpty() && precedence(stack.peek()) >= precedence(str)) {
                    result.add(stack.pop());
                }
                stack.push(str);
            } else if(str.equals(")")) {
                String x = stack.pop();
                while(!x.equals("(")) {
                    result.add(x);
                    x = stack.pop();
                }
            } else if(str.equals("(")){
                stack.push(str);
            } else {
                result.add(str);
            }
        }

        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }
        return result;
    }

    public static float evaluatePostfix(ArrayList<String> exp) {
        Stack<Float> stack = new Stack<>();

        for(String str : exp) {
            try{
                float digit = Float.parseFloat(str);
                stack.push(digit);
            } catch (Exception e) {

                float val1 = stack.pop();
                float val2 = stack.isEmpty() ? 0.0F : stack.pop();

                switch(str)
                {
                    case "+":
                        stack.push(val2+val1);
                        break;
                    case "-":
                        stack.push(val2- val1);
                        break;
                    case "/":
                        stack.push(val2/val1);
                        break;
                    case "*":
                        stack.push(val2*val1);
                        break;
                    case "^":
                        stack.push((float) Math.pow(val2, val1));
                        break;
                }
            }
        }
        return stack.pop();
    }

}
