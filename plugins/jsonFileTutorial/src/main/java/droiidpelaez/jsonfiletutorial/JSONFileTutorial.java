package droiidpelaez.jsonfiletutorial;

import droiidpelaez.jsonfiletutorial.commands.createNoteCommand;
import droiidpelaez.jsonfiletutorial.utils.noteStorageUtil;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public final class JSONFileTutorial extends JavaPlugin {

    private static JSONFileTutorial plugin;

    public static JSONFileTutorial getPlugin() {
        return plugin;
    }

    @Override
    public void onEnable() {
        //We need an instance of this plugin within itself so we can access it from other parts of the plugin / other classes
        plugin = this;
        //This line of code is what grabs the command we created and will execute it if the server is online
        JSONFileTutorial.getPlugin().getCommand("note").setExecutor(new createNoteCommand());
        try {
            noteStorageUtil.loadNotes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        try {
            noteStorageUtil.saveNotes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
