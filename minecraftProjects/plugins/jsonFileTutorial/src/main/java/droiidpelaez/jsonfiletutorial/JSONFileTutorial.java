package droiidpelaez.jsonfiletutorial;

import droiidpelaez.jsonfiletutorial.commands.createNoteCommand;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;

public final class JSONFileTutorial extends JavaPlugin {

    private static JSONFileTutorial plugin;

    public static JSONFileTutorial getPlugin() {
        return plugin;
    }

    @Override
    public void onEnable() {
        plugin = this;
        JSONFileTutorial.getPlugin().getCommand("note").setExecutor(new createNoteCommand());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
