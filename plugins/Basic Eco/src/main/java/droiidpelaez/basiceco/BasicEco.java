package droiidpelaez.basiceco;

import droiidpelaez.basiceco.commands.CheckBalanceCommand;
import droiidpelaez.basiceco.commands.EcoAdminCommands;
import droiidpelaez.basiceco.utils.BankUtils;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public final class BasicEco extends JavaPlugin {

    private static BasicEco plugin;

    public static BasicEco getPlugin(){ return plugin; }

    @Override
    public void onEnable() {
        // Plugin startup logic
        getCommand("balance").setExecutor(new CheckBalanceCommand());
        getCommand("eco").setExecutor(new EcoAdminCommands());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        try {
            BankUtils.saveBank();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
