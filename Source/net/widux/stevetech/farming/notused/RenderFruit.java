package net.widux.stevetech.farming.notused;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class RenderFruit implements ISimpleBlockRenderingHandler
{

	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer)
	{
		
	}

	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
	{
        Tessellator var5 = Tessellator.instance;
        var5.setBrightness(block.getMixedBrightnessForBlock(world, x, y, z));
        float var6 = 1.0F;
        int var7 = block.colorMultiplier(world, x, y, z);
        float var8 = (float)(var7 >> 16 & 255) / 255.0F;
        float var9 = (float)(var7 >> 8 & 255) / 255.0F;
        float var10 = (float)(var7 & 255) / 255.0F;

        if (EntityRenderer.anaglyphEnable)
        {
            float var11 = (var8 * 30.0F + var9 * 59.0F + var10 * 11.0F) / 100.0F;
            float var12 = (var8 * 30.0F + var9 * 70.0F) / 100.0F;
            float var13 = (var8 * 30.0F + var10 * 70.0F) / 100.0F;
            var8 = var11;
            var9 = var12;
            var10 = var13;
        }

        var5.setColorOpaque_F(var6 * var8, var6 * var9, var6 * var10);
        double var19 = (double)x;
        double var20 = (double)y;
        double var15 = (double)z;

        /*if (block == Block.tallGrass)
        {
            long var17 = (long)(x * 3129871) ^ (long)z * 116129781L ^ (long)y;
            var17 = var17 * var17 * 42317861L + var17 * 11L;
            var19 += ((double)((float)(var17 >> 16 & 15L) / 15.0F) - 0.5D) * 0.5D;
            var20 += ((double)((float)(var17 >> 20 & 15L) / 15.0F) - 1.0D) * 0.2D;
            var15 += ((double)((float)(var17 >> 24 & 15L) / 15.0F) - 0.5D) * 0.5D;
        }*/

        this.renderFruit(block, world.getBlockMetadata(x, y, z), var19, var20, var15, 1.0F, renderer);
        return true;
		/*Tessellator var5 = Tessellator.instance;
		int meta = world.getBlockMetadata(x, y, z);
        var5.setBrightness(block.getMixedBrightnessForBlock(world, x, y, z));
        float var6 = 1.0F;
        int var7 = block.colorMultiplier(world, x, y, z);
        float var8 = (float)(var7 >> 16 & 255) / 255.0F;
        float var9 = (float)(var7 >> 8 & 255) / 255.0F;
        float var10 = (float)(var7 & 255) / 255.0F;

        if (EntityRenderer.anaglyphEnable)
        {
            float var11 = (var8 * 30.0F + var9 * 59.0F + var10 * 11.0F) / 100.0F;
            float var12 = (var8 * 30.0F + var9 * 70.0F) / 100.0F;
            float var13 = (var8 * 30.0F + var10 * 70.0F) / 100.0F;
            var8 = var11;
            var9 = var12;
            var10 = var13;
        }

        var5.setColorOpaque_F(var6 * var8, var6 * var9, var6 * var10);
        double var19 = (double)x;
        double var20 = (double)y;
        double var15 = (double)z;
        renderFruit(block, world.getBlockMetadata(x, y, z), var19, var20, var15, 1.0F, renderer);
        return true;*/
	}

	private void renderFruit(Block block, int meta, double x, double y, double z, float f, RenderBlocks renderer)
	{
		
		Tessellator tessellator = Tessellator.instance;
        int texture = block.getBlockTextureFromSideAndMetadata(0, meta);

        if (renderer.overrideBlockTexture >= 0)
        {
            texture = renderer.overrideBlockTexture;
        }

        int var12 = (texture & 15) << 4;
        int var13 = texture & 240;
        double var14 = (double)((float)var12 / 256.0F);
        double var16 = (double)(((float)var12 + 15.99F) / 256.0F);
        double var18 = (double)((float)var13 / 256.0F);
        double var20 = (double)(((float)var13 + 15.99F) / 256.0F);
        double var22 = 0.45D * (double)f;
        double var24 = x + 0.5D - var22;
        double var26 = x + 0.5D + var22;
        double var28 = z + 0.5D - var22;
        double var30 = z + 0.5D + var22;
        tessellator.addVertexWithUV(var24, y + (double)f, var28, var14, var18);
        tessellator.addVertexWithUV(var24, y + 0.0D, var28, var14, var20);
        tessellator.addVertexWithUV(var26, y + 0.0D, var30, var16, var20);
        tessellator.addVertexWithUV(var26, y + (double)f, var30, var16, var18);
        tessellator.addVertexWithUV(var26, y + (double)f, var30, var14, var18);
        tessellator.addVertexWithUV(var26, y + 0.0D, var30, var14, var20);
        tessellator.addVertexWithUV(var24, y + 0.0D, var28, var16, var20);
        tessellator.addVertexWithUV(var24, y + (double)f, var28, var16, var18);
        tessellator.addVertexWithUV(var24, y + (double)f, var30, var14, var18);
        tessellator.addVertexWithUV(var24, y + 0.0D, var30, var14, var20);
        tessellator.addVertexWithUV(var26, y + 0.0D, var28, var16, var20);
        tessellator.addVertexWithUV(var26, y + (double)f, var28, var16, var18);
        tessellator.addVertexWithUV(var26, y + (double)f, var28, var14, var18);
        tessellator.addVertexWithUV(var26, y + 0.0D, var28, var14, var20);
        tessellator.addVertexWithUV(var24, y + 0.0D, var30, var16, var20);
        tessellator.addVertexWithUV(var24, y + (double)f, var30, var16, var18);
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
