package dev.norska.dwaddon.tm.ndev.handlers;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.iridium.iridiumcolorapi.IridiumColorAPI;

import dev.norska.dwaddon.tm.TMAddon;
import dev.norska.dwaddon.tm.update.UpdateChecker;
import lombok.Getter;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class UpdateHandler {
	
	@Getter private Boolean updateAvailable = false, notifyForUpdates = false;
	@Getter private String latestUpdate;
	@Getter private int resourceID = 977;
	@Getter private String downloadLinkSpigot = "https://norska.dev/r/spigot/dw/";
	@Getter private String downloadLinkPolymart = "https://norska.dev/r/polymart/dw/";
	@Getter private String version;
	
	public void checkForUpdates(TMAddon main) {
		version = main.getDescription().getVersion();
		UpdateChecker updater = new UpdateChecker(main, resourceID);
	    try {
	        if (updater.checkForUpdates()) {
	        	updateAvailable = true;
	        	latestUpdate = updater.getLatestVersion();
	    		new BukkitRunnable() {
	    			@Override
	    			public void run() {
	    				if(main.getNHandler().getConfigurationHandler().getConfigFile().contains("updates.notifications")) {
	    					notifyForUpdates = main.getNHandler().getConfigurationHandler().getConfigFile().getBoolean("updates.notifications");
	    				} else {
	    					notifyForUpdates = true;
	    				}
	    				if(notifyForUpdates) sendUpdateMessage(main, (CommandSender) Bukkit.getConsoleSender(), false);
	    			}
	    		}.runTaskLater(main, 20 * 5);
	        }else {
	        	updateAvailable = false;
	        }
	    } catch (Exception e) {
	    	updateAvailable = false;
	    }
	}
	
	@SuppressWarnings("deprecation")
	public void sendUpdateMessage(TMAddon main, CommandSender cms, Boolean join) {
		if (updateAvailable) {
				cms.sendMessage("");
				cms.sendMessage(IridiumColorAPI.process(" " + main.prefix + " &7&oA new update is available!"));
				cms.sendMessage(IridiumColorAPI.process(" &7Running on <SOLID:ff5d54>" + version + " &7while latest is <SOLID:82ff54>" + latestUpdate + "&7!"));
				cms.sendMessage("");
				if(cms instanceof Player) {
					Player p = (Player) cms;
		 	       TextComponent message1 = new TextComponent(" §a§l•§r §7Resource Page");
		 	        
		 	        TextComponent message1a = new TextComponent(IridiumColorAPI.process(" §e[SpigotMC]"));
		 	        message1a.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, downloadLinkSpigot));
		 	        message1a.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(IridiumColorAPI.process("&a&l• &7Go to &eSpigotMC &7Resource Page...")).create()));
		 	        message1.addExtra(message1a);
		 	        
		 	       TextComponent message1b = new TextComponent(IridiumColorAPI.process(" §9[Polymart]"));
		 	        message1b.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, downloadLinkPolymart));
		 	        message1b.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(IridiumColorAPI.process("&a&l• &7Go to &9Polymart &7Resource Page...")).create()));
		 	        message1.addExtra(message1b);
		 	        p.spigot().sendMessage(message1);
		        } else {
		        	cms.sendMessage(IridiumColorAPI.process(" &e" + downloadLinkSpigot));
					cms.sendMessage(IridiumColorAPI.process(" &9" + downloadLinkPolymart));
		        }
				cms.sendMessage("");
        } else {
        	if(join) return;
        		cms.sendMessage("");
            	cms.sendMessage(IridiumColorAPI.process(" " + main.prefix + " &7No updates available! Running on <SOLID:cbffb8>&o" + version + "&7&o!"));
    			cms.sendMessage("");
        }
	}



}
