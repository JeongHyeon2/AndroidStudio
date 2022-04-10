package com.example.tutorial;

public class Token {
	private String operator; // ������
	private double operand; // �ǿ�����
	private int isp,icp; 
	public Token(String s) throws Exception {	
		if(isOperator(s)) { // �������̸�
			operator =s; // �����ڿ� ����
			setPriority(s);
		}
		else operand=Integer.parseInt(s); // �ǿ�����(����)�̸� �ǿ����ڿ� ����
	}
	public Token(double n) {
		operand=n;
	}
	public boolean isOperator(String s) {
		try {
			Integer.parseInt(s); // s �� ���ڷ� ��ȯ �Ǹ� �����ڰ� �ƴϹǷ� false
			return false;
		} catch (NumberFormatException e) {
			return true; 
		}
	}
	public boolean isOperator() {
		if(operator==null) return false;
		return true;
	}
	public String getToken() {
		if(operator==null) return Double.toString(operand); // �����ڰ� null (��, token�� ������ ���)
		return operator;
	}
	public int getISP() {
		return isp;
	}
	public int getICP() {
		return icp;
	}
	private void setPriority(String s) throws Exception {
		switch(s) {
		case "(": case ")":	isp=8; icp=0; break;
		case "m": case "~": isp=1; icp=1; break;
		case "*": case "/": case"%":isp=2; icp=2; break;
		case "+": case "-": isp=3; icp=3; break;
		case "<<": case ">>": isp=4; icp=4; break;
		case "&": isp=5; icp=5; break;
		case "^": isp=6; icp=6; break;
		case "|": isp=7; icp=7; break;
		case "#": isp=10; icp=10; break;
		default : throw new Exception();	
		}
	}
}


