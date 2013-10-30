package de.elicis.lom.listener;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerInteractEvent;

import de.elicis.lom.api.LoM_API;
import de.elicis.lom.champions.Champion;
import de.elicis.lom.data.Arena;
import de.elicis.lom.sign.LoM_TowerSign;
import de.elicis.lom.tower.Tower;

public class L_Combat implements Listener {
	ArrayList<Material> weapons = new ArrayList<Material>();
	public L_Combat() {
		addweapons();
	}

	@EventHandler
	public void onEntityDamage(EntityDamageByEntityEvent event) {
		if (event.getEntityType() == (EntityType.PLAYER)) {
			Player player1 = (Player) event.getEntity();
			if (LoM_API.isInArena(player1)) {
				Arena arena = LoM_API.getArenaP(player1);

				if (event.getDamager().getType() == (EntityType.PLAYER)) {
					if (weapons.contains(player1.getItemInHand().getData().getItemType())) {
						Player player2 = (Player) event.getDamager();
						if (LoM_API.isInArena(player2)) {
							if (arena.isActive()) {
								if (arena.getTeam(player1) != arena
										.getTeam(player2)) {
									Champion champPlayer1 = arena.getChamps()
											.get(player1.getName());
									Champion champPlayer2 = arena.getChamps()
											.get(player2.getName());
									event.setDamage((float) champPlayer1.getDamage()
											* (100 / (100 + champPlayer2
													.getArmor())));
								}
							}
						}
					}
				}
				if (event.getCause() == DamageCause.PROJECTILE) {
					if (event.getDamager().getType() == (EntityType.ARROW)) {
						Arrow a = (Arrow) event.getDamager();
						if (a.getShooter() instanceof Player) {
							Player player2 = (Player) a.getShooter();
							if (LoM_API.isInArena(player2)) {
								if (arena.isActive()) {
									if (arena.getTeam(player1) != arena
											.getTeam(player2)) {
										Champion champPlayer1 = arena
												.getChamps().get(
														player1.getName());
										Champion champPlayer2 = arena
												.getChamps().get(
														player2.getName());
										event.setDamage((float) champPlayer1
												.getDamage()
												* (100 / (100 + champPlayer2
														.getArmor())));
									}
								}
							}
						}
					}
					if (event.getDamager().getType() == (EntityType.SNOWBALL)) {
						Snowball a = (Snowball) event.getDamager();
						if (a.getShooter() instanceof Player) {
							Player player2 = (Player) a.getShooter();
							if (LoM_API.isInArena(player2)) {
								if (arena.isActive()) {
									if (arena.getTeam(player1) != arena
											.getTeam(player2)) {
										Champion champPlayer1 = arena
												.getChamps().get(
														player1.getName());
										Champion champPlayer2 = arena
												.getChamps().get(
														player2.getName());
										event.setDamage((float) champPlayer1
												.getDamage()
												* (100 / (100 + champPlayer2
														.getArmor())));
									}
								}
							}
						}
					}

				}
			}
		}
	}

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		if (event.getAction() == (Action.LEFT_CLICK_AIR)
				|| event.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
			if (LoM_API.isInArena(player)) {
				if (player.getItemInHand().getData().getItemType() == Material.GOLD_HOE) {

				}
			}
		}
		if(event.getAction() == Action.LEFT_CLICK_BLOCK){
			if(event.getClickedBlock().getType() == Material.SIGN
					|| event.getClickedBlock().getType() == Material.SIGN_POST){
				Sign sign = (Sign) event.getClickedBlock();
				if(LoM_API.isLoM_TowerSign(sign)){
					LoM_TowerSign lsign = LoM_API.getLoM_TowerSign(sign);
						if(LoM_API.getArenaW(sign.getWorld()) != null){
								if(LoM_API.isInArena(player)){
										Arena a = LoM_API.getArenaW(sign.getWorld());
									if(a.getChamps().get(player.getName()) != null){
										Tower t = lsign.getTower();
										Champion c = a.getChamps().get(player.getName());
										t.setHealth(t.getHealth()-(c.getDamage()* (100/100 + t.getArmor())));
									}
								
							}
								
						
					}
				}
			}
		}
	}

	public void addweapons() {
		weapons.add(Material.IRON_HOE);
		weapons.add(Material.IRON_SWORD);
		weapons.add(Material.WOOD_SWORD);
		weapons.add(Material.BONE);
		weapons.add(Material.GOLD_SWORD);
		weapons.add(Material.DIAMOND_SWORD);
	}
}
