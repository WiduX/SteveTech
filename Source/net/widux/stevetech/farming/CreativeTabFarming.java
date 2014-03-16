package net.widux.stevetech.farming;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class CreativeTabFarming extends CreativeTabs
{
	
	public CreativeTabFarming(String name)
	{
		super(name);
	}
	
	public ItemStack getIconItemStack()
	{
		return new ItemStack(SteveTechFarming.seeds, 1, 0);
	}
	
}
