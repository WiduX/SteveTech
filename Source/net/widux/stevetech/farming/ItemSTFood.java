package net.widux.stevetech.farming;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;

public class ItemSTFood extends ItemFood
{
	
	public ItemSTFood(int itemID)
	{
		super(itemID, healAmount, saturation, willWolfEat);
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
		this.setCreativeTab(SteveTechFarming.stFarmingTab);
	}
	
	public String getTextureFile()
	{
		return "/net/widux/stevetech/farming/Foods.png";
	}
	
	public int getIconFromDamage(int meta)
	{
		return 0 + meta;
	}
	
	public String getItemNameIS(ItemStack item)
	{
		return "WiduX-BF-Item-Foods." + EnumFood.getFood(item.getItemDamage()).getName();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void getSubItems(int i, CreativeTabs tab, List items)
	{
		for(int meta = 0; meta < EnumFood.getNumberFoods(); meta++)
		{
			items.add(new ItemStack(this, 1, meta));
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void addInformation(ItemStack item, EntityPlayer player, List list, boolean flag)
	{
		list.add("Restores: " + EnumFood.getFood(item.getItemDamage()).getHealAmount() / 2.0F);
	}
	
}
