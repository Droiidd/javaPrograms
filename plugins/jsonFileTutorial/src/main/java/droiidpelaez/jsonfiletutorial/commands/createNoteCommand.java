package droiidpelaez.jsonfiletutorial.commands;

import droiidpelaez.jsonfiletutorial.models.Note;
import droiidpelaez.jsonfiletutorial.utils.noteStorageUtil;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class createNoteCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;
            //If the user did not input the correct syntax of the command
            if(args.length == 0){
                p.sendMessage("========================================");
                p.sendMessage("Usage:\n/note create \"message\"  :  creates a note\n/note list  :  provides a list of notes\n/note delete \"ID\"  :  delete a note from the list");
                p.sendMessage("========================================");
                return true;
            }
            String create = "create";
            String delete = "delete";
            String list = "list";
            if(args[0].toLowerCase().compareTo(create) == 0){
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 1; i < args.length - 1; i++){
                    //This will take all array items and put them into a string
                    //EXCEPT THE LAST - since we don't want the extra space at the end
                    stringBuilder.append(args[i]).append(" ");
                }
                //This line takes the last argument of the array and adds to str, no end space
                stringBuilder.append(args[args.length-1]);
                noteStorageUtil.createNote(p, stringBuilder.toString());
                p.sendMessage("Note created!");
            }
            //If the user is trying to view "list"
            else if(args[0].toLowerCase().compareTo(list) == 0){
                    //This will grab the array list storing all the local notes
                    ArrayList<Note> noteList = noteStorageUtil.findAllNotes();
                    //This sorts through the list to display it to the player
                    int i = 1;
                    for(Note note : noteList){
                        p.sendMessage("ID : "+ i +" ==> "+ note.getMessage());
                        i+=1;
                    }
                    return true;
            }

            else if(args[0].toLowerCase().compareTo(delete) == 0){
                ArrayList<Note> noteList = noteStorageUtil.findAllNotes();
                String all = "all";
                if(args[1].length() == 0){
                    p.sendMessage(ChatColor.RED+"Incorrect usage, please try /note delete \"ID\"");
                    return true;
                }
                if(args[1].toLowerCase().compareTo(all) == 0){
                    for(int i = 0;i<args.length-2;i++){
                        noteStorageUtil.deleteNote(noteList.get(i).getId());
                    }
                    p.sendMessage("All notes removed!");
                    return true;
                }
                for(int i = 0; i< args[1].length();i++){
                    if(Character.isDigit(args[1].charAt(i)) == false){
                        p.sendMessage(ChatColor.RED+"Incorrect usage, please try /note delete \"ID\"");
                        return true;
                    }
                }
                    int deletedId = Integer.parseInt(args[1]);
                    p.sendMessage("Note deleted!");
                    noteStorageUtil.deleteNote(noteList.get(deletedId-1).getId());
            }
            //If the user inputted anything else that is incorrect
            else{
                p.sendMessage(ChatColor.RED+"Incorrect usage, please try /note {create,delete,update} \"message\"");
                return true;
            }
        }
        //Commands must return either true{If the command is finished executing} || false {If: the command will loop itself continuously}
        return true;
    }
}
