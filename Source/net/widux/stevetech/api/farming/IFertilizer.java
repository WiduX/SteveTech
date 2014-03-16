package net.widux.stevetech.api.farming;

public interface IFertilizer
{
	/**
	 * The higher this value, the more fertilization it will add to the plant, therefore making it a better fertilizer.
	 * Bonemeal is 15. The maximum fertilization a plant can have is 100.
	 * If this returns 0, it will be an invalid fertilizer.
	 * @return The fertilization value of the fertilizer.
	 */
	public abstract int getFertilizeAmount();
	
}
