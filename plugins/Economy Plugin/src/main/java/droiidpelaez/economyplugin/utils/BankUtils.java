package droiidpelaez.economyplugin.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.*;

public class BankUtils {
    private static HashMap<UUID, Double> bankList = new HashMap<>();

    // ==== CREATE ====
    public static void createBankAccount(Player p){
        Double startBal = 500.00;
        bankList.put(p.getUniqueId(),startBal);
        p.sendMessage(ChatColor.GREEN+"Player bank created.");
        p.sendMessage(ChatColor.GRAY+p.getDisplayName()+"'s current balance: $"+startBal);
    }
    // ==== READ (From) ====
    public static HashMap<UUID, Double> listAllBanks(){return bankList;}

    // ==== UPDATE ====
    public static void updateBalance(Player p, Double revenue){
        if(bankList.containsKey(p.getUniqueId()) == true){
            bankList.replace(p.getUniqueId(), revenue);
            p.sendMessage(ChatColor.GRAY+"$"+bankList.get(p.getUniqueId())+" has been added to your account!");
        }
        createBankAccount(p);
    }
    // ==== DELETE ====
    public static void removeMoney(Player p, Double withdrawal){
        Double newBalance = bankList.get(p.getUniqueId()) - withdrawal;
        bankList.replace(p.getUniqueId(), newBalance);
        p.sendMessage(ChatColor.GRAY+"Remaining balance: $"+bankList.get(p.getUniqueId()));
    }
    public static void deleteAccount(Player p){
        bankList.remove(p.getUniqueId());
    }
    public static void deleteAllAct(){
        bankList = new HashMap<UUID, Double>();
    }
    // ==== MISC ====
    public static void displaySortedBanks(Player p){

        Collection<Double> values = bankList.values();
        ArrayList<Double> actBalance = new ArrayList<>(values);

        Set<UUID> keySet = bankList.keySet();
        ArrayList<UUID> pIdList = new ArrayList(keySet);

        ArrayList<Double> sortedBalance = sort(actBalance);


        for(int i = 0; i<5; i++){
        if(bankList.containsValue(sortedBalance.get(i)) != false){


            p.sendMessage(i+".) ");
        }
        }

    }

    public static ArrayList<Double> sort(ArrayList<Double> arr){
        if(arr.size() <= 1){return arr;}

        ArrayList<Double> left = new ArrayList<>();
        ArrayList<Double> right = new ArrayList<>();
        for (int i = 0; i< arr.size();i++){
            if(i%2 != 0){ left.add(arr.get(i)); }
            else{ right.add(arr.get(i)); }
        }

        left = sort(left);
        right = sort(right);

        return merge(left, right);
    }

    public static ArrayList<Double> merge(ArrayList<Double> left ,ArrayList<Double> right){
        ArrayList<Double> ret = new ArrayList<>();
        while(!left.isEmpty() && !right.isEmpty()){
            if(left.get(0)<=right.get(0)){ ret.add(left.get(0)); left.remove(0); }
            else{ ret.add(right.get(0)); right.remove(0); }
        }
        while(!left.isEmpty()){
            ret.add(left.get(0));
            left.remove(0);
        }
        while(!right.isEmpty()){
            ret.add(right.get(0));
            right.remove(0);
        }
        return ret;
    }
}
