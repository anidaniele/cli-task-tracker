import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataLoader {

    public Map<Integer, Task> loadData(){
        Map<Integer, Task> loadedTasks = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        try (BufferedReader bf = new BufferedReader(new FileReader("tasks.json"))) {
            String taskString;
            while ((taskString = bf.readLine()) != null && !taskString.isEmpty()) {
                sb.append(taskString.trim());
            }
            String json = sb.toString();
            if (json.startsWith("[")){
                json = json.substring(1);
            }
            if (json.endsWith("]")){
                json = json.substring(0, json.length()-1);
            }
            String[] taskJsons = json.split("(?<=\\}),\\s*(?=\\{)");

            for (String taskJson : taskJsons) {
                int id = (getIntValueFromJson(taskJson, "id"));
                String description = (getStringValueFromJson(taskJson, "description"));
                Status status = (Status.valueOf(getStringValueFromJson(taskJson, "status")));
                LocalDateTime createdAt = (LocalDateTime.parse(getStringValueFromJson(taskJson, "createdAt")));
                LocalDateTime updatedAt = (LocalDateTime.parse(getStringValueFromJson(taskJson, "updatedAt")));
                Task task = new Task(id, description, status, createdAt, updatedAt);
                loadedTasks.put(task.getId(), task);
            }
        }
        catch(IOException e){
                System.out.println("Currently no tasks are present");
        }
        return loadedTasks;
    }

    private String getStringValueFromJson(String json, String key) {
        Pattern pattern = Pattern.compile("\"" + key + "\"\\s*:\\s*\"(.*?)\"");
        Matcher matcher = pattern.matcher(json);
        if (matcher.find()) {
            return matcher.group(1); // group(1) gets the captured value instead of the whole match.
        }
        throw new IllegalArgumentException("Key not found: " + key);
    }

    private int getIntValueFromJson(String json, String key) {
        Pattern pattern = Pattern.compile("\"" + key + "\":\\s*(\\d+)");
        Matcher matcher = pattern.matcher(json);
        if (matcher.find()) {
            return Integer.parseInt(matcher.group(1));
        }
        throw new IllegalArgumentException("Key not found: " + key);
    }
}
