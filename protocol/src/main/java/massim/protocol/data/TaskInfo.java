package massim.protocol.data;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashSet;
import java.util.Set;

public class TaskInfo {
    public String name;
    public int deadline;
    public int reward;
    public Set<Thing> requirements;

    public TaskInfo(String name, int deadline, int reward, Set<Thing> requirements) {
        this.name = name;
        this.deadline = deadline;
        this.requirements = requirements;
        this.reward = reward;
    }

    public JSONObject toJSON() {
        JSONObject task = new JSONObject();
        task.put("name", name);
        task.put("deadline", deadline);
        task.put("reward", reward);
        JSONArray jsonReqs = new JSONArray();
        for (Thing requirement : requirements) {
            jsonReqs.put(requirement.toJSON());
        }
        task.put("requirements", jsonReqs);
        return task;
    }

    public static TaskInfo fromJson(JSONObject jsonTask) {
        Set<Thing> requirements = new HashSet<>();
        JSONArray jsonRequirements = jsonTask.getJSONArray("requirements");
        for (int i = 0; i < jsonRequirements.length(); i++) {
            requirements.add(Thing.fromJson(jsonRequirements.getJSONObject(i)));
        }
        return new TaskInfo(jsonTask.getString("name"), jsonTask.getInt("deadline"),
                jsonTask.getInt("reward"), requirements);
    }
}
