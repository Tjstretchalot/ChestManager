package code.husky;

import org.bukkit.plugin.java.JavaPlugin;

public class ChestManager extends JavaPlugin {
	
	public void onEnable() {
		getServer().getPluginManager().registerEvents(new ChestListener(), this);
	}

}
