package Roller;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Dice.Dice;

public class Roller extends JFrame
{
	private JTextField Rolls, Sides, output, rollsOutput;
	private JCheckBox fivesCount;
	ArrayList<Integer> outputArray = new ArrayList<Integer>();
	private JLabel RollsL, SidesL;
	private JButton RollDice, RollAgain;
	//private ButtonGroup radioGroup;
	//private JRadioButton fivesCount;
	
	Dice d = new Dice(10);
	public Roller()
	{
		super("Exalted Roller");
		
		this.getContentPane();
		this.setLayout(new FlowLayout());
		Rolls = new JTextField(4);
		Sides = new JTextField(3);
		output = new JTextField(20);
		rollsOutput = new JTextField(50);
		RollsL = new JLabel("# of Rolls");
		SidesL = new JLabel("# of Sides");
		RollDice = new JButton("Roll");
		RollAgain = new JButton("Roll Success");
		
		fivesCount = new JCheckBox("5's Count");
		this.add(RollsL);
		this.add(Rolls);
		this.add(SidesL);
		this.add(Sides);
		
		this.add(RollDice);
		this.add(RollAgain);
		this.add(output);
		this.add(fivesCount);
		this.add(rollsOutput);
		
		setSize(640, 300);
		setVisible(true);
		
		ButtonHandler handlerB= new ButtonHandler();
		RollDice.addActionListener(handlerB);
		RollAgain.addActionListener(handlerB);
	}
	public static void main(String [] args)
	{
		Roller application = new Roller();
		application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public class ButtonHandler implements ActionListener 
	{ 
		public void actionPerformed(ActionEvent event)
		{
			if(event.getSource() == RollDice)
			{ 
				boolean Jack = false;
				if(fivesCount.isSelected())
					Jack = true;
				rollsOutput.setText(" ");
				outputArray.clear();
				Random r = new Random();
				String s = Rolls.getText();
				int x = Integer.valueOf(s);
				String k = Sides.getText();
				int w;
				
				if(k.equals("") != true)
				{
					w = Integer.valueOf(k);
					d.setSides(w);
				}			
				
				Integer success = 0;
				for(int y = 0; y < x; y++ )
				{
					if(d.getSides() == 10)
					{
						int z = r.nextInt(d.getSides()) + 1;
						outputArray.add(z);
						if (z > 6 && z < 10)
						{
							success++;
						}
						if (z == 10)
						{
							success= success + 2;
						}
					}
					
					if(d.getSides() == 6 )
					{
						int z = r.nextInt(d.getSides()) + 1;
						outputArray.add(z);
						if (z == 6)
						{
							success++;
						}
						if(Jack == true && z == 5)
						{
							success++;
						}
					}
					
				}
				
				System.out.println("Success = " + success);
				output.setText(success.toString());
				
				Collections.sort(outputArray);
				Collections.reverse(outputArray);
				rollsOutput.setText(outputArray.toString());
			    
			}
			if(event.getSource() == RollAgain)
			{
				Random r = new Random();
				Integer success = Integer.valueOf(output.getText());
				Integer newSuccess = 0;
				for(int i = 0; i < success; i++)
				{
					int z = r.nextInt(d.getSides()) + 1;
					if(z > 6 && newSuccess < 10)
					{
						newSuccess++;
					}
					if(z == 10)
					{
						newSuccess = newSuccess + 2;
					}
				}
				System.out.println(newSuccess);
				output.setText(newSuccess.toString());
			}
		}
	}
			
}				
