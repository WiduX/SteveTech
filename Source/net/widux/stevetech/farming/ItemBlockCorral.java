package net.widux.stevetech.farming;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockCorral extends ItemBlock
{
	
	public static String[] names = {"redCorral", "greenCorral", "blueCorral", "purpleCorral", "yellowCorral", "orangeCorral"};
	
	public ItemBlockCorral(int ID)
	{
		super(ID);
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
	}
	
	public int getPlacedBlockMetadata(int meta)
	{
		return meta;
	}
	
	public int getMetadata(int meta)
	{
		return meta;
	}
	
	public String getTextureFile()
	{
		return "/net/widux/stevetech/farming/Block.png";
	}
	
	public int getIconFromDamage(int meta)
	{
		return SteveTechFarming.corral.getBlockTextureFromSideAndMetadata(0, meta);
	}
	
	public String getUnlocalizedName(ItemStack item)
	{
		return (new StringBuilder()).append("WiduX-BF-ItemBlock-Corral.").append(names[item.getItemDamage()]).toString();
	}
	
}
