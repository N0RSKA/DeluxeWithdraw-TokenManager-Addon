package dev.norska.dwaddon.tm.ndev;

import dev.norska.dwaddon.tm.ndev.handlers.CacheHandler;
import dev.norska.dwaddon.tm.ndev.handlers.ConfigHandler;
import lombok.Getter;

public class NorskaHandler {
	
	@Getter private CacheHandler cacheHandler = new CacheHandler();
	@Getter private ConfigHandler configurationHandler = new ConfigHandler();

}
