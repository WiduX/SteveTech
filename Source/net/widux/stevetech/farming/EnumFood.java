package net.widux.stevetech.farming;

public enum EnumFood
{
	
	BURGER("Hamburger", 6),
	BURGER_CHEESE("Cheeseburger", 7),
	BURGER_BACON("Baconburger", 8),
	BURGER_ALL("Bacon Cheeseburger", 9),
	COLESLAW("Coleslaw", 5),
	SALAD("Salad", 4),
	SALAD_CHICKEN("Salad with Chicken", 6),
	SALAD_CAESAR("Caesar Salad", 6),
	CHILI("Chili Concarne", 8),
	PIZZA_HAWAII("Hawaiian Pizza", 8),
	PIZZA_CHEESE("Cheese Pizza", 7),
	PIZZA_PEPPERONI("Pepperoni Pizza", 8),
	PIZZA_VEGGIE("Veggie Pizza", 7),
	CHEESEBREAD("Cheesebread", 6),
	HOTDOG("Hotdog", 6),
	CHOCOLATE("Chocolate", 2),
	CAKE_CARROT("Carrot Cake", 10),
	CAKE_CHOCOLATE("Chocolate Cake", 10),
	CAKE_CHEESE("Cheesecake", 10),
	PUDDING_VANILLA("Vanilla Pudding", 5),
	PUDDING_CHOCOLATE("Chocolate Pudding", 5),
	PUDDING_RICE("Rice Pudding", 8),
	ICECREAM_VANILLA("Vanilla Ice Cream", 5),
	ICECREAM_CHOCOLATE("Chocolate Ice Cream", 5),
	ICECREAM_STRAWBERRY("Strawberry Ice Cream", 5),
	ROULADEN("Rouladen", 10),
	TACO("Taco", 7),
	BURRITO("Burrito", 7),
	PASTA_MEATBALLS("Spaghetti and Metaballs", 9),
	CURRY("Curry", 8),
	GOULASH("Goulash", 9),
	ONION_RINGS("Onion Rings", 5),
	FRIES("French Fries", 5),
	SMOOTHIE_BERRY("Berry Smoothie", 4),
	SMOOTHIE_MELON("Melon Smoothie", 4),
	SOUP_NOODLE("Noodle Soup", 5),
	SOUP_CHICKEN_NOODLE("Chicken Noodle Soup", 7),
	SOUP_VEGETABLE("Vegetable Soup", 5),
	SOUP_PUMPKIN("Pumpkin Soup", 6),
	SOUP_TOMATO("Tomato Soup", 5),
	YOGURT("Yogurt", 3),
	POUTINE("Poutine", 9),
	STUFFED_BAKED_POTATO("Stuffed Baked Potato", 6),
	PIE_SHEPHERD("Shepherd's Pie", 9),
	PIE_APPLE("Apple Pie", 8),
	PIE_RASPBERRY("Raspberry Pie", 8),
	PIE_BERRY("Mixed Berry Pie", 8),
	PIE_BLUEBERRY("Blueberry Pie", 8),
	PIE_CHICKEN("Chicken Pot Pie", 6),
	SAUCE_APPLE("Apple Sauce", 4),
	MAC_CHEESE("Mac & Cheese", 7),
	GRILLED_CHEESE("Grilled Cheese", 6),
	FRENCH_TOAST("French Toast", 6),
	SUSHI("Sushi", 7),
	CHOW_MEIN("Chow Mein", 7),
	PANCAKES("Pancakes", 6),
	SWEET_SOUR_PORK("Sweet & Sour Pork", 8),
	MUFFIN_BLUEBERRY("Blueberry Muffin", 5),
	MUFFIN_CHOCOLATE("Chocolate Muffin", 5),
	BROWNIES("Brownies", 5);
	
	private String name;
	private int halfHealingUnits;
	private float saturation;
	private boolean willWolfEat;
	
	private EnumFood(String name, int halfHealingUnits)
	{
		this.name = name;
		this.halfHealingUnits = halfHealingUnits;
		this.saturation = 0.6F;
	}
	
	private EnumFood(String name, int halfHealingUnits, float saturation)
	{
		this.name = name;
		this.halfHealingUnits = halfHealingUnits;
		this.saturation = saturation;
	}
	
	private EnumFood(String name, int halfHealingUnits, float saturation, boolean willWolfEat)
	{
		this.name = name;
		this.halfHealingUnits = halfHealingUnits;
		this.saturation = saturation;
		this.willWolfEat = willWolfEat;
	}
	
	public String getName() {return this.name;}
	public int getHealAmount() {return this.halfHealingUnits;}
	public float getSaturation() {return this.saturation;}
	public boolean willWolfEat() {return this.willWolfEat;}
	
	public int getID() {return this.ordinal();}
	
	public static int getNumberFoods() {return values().length;}
	public static EnumFood getFood(int itemDamage) {return values()[itemDamage];}
	
}
