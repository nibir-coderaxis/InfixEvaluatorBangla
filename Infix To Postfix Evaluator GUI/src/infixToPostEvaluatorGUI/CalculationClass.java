package infixToPostEvaluatorGUI;


import java.util.*;

public class CalculationClass 
{
	
	private String postfix;
	private double result;
	private int validator;
	
	private Stack s;
	private Stack m;
	
	
	public CalculationClass()
	{
		postfix = "";
		result = 0.0;
		validator = 0;
		
		s = new Stack();
		m = new Stack();
	}
	
	public boolean rightAssociativity(char c)
	{
		if(c == '$') return true;
		return false;
	}
	
	public int opWeight(char c)
	{
		int w = 0;
		
		if(c == '+' || c == '-') w = 1;
		else if(c == '*' || c == '/') w = 2;
		else if(c == '$') w = 3;
		
		return w;
	}
	
	
	public boolean precedency(char operator1, char operator2)
	{
		int op1 = opWeight(operator1);
		int op2 = opWeight(operator2);
		
		if(op1 == op2)
		{
			return rightAssociativity(operator1) ? true : false;
		}
		
		return op1 > op2 ? true : false;
	}

	
	public boolean isValid()
	{
		if(validator == 1) return true;
		return false;
	}

	public boolean isOperator(char c)
	{
		if(c == '+' || c == '-' || c == '*' || c == '/')
			return true;
		
		return false;
	}
	
	
	public String converter(String infix)
	{
		postfix = "";
		
		String in = "(" + infix + ")";
		
		char c, t;
		
		
		for(int i = 0; i < in.length(); i++)
		{
			c = in.charAt(i);
			
			if(c == ' ') continue;
			
			else if(c == '(') s.push(c);
			
			else if(c == ')')
			{
				c = (char) s.pop();
				
				while(c != '(')
				{
					postfix += c;
					c = (char) s.pop();
				}
			}
			
			else if(isOperator(c))
			{
				if(c == '*' || c == '/')
				{
					t = (char) s.pop();
					
					while(t == '*' || t == '/')
					{
						postfix += t;
						t = (char) s.pop();
					}
					
					s.push(t);
				}
				
				else if(c == '+' || c == '-')
				{
					t = (char) s.pop();
					
					while(t != '(')
					{
						postfix += t;
						t = (char) s.pop();
					}
					
					s.push(t);
				}
				
				s.push(c);
			}
			
			
			else postfix += c;
		}
		
		s.empty();
		return postfix;
	}

	
	public double evaluator()
	{
		String p = postfix;
		
		char c;
		double a = 0, b = 0, v = 0, r;
		
		for(int i = 0; i < p.length(); i++)
		{
			c = p.charAt(i);
			
			if(!isOperator(c))
			{
				m.push( (double) (c - '0') );
				v++;
			}
			
			else if(m.isEmpty() == true)
			{
				validator = 0;
				result = 0.0;
				
				return result;
			}
			
			else
			{
				switch(c)
				{
					case '+':
						a = (double) m.pop();
						v--;
						
						if(m.isEmpty() == true)
						{
							validator = 0;
							result = 0.0;
							
							return result;
						}
						
						b = (double) m.pop();
						v--;
						
						result = a + b;
						m.push(result);
						v++;
						
						break;
						
					case '-':
						a = (double) m.pop();
						v--;
						
						if(m.isEmpty() == true)
						{
							validator = 0;
							result = 0.0;
							
							return result;
						}
						
						b = (double) m.pop();
						v--;
						
						if(b > a) 
						{
							double t = a;
							
							a = b;
							b = t;
						}
						
						result = a - b;
						
						m.push(result);
						v++;
						
						break;
						
					case '*':
						a = (double) m.pop();
						v--;
						
						if(m.isEmpty() == true)
						{
							validator = 0;
							result = 0.0;
							
							return result;
						}
						
						b = (double) m.pop();
						v--;
						
						result = a * b;
						m.push(result);
						v++;
						break;
						
					case '/':
						a = (double) m.pop();
						v--;
						
						if(m.isEmpty() == true)
						{
							validator = 0;
							result = 0.0;
							
							return result;
						}
						
						b = (double) m.pop();
						v--;
						
						if(a < b)
						{
							double t = a; 
							
							a = b;
							b = t;
						}
						
						result = a / b;
						m.push(result);
						v++;
						break;
				}
			}
		}
		
		validator = (int) v;
		r = (double) m.pop();
		
		m.empty();
		return r;
	}

	
	

	
}
