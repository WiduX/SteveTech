package net.widux.stevetech.farming;

public enum EnumFood
{
	
	BURGER("Hamburger", 6, new Object[]{}),
	BURGER_CHEESE("Cheeseburger", 7, new Object[]{}),
	BURGER_BACON("Baconburger", 8, new Object[]{}),
	BURGER_ALL("Bacon Cheeseburger", 9, new Object[]{}),
	COLESLAW("Coleslaw", 5, new Object[]{}),
	SALAD("Salad", 4, new Object[]{}),
	SALAD_CHICKEN("Salad with Chicken", 6, new Object[]{}),
	SALAD_CAESAR("Caesar Salad", 6, new Object[]{}),
	CHILI("Chili Concarne", 8, new Object[]{}),
	PIZZA_HAWAII("Hawaiian Pizza", 8, new Object[]{}),
	PIZZA_CHEESE("Cheese Pizza", 7, new Object[]{}),
	PIZZA_PEPPERONI("Pepperoni Pizza", 8, new Object[]{}),
	PIZZA_VEGGIE("Veggie Pizza", 7, new Object[]{}),
	CHEESEBREAD("Cheesebread", 6, new Object[]{}),
	HOTDOG("Hotdog", 6, new Object[]{}),
	CHOCOLATE("Chocolate", 2, new Object[]{}),
	CAKE_CARROT("Carrot Cake", 10, new Object[]{}),
	CAKE_CHOCOLATE("Chocolate Cake", 10, new Object[]{}),
	CAKE_CHEESE("Cheesecake", 10, new Object[]{}),
	PUDDING_VANILLA("Vanilla Pudding", 5, new Object[]{}),
	PUDDING_CHOCOLATE("Chocolate Pudding", 5, new Object[]{}),
	PUDDING_RICE("Rice Pudding", 8, new Object[]{}),
	ICECREAM_VANILLA("Vanilla Ice Cream", 5, new Object[]{}),
	ICECREAM_CHOCOLATE("Chocolate Ice Cream", 5, new Object[]{}),
	ICECREAM_STRAWBERRY("Strawberry Ice Cream", 5, new Object[]{}),
	ROULADEN("Rouladen", 10, new Object[]{}),
	TACO("Taco", 7, new Object[]{}),
	BURRITO("Burrito", 7, new Object[]{}),
	PASTA_MEATBALLS("Spaghetti and Metaballs", 9, new Object[]{}),
	CURRY("Curry", 8, new Object[]{}),
	GOULASH("Goulash", 9, new Object[]{}),
	ONION_RINGS("Onion Rings", 5, new Object[]{}),
	FRIES("French Fries", 5, new Object[]{}),
	SMOOTHIE_BERRY("Berry Smoothie", 4, new Object[]{}),
	SMOOTHIE_MELON("Melon Smoothie", 4, new Object[]{}),
	SOUP_NOODLE("Noodle Soup", 5, new Object[]{}),
	SOUP_CHICKEN_NOODLE("Chicken Noodle Soup", 7, new Object[]{}),
	SOUP_VEGETABLE("Vegetable Soup", 5, new Object[]{}),
	SOUP_PUMPKIN("Pumpkin Soup", 6, new Object[]{}),
	SOUP_TOMATO("Tomato Soup", 5, new Object[]{}),
	YOGURT("Yogurt", 3, new Object[]{}, EnumCraftingArea.FURNACE),
	POUTINE("Poutine", 9, new Object[]{}),
	STUFFED_BAKED_POTATO("Stuffed Baked Potato", 6, new Object[]{}),
	PIE_SHEPHERD("Shepherd's Pie", 9, new Object[]{}),
	PIE_APPLE("Apple Pie", 8, new Object[]{}),
	PIE_RASPBERRY("Raspberry Pie", 8, new Object[]{}),
	PIE_BERRY("Mixed Berry Pie", 8, new Object[]{}),
	PIE_BLUEBERRY("Blueberry Pie", 8, new Object[]{}),
	PIE_CHICKEN("Chicken Pot Pie", 6, new Object[]{}),
	SAUCE_APPLE("Apple Sauce", 4, new Object[]{}),
	MAC_CHEESE("Mac & Cheese", 7, new Object[]{}),
	GRILLED_CHEESE("Grilled Cheese", 6, new Object[]{}),
	FRENCH_TOAST("French Toast", 6, new Object[]{}),
	SUSHI("Sushi", 7, new Object[]{}),
	CHOW_MEIN("Chow Mein", 7, new Object[]{}),
	PANCAKES("Pancakes", 6, new Object[]{}),
	SWEET_SOUR_PORK("Sweet & Sour Pork", 8, new Object[]{}),
	MUFFIN_BLUEBERRY("Blueberry Muffin", 5, new Object[]{}),
	MUFFIN_CHOCOLATE("Chocolate Muffin", 5, new Object[]{}),
	BROWNIES("Brownies", 5, new Object[]{});
	
	
	private EnumFood(String identifier, int halfHealingUnits, Object[] crafting)
	{
		
	}
	
	private EnumFood(String identifier, int halfHealingUnits, Object[] crafting, EnumCraftingArea area)
	{
		
	}
	
	private EnumFood(String identifier, int halfHealingUnits, Object[] crafting1, Object[] crafting2)
	{
		
	}
	
}
