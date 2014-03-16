package net.widux.stevetech.farming;

import javax.swing.Icon;

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
        return true;
	}

	private void renderFruit(Block block, int par2, double par3, double par5, double par7, float par9, RenderBlocks renderer)
	{
		
		Tessellator tessellator = Tessellator.instance;
        Icon icon = renderer.getBlockIconFromSideAndMetadata(block, 0, par2);

        if (renderer.hasOverrideBlockTexture())
        {
            icon = renderer.overrideBlockTexture;
        }

        double d3 = (double)icon.getMinU();
        double d4 = (double)icon.getMinV();
        double d5 = (double)icon.getMaxU();
        double d6 = (double)icon.getMaxV();
        double d7 = 0.45D * (double)par9;
        double d8 = par3 + 0.5D - d7;
        double d9 = par3 + 0.5D + d7;
        double d10 = par7 + 0.5D - d7;
        double d11 = par7 + 0.5D + d7;
        tessellator.addVertexWithUV(d8, par5 + (double)par9, d10, d3, d4);
        tessellator.addVertexWithUV(d8, par5 + 0.0D, d10, d3, d6);
        tessellator.addVertexWithUV(d9, par5 + 0.0D, d11, d5, d6);
        tessellator.addVertexWithUV(d9, par5 + (double)par9, d11, d5, d4);
        tessellator.addVertexWithUV(d9, par5 + (double)par9, d11, d3, d4);
        tessellator.addVertexWithUV(d9, par5 + 0.0D, d11, d3, d6);
        tessellator.addVertexWithUV(d8, par5 + 0.0D, d10, d5, d6);
        tessellator.addVertexWithUV(d8, par5 + (double)par9, d10, d5, d4);
        tessellator.addVertexWithUV(d8, par5 + (double)par9, d11, d3, d4);
        tessellator.addVertexWithUV(d8, par5 + 0.0D, d11, d3, d6);
        tessellator.addVertexWithUV(d9, par5 + 0.0D, d10, d5, d6);
        tessellator.addVertexWithUV(d9, par5 + (double)par9, d10, d5, d4);
        tessellator.addVertexWithUV(d9, par5 + (double)par9, d10, d3, d4);
        tessellator.addVertexWithUV(d9, par5 + 0.0D, d10, d3, d6);
        tessellator.addVertexWithUV(d8, par5 + 0.0D, d11, d5, d6);
        tessellator.addVertexWithUV(d8, par5 + (double)par9, d11, d5, d4);
        
		/*
		Tessellator var10 = Tessellator.instance;
		Icon var11 = block.getBlockTextureFromSideAndMetadata(0, par2);

        //if (this.overrideBlockTexture >= 0)
        //{
        //    var11 = this.overrideBlockTexture;
        //}

        int var12 = (var11 & 15) << 4;
        int var13 = var11 & 240;
        double var14 = (double)((float)var12 / 256.0F);
        double var16 = (double)(((float)var12 + 15.99F) / 256.0F);
        double var18 = (double)((float)var13 / 256.0F);
        double var20 = (double)(((float)var13 + 15.99F) / 256.0F);
        double var22 = 0.45D * (double)par9;
        double var24 = par3 + 0.5D - var22;
        double var26 = par3 + 0.5D + var22;
        double var28 = par7 + 0.5D - var22;
        double var30 = par7 + 0.5D + var22;
        var10.addVertexWithUV(var24, par5 + (double)par9, var28, var14, var18);
        var10.addVertexWithUV(var24, par5 + 0.0D, var28, var14, var20);
        var10.addVertexWithUV(var26, par5 + 0.0D, var30, var16, var20);
        var10.addVertexWithUV(var26, par5 + (double)par9, var30, var16, var18);
        var10.addVertexWithUV(var26, par5 + (double)par9, var30, var14, var18);
        var10.addVertexWithUV(var26, par5 + 0.0D, var30, var14, var20);
        var10.addVertexWithUV(var24, par5 + 0.0D, var28, var16, var20);
        var10.addVertexWithUV(var24, par5 + (double)par9, var28, var16, var18);
        var10.addVertexWithUV(var24, par5 + (double)par9, var30, var14, var18);
        var10.addVertexWithUV(var24, par5 + 0.0D, var30, var14, var20);
        var10.addVertexWithUV(var26, par5 + 0.0D, var28, var16, var20);
        var10.addVertexWithUV(var26, par5 + (double)par9, var28, var16, var18);
        var10.addVertexWithUV(var26, par5 + (double)par9, var28, var14, var18);
        var10.addVertexWithUV(var26, par5 + 0.0D, var28, var14, var20);
        var10.addVertexWithUV(var24, par5 + 0.0D, var30, var16, var20);
        var10.addVertexWithUV(var24, par5 + (double)par9, var30, var16, var18);*/
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
