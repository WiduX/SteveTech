package net.widux.stevetech.farming;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;

public class CommonProxy
{
	
	public void addComponents()
	{
		//SteveTechFarming.corral = new BlockCorral(SteveTechFarming.config.getBlockID(1201, "Corral", null)).setBlockName("WiduX-SteveTech-Farm-Corral");
		SteveTechFarming.fruit = new BlockFruit(SteveTechFarming.config.getBlockID(1202, "Fruit", null)).setBlockName("WiduX-SteveTech-Farm-Fruit");
		SteveTechFarming.crops = new BlockCrops(SteveTechFarming.config.getBlockID(1203, "Crops", null)).setBlockName("WiduX-SteveTech-Farm-Crops");
		
		SteveTechFarming.seeds = new ItemSeeds(SteveTechFarming.config.getItemID(11000, "Seeds", null)).setItemName("WiduX-SteveTech-Farm-Seeds");
		SteveTechFarming.harvestedItems = new ItemHarvest(SteveTechFarming.config.getItemID(11002, "Harvest", null)).setItemName("WiduX-SteveTech-Farm-Harvest");
		SteveTechFarming.creativeTools = new ItemCreativeTools(SteveTechFarming.config.getItemID(11001, "Creative Tools", null)).setItemName("WiduX-SteveTech-Farm-CTools");
		SteveTechFarming.foods = new Item[EnumFood.getNumberFoods()];
		int firstFoodItemID = SteveTechFarming.config.getItemID(11003, "Foods Array", "This is the first item ID in the list of foods. Item IDs used will start here, and use the next " + EnumFood.getNumberFoods() + " IDs. Make sure they are all available.");
		for(int idOffset = 0; idOffset < SteveTechFarming.foods.length; idOffset++)
		{
			EnumFood food = EnumFood.getFood(idOffset);
			SteveTechFarming.foods[idOffset] = new ItemSTFood(firstFoodItemID + idOffset, food).setItemName("WiduX-SteveTech-Farm-Foods-Food" + idOffset);
		}
	}
	
	public void registerRenderers()
	{
		//Not done server-side.
	}
	
	public void registerNames()
	{
		// Not done server-side.
	}
	
	public void registerCrafting()
	{
		GameRegistry.addRecipe(new ItemStack(Block.dirt, 3), new Object[]{"ddd", 'd', Block.dirt}); // TODO Remove this.
	}
	
	public void registerBlocks()
	{
		//GameRegistry.registerBlock(SteveTechFarming.corral, ItemBlockCorral.class, "WiduX-SteveTech-Farm-Corral");
		GameRegistry.registerBlock(SteveTechFarming.fruit, "WiduX-SteveTech-Farm-Fruit");
		GameRegistry.registerBlock(SteveTechFarming.crops, "WiduX-SteveTech-Farm-Crops");
		GameRegistry.registerTileEntity(TileEntityFruit.class, "WiduX-SteveTech-Farm-Fruit-TE");
		GameRegistry.registerTileEntity(TileEntityCrop.class, "WiduX-SteveTech-Farm-Crop-TE");
	}
	
	public void addGeneration()
	{
		//GameRegistry.registerWorldGenerator(new GenerateUnderwater());
	}
	
}
