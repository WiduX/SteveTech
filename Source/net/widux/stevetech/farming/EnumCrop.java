package net.widux.stevetech.farming;

import net.minecraft.item.ItemStack;

public enum EnumCrop
{
	
	TOMATO		("Tomato",		8, 2, 5, 3, 80,  new ItemStack(SteveTechFarming.harvestedItems, 1, 0),	new ItemStack(SteveTechFarming.seeds, 1, 0),	true, true),
	ONION		("Onion",		4, 1, 4, 1, 110, new ItemStack(SteveTechFarming.seeds, 1, 1),			null,											false, false),
	CUCUMBER	("Cucumber",	6, 1, 4, 4, 60,  new ItemStack(SteveTechFarming.harvestedItems, 1, 1),	new ItemStack(SteveTechFarming.seeds, 1, 2),	true, true),
	EGGPLANT	("Eggplant",	7, 1, 5, 3, 140, new ItemStack(SteveTechFarming.harvestedItems, 1, 2),	new ItemStack(SteveTechFarming.seeds, 1, 3),	true, true),
	TEA			("Tea",			4, 1, 5, 3, 100, new ItemStack(SteveTechFarming.harvestedItems, 1, 3),	new ItemStack(SteveTechFarming.seeds, 1, 4),	true, true),
	COFFEE		("Coffee",		6, 1, 6, 1, 100, new ItemStack(SteveTechFarming.seeds, 1, 5),			null,											false, false),
	LETTUCE		("Lettuce",		5, 1, 5, 3, 55,  new ItemStack(SteveTechFarming.harvestedItems, 1, 4),	new ItemStack(SteveTechFarming.seeds, 1, 6),	true, true),
	RADISHES    ("Radishes",	5, 1, 3, 2, 40,  new ItemStack(SteveTechFarming.harvestedItems, 1, 5),	new ItemStack(SteveTechFarming.seeds, 1, 7),	true, true);
	//CABBAGE
	//BELLPEPPER
	//PEAS
	//BEANS
	//STRWABERRY
	//RASPBERRY
	//BLUEBERRY
	//HUCKLEBERRY
	//BLACKBERRY
	//BANANA
	//GRAPE
	//CANTALOUPE
	//GREENONION
	//PARSLEY
	//PEPPER
	//RICE
	//VANILLA
	//CORN
	//GARLIC
	//GINGER
	//CHILI
	
	// Old:
	//BEANS		("Beans",		7, 2, 5, 1, 50, new ItemStack(SteveTechFarming.seeds, 1, 1),			null,											false, false),
	//BELLPEPPER	("Bell Pepper",	7, 1, 4, 3, 70, new ItemStack(SteveTechFarming.harvestedItems, 1, 0),	new ItemStack(SteveTechFarming.seeds, 1, 3),	true, true),
	//LETTUCE		("Lettuce",		0, 1, 5, 4, 55, new ItemStack(SteveTechFarming.harvestedItems, 1, 6),	new ItemStack(SteveTechFarming.seeds, 1, 6),	true, true);
	
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
	 * @param growthSpeed The growth speed of the plant. Lower = faster, use actual plant growth days.
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
	
	public int getID()
	{
		return this.ordinal();
	}
	
	/*
	 * Below this point are non-crop-specific details, therefore static.
	 */
	
	public static int getNumberCrops()
	{
		return values().length;
	}
	
	public static EnumCrop getCrop(int ID)
	{
		return values()[ID];
	}
	
}
