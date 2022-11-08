package droiidpelaez.jsonfiletutorial.commands;

import droiidpelaez.jsonfiletutorial.utils.noteStorageUtil;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class createNoteCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;
            if(args.length > 0){
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < args.length - 1; i++){
                    //This will take all array items and put them into a string
                    //EXCEPT THE LAST - since we don't want the extra space at the end
                    stringBuilder.append(args[i]).append(" ");
                }
                //This line takes the last argument of the array and adds to str, no end space
                stringBuilder.append(args[args.length-1]);

                noteStorageUtil.createNote(p, stringBuilder.toString());
            }else{
                p.sendMessage(ChatColor.RED+"Incorrect usage, please try /note \"message\"");0
            }
        }

        return true;
    }
}
