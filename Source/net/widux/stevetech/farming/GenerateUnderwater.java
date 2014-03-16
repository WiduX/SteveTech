package net.widux.stevetech.farming;

import java.util.Random;

import cpw.mods.fml.common.IWorldGenerator;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;

public class GenerateUnderwater implements IWorldGenerator
{
	
	Random rand;
	
	public boolean generate(World world, Random rand, int x, int z)
	{
		
		int blockID = SteveTechFarming.corral.blockID;
		
		int xNew;
		int zNew;
		int yNew = -1;
		
		for (int generate = 0; generate < 10; generate++)
		{
			
			xNew = x + (8 - rand.nextInt(16));
			zNew = z + (8 - rand.nextInt(16));
			
			if(world.getBiomeGenForCoords(xNew, zNew) == BiomeGenBase.ocean)
			{
				yNew = getOceanFloor(world, xNew, zNew);
				
				if(areSurroundingsAcceptable(world, xNew, yNew + 1, zNew, blockID) && yNew != -1)
				{
					world.setBlockAndMetadata(xNew, yNew + 1, zNew, blockID, rand.nextInt(6));
				}
				
			}
		
			
		}
		
		return true;
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
	{
		rand = random;
		if(world.provider.dimensionId == 0)
		{
			generate(world, random, chunkX * 16, chunkZ * 16);
		}
		
	}
	
	private int getOceanFloor(World world, int x, int z)
	{
		for(int height = 56; height > 40; height--)
		{
			if(!isWater(world, x, height, z))
			{
				return height;
			}
		}
		return -1;
	}
	
	private boolean areSurroundingsAcceptable(World world, int x, int y, int z, int block)
	{
		if(world.getBlockId(x, y - 1, z) != block &&
			isWater(world, x, y + 1, z))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	private boolean isWater(World world, int x, int y, int z)
	{
		if(world.getBlockId(x, y, z) == Block.waterMoving.blockID ||
		   world.getBlockId(x, y, z) == Block.waterStill.blockID)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

}
