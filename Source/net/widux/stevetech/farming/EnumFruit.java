package net.widux.stevetech.farming;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public enum EnumFruit
{
	
	APPLE("Apple", 4, 1, 30, new ItemStack(Item.appleRed)),
	ORANGE("Orange", 4, 1, 30, new ItemStack(Item.arrow)), // TODO Implement fruit items, change these 4 drops.
	PEAR("Pear", 4, 1, 30, new ItemStack(Item.arrow)),
	LEMON("Lemon", 4, 1, 30, new ItemStack(Item.arrow)),
	GRAPEFRUIT("Grapefruit", 4, 1, 30, new ItemStack(Item.arrow));
	
	private String identifier;
	private int growthStages;
	private int maxDrop;
	private int growthSpeed;
	private ItemStack droppedItem;
	
	/**
	 * @param identifier The external identifier of the fruit, what the user sees.
	 * @param growthStages How many stages of growth this fruit has.
	 * @param maxDrop The maximum quantity of fruit that can be dropped when harvested.
	 * @param growthSpeed How period at which the fruit grows (Higher = slower)
	 * @param droppedItem An ItemStack representing what item is dropped when harvested.
	 */
	private EnumFruit(String identifier, int growthStages, int maxDrop, int growthSpeed, ItemStack droppedItem)
	{
		this.identifier = identifier;
		this.growthStages = growthStages;
		this.maxDrop = maxDrop;
		this.growthSpeed = growthSpeed;
		this.droppedItem = droppedItem;
	}
	
	public String getName()
	{
		return this.identifier;
	}
	
	public int getGrowthStages()
	{
		return this.growthStages;
	}
	
	public int getMaxDrop()
	{
		return this.maxDrop;
	}
	
	public int getGrowthSpeed()
	{
		return this.growthSpeed;
	}
	
	public ItemStack getDrop()
	{
		return this.droppedItem;
	}
	
	// Below this point are non-crop-specific details, therefore static.
	
	public static int getNumberFruits()
	{
		return values().length;
	}
	
	public int getID()
	{
		return this.ordinal();
	}
	
	public EnumFruit getFruit(int ID)
	{
		return values()[ID];
	}
	
}
