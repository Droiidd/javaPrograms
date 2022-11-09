package droiidpelaez.economyplugin;

import droiidpelaez.economyplugin.commands.CheckBalanceCommand;
import droiidpelaez.economyplugin.commands.EcoAdminCommands;
import org.bukkit.plugin.java.JavaPlugin;

public final class EconomyPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getCommand("balance").setExecutor(new CheckBalanceCommand());
        getCommand("eco").setExecutor(new EcoAdminCommands());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
