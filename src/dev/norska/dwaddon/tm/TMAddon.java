package dev.norska.dwaddon.tm;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.iridium.iridiumcolorapi.IridiumColorAPI;

import dev.norska.dw.DeluxeWithdraw;
import dev.norska.dwaddon.tm.addon.TMInterface;
import dev.norska.dwaddon.tm.ndev.NorskaHandler;
import lombok.Getter;

public class TMAddon extends JavaPlugin {
	
	private static TMAddon instance;
	public TMAddon() { instance = this; }
	public static TMAddon getInstance() { return instance; }
	
	@Getter private NorskaHandler nHandler = new NorskaHandler();
	
	public String prefix;
	
	public void onEnable() {
		
		prefix = Bukkit.getVersion().contains("1.16") || Bukkit.getVersion().contains("1.17") || Bukkit.getVersion().contains("1.18") ? 
				IridiumColorAPI.process("§8[<GRADIENT:FFE818>&lDeluxeWithdraw TokenManager Addon</GRADIENT:B7FF1D>§8]§r") :
				IridiumColorAPI.process("&8[&e&lDeluxeWithdraw TokenManager Addon&8]&r");
		
		Bukkit.getConsoleSender().sendMessage(" ");
    	Bukkit.getConsoleSender().sendMessage(prefix + " §7" + getDescription().getVersion() + "§f, a §efreemium §faddon by §7Norska §f- §7Thanks for using!");
		Bukkit.getConsoleSender().sendMessage(" ");
		
		getNHandler().getConfigurationHandler().generateFiles(this);
		getNHandler().getCacheHandler().cache(this);
		
		try {
			DeluxeWithdraw.getInstance().getAddonProvider().registerNewProvider("TOKENMANAGER", new TMInterface());
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
