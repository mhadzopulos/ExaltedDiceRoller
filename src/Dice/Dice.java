package Dice;

public class Dice 
{
	private int sides;
	
	public Dice()
	{
		sides = 1;
	}
	public Dice(int x)
	{
		sides = x;
	}
	public int getSides()
	{
		return sides;
	}
	public void setSides(int x)
	{
		sides = x;
	}
	
}
