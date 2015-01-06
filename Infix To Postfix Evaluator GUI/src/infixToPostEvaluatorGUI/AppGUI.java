package infixToPostEvaluatorGUI;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class AppGUI implements ActionListener
{
	
	public JFrame mainFrame;
	
	public  JTextField inputField;
	public  JTextField postFix;
	public  JTextField result;
	
	public JLabel i;
	public JLabel p;
	public JLabel r;
	
	public JButton resultButton;
	public JButton clearButton;
	public JButton exitButton;
	
	public JPanel upperPanel;
	public JPanel lowerPanel;
	
	public GridLayout gl; // for the total frame
	public GridLayout gl2; // for the upper panel
	public GridLayout gl3; // for the lower panel
	
	
	public AppGUI()
	{
		mainFrame = new JFrame("আজাইরা হিসাবের জিনিস");
		mainFrame.setFont(new Font("Siyam Rupali", Font.BOLD, 14));
		
		gl = new GridLayout(2, 1);
		gl2 = new GridLayout(3, 2);
		gl3 = new GridLayout(1, 3);
		
		inputField = new JTextField();
		postFix = new JTextField();	
		result = new JTextField();
		
		
		resultButton = new JButton("হিসাব কর!");
		resultButton.setFont(new Font("Siyam Rupali", Font.BOLD, 14));
		
		clearButton = new JButton("ঝাড়ু মার!");
		clearButton.setFont(new Font("Siyam Rupali", Font.BOLD, 14));
		
		exitButton = new JButton("হারিয়ে যাও!");
		exitButton.setFont(new Font("Siyam Rupali", Font.BOLD, 14));
		
		i = new JLabel("   ইনপুট");
		i.setFont(new Font("Siyam Rupali", Font.BOLD, 14));
		
		p = new JLabel("   আজাইরা জিনিস");
		p.setFont(new Font("Siyam Rupali", Font.BOLD, 14));
		
		r = new JLabel("   শেষ হইসে!");
		r.setFont(new Font("Siyam Rupali", Font.BOLD, 14));
		
		
		upperPanel = new JPanel();
		lowerPanel = new JPanel();
		

		mainFrame.setLayout(gl);
		upperPanel.setLayout(gl2);
		lowerPanel.setLayout(gl3);
		
		upperPanel.add(i);
		upperPanel.add(inputField);
		
		upperPanel.add(p);
		upperPanel.add(postFix);
		
		upperPanel.add(r);
		upperPanel.add(result);
		
		lowerPanel.add(resultButton);
		lowerPanel.add(clearButton);
		lowerPanel.add(exitButton);
		
		
		mainFrame.add(upperPanel);
		mainFrame.add(lowerPanel);
		
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setVisible(true);
		mainFrame.pack();
		mainFrame.setResizable(true);
		mainFrame.setLocationRelativeTo(null);
		
		// adding event handlers
		
		resultButton.addActionListener(this);
		clearButton.addActionListener(this);
		exitButton.addActionListener(this);
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == resultButton)
		{
			CalculationClass c = new CalculationClass();
			String input;
			String output;
			String post;
			
			try
			{
				postFix.setText("");
				result.setText("");
				
				input = inputField.getText();
				post = c.converter(input);
				
				output = "" + c.evaluator();
				
				postFix.setText(post);
				result.setText(output);
			}
			catch(Exception x)
			{
				JOptionPane p = new  JOptionPane();
				p.setFont(new Font("Siyam Rupali", Font.BOLD, 14));
				p.showMessageDialog(null, "আরে ইনপুট তো দিবেন!!!!!", "লাল বাতি! ফায়ার ফায়ার! না কিছু না।", JOptionPane.ERROR_MESSAGE);
			}
		}
		
		
		else if(e.getSource() == exitButton)
		{
			JOptionPane p = new  JOptionPane();
			p.setFont(new Font("Siyam Rupali", Font.BOLD, 14));
			p.showMessageDialog(null, "টাটা! বাই বাই!", "রান দিলে আবার হবে দেখা!", JOptionPane.INFORMATION_MESSAGE);
			System.exit(0);
		}
		
		else
		{
			inputField.setText("");
			postFix.setText("");
			result.setText("");
		}
		
	}

}
