package net.widux.stevetech.farming;

import java.util.Random;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class BlockFruit extends BlockContainer
{
	
	public BlockFruit(int ID)
	{
		super(ID, Material.grass);
		this.setCreativeTab(CreativeTabs.tabBlock);
	}
	
	public String getTextureFile()
	{
		return "/net/widux/stevetech/farming/Block.png";
	}
	
	public TileEntity createNewTileEntity(World world)
	{
		return new TileEntityFruit();
	}
	
	public boolean renderAsNormalBlock()
	{
		return false;
	}
	
	public int getTextureFromSideAndMetadata(int side, int meta)
	{
		return 255;
	}
	
	public int getRenderType()
	{
		return SteveTechFarming.renderFruitID;
	}
	
	public boolean isOpaqueCube()
	{
		return false;
	}
	
	public boolean canBeHere(World world, int x, int y, int z)
	{
		//TODO Re-implement this check.
		//if(world.getBlockId(x, y + 1, z) == SteveTechFarming.leaves.blockID)
		{
			return true;
		}
		/*else
		{
			return false;
		}*/
	}
	
	public void onNeighborBlockChange(World world, int x, int y, int z, int neighbor)
	{
		if(!canBeHere(world, x, y, z))
		{
			world.setBlock(x, y, z, 0);
		}
		
		super.onNeighborBlockChange(world, x, y, z, neighbor);
	}
	
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
    {
        return null;
    }
    
    public int idDropped(int meta, Random rand, int i)
    {
    	return 0;
    }
    
    public int quantityDropped(Random rand)
    {
    	return 0;
    }
}
