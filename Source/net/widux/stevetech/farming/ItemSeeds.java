package net.widux.stevetech.farming;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemSeeds extends Item
{
	
	int spawnID = SteveTechFarming.crops.blockID;
	
	public ItemSeeds(int ID)
	{
		super(ID);
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
		this.setCreativeTab(SteveTechFarming.stFarmingTab);
	}
	
	public String getTextureFile()
	{
		return "/net/widux/stevetech/farming/Seeds.png";
	}
	
	public int getIconFromDamage(int meta)
	{
		return 0 + meta;
	}
	
	public String getItemNameIS(ItemStack item)
	{
		return "WiduX-BF-Item-Seeds." + EnumCrop.getCrop(item.getItemDamage()).getName();
	}
	
	public boolean onItemUse(ItemStack item, EntityPlayer player, World world, int x, int y, int z, int side, float xOffset, float yOffset, float zOffset)
    {
        int currentID = world.getBlockId(x, y, z);

        if (currentID != Block.tilledField.blockID)
        {
        	return false;
        }
        if(world.getBlockId(x, y + 1, z) != 0)
        {
        	return false;
        }
        else
        {
        	y++;
        }

        if (!player.canPlayerEdit(x, y, z, side, item))
        {
            return false;
        }
        else if (item.stackSize == 0)
        {
            return false;
        }
        else
        {
            if (world.canPlaceEntityOnSide(this.spawnID, x, y, z, false, side, (Entity)null))
            {
                Block newBlock = Block.blocksList[this.spawnID];
                int meta = item.getItemDamage();

                if (world.setBlockAndMetadata(x, y, z, this.spawnID, 0))
                {
                    if (world.getBlockId(x, y, z) == this.spawnID)
                    {
                        Block.blocksList[this.spawnID].onBlockPlacedBy(world, x, y, z, player);
                        Block.blocksList[this.spawnID].onPostBlockPlaced(world, x, y, z, meta);
                        world.setBlockTileEntity(x, y, z, new TileEntityCrop());
                        TileEntityCrop teCrop = (TileEntityCrop) world.getBlockTileEntity(x, y, z);
                        teCrop.setCropType(EnumCrop.getCrop(item.getItemDamage()));
                    }

                    world.playSoundEffect((double)((float)x + 0.5F), (double)((float)y + 0.5F), (double)((float)z + 0.5F), newBlock.stepSound.getPlaceSound(), (newBlock.stepSound.getVolume() + 1.0F) / 2.0F, newBlock.stepSound.getPitch() * 0.8F);
                    item.stackSize--;
                }
            }

            return true;
        }
    }
	
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public void getSubItems(int i, CreativeTabs tab, List items)
	{
		for(int meta = 0; meta < TileEntityCrop.getSeedTypesAmount(); meta++)
		{
			items.add(new ItemStack(this, 1, meta));
		}
	}
	
}
