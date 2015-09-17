package net.widux.stevetech.farming;

import java.util.Random;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
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
	private final String NBT_FRUIT = "FruitType";
	private final String NBT_STAGE = "GrowthStage";
	private final String NBT_FINISHED = "NoLongerGrows";
	
	// Block-specific
	private EnumFruit fruit;
	private int growthStage;
	private boolean stopGrowing;
	private byte tickDelay = 0; // Used to delay updates from ticks. Improves performance, slows rendering updates.
	
	public TileEntityFruit()
	{
		
	}
	
	public void readFromNBT(NBTTagCompound nbt)
    {
		super.readFromNBT(nbt);
		
		fruit = EnumFruit.values()[nbt.getInteger(NBT_FRUIT)];
		growthStage = nbt.getInteger(NBT_STAGE);
		stopGrowing = nbt.getBoolean(NBT_FINISHED);
    }
	
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		
		nbt.setInteger(NBT_FRUIT, fruit.getID());
		nbt.setInteger(NBT_STAGE, growthStage);
		nbt.setBoolean(NBT_FINISHED, stopGrowing);
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
    
    /** Returns true to remove a single item from the player (e.g. bone meal) */
    public boolean rightClick(ItemStack itemHeld, EntityPlayer player)
	{
		if(itemHeld == null) // No item being held
		{
			return false;
		}
		
		if(itemHeld.itemID == SteveTechFarming.creativeTools.itemID) // Creative Tools
		{
			if(itemHeld.getItemDamage() == 0) // Growth Progressor
	        {
	        	this.attemptAddGrowthStage();
	        	worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
	        }
			
	        else if(itemHeld.getItemDamage() == 1) // Growth Halter
	        {
	        	this.stopGrowing();
	        	worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
	        }
			
	        else if(itemHeld.getItemDamage() == 3) // Plant Informer
	        {
				player.sendChatToPlayer("\u00a72----- Plant Status -----");
				player.sendChatToPlayer("\u00a76Fruit Type: \u00a7f" + this.fruit + " (" + this.fruit.getName() + ")");
				player.sendChatToPlayer("\u00a76Growth Speed: \u00a7f" + this.getGrowthSpeed());
				player.sendChatToPlayer("\u00a76Stage: \u00a7f" + this.growthStage);
				player.sendChatToPlayer("\u00a76Stopped Growing: \u00a7f" + this.stopGrowing);
	        }
			
			return false;
		}
	}
	
	public void updateEntity()
	{
		if(tickDelay >= 40) // This will only tick the block every 40 ticks, reducing server & client load, and network usage.
		{
			tickDelay = 0;
			worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		}
		tickDelay++;
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
