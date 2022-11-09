package droiidpelaez.economyplugin.commands;

import droiidpelaez.economyplugin.utils.BankUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class EcoAdminCommands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;
            //If the entered /eco -> Show the usage
            if(args.length == 0){
                p.sendMessage("========================================");
                p.sendMessage("Usage:\n/eco add {player} {amount}  :  Add funds to a player\n/eco remove {player} {amount}  :  removes funds from a player\n/eco reset  :  resets the entire economy.");
                p.sendMessage("========================================");
                return true;
            }
            String add = "add";
            String remove = "remove";
            String reset = "reset";
            // CASE : add
            if(args[0].toLowerCase().compareTo(add) == 0){
                //Handles the /eco add ____ case : null player input
                if(args[1] == null){ p.sendMessage("Please specify a player."); return true; }
                //This saves a player object of the target.
                Player target = Bukkit.getServer().getPlayerExact(args[1]);
                //This handles offline players, or misspelled players
                if(target == null){ p.sendMessage(ChatColor.GRAY+"This player is not online."); }
                //Reading the amount input to make sure it's a number
                for(int i = 0; i< args[1].length();i++) {
                    if (Character.isDigit(args[1].charAt(i)) == false) {
                        p.sendMessage(ChatColor.RED + "Incorrect usage, please try /eco add {player} {amount}");
                        return true;
                    }
                }
                Double deposit = Double.parseDouble(args[2]);
                BankUtils.updateBalance(target, deposit);

            }
            // CASE : remove
            else if(args[0].toLowerCase().compareTo(remove) == 0){
                if(args[1] == null){ p.sendMessage("Please specify a player."); return true; }

                Player target = Bukkit.getServer().getPlayerExact(args[1]);

                if(target == null){ p.sendMessage(ChatColor.GRAY+"This player is not online."); }
                for(int i = 0; i< args[1].length();i++) {
                    if (Character.isDigit(args[1].charAt(i)) == false) {
                        p.sendMessage(ChatColor.RED + "Incorrect usage, please try /eco remove {player} {amount}");
                        return true;
                    }
                }
                Double withdrawal = Double.parseDouble(args[2]);
                HashMap<UUID, Double> bankList = BankUtils.listAllBanks();
                Double original = bankList.get(target.getUniqueId());
                if(original - withdrawal < 0){ p.sendMessage(ChatColor.RED+"Attempted to remove too many funds!"); }
                BankUtils.updateBalance(target, original - withdrawal);
            }
            // CASE : reset
            else if(args[0].toLowerCase().compareTo(reset) == 0){
                if(args[1] == null){
                    BankUtils.deleteAllAct();
                }
                Player target = Bukkit.getServer().getPlayerExact(args[1]);
                if(target == null){ p.sendMessage(ChatColor.GRAY+"This player is not online."); }
                BankUtils.deleteAccount(target);
            }


        }





        return true;
    }


}
