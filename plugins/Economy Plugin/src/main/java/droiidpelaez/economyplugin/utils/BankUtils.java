package droiidpelaez.economyplugin.utils;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class BankUtils {
    private static HashMap<UUID, Double> bankList = new HashMap<>();

    // ==== CREATE ====
    public void createBankAccount(Player p){
        Double startBal = 500.00;
        bankList.put(p.getUniqueId(),startBal);
        p.sendMessage(ChatColor.GREEN+"Player bank created.");
        p.sendMessage(ChatColor.GRAY+p.getDisplayName()+"'s current balance: $"+startBal);
    }
    // ==== READ (From) ====
    public static HashMap<UUID, Double> listAllBanks(){return bankList;}

    // ==== UPDATE ====
    public void updateBalance(Player p, Double revenue){
        if(bankList.containsKey(p.getUniqueId()) == true){
            bankList.replace(p.getUniqueId(), revenue);
            p.sendMessage(ChatColor.GRAY+"$"+bankList.get(p.getUniqueId())+" has been added to your account!");
        }
        createBankAccount(p);
    }
    // ==== DELETE ====
    public void removeMoney(Player p, Double withdrawal){
        Double newBalance = bankList.get(p.getUniqueId()) - withdrawal;
        bankList.replace(p.getUniqueId(), newBalance);
        p.sendMessage(ChatColor.GRAY+"Remaining balance: $"+bankList.get(p.getUniqueId()));
    }
    // ==== MISC ====
}
