package net.widux.stevetech.farming;

import net.minecraft.item.ItemStack;
import net.minecraftforge.client.MinecraftForgeClient;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class ClientProxy extends CommonProxy
{
	
	@Override
	public void registerRenderers()
	{
		MinecraftForgeClient.preloadTexture("/net/widux/stevetech/farming/Block.png");
		MinecraftForgeClient.preloadTexture("/net/widux/stevetech/farming/Crops.png");
		MinecraftForgeClient.preloadTexture("/net/widux/stevetech/farming/Fruits.png");
		MinecraftForgeClient.preloadTexture("/net/widux/stevetech/farming/Item.png");
		MinecraftForgeClient.preloadTexture("/net/widux/stevetech/farming/Seed.png");
		MinecraftForgeClient.preloadTexture("/net/widux/stevetech/farming/Food.png");
		SteveTechFarming.renderFruitID = RenderingRegistry.getNextAvailableRenderId();
		SteveTechFarming.renderCropID = RenderingRegistry.getNextAvailableRenderId();
		RenderingRegistry.registerBlockHandler(SteveTechFarming.renderFruitID, new RenderFruit());
		RenderingRegistry.registerBlockHandler(SteveTechFarming.renderCropID, new RenderCrop());
	}
	
	@Override
	public void registerNames()
	{
		/*for(int meta = 0; meta < 6; meta++)
		{
			LanguageRegistry.addName(new ItemStack(SteveTechFarming.corral, 1, meta), "Corral");
		}*/
		
		LanguageRegistry.addName(new ItemStack(SteveTechFarming.crops, 1), "Crop Block");
		
		for(int meta = 0; meta < EnumCrop.getNumberCrops(); meta++)
		{
			if(EnumCrop.getCrop(meta).getHasNameSuffix())
			{
				LanguageRegistry.addName(new ItemStack(SteveTechFarming.seeds, 1, meta), EnumCrop.getCrop(meta).getName() + " Seeds");
			}
			else
			{
				LanguageRegistry.addName(new ItemStack(SteveTechFarming.seeds, 1, meta), EnumCrop.getCrop(meta).getName());
			}
			
		}
		
		for(int meta = 0; meta < ItemHarvest.names.length; meta++)
		{
			LanguageRegistry.addName(new ItemStack(SteveTechFarming.harvestedItems, 1, meta), ItemHarvest.names[meta]);
		}
		
		for(int idOffset = 0; idOffset < EnumFood.getNumberFoods(); idOffset++)
		{
			LanguageRegistry.addName(new ItemStack(SteveTechFarming.foods[idOffset], 1), EnumFood.getFood(idOffset).getName());
		}
		
		LanguageRegistry.addName(new ItemStack(SteveTechFarming.creativeTools, 1, 0), "Growth Progressor");
		LanguageRegistry.addName(new ItemStack(SteveTechFarming.creativeTools, 1, 1), "Growth Halter");
		LanguageRegistry.addName(new ItemStack(SteveTechFarming.creativeTools, 1, 2), "Full Fertilizer");
		LanguageRegistry.addName(new ItemStack(SteveTechFarming.creativeTools, 1, 3), "Plant Info");
		
		LanguageRegistry.instance().addStringLocalization("itemGroup.SteveTechFarming", "en_US", "SteveTech Farming");
		LanguageRegistry.addName(new ItemStack(SteveTechFarming.fruit, 1), "Fruit");
	}
	
}

