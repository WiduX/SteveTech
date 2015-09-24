package net.widux.stevetech.farming;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;

public class ItemSTFood extends ItemFood
{
	
	private EnumFood food;
	
	public ItemSTFood(int itemID, EnumFood foodType)
	{
		super(itemID, foodType.getHealAmount(), foodType.getSaturation(), foodType.willWolfEat());
		this.setCreativeTab(SteveTechFarming.stFarmingTab);
		this.food = foodType;
	}
	
	public String getTextureFile()
	{
		return "/net/widux/stevetech/farming/Foods.png";
	}
	
	public int getIconFromDamage(int meta)
	{
		return 0 + food.getID();
	}
	
	public String getItemNameIS(ItemStack item)
	{
		return "WiduX-BF-Item-Foods." + food.getName();
	}
	
	public EnumFood getFood() {return food;}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void addInformation(ItemStack item, EntityPlayer player, List list, boolean flag)
	{
		ItemSTFood foodItem = (ItemSTFood) item.getItem();
		list.add("Restores: " + (foodItem.getFood().getHealAmount() / 2.0F));
	}
	
}
