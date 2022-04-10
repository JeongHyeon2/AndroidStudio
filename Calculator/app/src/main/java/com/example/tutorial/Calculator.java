package com.example.tutorial;

public class Calculator {
	private LinkedListQueue<Token> infix = new LinkedListQueue<Token>(); // ����ǥ��
	private LinkedListQueue<Token> postfix = new LinkedListQueue<Token>(); // ����ǥ��
	private ArrayStack<Token> stack = new ArrayStack<Token>();
	private String postfixNotation = "";

	public Calculator() throws Exception {
		stack.push(new Token("#")); // ���� �ٴ�
	}

	public String getAnswer() {
		return stack.pop().getToken();
	}

	public String getPostfixNotation() {
		return postfixNotation;
	}

	public void cal(String s) throws Exception {
		stringToQueue(s);
		infixToPostfix();
		calculate();
	}

	private void stringToQueue(String s) throws Exception {
		String sArray[] = s.trim().split(" "); // �յ� ���� ���� �� �迭�� ����
		for (int i = 0; i < sArray.length; i++) {

			if (sArray[i].equals("-")) {
				if (i == 0)
					infix.enqueue(new Token("m")); // ���׿����� -�� ���
				else {
					if (isOperator(sArray[i - 1]))
						infix.enqueue(new Token("m"));// ���׿����� -�� ���
					else
						infix.enqueue(new Token(sArray[i])); // ���׿����� -�� ���
				}
			} else
				infix.enqueue(new Token(sArray[i]));

		}
	}

	private void infixToPostfix() throws Exception {
		int count = infix.size();

		for (int i = 0; i < count; i++) {
			tokenInsertToStack(infix.dequeue());
		}
		while (!stack.isEmpty()) {
			Token tmp = stack.pop();
			if (tmp.getICP() == 0)
				throw new Exception("���� ����!"); // ���ÿ� ��ȣ�� ���������� ����
			if (!tmp.getToken().equals("#"))
				postfix.enqueue(tmp);
		}
	}

	private void tokenInsertToStack(Token t) throws Exception {
		if (t.isOperator()) // �����ڸ� stack�� �ֱ�
		{
			if (t.getToken().equals(")")) // �ݴ� ��ȣ�϶�
			{
				while (true) {
					Token token = stack.pop();
					if (token.getToken().equals("("))
						break;
					postfix.enqueue(token);
				}

			} else { // �ݴ� ��ȣ�� �ƴ� �������϶�
				if (stack.top().getISP() == 1) { // ���� �������϶�
					while (stack.top().getISP() < t.getICP()) {
						Token top = stack.pop();
						postfix.enqueue(top);

					}
				} else { // �ƴҶ�
					while (stack.top().getISP() <= t.getICP()) {
						Token top = stack.pop();
						postfix.enqueue(top);

					}
				}
				stack.push(t);

			}
		} else { // ���ڸ� ���
			postfix.enqueue(t);
		}

	}

	private void calculate() throws Exception {
		int size = postfix.size();

		for (int i = 0; i < size; i++) {
			Token t = postfix.dequeue();
			postfixNotation += t.getToken() + " ";

			if (t.isOperator()) { // �������϶�
				try {
					if (t.getToken().equals("m") || t.getToken().equals("~")) { // ���� �������϶�
						int num1 = Integer.parseInt(stack.pop().getToken());
						stack.push(new Token(calculate(num1, t.getToken())));
					} else {// ���� �������϶�
						double num1 = Double.parseDouble(stack.pop().getToken());
						double num2 =Double.parseDouble(stack.pop().getToken());

						stack.push(new Token(calculate(num2, num1, t.getToken()))); // ��� �� �� stack�� ����
					}
				} catch (Exception e) { // ���׿����ڰ� ���ö� pop�� �ΰ��� ���ڰ� ������ ���� �߻�
					throw new Exception();
				}

			} else { // �����϶�
				stack.push(t);
			}
		}
	}

	private double calculate(double num1, double num2, String s) throws Exception {
		switch (s) {
		case "*":
			return num1 * num2;
		case "/":
			return num1 / num2;
		case "%":
			return num1 % num2;
		case "+":
			return num1 + num2;
		case "-":
			return num1 - num2;
		}
		throw new Exception();
	}

	private int calculate(int num1, String s) throws Exception {
		switch (s) {
		case "m":
			return -1 * num1;
		case "~":
			return ~num1;
		}
		throw new Exception();
	}

	private boolean isOperator(String s) {
		try {
			Integer.parseInt(s); // s �� ���ڷ� ��ȯ �Ǹ� �����ڰ� �ƴϹǷ� false
			return false;
		} catch (NumberFormatException e) {
			return true;
		}
	}

}
