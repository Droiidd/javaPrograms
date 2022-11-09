package droiidpelaez.jsonfiletutorial.utils;

import com.google.gson.Gson;
import droiidpelaez.jsonfiletutorial.JSONFileTutorial;
import droiidpelaez.jsonfiletutorial.models.Note;
import org.bukkit.entity.Player;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class noteStorageUtil {

    private static ArrayList<Note> noteList = new ArrayList<>();


    //Using the CRUD Database method: Create, read, update, delete

    // ==== CREATE ====
    public static Note createNote(Player p , String message){
        Note note = new Note(p.getDisplayName(), message);
        noteList.add(note);
        try{
            saveNotes();
        }catch (IOException e){
            e.printStackTrace();
        }
        return note;
    }

    // ==== DELETE ====
    public static void deleteNote(String id){
    for(Note note : noteList){
        if(note.getId().equalsIgnoreCase(id)){
            noteList.remove(note);
            break;
             }
         }
    try{
        saveNotes();

    }catch (IOException e){
        e.printStackTrace();
    }
    }

    // ==== READ ====
    public static Note findNote(String id) {
        for (Note note : noteList) {
            if (note.getId().equalsIgnoreCase(id)) {
                return note;
            }
        }
        return null;
    }
    // ==== UPDATE ====
    public static Note updateNote(String id, Note newNote){
        for(Note note : noteList){
            if(note.getId().equalsIgnoreCase(id)){
                note.setPlayerName(newNote.getPlayerName());
                note.setMessage(newNote.getMessage());
                try{
                    saveNotes();
                }catch (IOException e){
                    e.printStackTrace();
                }
                return note;
            }
        }
        try{
            saveNotes();
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    //This method is used to save the notes to the JSON file of the server. It moves all local data within the server to the file, which can be read -
    // - upon server start up: allowing you to access to the previous data.
    public static void saveNotes() throws IOException {
        Gson gson = new Gson();
        File file = new File(JSONFileTutorial.getPlugin().getDataFolder().getAbsolutePath()+"/notes.json");
        file.getParentFile().mkdir();
        file.createNewFile();
        Writer writer = new FileWriter(file, false);
        gson.toJson(noteList, writer);
        writer.flush();
        writer.close();
        System.out.println("Notes saved.");
    }
    //Returns the list of notes held in local data.
    public static ArrayList<Note> findAllNotes(){
        return noteList;
    }

    public static void loadNotes() throws IOException {
        Gson gson = new Gson();
        File file = new File(JSONFileTutorial.getPlugin().getDataFolder().getAbsolutePath()+"/notes.json");
        if(file.exists()){
            Reader reader = new FileReader(file);
            Note[] n = gson.fromJson(reader, Note[].class);
            noteList = new ArrayList<>(Arrays.asList(n));
            System.out.println("Notes loaded.");

        }

    }

}


