package net.widux.stevetech.farming;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class RenderFruit implements ISimpleBlockRenderingHandler
{

	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {} // N/A, doesn't render 3D in inventory

	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
	{
        Tessellator tess = Tessellator.instance;
        tess.setBrightness(block.getMixedBrightnessForBlock(world, x, y, z));
        int colorMult = block.colorMultiplier(world, x, y, z);
        float rMod = (float)(colorMult >> 16 & 255) / 255.0F;
        float gMod = (float)(colorMult >> 8 & 255) / 255.0F;
        float bMod = (float)(colorMult & 255) / 255.0F;
        if (EntityRenderer.anaglyphEnable)
        {
            float rMod3D = (rMod * 30.0F + gMod * 59.0F + bMod * 11.0F) / 100.0F;
            float gMod3D = (rMod * 30.0F + gMod * 70.0F) / 100.0F;
            float bMod3D = (rMod * 30.0F + bMod * 70.0F) / 100.0F;
            rMod = rMod3D;
            gMod = gMod3D;
            bMod = bMod3D;
        }
        tess.setColorOpaque_F(rMod, gMod, bMod);
        this.renderFruit(world, block, world.getBlockMetadata(x, y, z), (double)x, (double)y, (double)z, 1.0F, renderer);
        return true;
	}

	private void renderFruit(IBlockAccess world, Block block, int meta, double x, double y, double z, float scale, RenderBlocks renderer)
	{
		
		Tessellator tessellator = Tessellator.instance;
		TileEntityFruit teFruit = (TileEntityFruit) world.getBlockTileEntity((int)x, (int)y, (int)z);
        int texture = teFruit.getFruitTexture();

        if (renderer.overrideBlockTexture >= 0)
        {
            texture = renderer.overrideBlockTexture;
        }

        int col = (texture & 15) << 4;
        int row = texture & 240;
        double d1 = (double)((float)col / 256.0F);
        double d2 = (double)(((float)col + 15.99F) / 256.0F);
        double d3 = (double)((float)row / 256.0F);
        double d4 = (double)(((float)row + 15.99F) / 256.0F);
        //                0.45D is the texture pullback from the corners.
        double adjScale = 0.45D * (double)scale;
        double cornerSW = x + 0.5D - adjScale;
        double cornerSE = x + 0.5D + adjScale;
        double cornerNE = z + 0.5D - adjScale;
        double cornerNW = z + 0.5D + adjScale;
        tessellator.addVertexWithUV(cornerSW, y + (double)scale, cornerNE, d1, d3);
        tessellator.addVertexWithUV(cornerSW, y + 0.0D, cornerNE, d1, d4);
        tessellator.addVertexWithUV(cornerSE, y + 0.0D, cornerNW, d2, d4);
        tessellator.addVertexWithUV(cornerSE, y + (double)scale, cornerNW, d2, d3);
        tessellator.addVertexWithUV(cornerSE, y + (double)scale, cornerNW, d1, d3);
        tessellator.addVertexWithUV(cornerSE, y + 0.0D, cornerNW, d1, d4);
        tessellator.addVertexWithUV(cornerSW, y + 0.0D, cornerNE, d2, d4);
        tessellator.addVertexWithUV(cornerSW, y + (double)scale, cornerNE, d2, d3);
        tessellator.addVertexWithUV(cornerSW, y + (double)scale, cornerNW, d1, d3);
        tessellator.addVertexWithUV(cornerSW, y + 0.0D, cornerNW, d1, d4);
        tessellator.addVertexWithUV(cornerSE, y + 0.0D, cornerNE, d2, d4);
        tessellator.addVertexWithUV(cornerSE, y + (double)scale, cornerNE, d2, d3);
        tessellator.addVertexWithUV(cornerSE, y + (double)scale, cornerNE, d1, d3);
        tessellator.addVertexWithUV(cornerSE, y + 0.0D, cornerNE, d1, d4);
        tessellator.addVertexWithUV(cornerSW, y + 0.0D, cornerNW, d2, d4);
        tessellator.addVertexWithUV(cornerSW, y + (double)scale, cornerNW, d2, d3);
	}
	
	public boolean shouldRender3DInInventory()
	{
		return false;
	}

	public int getRenderId()
	{
		return SteveTechFarming.renderFruitID;
	}
	
}
