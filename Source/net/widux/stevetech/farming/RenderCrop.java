package net.widux.stevetech.farming;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class RenderCrop implements ISimpleBlockRenderingHandler
{

	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer)
	{
		
	}

	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
	{
		Tessellator tess = Tessellator.instance;
        tess.setBrightness(block.getMixedBrightnessForBlock(world, x, y, z));
        tess.setColorOpaque_F(1.0F, 1.0F, 1.0F);
        
        TileEntityCrop teCrop = (TileEntityCrop) world.getBlockTileEntity(x, y, z);
        //System.out.println("Getting ID for crop at X" + x + "  Y" + y + "  Z" + z);
        int cropType = teCrop.getCropID();
        int cropStage = teCrop.getGrowthStage();
        int texture = teCrop.getCropTexture(cropType, cropStage);
        
        double xModel, yModel, zModel;
        xModel = x;
        yModel = (double)((float)y - 0.0625F);
        zModel = z;

        doRender(tess, xModel, yModel, zModel, texture);
		return true;
	}
	
	private void doRender(Tessellator tess, double x, double y, double z, int texture)
	{
        int i = (texture & 15) << 4;
        int j = texture & 240;
        
        double d1 = (double) ((float) i / 256.0F);
        double d2 = (double) (((float) i + 15.99F) / 256.0F);
        double d3 = (double) ((float) j / 256.0F);
        double d4 = (double) (((float) j + 15.99F) / 256.0F);
        
        double d10 = x + 0.5D - 0.25D;
        double d11 = x + 0.5D + 0.25D;
        double d12 = z + 0.5D - 0.5D;
        double d13 = z + 0.5D + 0.5D;
        
        tess.addVertexWithUV(d10, y + 1.0D, d12, d1, d3);
        tess.addVertexWithUV(d10, y + 0.0D, d12, d1, d4);
        tess.addVertexWithUV(d10, y + 0.0D, d13, d2, d4);
        tess.addVertexWithUV(d10, y + 1.0D, d13, d2, d3);
        tess.addVertexWithUV(d10, y + 1.0D, d13, d1, d3);
        tess.addVertexWithUV(d10, y + 0.0D, d13, d1, d4);
        tess.addVertexWithUV(d10, y + 0.0D, d12, d2, d4);
        tess.addVertexWithUV(d10, y + 1.0D, d12, d2, d3);
        tess.addVertexWithUV(d11, y + 1.0D, d13, d1, d3);
        tess.addVertexWithUV(d11, y + 0.0D, d13, d1, d4);
        tess.addVertexWithUV(d11, y + 0.0D, d12, d2, d4);
        tess.addVertexWithUV(d11, y + 1.0D, d12, d2, d3);
        tess.addVertexWithUV(d11, y + 1.0D, d12, d1, d3);
        tess.addVertexWithUV(d11, y + 0.0D, d12, d1, d4);
        tess.addVertexWithUV(d11, y + 0.0D, d13, d2, d4);
        tess.addVertexWithUV(d11, y + 1.0D, d13, d2, d3);
        
        d10 = x + 0.5D - 0.5D;
        d11 = x + 0.5D + 0.5D;
        d12 = z + 0.5D - 0.25D;
        d13 = z + 0.5D + 0.25D;
        
        tess.addVertexWithUV(d10, y + 1.0D, d12, d1, d3);
        tess.addVertexWithUV(d10, y + 0.0D, d12, d1, d4);
        tess.addVertexWithUV(d11, y + 0.0D, d12, d2, d4);
        tess.addVertexWithUV(d11, y + 1.0D, d12, d2, d3);
        tess.addVertexWithUV(d11, y + 1.0D, d12, d1, d3);
        tess.addVertexWithUV(d11, y + 0.0D, d12, d1, d4);
        tess.addVertexWithUV(d10, y + 0.0D, d12, d2, d4);
        tess.addVertexWithUV(d10, y + 1.0D, d12, d2, d3);
        tess.addVertexWithUV(d11, y + 1.0D, d13, d1, d3);
        tess.addVertexWithUV(d11, y + 0.0D, d13, d1, d4);
        tess.addVertexWithUV(d10, y + 0.0D, d13, d2, d4);
        tess.addVertexWithUV(d10, y + 1.0D, d13, d2, d3);
        tess.addVertexWithUV(d10, y + 1.0D, d13, d1, d3);
        tess.addVertexWithUV(d10, y + 0.0D, d13, d1, d4);
        tess.addVertexWithUV(d11, y + 0.0D, d13, d2, d4);
        tess.addVertexWithUV(d11, y + 1.0D, d13, d2, d3);
	}
	
	public boolean shouldRender3DInInventory()
	{
		return false;
	}

	public int getRenderId()
	{
		return SteveTechFarming.renderCropID;
	}
	
	
	
}
