package scientcalc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ScientCalc extends JFrame
{
    JTextField t1 = new JTextField(), t2, t3;
    JLabel l1, l2, l3, l4;
    JButton[] b = new JButton[28];
    String[] bStr =         {"<-", "C", "sqrt", "+", "pi", "Log",
                             "7", "8", "9", "-", "x^y", "sin",
                             "4", "5", "6", "*", "x^3", "cos",
                             "1", "2", "3", "/", "x^2", "tan",
                             "0", ".", "+/-", "="};
    JPanel panel = new JPanel(null);
    int i, x, y, sizex = 65, sizey = 40, step = 7, x0 = 20, y0 = 70;
    double num1, num2, res;
    String operat;
    Wood wooden = new Wood();
    
    public ScientCalc(String s)
    {
        super(s);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(2*x0+(sizex+step)*6, 2*y0+(sizey+step)*5);
        this.add(panel, BorderLayout.CENTER);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        panel.setBackground(new Color(70,70,70));
        Font font = new Font("Calibri", Font.BOLD, 20);
        Font font2 = new Font("Calibri", Font.BOLD, 15);
        
        //создаём и настраиваем все элементы и размещаем их на панели
        for(i = 0; i < 28; i++)
        {
            b[i] = new JButton();
            b[i].setText(bStr[i]);
            b[i].addActionListener(wooden);
            b[i].setBackground(new Color(50,50,50));
            b[i].setForeground(Color.WHITE);
            if(i!=2) b[i].setFont(font);
            else     b[i].setFont(font2);
        }
        i=0;
        for(y = y0; y < y0+(sizey+step)*4; y+=sizey+step)
            for(x = x0; x < x0+(sizex+step)*6; x+=sizex+step)
            {
                b[i].setBounds(x, y, sizex, sizey);
                i++;
            }
        for(x = x0; x < x0+(sizex+step)*3; x+=sizex+step)
        {
            b[i].setBounds(x, y, sizex, sizey);
            i++;
        }
        b[i].setBounds(x, y, sizex+sizex+step, sizey);
        b[i].setBackground(new Color(255,74,0));
        t1.setBounds(x0, x0, sizex*6, 2*x0);
        t1.setBackground(new Color(100,100,100));
        t1.setForeground(Color.WHITE);
        t1.setFont(font);
        panel.add(t1);
        for(i = 0; i < 28; i++)
        {
            panel.add(b[i]);
        }
        i=0;
    }
    public class Wood implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                //настройка кнопок с цирфрами
                for(i = 0; i < 3; i++)
                for(int j = 6; j < 9; j++)
                    if(e.getSource() == b[j+i*6])
                    {
                        String Enternumber = t1.getText() + b[j+i*6].getText();
                        t1.setText(Enternumber);
                    }
                if(e.getSource() == b[24])
                {
                    String Enternumber = t1.getText() + b[24].getText();
                    t1.setText(Enternumber);
                }
                
                if(e.getSource() == b[26])  //поменять знак числа
                {
                    double ops = Double.parseDouble(String.valueOf(t1.getText()));
                    ops = ops *(-1);
                    t1.setText(String.valueOf(ops));
                }
                if(e.getSource() == b[2])   //корень квадратный
                {
                    double ops = Double.parseDouble(String.valueOf(t1.getText()));
                    ops = Math.sqrt(ops);
                    t1.setText(String.valueOf(ops));
                }
                if(e.getSource() == b[4])
                {
                    double ops = (3.1415926535897932384626433832795028841971);
                    t1.setText(String.valueOf(ops));
                }
                
                //фу-и Log, Sinus, Cosinus, Tangens
                if(e.getSource() == b[5])
                {
                    double ops = Double.parseDouble(String.valueOf(t1.getText()));
                    ops = Math.log(ops);
                    t1.setText(String.valueOf(ops));
                }
                if(e.getSource() == b[11])
                {
                    double ops = Double.parseDouble(String.valueOf(t1.getText()));
                    ops = Math.sin(ops);
                    t1.setText(String.valueOf(ops));
                }
                if(e.getSource() == b[17])
                {
                    double ops = Double.parseDouble(String.valueOf(t1.getText()));
                    ops = Math.cos(ops);
                    t1.setText(String.valueOf(ops));
                }
                if(e.getSource() == b[23])
                {
                    double ops = Double.parseDouble(String.valueOf(t1.getText()));
                    ops = Math.tan(ops);
                    t1.setText(String.valueOf(ops));
                }
                
                //фу-и возведения в степень 2 и 3
                if(e.getSource() == b[16])
                {
                    double ops = Double.parseDouble(String.valueOf(t1.getText()));
                    ops = Math.pow(ops,3);
                    t1.setText(String.valueOf(ops));
                }
                if(e.getSource() == b[22])
                {
                    double ops = Double.parseDouble(String.valueOf(t1.getText()));
                    ops = Math.pow(ops,2);
                    t1.setText(String.valueOf(ops));
                }
                
                if(e.getSource() == b[1])   //кнопка очистить
                    t1.setText("");
                if(e.getSource() == b[0])   //кнопка Backspace
                {
                    String bsp = null;
                    if(t1.getText().length() > 0)
                    {
                        StringBuilder strB = new StringBuilder(t1.getText());
                        strB.deleteCharAt(t1.getText().length() - 1);
                        bsp = strB.toString();
                        t1.setText(bsp);
                    }
                }
                if(e.getSource() == b[25])
                    if(!t1.getText().contains("."))
                        t1.setText(t1.getText()+b[25].getText());
                
                //операции +, -, *, /, х^y
                if(e.getSource() == b[3])
                {
                    num1 = Double.parseDouble(t1.getText());
                    t1.setText("");
                    operat = "+";
                }
                if(e.getSource() == b[9])
                {
                    num1 = Double.parseDouble(t1.getText());
                    t1.setText("");
                    operat = "-";
                }
                if(e.getSource() == b[15])
                {
                    num1 = Double.parseDouble(t1.getText());
                    t1.setText("");
                    operat = "*";
                }
                if(e.getSource() == b[21])
                {
                    num1 = Double.parseDouble(t1.getText());
                    t1.setText("");
                    operat = "/";
                }
                if(e.getSource() == b[10])
                {
                    num1 = Double.parseDouble(t1.getText());
                    t1.setText("");
                    operat = "^";
                }
                if(e.getSource() == b[27])
                {
                    String answer;
                    num2 = Double.parseDouble(t1.getText());
                    if (operat == "+") res = num1 + num2;
                    if (operat == "-") res = num1 - num2;
                    if (operat == "*") res = num1 * num2;
                    if (operat == "/") res = num1 / num2;
                    if (operat == "^") res = Math.pow(num1, num2);
                    operat = "";
                    answer = String.format("%.0f",res);
                    t1.setText(answer);
                }
            }
            catch (Exception ex)
            {
                JOptionPane.showMessageDialog(null, "Введи в поле число");
            }
        }
    }
    public static void main(String[] args)
    {
        ScientCalc r = new ScientCalc("Calculatoruss");
        r.setVisible(true);
    }
}