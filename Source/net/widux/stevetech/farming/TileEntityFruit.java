package net.widux.stevetech.farming;

import java.util.Random;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TileEntityFruit extends TileEntity
{
	
	// Names for NBT Tags
	private String FRUIT = "FruitType";
	private String STAGE = "GrowthStage";
	
	// Block-specific
	private int fruitType = 0;
	private int growthStage = 0;
	private boolean isReady;
	
	// Applies to all fruits
	private String[] fruits = {"Apple", "Orange", "Pear", "Lemon", "Grapefruit"};
	private int[] stages = {3, 3, 3, 3, 3};
	private int[] maxDrop = {1, 1, 1, 1, 1};
	private ItemStack[] drops = {new ItemStack(Item.appleRed)};
	
	public TileEntityFruit()
	{
		
	}
	
	public void readFromNBT(NBTTagCompound nbt)
    {
		super.readFromNBT(nbt);
		
		fruitType = nbt.getInteger(FRUIT);
		growthStage = nbt.getInteger(STAGE);
    }
	
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		
		nbt.setInteger(FRUIT, fruitType);
		nbt.setInteger(STAGE, growthStage);
	}
	
    @Override
    public Packet getDescriptionPacket()
    {
    	NBTTagCompound tileTag = new NBTTagCompound();
    	this.writeToNBT(tileTag);
    	return new Packet132TileEntityData(this.xCoord, this.yCoord, this.zCoord, 0, tileTag);
    }
	
    @Override
    public void onDataPacket(INetworkManager net, Packet132TileEntityData pkt)
    {
    	this.readFromNBT(pkt.customParam1);
    }
    
    public void dropAndInvalidate(World world, int x, int y, int z)
    {
    	double xItem, yItem, zItem;
    	int amountDropped = 1;
    	Random rand = new Random();
    	xItem = rand.nextDouble() * 0.5D;
    	yItem = rand.nextDouble() * 0.5D;
    	zItem = rand.nextDouble() * 0.5D;
    	if(getMaxDropped(getFruitID()) != 1)
    	{
    		amountDropped = rand.nextInt(getMaxDropped(getFruitID()) + 1);
    	}
    	EntityItem dropped = new EntityItem(world, x + xItem, y + yItem, z + zItem, new ItemStack(getDrop(getFruitID()).itemID, amountDropped, getDrop(getFruitID()).getItemDamage()));
    	dropped.delayBeforeCanPickup = 5;
    	world.spawnEntityInWorld(dropped);
    }
    
    public int getFruitID()
    {
    	return fruitType;
    }
    
    public int getMaxDropped(int fruit)
    {
    	return maxDrop[fruit];
    }
    
    public ItemStack getDrop(int fruit)
    {
    	return drops[fruit];
    }
    
	public int getFruitTexture()
	{
		return (fruitType * 8) + growthStage;
	}
}
