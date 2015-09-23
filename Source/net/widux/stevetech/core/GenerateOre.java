package net.widux.stevetech.core;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class GenerateOre
{
	
	private int blockID, maxBlocks, meta;
	
	/**
	 * 
	 * @param blockID The ore block id.
	 * @param maxBlocks Max ore blocks in one vein.
	 * @param meta The ore block meta data.
	 */
	public GenerateOre(int blockID, int maxBlocks, int meta)
	{
		this.blockID = blockID;
		this.maxBlocks = maxBlocks;
		this.meta = meta;
	}
	
	public boolean generateSurface(World world, Random random, int x, int y, int z)
	{
		float f = random.nextFloat() * (float)Math.PI;
		double d0 = (double)((float)(x + 8) + MathHelper.sin(f) * (float)this.maxBlocks / 8.0F);
		double d1 = (double)((float)(x + 8) - MathHelper.sin(f) * (float)this.maxBlocks / 8.0F);
		double d2 = (double)((float)(z + 8) + MathHelper.cos(f) * (float)this.maxBlocks / 8.0F);
		double d3 = (double)((float)(z + 8) - MathHelper.cos(f) * (float)this.maxBlocks / 8.0F);
		double d4 = (double)(y + random.nextInt(3) - 2);
		double d5 = (double)(y + random.nextInt(3) - 2);

		for (int l = 0; l <= this.maxBlocks; ++l)
		{
			double d6 = d0 + (d1 - d0) * (double)l / (double)this.maxBlocks;
			double d7 = d4 + (d5 - d4) * (double)l / (double)this.maxBlocks;
			double d8 = d2 + (d3 - d2) * (double)l / (double)this.maxBlocks;
			double d9 = random.nextDouble() * (double)this.maxBlocks / 16.0D;
			double d10 = (double)(MathHelper.sin((float)l * (float)Math.PI / (float)this.maxBlocks) + 1.0F) * d9 + 1.0D;
			double d11 = (double)(MathHelper.sin((float)l * (float)Math.PI / (float)this.maxBlocks) + 1.0F) * d9 + 1.0D;
			int i1 = MathHelper.floor_double(d6 - d10 / 2.0D);
			int j1 = MathHelper.floor_double(d7 - d11 / 2.0D);
			int k1 = MathHelper.floor_double(d8 - d10 / 2.0D);
			int l1 = MathHelper.floor_double(d6 + d10 / 2.0D);
			int i2 = MathHelper.floor_double(d7 + d11 / 2.0D);
			int j2 = MathHelper.floor_double(d8 + d10 / 2.0D);

			for (int oreX = i1; oreX <= l1; ++oreX)
			{
				double d12 = ((double)oreX + 0.5D - d6) / (d10 / 2.0D);

				if (d12 * d12 < 1.0D)
				{
					for (int oreY = j1; oreY <= i2; ++oreY)
					{
						double d13 = ((double)oreY + 0.5D - d7) / (d11 / 2.0D);

						if (d12 * d12 + d13 * d13 < 1.0D)
						{
							for (int oreZ = k1; oreZ <= j2; ++oreZ)
							{
								double d14 = ((double)oreZ + 0.5D - d8) / (d10 / 2.0D);
								Block block = Block.blocksList[world.getBlockId(oreX, oreY, oreZ)];
								if (d12 * d12 + d13 * d13 + d14 * d14 < 1.0D && (block != null && block.isGenMineableReplaceable(world, oreX, oreY, oreZ)))
								{
									world.setBlockAndMetadata(oreX, oreY, oreZ, this.blockID, this.meta);
								}
							}
						}
					}
				}
			}
		}
		return true;
	}
	
}
