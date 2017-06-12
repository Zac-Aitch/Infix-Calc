//Zac Henney : Lab 4
import java.util.Stack;

class Calculator
{
	private String infixExp;
	private String postfixExp = null;
	private Stack<Integer> iStack = new Stack<Integer>();
	private Stack<Character> cStack = new Stack<Character>();



	public Calculator(String exp)
	{
		infixExp = exp;
		convertPostfix();
	}
	
	public String toString()
	{
	//---------------------------------------------------//
	//Returns the infix expression input by user
	//Precondition: none
	//Postcondition: infixExp is returned
	//---------------------------------------------------//
		return infixExp;
	}
	private void convertPostfix()
	{
	//---------------------------------------------------//
	//Converts the user input infix expression into a
	//postfix expression.
	//Precondition: none
	//Postcondition: infixExp converted into a postfix
	//expresssion and placed in postfixExp
	//---------------------------------------------------//

		//loop through infixExp
		for (int i=0 ;i < infixExp.length(); i++) 
		{
			//if char at i is operand either add or append to postfixExp
			if (Character.toString(infixExp.charAt(i)).matches("[0123456789]")) 
			{
				if (postfixExp == null) 
				{	
					postfixExp = Character.toString(infixExp.charAt(i));
				}
				else
				{
					postfixExp += Character.toString(infixExp.charAt(i));
				}
			}

			//if char is '(' then push the char onto the stack
			else if (Character.toString(infixExp.charAt(i)).matches("[(]"))
			{
				cStack.push(infixExp.charAt(i));
			}

			//if char is operator and stack is empty then push to stack. 
			else if (Character.toString(infixExp.charAt(i)).matches("[/*+-]")) 
			{
				if (cStack.empty()) 
				{
					cStack.push(infixExp.charAt(i));	
				}
				//if char is operator and stack is not empty pop then stack 
				//until the top item is less than char or stack is empty
				else
				{
					char top = cStack.peek();;
					while(!cStack.empty())
					{
						top = cStack.peek();
						
						if (top >= infixExp.charAt(i) && top != '(') 
						{
							postfixExp += Character.toString(cStack.pop());
						}

						else
						{
							break;	
						}
					}
					//pop '(' off of stack once matching ')' is found
					/*
					if (top == '(' && infixExp.charAt(i) == ')') 
					{
						cStack.pop();
					}
					
					//else push operator onto stack
					
					else
					{
						*/
						cStack.push(infixExp.charAt(i));
					//}
					
				}
			}
		}
		
		//clean up stack by popping '(' and appending remaining operators to postfixExp
		while(!cStack.empty())
		{
			if (cStack.peek() == '(') 
			{
				cStack.pop();
			}

			else if (cStack.peek() != '(')
			{
				postfixExp += Character.toString(cStack.pop());
			}
		}
	
	}

	public String getPostfix()
	{
	//---------------------------------------------------//
	//Returns postfixExp. If postfixExp == null, convert
	//infixExp to a postfix expression and place in 
	//postfixExp
	//Precondition: postfixExp cannot equal null?
	//Postcondition: postfixExp is returned
	//---------------------------------------------------//
		return postfixExp;
	}
	
	public int evaluate()
	{
	//---------------------------------------------------//
	//After converting infixExp to postfixExp, evaluate  
	//using a stack and the postfixExp
	//Precondition: infixExp must be converted to postfixExp
	//Postcondition: The result of the evaluated postfixExp
	//is placed in int result	
	//---------------------------------------------------//
		int operand1;
		int operand2;
		int result;
		//loop through postfixExp string
		for (int i=0 ;i < postfixExp.length(); i++) 
		{
			//if char is operand push onto stack
			if (Character.toString(postfixExp.charAt(i)).matches("[0123456789]")) 
			{
				int a = Integer.parseInt(String.valueOf(postfixExp.charAt(i)));
				iStack.push(a);
			}
			//else if char is operator then switch to case, evaluate, and push result to stack
			else if (Character.toString(postfixExp.charAt(i)).matches("[-/*+]")) 
			{
				switch(postfixExp.charAt(i))
				{

					case '/': 
								operand2 = iStack.pop();
								operand1 = iStack.pop();
								result = operand1 / operand2;
								iStack.push(result);
								break;

					case '*':
								operand2 = iStack.pop();
								operand1 = iStack.pop();
								result = operand1 * operand2;
								iStack.push(result);
								break;
					case '-':
								operand2 = iStack.pop();
								operand1 = iStack.pop();
								result = operand1 - operand2;
								iStack.push(result);
								break;
					case '+':
								operand2 = iStack.pop();
								operand1 = iStack.pop();
								result = operand1 + operand2;
								iStack.push(result);
								break;
				}
			}		
		}
		//pop result from stack and return
		result = iStack.pop();
		return result;
	}
}