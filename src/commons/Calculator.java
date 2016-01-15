package commons;

import java.awt.*;
import java.awt.event.*;
import java.math.*;

import javax.swing.*;

public class Calculator extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String Number[] = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
	String Symbol[] = { ".", "=", "＋", "-", "×", "÷", "清空", "退格" };
	JButton Num[] = new JButton[Number.length];
	JButton Sym[] = new JButton[Symbol.length];
	JPanel JP_L, JP_P, JP_N, JP_S;
	JTextField Display;
	String strOut;
	double result, num1, num2;
	//FirstPress为第一个数字按下的标识，point为小数点被按下的标识
	boolean FirstPress, point, LastPressEqual;
	int operator;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Calculator();
	}

	void Init() {
		int i = 0;
		for (i = 0; i < Number.length; i++) {
			Num[i] = new JButton(Number[i]);
			Num[i].addActionListener(this);
		}
		for (i = 0; i < Symbol.length; i++) {
			Sym[i] = new JButton(Symbol[i]);
			Sym[i].addActionListener(this);
		}
		JP_L = new JPanel();
		JP_P = new JPanel();
		JP_N = new JPanel();
		JP_S = new JPanel();
		Display = new JTextField(13);
		strOut = new String("0");
		result = 0;
		num1 = 0;
		num2 = 0;
		FirstPress = true;
		point = false;
		LastPressEqual = false;
		operator = 0;
	}

	void View() {
		this.setLayout(new BorderLayout());
		((JPanel) this.getContentPane()).setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
		this.add(JP_L, BorderLayout.NORTH);
		this.add(JP_P, BorderLayout.CENTER);
		JP_L.add(Display);
		JP_L.setBackground(Color.DARK_GRAY);
		JP_L.setVisible(true);
		//设置面板背景颜色
		JP_L.setBorder(BorderFactory.createLineBorder(Color.black));
		Display.setText(strOut);
		//设置文本域字体
		Display.setFont(new Font("Segoe Print", Font.PLAIN, 18));
		//设置文本域边框线条颜色
		Display.setBorder(BorderFactory.createLineBorder(Color.black));
		//设置文本域背景颜色
		Display.setBackground(Color.CYAN);
		Display.setEditable(false);
		//设置文字从右边显示
		Display.setHorizontalAlignment(JTextField.RIGHT);
		JP_P.setLayout(new BorderLayout());
		JP_P.add(JP_N, BorderLayout.CENTER);
		JP_P.add(JP_S, BorderLayout.EAST);
		JP_N.setLayout(new GridLayout(4, 3, 15, 20));
		JP_N.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 10));
		JP_N.add(Num[7]);
		JP_N.add(Num[8]);
		JP_N.add(Num[9]);
		JP_N.add(Num[4]);
		JP_N.add(Num[5]);
		JP_N.add(Num[6]);
		JP_N.add(Num[1]);
		JP_N.add(Num[2]);
		JP_N.add(Num[3]);
		JP_N.add(Num[0]);
		JP_N.add(Sym[0]);
		JP_N.add(Sym[1]);
		JP_S.setLayout(new GridLayout(6, 1, 0, 5));
		JP_S.setBorder(BorderFactory.createEmptyBorder(10, 5, 0, 0));
		JP_S.add(Sym[7]);
		JP_S.add(Sym[6]);
		JP_S.add(Sym[5]);
		JP_S.add(Sym[4]);
		JP_S.add(Sym[3]);
		JP_S.add(Sym[2]);
	}

	Calculator() {
		Init();
		View();
		this.setLocation(550, 200);
		this.setTitle("计算器");
		this.setSize(300, 320);
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	double Operators() {
		BigDecimal BD_num1 = new BigDecimal(num1);
		BigDecimal BD_num2 = new BigDecimal(num2);
		switch (operator) {
		case 1:
			return (BD_num1.add(BD_num2, new MathContext(15))).doubleValue();
		case 2:
			return (BD_num1.subtract(BD_num2, new MathContext(15))).doubleValue();
		case 3:
			//乘法，BigDecimal解决因double计算引起的失精度问题，MathContext(15)表示四舍五入精度为小数点后15位
			return (BD_num1.multiply(BD_num2, new MathContext(15))).doubleValue();
		case 4: {
			if (num2 == 0) {
				//除数为0时，返回正无穷大
				return Double.POSITIVE_INFINITY;
			} else {
				//除法，BigDecimal解决因double计算引起的失精度问题，MathContext(15)表示四舍五入精度为小数点后15位
				return (BD_num1.divide(BD_num2, new MathContext(15))).doubleValue();
			}
		}
		}
		return 0;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		strOut = Display.getText();
		if (e.getActionCommand().equals("=")) {
			if (operator == 0) {
				return;
			} else {
				num2 = Double.parseDouble(strOut);
				result = Operators();
				if (0 == (result - (int) result)) {
					Display.setText(String.valueOf((int) result));
				} else {
					Display.setText(String.valueOf(result));
				}
				num1 = result;
				FirstPress = true;
				point = false;
				LastPressEqual = true;
				operator = 0;
			}
		} else if (e.getActionCommand().equals("＋")) {
			if (operator == 0) {
				num1 = Double.parseDouble(strOut);
			} else {
				num2 = Double.parseDouble(strOut);
				result = Operators();
				if (0 == (result - (int) result)) {
					Display.setText(String.valueOf((int) result));
				} else {
					Display.setText(String.valueOf(result));
				}
				num1 = result;
			}
			FirstPress = true;
			point = false;
			LastPressEqual = false;
			operator = 1;
		} else if (e.getActionCommand().equals("-")) {
			if (operator == 0) {
				num1 = Double.parseDouble(strOut);
			} else {
				num2 = Double.parseDouble(strOut);
				result = Operators();
				if (0 == (result - (int) result)) {
					Display.setText(String.valueOf((int) result));
				} else {
					Display.setText(String.valueOf(result));
				}
				num1 = result;
			}
			FirstPress = true;
			point = false;
			LastPressEqual = false;
			operator = 2;
		} else if (e.getActionCommand().equals("×")) {
			if (operator == 0) {
				num1 = Double.parseDouble(strOut);
			} else {
				num2 = Double.parseDouble(strOut);
				result = Operators();
				if (0 == (result - (int) result)) {
					Display.setText(String.valueOf((int) result));
				} else {
					Display.setText(String.valueOf(result));
				}
				num1 = result;
			}
			FirstPress = true;
			point = false;
			LastPressEqual = false;
			operator = 3;
		} else if (e.getActionCommand().equals("÷")) {
			if (operator == 0) {
				num1 = Double.parseDouble(strOut);
			} else {
				num2 = Double.parseDouble(strOut);
				result = Operators();
				if (0 == (result - (int) result)) {
					Display.setText(String.valueOf((int) result));
				} else {
					Display.setText(String.valueOf(result));
				}
				num1 = result;
			}
			FirstPress = true;
			point = false;
			LastPressEqual = false;
			operator = 4;
		} else if (e.getActionCommand().equals("退格")) {
			if (LastPressEqual) {
				strOut = "0";
				FirstPress = true;
				LastPressEqual = false;
				point = false;
			} else {
				if (strOut.length() > 1) {
					strOut = String.valueOf(strOut.toCharArray(), 0, strOut.length() - 1);
				} else {
					strOut = "0";
					FirstPress = true;
					point = false;
				}
			}
			Display.setText(strOut);
		} else if (e.getActionCommand().equals("清空")) {
			strOut = "0";
			result = 0;
			num1 = 0;
			num2 = 0;
			FirstPress = true;
			point = false;
			LastPressEqual = false;
			operator = 0;
			Display.setText(strOut);
		} else if (e.getActionCommand().equals(".")) {
			//对小数点按下的处理
			if (!point) {
				if (FirstPress) {
					strOut = "0" + e.getActionCommand();
					FirstPress = false;
				} else {
					strOut += e.getActionCommand();
				}
				point = true;
			}
			Display.setText(strOut);
		} else if (e.getActionCommand().equals("0")) {
			//对0按下的处理
			if (!FirstPress) {
				strOut += e.getActionCommand();
			} else {
				strOut = e.getActionCommand();
			}
			Display.setText(strOut);
		} else {
			//对1~9按下的处理
			if (FirstPress) {
				strOut = e.getActionCommand();
				FirstPress = false;
			} else {
				strOut += e.getActionCommand();
			}
			Display.setText(strOut);
		}
	}
}

