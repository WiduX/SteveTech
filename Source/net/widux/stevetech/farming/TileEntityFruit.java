package net.widux.stevetech.farming;

import java.util.Random;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
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
	private EnumFruit fruit = EnumFruit.APPLE;
	private int growthStage = 0;
	private boolean stopGrowing = false;
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
    
    public void dropAndInvalidate(World world, int x, int y, int z) // TODO Test drops.
    {
    	if(!world.isRemote)
    	{
    		Random rand = new Random();
    		double xItem, yItem, zItem;
    		int amountDropped = 1;
    		
    		xItem = (rand.nextDouble() * 0.0015D) + x;
    		yItem = (rand.nextDouble() * 0.0015D) + y;
    		zItem = (rand.nextDouble() * 0.0015D) + z;
    		
    		if(fruit.getMaxDrop() != 1)
    		{
    			amountDropped = rand.nextInt(fruit.getMaxDrop() + 1);
    		}
    		
    		ItemStack itemDropped = fruit.getDrop();
    		itemDropped.stackSize = amountDropped;
    		
    		EntityItem dropped = new EntityItem(world, xItem, yItem, zItem, itemDropped);
    		dropped.delayBeforeCanPickup = 5;
    		world.spawnEntityInWorld(dropped);
    	}
    }
    
    public void stopGrowing()
    {
    	this.stopGrowing = true;
    }
    
    public int getFruitID()
    {
    	return fruit.getID();
    }
    
    public int getGrowthStage()
    {
    	return this.growthStage;
    }
    
    public int getFruitTexture(int fruitType, int growth)
	{
		return (fruitType * 8) + growth;
	}

	public void setCropType(EnumFruit newFruit)
	{
		this.fruit = newFruit;
	}

	public void setGrowthStage(int newStage)
	{
		this.growthStage = newStage;
	}
    
	public void attemptAddGrowthStage() // TODO Implement growing.
	{
		
	}
	
	public void tick()
	{
		Random rand = new Random();
		
		if(!this.stopGrowing && rand.nextInt(fruit.getGrowthSpeed()) < 3) // (3 / Growth Speed) chance of growing
		{
			this.attemptAddGrowthStage();
		}
		else // Not yet ready to grow.
		{
			return;
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
				player.sendChatToPlayer("\u00a72----- Fruit Status -----");
				player.sendChatToPlayer("\u00a76Fruit Type: \u00a7f" + this.fruit + " (" + this.fruit.getName() + ")");
				player.sendChatToPlayer("\u00a76Growth Speed: \u00a7f" + this.fruit.getGrowthSpeed());
				player.sendChatToPlayer("\u00a76Stage: \u00a7f" + this.growthStage);
				player.sendChatToPlayer("\u00a76Stopped Growing: \u00a7f" + this.stopGrowing);
	        }
			
			return false;
		}
		return false;
	}
    
}
