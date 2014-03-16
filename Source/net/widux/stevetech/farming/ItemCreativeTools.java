package net.widux.stevetech.farming;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemCreativeTools extends Item
{
	
	public ItemCreativeTools(int ID)
	{
		super(ID);
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
		this.setCreativeTab(SteveTechFarming.betterFarmingTab);
	}
	
	public String getTextureFile()
	{
		return "/net/widux/stevetech/farming/Item.png";
	}
	
	public int getIconFromDamage(int meta)
	{
		return 0 + meta;
	}
	
	public String getUnlocalizedName(ItemStack item)
	{
		return "WiduX-BF-Item-CreativeTools." + item.getItemDamage();
	}
	
	public boolean onItemUse(ItemStack item, EntityPlayer player, World world, int x, int y, int z, int side, float xOffset, float yOffset, float zOffset)
    {
		int meta = item.getItemDamage();
		int metaBlock = world.getBlockMetadata(x, y, z);
		int blockID = world.getBlockId(x, y, z);
		
		if(meta == 2 && blockID == Block.tilledField.blockID) // Fertilizer on farmland
		{
			world.setBlockMetadataWithNotify(x, y, z, 7);
			return true;
		}
		else if(meta == 0 && blockID == Block.crops.blockID) // Progressor, Wheat
		{
			if(metaBlock < 7)
			{
				metaBlock++;
				world.setBlockMetadataWithNotify(x, y, z, metaBlock);
				return true;
			}
		}
		else if(meta == 0 && blockID == Block.carrot.blockID) // Progressor, Carrot
		{
			if(metaBlock < 5)
			{
				metaBlock += 2;
				world.setBlockMetadataWithNotify(x, y, z, metaBlock);
				return true;
			}
		}
		else if(meta == 0 && blockID == Block.potato.blockID) // Progressor, Potato
		{
			if(metaBlock < 5)
			{
				metaBlock += 2;
				world.setBlockMetadataWithNotify(x, y, z, metaBlock);
				return true;
			}
		}
		return false;
    }
	
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public void getSubItems(int i, CreativeTabs tab, List items)
	{
		for(int meta = 0; meta < 4; meta++)
		{
			items.add(new ItemStack(this, 1, meta));
		}
	}
	
}
