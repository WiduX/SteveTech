package net.widux.stevetech.farming;

public enum Color
{
	
	White, Orange, Magenta, LightBlue, Yellow, Lime, Pink, Gray, LightGray, Cyan, Purple, Blue, Brown, Green, Red, Black;
	
	private String[] colors = {"White", "Orange", "Magenta", "Light Blue", "Yellow", "Lime", "Pink", "Gray", "Light Gray", "Cyan", "Purple", "Blue", "Brown", "Green", "Red", "Black"};
	
	public int getMeta()
	{
		return this.ordinal();
	}
	
	public int getMetaInverted()
	{
		return 15 - this.ordinal();
	}
	
	public Color getColor(int meta)
	{
		return (Color.values())[meta];
	}
	
	public Color getColorInverted(int meta)
	{
		return (Color.values())[15 - meta];
	}
	
	public String getName(int meta)
	{
		return Color.values()[meta].toString();
	}
	
	public String getNameInverted(int meta)
	{
		return Color.values()[15 - meta].toString();
	}
	
	public String getName()
	{
		return this.toString();
	}
	
	public String getNameInverted()
	{
		return Color.values()[15 - this.ordinal()].toString();
	}
}
