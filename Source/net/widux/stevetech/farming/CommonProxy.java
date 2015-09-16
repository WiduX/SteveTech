package net.widux.stevetech.farming;

import net.minecraft.block.Block;
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
		GameRegistry.addRecipe(new ItemStack(Block.dirt, 3), new Object[]{"ddd", 'd', Block.dirt});
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
