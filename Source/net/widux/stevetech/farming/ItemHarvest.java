package net.widux.stevetech.farming;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemHarvest extends Item
{
	
	public static final String[] names = {"Tomato", "Onion", "Cucumber", "Eggplant", "Tea Leaves", "Raw Coffe Beans", "Roasted Coffee Beans", "Lettuce", "Radishes"};
	
	public ItemHarvest(int ID)
	{
		super(ID);
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
		this.setCreativeTab(SteveTechFarming.betterFarmingTab);
	}
	
	public String getTextureFile()
	{
		return "/widux/betterfarming/Item.png";
	}
	
	public int getIconFromDamage(int meta)
	{
		return 16 + meta;
	}
	
	public String getUnlocalizedName(ItemStack item)
	{
		return "WiduX-BF-Item-Harvest." + names[item.getItemDamage()];
	}
	
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public void getSubItems(int i, CreativeTabs tab, List items)
	{
		for(int meta = 0; meta < names.length; meta++)
		{
			items.add(new ItemStack(this, 1, meta));
		}
	}
	
}