class MyCalculator extends JFrame {

	private static final long serialVersionUID = 1L;

	private String showStr = "", remainStr = "", method = "";
	private boolean isCalculated = false;

	private void setShowStr(String showStr) {
		this.showStr = showStr;
	}

	private String[] str = { "C", "CE", "+/-", "7", "8", "9", "4", "5", "6", "1", "2", "3", "0", ".", "=", "+", "-", "*", "/" };
	JButton[] jbt = new JButton[str.length];
	JTextField jtf = new JTextField();

	public MyCalculator() {
		for (int i = 0; i < str.length; i++) {
			jbt[i] = new JButton(str[i]);
		}

		JPanel p1 = new JPanel();
		p1.setLayout(new GridLayout(5, 3));
		for (int i = 0; i < str.length - 4; i++) {
			p1.add(jbt[i]);
		}

		JPanel p2 = new JPanel();
		p2.setLayout(new GridLayout(4, 1));
		for (int i = str.length - 4; i < str.length; i++) {
			p2.add(jbt[i]);
		}
		add(jtf, BorderLayout.NORTH);
		add(p1, BorderLayout.CENTER);
		add(p2, BorderLayout.EAST);

		ButtonListener listener = new ButtonListener();
		for (int i = 0; i < str.length; i++) {
			jbt[i].addActionListener(listener);
		}
	}

	class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String s = e.getActionCommand();
			if (s == "C" && showStr.length() > 0) {
				showStr = showStr.substring(0, showStr.length() - 1);
				jtf.setText(showStr);
			}//clear
			else if (s == "CE") {
				showStr = "";
				remainStr = "";
				isCalculated = false;
				jtf.setText(showStr);
			}
			//clear all
			else if (s == "+" || s == "-" || s == "*" || s == "/") {
				remainStr = showStr;
				showStr = "";
				method = s;
				jtf.setText(showStr);
			} else if (s == "+/-" && showStr == "") {
				showStr += "-";
				jtf.setText(showStr);
			} else if (s == "=") {
				jtf.setText(calculation(remainStr, showStr, method));
			} else {
				if (isCalculated) {
					showStr = "";
					jtf.setText(showStr);
					isCalculated = false;
				}
				showStr += s;
				jtf.setText(showStr);
			}//number
		}
	}

	private String calculation(String s1, String s2, String s3) {
		Double num1 = Double.parseDouble(s1);
		Double num2 = Double.parseDouble(s2);
		isCalculated = true;
		if (s3 == "+") {
			setShowStr(num1 + num2 + "");
			return num1 + num2 + "";
		} else if (s3 == "-") {
			setShowStr(num1 - num2 + "");
			return num1 - num2 + "";
		} else if (s3 == "*") {
			setShowStr(num1 * num2 + "");
			return num1 * num2 + "";
		} else if (s3 == "/") {
			setShowStr(num1 / num2 + "");
			return num1 / num2 + "";
		}
		return "error";
	}

	public static void main(String[] args) {
		JFrame frame = new MyCalculator();
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
