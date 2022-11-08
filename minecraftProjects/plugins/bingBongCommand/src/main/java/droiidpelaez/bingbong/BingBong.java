package droiidpelaez.bingbong;

import org.bukkit.plugin.java.JavaPlugin;

public final class BingBong extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        //This is how you register a command into the plugins boot
        //The string is what the command will be :/bing
        getCommand("bing").setExecutor(new BingBong());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
