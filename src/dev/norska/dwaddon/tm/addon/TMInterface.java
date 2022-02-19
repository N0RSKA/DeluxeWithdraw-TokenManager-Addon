package dev.norska.dwaddon.tm.addon;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import dev.norska.dw.api.DWAddonInterface;
import dev.norska.dwaddon.tm.TMAddon;
import me.realized.tokenmanager.api.TokenManager;

public class TMInterface implements DWAddonInterface {
	
	final TokenManager tokenManager = (TokenManager) Bukkit.getPluginManager().getPlugin("TokenManager");

	@Override
	public void add(Player p, Double amount) {
		tokenManager.addTokens(p, amount.intValue());
	}

	@Override
	public void add(Player p, Integer amount) {
		tokenManager.addTokens(p, amount);
	}

	@Override
	public String adminCreator() {
		return TMAddon.getInstance().getNHandler().getCacheHandler().getCreatorForAdminItems();
	}

	@Override
	public int currencyFormat() {
		return TMAddon.getInstance().getNHandler().getCacheHandler().getCurrencyFormat();
	}

	@Override
	public String currencyString() {
		return TMAddon.getInstance().getNHandler().getCacheHandler().getCurrencyName();
	}

	@Override
	public Double getCurrent(Player p) {
		return (double) tokenManager.getTokens(p).getAsLong();
	}

	@Override
	public Boolean has(Player p, Double amount) {
		return tokenManager.getTokens(p).getAsLong() >= amount;
	}

	@Override
	public Boolean has(Player p, Integer amount) {
		return tokenManager.getTokens(p).getAsLong() >= amount;
	}

	@Override
	public String layout() {
		return TMAddon.getInstance().getNHandler().getCacheHandler().getItemLayout();
	}

	@Override
	public Double maxWithdraw() {
		return TMAddon.getInstance().getNHandler().getCacheHandler().getMaxWithdraw();
	}

	@Override
	public Double minWithdraw() {
		return TMAddon.getInstance().getNHandler().getCacheHandler().getMinWithdraw();
	}

	@Override
	public void set(Player p, Double arg1) {
		
		
	}

	@Override
	public void set(Player p, Integer amount) {
		
	}

	@Override
	public void take(Player p, Double amount) {
		tokenManager.setTokens(p, tokenManager.getTokens(p).getAsLong() - amount.intValue());
	}

	@Override
	public void take(Player p, Integer amount) {
		tokenManager.setTokens(p, tokenManager.getTokens(p).getAsLong() - amount);
	}

	@Override
	public List<String> withdrawCommands() {
		return TMAddon.getInstance().getNHandler().getCacheHandler().getCommands();
	}

	@Override
	public void reloadConfiguration() {
		TMAddon.getInstance().generateFiles();
		TMAddon.getInstance().cache();
	}

}
