package net.widux.stevetech.farming;

import java.util.Random;
import net.widux.stevetech.api.farming.IFertilizer;
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

public class TileEntityCrop extends TileEntity
{

	// Names for NBT Tags
	private final String NBT_CROP = "CropType";
	private final String NBT_STAGE = "GrowthStage";
	private final String NBT_FINISHED = "NoLongerGrows";
	private final String NBT_FERTILIZER = "FertilizerAmount";
	private final String NBT_GROWTH_RATE = "CurrentGrowthRate";

	// Block-specific
	private EnumCrop crop; // The ID of the crop in this block
	private int growthStage; // The stage of the crop in this block
	private boolean stopGrowing; // Is this plant finished growing?
	private byte fertilizer; // The amount of fertilizer the plant has (Up to 100)
	private int growthRate; // The current speed of growth. --- Speed of growth / (Fertilizer / 50) ---
	private byte tickDelay = 0; // Used to delay updates from ticks. Improves performance, slows rendering updates.
	private byte blockHeight = 1; // 
	
	private static int amountSeeds = EnumCrop.getNumberCrops();//crops.length; // Amount of different types of seeds
	
	public TileEntityCrop()
	{

	}

	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);

		crop = EnumCrop.values()[nbt.getInteger(NBT_CROP)];
		growthStage = nbt.getInteger(NBT_STAGE);
		stopGrowing = nbt.getBoolean(NBT_FINISHED);
		fertilizer = nbt.getByte(NBT_FERTILIZER);
		growthRate = nbt.getInteger(NBT_GROWTH_RATE);

	}

	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);

		nbt.setInteger(NBT_CROP, crop.getID());
		nbt.setInteger(NBT_STAGE, growthStage);
		nbt.setBoolean(NBT_FINISHED, stopGrowing);
		nbt.setByte(NBT_FERTILIZER, fertilizer);
		nbt.setInteger(NBT_GROWTH_RATE, growthRate);
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
	
	public static int getSeedTypesAmount()
	{
		return amountSeeds;
	}
	
	public static EnumCrop getCrop(int ID)
	{
		return EnumCrop.values()[ID];
	}
	
	public static int getAmountCrops()
	{
		return EnumCrop.getNumberCrops();
	}
	
	public static boolean getSuffix(int ID)
	{
		return EnumCrop.values()[ID].getHasNameSuffix();
	}
	
	public void dropAndInvalidate(World world, int x, int y, int z)
	{
		if(!world.isRemote)
		{
			Random rand = new Random();

			double xItem, yItem, zItem;
			xItem = (rand.nextDouble() * 0.0015D) + x;
			yItem = (rand.nextDouble() * 0.0015D) + y + 0.5;
			zItem = (rand.nextDouble() * 0.0015D) + z;

			ItemStack[] itemsDropped = getItemsDropped();

			if(itemsDropped[0] != null)
			{
				EntityItem droppedHarvest = new EntityItem(world, xItem, yItem, zItem, itemsDropped[0]);
				droppedHarvest.delayBeforeCanPickup = 5;
				world.spawnEntityInWorld(droppedHarvest);
			}

			if(itemsDropped[1] != null)
			{
				EntityItem droppedSeed = new EntityItem(world, xItem, yItem, zItem, itemsDropped[1]);
				droppedSeed.delayBeforeCanPickup = 5;
				world.spawnEntityInWorld(droppedSeed);
			}
		
			this.invalidate();
		}
	}
	
	private ItemStack[] getItemsDropped()
	{
		int crop = this.getCropID();
		int stage = this.getGrowthStage();
		boolean finished = !this.stopGrowing;
		boolean dropsSeed = this.crop.getDropsSeed();
		ItemStack[] items = {null, null};
		//TODO Re-add Bell pepper variation harvesting.
		/*if(crop == EnumCrop.BELLPEPPER.getID()) // Bell Pepper TODO Verify this.
		{
			items[0] = new ItemStack(SteveTechFarming.harvestedItems, getAmountDropped(true), stage - 2);
			items[1] = new ItemStack(SteveTechFarming.seeds, getAmountDropped(false), crop);
			return items;
		}*/
		if(!dropsSeed) // Shouldn't drop seeds, just harvest
		{
			items[0] = new ItemStack(getDropHarvest(crop).itemID, getAmountDropped(true), getDropHarvest(crop).getItemDamage());
			items[1] = null;
			return items;
		}
		else // Drops both seeds and harvest
		{
			items[0] = new ItemStack(getDropHarvest(crop).itemID, getAmountDropped(true), getDropHarvest(crop).getItemDamage());
			items[1] = new ItemStack(getDropSeed(crop).itemID, getAmountDropped(false), getDropSeed(crop).getItemDamage());
			return items;
		}
	}
	
	private int getAmountDropped(boolean harvest)
	{
		Random rand = new Random();
		int stage = this.getGrowthStage();
		boolean finished = this.stopGrowing;
		boolean otherFinished = false;
		
		if(crop.getID() == 3 && stage >= 3)
		{
			otherFinished = true;
		}
		
		if(finished || otherFinished)
		{
			if(harvest)
			{
				return rand.nextInt(crop.getMaxDropHarvest()) + 1;
			}
			else
			{
				return rand.nextInt(crop.getMaxDropSeed()) + 1;
			}
		}
		else
		{
			if(harvest)
			{
				return 0;
			}
			else
			{
				return rand.nextInt(2); // 0 or 1 seeds
			}
		}
	}
	
	public void stopGrowing()
	{
		this.stopGrowing = true;
	}
	
	public int getCropID()
	{
		return crop.getID();
	}

	public int getMaxDroppedHarvest(int ID)
	{
		return EnumCrop.values()[ID].getMaxDropHarvest();
	}

	public int getMaxDroppedSeed(int ID)
	{
		return EnumCrop.values()[ID].getMaxDropSeed();
	}

	public ItemStack getDropHarvest(int ID)
	{
		return EnumCrop.values()[ID].getDropHarvest();
	}

	public ItemStack getDropSeed(int ID)
	{
		return EnumCrop.values()[ID].getDropSeed();
	}

	public int getGrowthStage()
	{
		return this.growthStage;
	}

	public int getCropTexture(int cropType, int growth)
	{
		return (cropType * 8) + growth;
	}

	public void setCropType(EnumCrop newCrop)
	{
		this.crop = newCrop;
	}

	public void setGrowthStage(int newStage)
	{
		this.growthStage = newStage;
	}

	public void attemptAddGrowthStage()
	{
		if(this.stopGrowing) {return;}
		
		if(this.crop.getMaxHeight() > 1) // Create a plant above if necessary.
		{
			addGrowthStageMulti();
		}
		
		if(this.stopGrowing) {return;} // If plant above was created, the bottom shouldn't continue anymore.
		
		if(this.crop.getMaxHeight() == 2 && getGrowthStage() == this.crop.getGrowthStages() - 2)
		{
			if(this.worldObj.getBlockId(xCoord, yCoord + 1, zCoord) == 0)
			{
				// Add second plant on top
				this.worldObj.setBlockAndMetadata(xCoord, yCoord + 1, zCoord, SteveTechFarming.crops.blockID, 0);
				this.worldObj.setBlockTileEntity(xCoord, yCoord + 1, zCoord, new TileEntityCrop());

				TileEntityCrop up = (TileEntityCrop)this.worldObj.getBlockTileEntity(xCoord, yCoord + 1, zCoord);
				// Set-up top plant block
				up.setCropType(this.crop);
				up.stopGrowing = true;
				up.setGrowthStage(this.crop.getGrowthStages() - 1); // The maximum
				this.stopGrowing = true;
				worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
				return;
			}
		}

		if(getGrowthStage() >= this.crop.getGrowthStages() - 1)
		{
			this.stopGrowing = true;
		}

		else
		{
			addGrowthStage();
			worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		}
	}
	
	/**
	 * A special method for adding a growth stage if the plant is more than 1 block tall.
	 */
	public void addGrowthStageMulti()
	{
		if(getGrowthStage() == this.crop.getGrowthStages() - 2)
		{
			if(this.worldObj.getBlockId(xCoord, yCoord + 1, zCoord) == 0)
			{
				//Grow new plant block on top.
				this.worldObj.setBlockAndMetadata(xCoord, yCoord + 1, zCoord, SteveTechFarming.crops.blockID, 0);
				this.worldObj.setBlockTileEntity(xCoord, yCoord + 1, zCoord, new TileEntityCrop());
				
				TileEntityCrop up = (TileEntityCrop)this.worldObj.getBlockTileEntity(xCoord, yCoord + 1, zCoord);
				// Set-up top plant block
				up.setCropType(this.crop);
				up.stopGrowing = true;
				if(3 == 2+1)
				{
					up.setGrowthStage(this.crop.getGrowthStages() - 1); // The maximum
				}
				this.stopGrowing = true;
				worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
			}
		}
	}
	
	/**
	 * The lower the result is, the higher the chance of growing will be.
	 * This is a formula to get the range of half the growth time to 5, depending on fertilizer.
	 * @return The modified growth probability.
	 */
	private int getGrowthSpeed()
	{
		if(this.fertilizer <= 0)
		{
			return this.crop.getGrowthSpeed();
		}
		else
		{
			int half = this.crop.getGrowthSpeed() / 2;
			return (int) (half - (half * (this.fertilizer / 200.000F)));
		}
	}
	
	public void tick()
	{
		Random rand = new Random();
		
		if(!this.stopGrowing && rand.nextInt(getGrowthSpeed()) < 3)
		{
			this.attemptAddGrowthStage();
		}
		else // Not yet ready to grow.
		{
			return;
		}
	}
	
	/**
	 * DO NOT USE DIRECTLY.
	 */
	private void addGrowthStage()
	{
		this.growthStage++;
	}

	public void updateEntity()
	{
		if(tickDelay >= 10)
		{
			tickDelay = 0;
			worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		}
		tickDelay++;
	}
	
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
			
	        else if(itemHeld.getItemDamage() == 2) // Full Fertilizer
	        {
	        	this.useFertilizer(100);
	        	worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
	        }
			
	        else if(itemHeld.getItemDamage() == 3) // Plant Informer
	        {
				player.sendChatToPlayer("\u00a72----- Plant Status -----");
				player.sendChatToPlayer("\u00a76Crop: \u00a7f" + this.crop + " (" + this.crop.getName() + ")");
				player.sendChatToPlayer("\u00a76Growth Speed: \u00a7f" + this.getGrowthSpeed());
				player.sendChatToPlayer("\u00a76Stage: \u00a7f" + this.growthStage);
				player.sendChatToPlayer("\u00a76Stopped Growing: \u00a7f" + this.stopGrowing);
				player.sendChatToPlayer("\u00a76Amount of Fertilizer: \u00a7f" + this.fertilizer);
	        }
			
			return false;
		}
		
		if(getFertilizerAmount(itemHeld) != 0)
		{
			useFertilizer(getFertilizerAmount(itemHeld));
			if(this.fertilizer >= 100)
			{
				return false;
			}
			else
			{
				return true;
			}
		}
		
		else // Something insignificant being held.
		{
			return false;
		}
	}
	
	private int getFertilizerAmount(ItemStack item)
	{
		if(item.itemID == Item.dyePowder.itemID && item.getItemDamage() == 15) // Bonemeal
		{
			return 15;
		}
		
		else if(item.getItem() instanceof IFertilizer) // Other fertilizers added via the API
		{
			return ((IFertilizer)item.getItem()).getFertilizeAmount();
		}
		
		else // Item is not a fertilizer
		{
			return 0;
		}
	}
	
	public void useFertilizer(int amountFertilize)
	{
		if((this.fertilizer + amountFertilize) >= 100)
		{
			this.fertilizer = 100;
			return;
		}
		else
		{
			this.fertilizer += amountFertilize;
		}
	}
}
