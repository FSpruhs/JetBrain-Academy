package main.java.DesktopCalculator;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;


import static main.java.DesktopCalculator.utils.InfixPostfixConverter.evaluatePostfix;
import static main.java.DesktopCalculator.utils.InfixPostfixConverter.inFixToPostFix;

public class Calculator extends JFrame {

    private final int BUTTON_HEIGHT = 50;
    private final int BUTTON_WIDTH = 60;

    private JLabel equationLabel;
    private JLabel resultLabel;
    private JButton solveButton;


    public Calculator() {
        super("Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 600);
        initComponents();
        setLayout(null);
        setVisible(true);
    }

    private void initComponents() {
        createEquationLabel(10, 80);
        createResultLabel(10, 20);
        createCalcSolveButton(130, 500);
        createClearButton(130, 300);
        createDeleteButton(190, 300);
        createParenthesesButton(10, 250);
        createSquareRootButton(70, 250);
        createPowerTwoButton(130, 250);
        createPowerYButton(10, 300);
        createPlusMinusButton(70, 300);
        createDotButton(10, 500);
        createButton("Zero", "0",10, 350 );
        createButton("One", "1",10, 400);
        createButton("Two", "2",10, 450);
        createButton("Three", "3",70, 350);
        createButton("Four", "4",70, 400);
        createButton("Five", "5",70, 450);
        createButton("Six", "6",130, 350);
        createButton("Seven", "7",130, 400);
        createButton("Eight", "8",130, 450);
        createButton("Nine", "9",70, 500);
        createOperatorButton("Add", "+",190, 350);
        createOperatorButton("Subtract", "-",190, 400);
        createOperatorButton("Divide", "÷",190, 450);
        createOperatorButton("Multiply", "×",190, 500);

    }

    private void createDotButton(int x, int y) {
        JButton dotButton = new JButton(".");
        dotButton.setName("Dot");
        dotButton.setBounds(x,y,BUTTON_WIDTH, BUTTON_HEIGHT);
        this.add(dotButton);
        dotButton.addActionListener(e -> addDot());
    }

    private void addDot() {
        String text = equationLabel.getText();
        if (text.equals("")) {
            equationLabel.setText("0.");
        } else if (isLastCharOperator(text)) {
            equationLabel.setText( text + "0.");
        } else {
            equationLabel.setText(text + ".");
        }
    }

    private void createPlusMinusButton(int x, int y) {
        JButton plusMinusButton = new JButton("+-");
        plusMinusButton.setName("PlusMinus");
        plusMinusButton.setBounds(x,y,BUTTON_WIDTH, BUTTON_HEIGHT);
        this.add(plusMinusButton);
        plusMinusButton.addActionListener(e -> addPlusMinus());
    }

    private void addPlusMinus() {
        String text = equationLabel.getText();
        if (text.length() == 0) {
            equationLabel.setText("(\u002D");
            return;
        }
        if (text.startsWith("(\u002D") && !notContainsOperator(text)) {
            equationLabel.setText(text.substring(2));
            return;
        }
        if (notContainsOperator(text)) {
            equationLabel.setText("(\u002D" + text);
            return;
        }
        if (text.equals("(\u002D")) {
            equationLabel.setText("");
            return;
        }
        if (!text.startsWith("(\u002D")) {
            equationLabel.setText("(\u002D(" + text + "))");
        } else {
            equationLabel.setText(text.substring(3, text.length()-2));
        }
    }

    private boolean notContainsOperator(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (isOperator(str.charAt(i))) return false;
        }
        return true;
    }

    boolean isOperator(char ch) {
        return ch == '\u00F7' || ch == '^' || ch == '\u221A' || ch == '\u00D7' || ch == '\u002B' || ch == '-';
    }

    private void createPowerYButton(int x, int y) {
        JButton powerYButton = new JButton("x^y");
        powerYButton.setName("PowerY");
        powerYButton.setBounds(x,y,BUTTON_WIDTH, BUTTON_HEIGHT);
        this.add(powerYButton);
        powerYButton.addActionListener(e -> addSymbol("^("));
    }

