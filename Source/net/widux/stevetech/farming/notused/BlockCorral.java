package net.widux.stevetech.farming.notused;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockCorral extends Block
{
	
	public BlockCorral(int ID)
	{
		super(ID, Material.grass);
		this.setHardness(0.05F);
		this.setResistance(0.1F);
		this.setLightValue(1F);
		this.setCreativeTab(SteveTechFarming.betterFarmingTab);
	}
	
	public boolean isOpaqueCube()
	{
		return false;
	}
	
	public int damageDropped(int meta)
	{
		return meta;
	}
	
	public boolean renderAsNormalBlock()
	{
		return false;
	}
	
	public int getRenderType()
	{
		return 1;
	}
	
	public int getBlockTextureFromSideAndMetadata(int side, int meta)
	{
		return  0 + meta;
	}
	
	public String getTextureFile()
	{
		return "/net/widux/stevetech/farming/Block.png";
	}
	
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
    {
        return null;
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
	@SideOnly(Side.CLIENT)
    public void getSubBlocks(int unknown, CreativeTabs tab, List subItems)
    {
    	for (int meta = 0; meta < 6; meta++)
    	{
    		subItems.add(new ItemStack(this, 1, meta));
    	}
    }
	
}
