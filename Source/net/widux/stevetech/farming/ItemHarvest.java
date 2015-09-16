package net.widux.stevetech.farming;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemHarvest extends Item
{
	
	public static final String[] names = {"Tomato", "Cucumber", "Eggplant", "Tea Leaves", "Raw Coffee Beans", "Roasted Coffee Beans", "Lettuce", "Radishes"};
	public static final String[] info = {"", "", "", "", "Should be roasted before use", "", "", ""};
	
	public ItemHarvest(int ID)
	{
		super(ID);
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
		this.setCreativeTab(SteveTechFarming.stFarmingTab);
	}
	
	public String getTextureFile()
	{
		return "/net/widux/stevetech/farming/Item.png";
	}
	
	public int getIconFromDamage(int meta)
	{
		return 16 + meta;
	}
	
	public String getItemNameIS(ItemStack item)
	{
		return "WiduX-BF-Item-Harvest." + names[item.getItemDamage()];
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void addInformation(ItemStack item, EntityPlayer player, List list, boolean flag)
	{
		if(!"".equals(info[item.getItemDamage()])) // If this item has an extended description
		{
			list.add(info[item.getItemDamage()]);
		}
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
