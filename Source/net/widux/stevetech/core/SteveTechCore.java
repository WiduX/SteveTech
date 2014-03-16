package net.widux.stevetech.core;

import java.util.ArrayList;

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

@Mod(modid = SteveTechCore.MOD_INTERNAL_NAME, name = SteveTechCore.MOD_EXTERNAL_NAME, version = SteveTechCore.MOD_VERSION)
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class SteveTechCore
{

	public static final String MOD_INTERNAL_NAME = "WiduX-SteveTechCore";
	public static final String MOD_EXTERNAL_NAME = "SteveTech Core";
	public static final String MOD_VERSION = "r0";
	
	@Instance(MOD_INTERNAL_NAME)
	public static SteveTechCore instance;
	
	@SidedProxy(clientSide = "net.widux.stevetech.core.ClientProxy", serverSide = "net.widux.stevetech.core.CommonProxy")
	public static CommonProxy proxy;
	
	public ArrayList<String> installedAddons = new ArrayList<String>();
	
	@PreInit
	public void preInit(FMLPreInitializationEvent event)
	{
		
	}
	
	@Init
	public void init(FMLInitializationEvent event)
	{
		proxy.addComponents();
		proxy.registerBlocks();
		proxy.registerNames();
		proxy.addCrafting();
		proxy.registerRenderers();
		proxy.addGeneration();
	}
	
	@PostInit
	public void postInit(FMLPostInitializationEvent event)
	{
		
	}
	
}
