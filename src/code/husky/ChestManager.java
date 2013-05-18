package code.husky;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class ChestManager extends JavaPlugin {

	YamlConfiguration config = YamlConfiguration.loadConfiguration(new File("plugins/ChestManager/config.yml"));
	MySQL m = new MySQL(config.getString("MySQL-host"), config.getString("MySQL-port"), config.getString("MySQL-database"), config.getString("MySQL-user"), config.getString("MySQL-password"));
	Connection c = null;

	public void onEnable() {

		getServer().getPluginManager().registerEvents(new ChestListener(), this);
		getCommand("cm").setExecutor(new ChestCommands());

		setupMySQL();
		setupData();

	}

	public void onDisable() {
		try {
			m.getConn().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void setupMySQL() {
		c = m.open();
		
		try {
			Statement s = c.createStatement();
			s.executeUpdate("CREATE TABLE IF NOT EXISTS '" + config.getString("MySQL-table") + "';");
			s.executeUpdate("INSERT INTO `" + config.getString("MySQL-database") + "`.`" + config.getString("MySQL-database") + "` (`creator`, `worldname`, `coords`, `contents`) VALUES ('Placeholder', '', '', '');");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}

	private void setupData() {
		File f = new File("plugins/ChestManager/config.yml");
		YamlConfiguration config = YamlConfiguration.loadConfiguration(f);
		if (!f.exists()) {
			config.options().header("-- MySQL Settings --");
			config.set("MySQL-host", "localhost");
			config.set("MySQL-port", "3306");
			config.set("MySQL-user", "root");
			config.set("MySQL-password", "root");
			config.set("MySQL-database", "chestmanager");
			config.set("MySQL-table", "data");
			try {
				config.save(f);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
