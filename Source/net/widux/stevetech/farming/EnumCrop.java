package net.widux.stevetech.farming;

import net.minecraft.item.ItemStack;

public enum EnumCrop
{
	
	TOMATO("Tomato", 8, 2, 5, 3, 80, new ItemStack(SteveTechFarming.harvestedItems, 1, 0), new ItemStack(SteveTechFarming.seeds, 1, 0), true, true),
	BEANS("Beans", 7, 2, 5, 1, 50, new ItemStack(SteveTechFarming.seeds, 1, 1), null, false, false),
	ONION("Onion", 4, 1, 4, 1, 110, new ItemStack(SteveTechFarming.seeds, 1, 2), null, false, false),
	BELLPEPPER("Bell Pepper", 7, 1, 4, 3, 70, new ItemStack(SteveTechFarming.harvestedItems, 1, 0), new ItemStack(SteveTechFarming.seeds, 1, 3), true, true),
	CUCUMBER("Cucumber", 0, 3, 4, 4, 60, new ItemStack(SteveTechFarming.harvestedItems, 1, 5), new ItemStack(SteveTechFarming.seeds, 1, 5), true, true),
	LETTUCE("Lettuce", 0, 1, 5, 4, 55, new ItemStack(SteveTechFarming.harvestedItems, 1, 6), new ItemStack(SteveTechFarming.seeds, 1, 6), true, true);
	
	private String identifier;
	private int growthStages;
	private int maxHeight;
	private int maxDropHarvest;
	private int maxDropSeed;
	private int growthSpeed;
	private ItemStack dropHarvest;
	private ItemStack dropSeed;
	private boolean willDropSeed;
	private boolean nameSuffix;
	
	/**
	 * @param identifier The unique name of the crop
	 * @param growthStages The amount of different textures used in the plant's growth
	 * @param maxHeight The maximum height of the plant (in blocks)
	 * @param maxDropHarvest The maximum amount of harvest items dropped when harvested
	 * @param maxDropSeed The maximum seed items dropped when harvested
	 * @param growthSpeed The growth speed of the plant. Lower = faster
	 * @param dropHarvest The item dropped for the harvest
	 * @param dropSeed The item dropped for the seed
	 * @param willDropSeed Does this crop drop seeds?
	 * @param nameSuffix Does the seed have the suffix "Seed" in the name?
	 */
	private EnumCrop(String identifier, int growthStages, int maxHeight, int maxDropHarvest, int maxDropSeed, int growthSpeed, ItemStack dropHarvest, ItemStack dropSeed, boolean willDropSeed, boolean nameSuffix)
	{
		this.identifier = identifier;
		this.growthStages = growthStages;
		this.maxHeight = maxHeight;
		this.maxDropHarvest = maxDropHarvest;
		this.maxDropSeed = maxDropSeed;
		this.growthSpeed = growthSpeed;
		this.dropHarvest = dropHarvest;
		this.dropSeed = dropSeed;
		this.willDropSeed = willDropSeed;
		this.nameSuffix = nameSuffix;
	}
	
	/*
	 * A get method for every trait of the crop.
	 */
	
	public String getName()
	{
		return this.identifier;
	}
	
	public int getGrowthStages()
	{
		return this.growthStages;
	}
	
	public int getMaxHeight()
	{
		return this.maxHeight;
	}
	
	public int getMaxDropHarvest()
	{
		return this.maxDropHarvest;
	}
	
	public int getMaxDropSeed()
	{
		return this.maxDropSeed;
	}
	
	public int getGrowthSpeed()
	{
		return this.growthSpeed;
	}
	
	public ItemStack getDropHarvest()
	{
		return this.dropHarvest;
	}
	
	public ItemStack getDropSeed()
	{
		return this.dropSeed;
	}
	
	public boolean getDropsSeed()
	{
		return this.willDropSeed;
	}
	
	public boolean getHasNameSuffix()
	{
		return this.nameSuffix;
	}
	
	/*
	 * Below this point are non-crop-specific details, therefore static.
	 */
	
	public static int getNumberCrops()
	{
		return values().length;
	}
	
	public int getID()
	{
		return this.ordinal();
	}
	
	public EnumCrop getCrop(int ID)
	{
		return this.values()[ID];
	}
}