    private void createPowerTwoButton(int x, int y) {
        JButton powerTwoButton = new JButton("x^2");
        powerTwoButton.setName("PowerTwo");
        powerTwoButton.setBounds(x,y,BUTTON_WIDTH, BUTTON_HEIGHT);
        this.add(powerTwoButton);
        powerTwoButton.addActionListener(e -> addSymbol("^(2)"));
    }

    private void createSquareRootButton(int x, int y) {
        JButton squareRootButton = new JButton("√");
        squareRootButton.setName("SquareRoot");
        squareRootButton.setBounds(x,y,BUTTON_WIDTH, BUTTON_HEIGHT);
        this.add(squareRootButton);
        squareRootButton.addActionListener(e -> addSymbol("√("));
    }

    private void createParenthesesButton(int x, int y) {
        JButton parenthesesButton = new JButton("()");
        parenthesesButton.setName("Parentheses");
        parenthesesButton.setBounds(x,y,BUTTON_WIDTH, BUTTON_HEIGHT);
        this.add(parenthesesButton);
        parenthesesButton.addActionListener(e -> addParentheses());
    }

    private void addParentheses() {
        String text = equationLabel.getText();
        if (text.length() == 0 || text.charAt(text.length()-1) == '(' || isOperator(text.charAt(text.length()-1))) {
            equationLabel.setText(text+"(");
            return;
        }
        int cntStart = 0;
        int cntEnd = 0;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == '(') {
                cntStart++;
            }
            else if (text.charAt(i) == ')'){
                cntEnd++;
            }
        }
        if (cntStart == cntEnd) {
            equationLabel.setText(text + "(");
        } else {
            equationLabel.setText(text + ")");
        }

    }

    private void createResultLabel(int x, int y) {
        JLabel resultLabel = new JLabel("0");
        resultLabel.setName("ResultLabel");
        resultLabel.setFont(new Font("Serif", Font.BOLD, 40));
        resultLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        resultLabel.setBounds(x, y, 240, 100);
        this.resultLabel = resultLabel;
        this.add(resultLabel);
    }

    private void createEquationLabel(int x, int y) {
        JLabel equationLabel = new JLabel();
        equationLabel.setName("EquationLabel");
        equationLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        equationLabel.setFont(new Font("Serif", Font.BOLD, 20));
        equationLabel.setForeground(Color.GREEN);
        equationLabel.setBounds(x, y, 240, 50);
        this.equationLabel = equationLabel;
        this.add(equationLabel);
    }

    private void createOperatorButton(String name, String operator, int x, int y) {
        JButton digitButton = new JButton(operator);
        digitButton.setName(name);
        digitButton.setBounds(x,y,BUTTON_WIDTH, BUTTON_HEIGHT);
        this.add(digitButton);
        digitButton.addActionListener(e -> addOperatorSymbol(operator));
    }

    private void addOperatorSymbol(String operator) {
        String text = equationLabel.getText();
        if (text.equals("")) {
            return;
        } else if (getLastChar(text).equals(".")) {
            equationLabel.setText(text + "0" + operator);
        } else if (isLastCharOperator(text)) {
            equationLabel.setText(delLastChar(text) + operator);
        } else {
            equationLabel.setText(text + operator);
        }
    }


    private void createDeleteButton(int x, int y) {
        JButton deleteButton = new JButton("Del");
        deleteButton.setName("Delete");
        deleteButton.setBounds(x,y,BUTTON_WIDTH, BUTTON_HEIGHT);
        this.add(deleteButton);
        deleteButton.addActionListener(e -> equationLabel.setText(delLastChar(equationLabel.getText())));
    }

    private String getLastChar (String text) {
        return text.substring(text.length() - 1);
    }

    private String delLastChar(String str) {
        String result = "";
        if ((str != null) && (str.length() > 0)) {
            result = str.substring(0, str.length() - 1);
        }
        return result;
    }

    private void createClearButton(int x, int y) {
        JButton clearButton = new JButton("C");
        clearButton.setName("Clear");
        clearButton.setBounds(x,y,BUTTON_WIDTH, BUTTON_HEIGHT);
        this.add(clearButton);
        clearButton.addActionListener(e -> clearAll());
    }

    private void clearAll() {
        equationLabel.setText("");
        equationLabel.setForeground(Color.GREEN);
        resultLabel.setText("0");
    }


    private void createButton(String name, String digit, int x, int y) {
        JButton digitButton = new JButton(digit);
        digitButton.setName(name);
        digitButton.setBounds(x,y,BUTTON_WIDTH, BUTTON_HEIGHT);
        this.add(digitButton);
        digitButton.addActionListener(e -> addSymbol(digit));
    }

    private boolean isLastCharOperator(String text) {
        if (text.equals("")) {
            return  false;
        } else if (getLastChar(text).equals("+") ||
                getLastChar(text).equals("-") ||
                getLastChar(text).equals("÷") ||
                getLastChar(text).equals("×")) {
            return true;
        } else {
            return false;
        }
    }

    private void addSymbol(String symbol) {
        String text = equationLabel.getText();
        equationLabel.setText(text + symbol);
    }


    private void createCalcSolveButton(int x, int y) {
        JButton solveButton = new JButton("=");
        solveButton.setName("Equals");
        solveButton.setBounds(x,y, BUTTON_WIDTH,BUTTON_HEIGHT);
        this.solveButton = solveButton;
        this.add(solveButton);
        solveButton.addActionListener(e -> process());
    }

    private String replaceRoot(String text) {
        while (text.contains("√")) {
            int index = text.indexOf("√");
            int positionParentheses = 0;
            for (int i = 0; i < text.length(); i++) {
                if ((text.charAt(i) == ')') && (i > index)) {
                    positionParentheses = i;
                    break;
                }
            }
            text = text.substring(0, positionParentheses + 1) + "^0.5" +
                    text.substring(positionParentheses + 1);
            text = text.replaceFirst("√", "");
        }
        return text;
    }


    private void process() {
        String text = equationLabel.getText();
        if (isLastCharOperator(text)) {
            equationLabel.setForeground(Color.RED.darker());
        } else if (text.contains("÷0")) {
            equationLabel.setForeground(Color.RED.darker());
        } else if (text.contains("√()")) {
            equationLabel.setForeground(Color.RED.darker());
        } else {
            equationLabel.setForeground(Color.GREEN);
            System.out.println(text);
            text = replaceRoot(text);
            text = replaceOperator(text);
            String[] inFix = text.split("((?=[^\\d.])|(?<=[^\\d.]))");
            ArrayList<String> postFix = inFixToPostFix(inFix);
            float result = evaluatePostfix(postFix);
            if(Float.isNaN(result)) {
                equationLabel.setForeground(Color.RED.darker());
            } else {
                if (result == (int) result) {
                    resultLabel.setText(String.format("%d", (int) result));
                } else {
                    resultLabel.setText(String.format("%s", result));
                }
            }
        }
    }

    private String replaceOperator(String text) {
        text = text.replaceAll("÷", "/");
        text = text.replaceAll("×", "*");
        if (text.lastIndexOf("(") > text.lastIndexOf(")")) {
            int index = text.lastIndexOf("(");
            if (index == text.length() - 1) {
                text = text.substring(0, text.length() - 1);
            } else {
                text = text.substring(0, index) + text.substring(index);
            }
        }
        return text;
    }

    public static void main(String[] args) {
        Calculator calc = new Calculator();
        String text = "√()";
        text = calc.replaceRoot(text);
        System.out.println(text);
        text = calc.replaceOperator(text);
        System.out.println(text);
        String[] inFix = text.split("((?=[^\\d.])|(?<=[^\\d.]))");
        System.out.println(Arrays.toString(inFix));
        ArrayList<String> postFix = inFixToPostFix(inFix);
        System.out.println(postFix);
        float result = evaluatePostfix(postFix);
        if(Float.isNaN(result)) {
            System.out.println("here");
        }
        System.out.println(result);
    }

}
