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
		this.setCreativeTab(SteveTechFarming.stFarmingTab);
	}
	
	public String getTextureFile()
	{
		return "/net/widux/stevetech/farming/Item.png";
	}
	
	public int getIconFromDamage(int meta)
	{
		return 0 + meta;
	}
	
	public String getItemNameIS(ItemStack item)
	{
		return "WiduX-BF-Item-CreativeTools." + item.getItemDamage();
	}
	
	public boolean onItemUse(ItemStack item, EntityPlayer player, World world, int x, int y, int z, int side, float xOffset, float yOffset, float zOffset)
    {
		if(!world.isRemote)
		{
		int metaTool = item.getItemDamage();
		int metaBlock = world.getBlockMetadata(x, y, z);
		int blockID = world.getBlockId(x, y, z);
		
		if(metaTool == 2 && blockID == Block.tilledField.blockID) // Fertilizer on farmland
		{
			world.setBlockMetadataWithNotify(x, y, z, 7);
			return true;
		}
		else if(metaTool == 0 && blockID == Block.crops.blockID) // Progressor, Wheat
		{
			if(metaBlock < 7)
			{
				metaBlock++;
				world.setBlockMetadataWithNotify(x, y, z, metaBlock);
				return true;
			}
		}
		else if(metaTool == 0 && blockID == Block.carrot.blockID) // Progressor, Carrot
		{
			if(metaBlock < 7)
			{
				metaBlock += 2;
				if(metaBlock > 7) {metaBlock = 7;}
				world.setBlockMetadataWithNotify(x, y, z, metaBlock);
				return true;
			}
		}
		else if(metaTool == 0 && blockID == Block.potato.blockID) // Progressor, Potato
		{
			if(metaBlock < 7)
			{
				metaBlock += 2;
				if(metaBlock > 7) {metaBlock = 7;}
				world.setBlockMetadataWithNotify(x, y, z, metaBlock);
				return true;
			}
		}
		else if(metaTool == 0 && blockID == Block.cactus.blockID || blockID == Block.reed.blockID) // Progressor, Cactus or Sugar Cane
		{
			byte topBlock = (byte)y;
			for(byte up = 1; up < 32; up++)
			{
				if(world.getBlockId(x, y + up, z) == blockID) {topBlock = (byte)(y + up);}
				else {break;}
			}
			if(world.getBlockId(x, topBlock + 1, z) == 0)
			{
				world.setBlock(x, topBlock + 1, z, blockID);
			}
		}
		else if(metaTool == 3 && blockID == Block.crops.blockID || blockID == Block.carrot.blockID || blockID == Block.potato.blockID ) // Plant Info, Wheat/Carrot/Potato
		{
			String cropType = "Wheat";
			if(blockID == Block.carrot.blockID) {cropType = "Carrot";}
			if(blockID == Block.potato.blockID) {cropType = "Potato";}
			player.sendChatToPlayer("\u00a72----- Plant Status -----");
			player.sendChatToPlayer("\u00a76Crop: \u00a7f" + cropType);
			player.sendChatToPlayer("\u00a76Stage: \u00a7f" + metaBlock + " / 7");
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
