package droiidpelaez.bingbong;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

//First thing we do is implement the commandExecutor interface.
//If you type this, tab auto-fill to get the import, then press:
// Alt + Enter, then click enter again. This will autofill the method
//The command executor needs to actually execute.
public class bingCommand implements CommandExecutor {

    //This is the method I was talking about. I'll explain what happens inside of it
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        //The first thing we have to do is test who is executin the command
        //Is it a command block? A player? The console? We want the player, so we check for that:
        if(sender instanceof Player){
            //Now we create a player object that is == the sender that executed the message
            //(You can see why this would be a problem if it was not a player executing the message:
            //If it were the console, the player object we create in the next line would corrupt.
            Player p = (Player) sender;
            //Now that we have the player, we can do whatever we want: Get the location, send them
            //A message etc. For now we just want to make sure the command works, so lets send
            //a message.
            p.sendMessage(ChatColor.GRAY+"Bong!");
            // ===> The chat color this just changes the color, think of it like another
            //variable, hence the "+" in between the printed string and the ChatColor.

        }
        //This returns true is essentially a marker saying this command was successfully executed.
        //If it was false it would continue to run until the server stopped.
        return true;
    }
}
