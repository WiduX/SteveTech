package net.widux.stevetech.farming;

import java.io.File;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.widux.stevetech.core.ConfigAssist;
import net.widux.stevetech.core.SteveTechCore;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;

@Mod(modid = SteveTechFarming.MOD_INTERNAL_NAME, name = SteveTechFarming.MOD_EXTERNAL_NAME, version = SteveTechCore.MOD_VERSION)
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class SteveTechFarming
{
	
	public static final String MOD_INTERNAL_NAME = "WiduX-SteveTechFarm";
	public static final String MOD_EXTERNAL_NAME = "SteveTech Core";
	
	public static ConfigAssist config;
	
	public static Block crops;
	//public static Block corral;
	//public static Block leaves;
	//public static Block logs;
	//public static Block fruit;
	
	//public static TileEntityFruit teFruit;
	public static TileEntityCrop teCrop;
	
	public static Item seeds;
	public static Item harvestedItems;
	public static Item creativeTools;
	
	public static CreativeTabs betterFarmingTab = new CreativeTabFarming("BetterFarming");
	
	//public static int renderFruitID;
	public static int renderCropID;
	
	@Instance(MOD_INTERNAL_NAME)
	public static SteveTechFarming instance;
	
	@SidedProxy
	(
		clientSide="net.widux.stevetech.farming.ClientProxy",
		serverSide="net.widux.stevetech.farming.CommonProxy"
	)
	public static CommonProxy proxy;

	@PreInit
	public void preInit(FMLPreInitializationEvent event)
	{
		config = new ConfigAssist(MOD_EXTERNAL_NAME, new File(event.getModConfigurationDirectory() + "/SteveTech/Farming.cfg"));
		config.init();
	}

	@Init
	public void load(FMLInitializationEvent event)
	{
		proxy.addComponents();
		proxy.registerBlocks();
		proxy.registerNames();
		proxy.registerCrafting();
		proxy.registerRenderers();
		proxy.addGeneration();
	}

	@PostInit
	public void postInit(FMLPostInitializationEvent event)
	{
		config.save();
	}
	
}
