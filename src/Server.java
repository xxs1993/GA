import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Server {

    private int id;

    private List<Integer> executingSpeedList;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Integer> getExecutingSpeedList() {
        return executingSpeedList;
    }

    public void setExecutingSpeedList(List<Integer> executingSpeedList) {
        this.executingSpeedList = executingSpeedList;
    }


    public Server(int taskNum,int id){
        this.id = id;
        Random random = new Random();
        executingSpeedList = new ArrayList<>();
        for(int i = 0;i<taskNum;i++){
            executingSpeedList.add(random.nextInt(GAConfiguration.server_exceeding_speed_range)+1);
        }
    }
}
