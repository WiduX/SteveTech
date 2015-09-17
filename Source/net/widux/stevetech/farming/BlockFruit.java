package net.widux.stevetech.farming;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class BlockFruit extends BlockContainer
{
	
	public BlockFruit(int ID)
	{
		super(ID, Material.grass);
		this.setCreativeTab(SteveTechFarming.stFarmingTab);
		this.setTickRandomly(true);
	}
	
	public String getTextureFile()
	{
		return "/net/widux/stevetech/farming/Fruits.png";
	}
	
	public TileEntity createNewTileEntity(World world)
	{
		return new TileEntityFruit();
	}
	
	public boolean renderAsNormalBlock()
	{
		return false;
	}
	
	public int getRenderType()
	{
		return SteveTechFarming.renderFruitID;
	}
	
	public int getTextureFromSideAndMetadata(int side, int meta)
	{
		return 255;
	}
	
	public boolean isOpaqueCube()
	{
		return false;
	}
	
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float xOffset, float yOffset, float zOffset)
    {
    	if(!world.isRemote)
    	{
    		TileEntityFruit teFruit = (TileEntityFruit)world.getBlockTileEntity(x, y, z);
    		boolean worked = teFruit.rightClick(player.getHeldItem(), player);
    		if(worked && !player.capabilities.isCreativeMode)
    		{
    			player.getHeldItem().stackSize--;
    		}
    	}
    	world.markBlockForUpdate(x, y, z);
        return true;
    }
	
	public void breakBlock(World world, int x, int y, int z, int par5, int par6)
    {
    	TileEntityFruit teFruit = (TileEntityFruit) world.getBlockTileEntity(x, y, z);
    	teFruit.dropAndInvalidate(world, x, y, z);
    }
    
    public void updateTick(World world, int x, int y, int z, Random rand)
    {
    	super.updateTick(world, x, y, z, rand);

    	TileEntityFruit teFruit = (TileEntityFruit) world.getBlockTileEntity(x, y, z);
    	teFruit.tick();
    }
	
	public boolean canBeHere(World world, int x, int y, int z)
	{
		if(world.getBlockId(x, y + 1, z) == Block.leaves.blockID)
		{
			return true;
		}
		else
		{
			return false;
		}
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
