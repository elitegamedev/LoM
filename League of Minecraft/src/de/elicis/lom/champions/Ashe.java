package de.elicis.lom.champions;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Ashe extends Champion {
	public Ashe(Player player2) {
		name = "Ashe";
		player = player2.getName();
		level = getPlayer().getLevel();
		health = (double) (395 + (level * 79) + itemhealth);
		mana = 173 + (35 * level) + itemmana;
		manaregen = 6.3 + (0.4 * level) + itemmanaregen;
		damage = (int) (46.3 + (level * 2.85) + itemdamage);
		abilityPower = 0 * level + itemabilityPower;
		armor = (int) (11.5 + (level * 3.4) + itemarmor);
		magicResist = 30 + itemmagicResist;
		speed = 1 + itemspeed;
		weapon = new ItemStack(Material.BOW, 1);
		weapon.addEnchantment(Enchantment.ARROW_INFINITE, 1);
		weapon.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
		getPlayer().getInventory().addItem(weapon);
		getPlayer().getInventory().addItem(new ItemStack(Material.ARROW, 1));
		getPlayer().setMaxHealth(health);
		getPlayer().setHealth(health);
		getPlayer().setHealthScaled(true);

	}

	@Override
	public void updateChamp() {
		level = getPlayer().getLevel();
		health = (double) (395 + (level * 79) + itemhealth);
		mana = 173 + (35 * level) + itemmana;
		manaregen = 6.3 + (0.4 * level) + itemmanaregen;
		damage = (int) (46.3 + (level * 2.85) + itemdamage);
		abilityPower = 0 * level + itemabilityPower;
		armor = (int) (11.5 + (level * 3.4) + itemarmor);
		magicResist = 30 + itemmagicResist;
		speed = 1 + itemspeed;
	}
}