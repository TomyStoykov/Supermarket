import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class HashMapDB {
    public static final String USER_STORAGE_FILENAME = "UsersHashMap.txt";
    public static HashMap<String, String> userEmailMap;


    public static void writeUserEmailMapToFile(HashMap<String, String> userEmailMap) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(USER_STORAGE_FILENAME, true));
            for (Map.Entry<String, String> entry : userEmailMap.entrySet()) {
                writer.write(entry.getKey() + "," + entry.getValue() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static HashMap<String, String> readUserEmailMapFromFile() {
        userEmailMap = new HashMap<>();
            try {
                BufferedReader reader = new BufferedReader(new FileReader(USER_STORAGE_FILENAME));
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    userEmailMap.put(parts[0], parts[1]);
                    }
                reader.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        return userEmailMap;
    }
}
