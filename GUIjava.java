import javax.swing.*;  
import java.awt.*;  
import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;  

public class ScientificCalculator {  
    private JFrame frame;  
    private JTextField txtDisplay;  
    private double total;  
    private String current = "";  
    private boolean inputValue = true;  
    private boolean checkSum = false;  
    private boolean result = false;  
    private String operation = "";  

    public ScientificCalculator() {  
        frame = new JFrame("Silva's Scientific Calculator");  
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        frame.setSize(480, 568);  
        frame.setResizable(false);  
        frame.setLayout(new GridLayout(6, 4));  

        txtDisplay = new JTextField();  
        txtDisplay.setFont(new Font("Arial", Font.BOLD, 20));  
        txtDisplay.setHorizontalAlignment(SwingConstants.RIGHT);  
        txtDisplay.setEditable(false);  
        frame.add(txtDisplay);  

        createButtons();  

        frame.setVisible(true);  
    }  

    private void createButtons() {  
        String[] numbers = {"7", "8", "9", "4", "5", "6", "1", "2", "3", "0"};  
        for (int i = 0; i < numbers.length; i++) {  
            JButton button = new JButton(numbers[i]);  
            button.addActionListener(new NumberButtonListener());  
            frame.add(button);  
        }  

        String[] operations = {"+", "-", "*", "/", "C", "AC", "="};  
        for (String op : operations) {  
            JButton button = new JButton(op);  
            button.addActionListener(new OperationButtonListener(op));  
            frame.add(button);  
        }  

        String[] scientific = {"sin", "cos", "tan", "sqrt"};  
        for (String func : scientific) {  
            JButton button = new JButton(func);  
            button.addActionListener(new ScientificFunctionListener(func));  
            frame.add(button);  
        }  
    }  

    private class NumberButtonListener implements ActionListener {  
        @Override  
        public void actionPerformed(ActionEvent e) {  
            String num = e.getActionCommand();  
            if (inputValue) {  
                txtDisplay.setText(num);  
                inputValue = false;  
            } else {  
                txtDisplay.setText(txtDisplay.getText() + num);  
            }  
        }  
    }  

    private class OperationButtonListener implements ActionListener {  
        private String operation;  

        public OperationButtonListener(String operation) {  
            this.operation = operation;  
        }  

        @Override  
        public void actionPerformed(ActionEvent e) {  
            if (operation.equals("C")) {  
                txtDisplay.setText("");  
            } else if (operation.equals("AC")) {  
                total = 0;  
                current = "";  
                inputValue = true;  
                checkSum = false;  
                result = false;  
                txtDisplay.setText("");  
            } else if (operation.equals("=")) {  
                sumOfTotal();  
            } else {  
                if (!result) {  
                    total = Double.parseDouble(txtDisplay.getText());  
                }  
                current = txtDisplay.getText();  
                checkSum = true;  
                ScientificCalculator.this.operation = operation;  
                inputValue = true;  
            }  
        }  
    }  

    private void sumOfTotal() {  
        double secondNum = Double.parseDouble(txtDisplay.getText());  
        switch (operation) {  
            case "+":  
                total += secondNum;  
                break;  
            case "-":  
                total -= secondNum;  
                break;  
            case "*":  
                total *= secondNum;  
                break;  
            case "/":  
                total /= secondNum;  
                break;  
        }  
        result = true;  
        txtDisplay.setText(String.valueOf(total));  
    }  

    private class ScientificFunctionListener implements ActionListener {  
        private String function;  

        public ScientificFunctionListener(String function) {  
            this.function = function;  
        }  

        @Override  
        public void actionPerformed(ActionEvent e) {  
            double value = Double.parseDouble(txtDisplay.getText());  
            switch (function) {  
                case "sin":  
                    txtDisplay.setText(String.valueOf(Math.sin(Math.toRadians(value))));  
                    break;  
                case "cos":  
                    txtDisplay.setText(String.valueOf(Math.cos(Math.toRadians(value))));  
                    break;  
                case "tan":  
                    txtDisplay.setText(String.valueOf(Math.tan(Math.toRadians(value))));  
                    break;  
                case "sqrt":  
                    txtDisplay.setText(String.valueOf(Math.sqrt(value)));  
                    break;  
            }  
        }  
    }  

    public static void main(String[] args) {  
        new ScientificCalculator();  
    }  
}
