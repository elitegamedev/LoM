package de.elicis.lom.sign;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;

import de.elicis.lom.Arena;
import de.elicis.lom.LoMLocation;
import de.elicis.lom.Main;

public class LoM_Sign {
	LoMLocation loc;
	Main main;
	LoM_SignType type;
	String name;
	String line1;
	String line2;
	String line3;

	public LoM_Sign(String name2, LoM_SignType type2, Main t, Location location) {
		main = t;
		if (main.Arenas.containsKey(name2)) {
			name = name2;
			Arena a = main.Arenas.get(name);
			loc = new LoMLocation(location);
			if (loc.getLocation().getBlock().getType() == Material.SIGN
					|| loc.getLocation().getBlock().getType() == Material.SIGN_POST) {
				if (type == LoM_SignType.ARENA) {
					String state;
					if (a.isActive()) {
						state = ChatColor.RED + "Running";
					} else {
						state = ChatColor.GREEN + "Ready";
					}
					line1 = "[Arena]";
					line2 = name;
					line3 = "[" + a.getPlayers().size() + "/10]" + "[" + state
							+ ChatColor.BLACK + "]";
				} else if (type == LoM_SignType.CHAMPION) {
					line1 = "[Champion]";
					line2 = name;
					line3 = "";
				}
			}
			type = type2;
		}
	}

	public void updateSign() {
		World w = loc.getLocation().getWorld();
		Block b = w.getBlockAt(loc.getLocation());
		if (b.getTypeId() == Material.SIGN_POST.getId()
				|| b.getTypeId() == Material.WALL_SIGN.getId()) {
			if (type.getType()
					.equalsIgnoreCase(LoM_SignType.CHAMPION.getType())) {

				line1 = "[Champion]";
				line2 = name;

			}
			if (type.getType().equalsIgnoreCase(LoM_SignType.ARENA.getType())) {
				if (main.Arenas.containsKey(name)) {
					Arena a = main.Arenas.get(name);
					String state;
					if (a.isActive()) {
						state = ChatColor.RED + "Running";
					} else {
						state = ChatColor.GREEN + "Ready";
					}
					line1 = "[Arena]";
					line2 = name;
					line3 = "[" + a.getPlayers().size() + "/10]" + "[" + state
							+ ChatColor.BLACK + "]";
				}

			}
			Sign sign = (Sign) b.getState();
			sign.setLine(0, line1);
			sign.setLine(1, line2);
			sign.setLine(2, line3);
			sign.update(true);

		}
	}

	public LoMLocation getLocation() {
		return loc;
	}

	public String getName() {
		return name;
	}

	public LoM_SignType getType() {
		return type;
	}
}
