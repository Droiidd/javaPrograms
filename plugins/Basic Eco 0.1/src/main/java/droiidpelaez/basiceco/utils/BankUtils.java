package droiidpelaez.basiceco.utils;

import com.google.gson.Gson;
import droiidpelaez.basiceco.BasicEco;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.io.*;
import java.util.*;

public class BankUtils {
    private static HashMap<UUID, Double> bankList = new HashMap<>();
    private BasicEco plugin;

    // ==== CREATE ====
    public static void createBankAccount(Player p){
        Double startBal = 500.00;
        bankList.put(p.getUniqueId(),startBal);
        p.sendMessage(ChatColor.GREEN+"Player bank created.");
        p.sendMessage(ChatColor.GRAY+p.getDisplayName()+"'s current balance: $"+startBal);
        try{
            System.out.println("try worked");
            saveBank();
        }catch (IOException e){
            System.out.printf("caught instead");
            e.printStackTrace();
        }
        System.out.println("Pre trySaving()");
        trySaving();
    }
    // ==== READ (From) ====
    public static HashMap<UUID, Double> listAllBanks(){return bankList;}

    // ==== UPDATE ====
    public static void updateBalance(Player p, Double revenue){
        if(bankList.containsKey(p.getUniqueId()) == true){
            bankList.replace(p.getUniqueId(), revenue + bankList.get(p.getUniqueId()));
            p.sendMessage(ChatColor.GRAY+"$"+revenue+" has been added to your account!");
            trySaving();
        }
        else{ createBankAccount(p); }
    }
    // ==== DELETE ====
    public static void removeMoney(Player p, Double withdrawal){
        Double newBalance = bankList.get(p.getUniqueId()) - withdrawal;
        bankList.replace(p.getUniqueId(), newBalance);
        p.sendMessage(ChatColor.GRAY+"Remaining balance: $"+bankList.get(p.getUniqueId()));
        trySaving();
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

    public static void trySaving(){
        try{
            saveBank();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    // ===== SAVE NOTES / LOAD NOTES =====

    public static void saveBank() throws IOException {
        Gson gson = new Gson();
        File file = new File(BasicEco.getPlugin().getDataFolder().getAbsolutePath()+"/bankAccounts.json");
        file.getParentFile().mkdir();
        file.createNewFile();
        System.out.println("Pre file write");
        Writer writer = new FileWriter(file, false);
        gson.toJson(bankList, writer);
        System.out.println("Post file write");
        writer.flush();
        writer.close();
    }

    public static void loadBank() throws IOException{
        Gson gson = new Gson();
        File file = new File(BasicEco.getPlugin().getDataFolder().getAbsolutePath()+"/bankAccounts.json");
        if(file.exists()){
            Reader reader = new FileReader(file);
            Double[] n = gson.fromJson(reader, Double[].class);
        }

    }
}
