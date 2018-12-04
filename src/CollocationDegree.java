import java.util.*;

public class CollocationDegree {

    public static int getCollocationDegreeByGenes(List<Server> genes,List<Task> tasks) {
        //TODO:
        Map<Integer,Double> map = new HashMap<>();
        for(int i = 0;i<genes.size();i++) {
            Task task = tasks.get(i);
            Server server = genes.get(i);
            int usingTime = task.getUsingTime();
            int speed = server.getExecutingSpeedList().get(i);
            //TODO:
            double time  =new Double(usingTime)/new Double(speed);
            if (map.get(server.getId()) == null) {
                map.put(server.getId(), time);
                continue;
            }
            map.put(server.getId(), map.get(server.getId()) + time);
        }
        double longestTime = 0;
        for(double i: map.values()){
            if(i > longestTime) longestTime = i;
        }
        return getCollocationDegreeByTime(longestTime);

    }





    private static int getCollocationDegreeByTime(double time){
        return new Double(1/time * 10000).intValue();
    }

    public static List<Chromosome> fitness(List<Chromosome> list){
        //TODO: select parent chromosome
        List<Chromosome> result = new ArrayList<>();
        double collocationDegreeSum  = 0;
        double[] range = new double[list.size()];
        for(int i = 0;i<list.size();i++){
            Chromosome chromosome = list.get(i);
            collocationDegreeSum = chromosome.getCollocationDegree()+ collocationDegreeSum;
            range[i] = collocationDegreeSum;
        }
        double r1 = collocationDegreeSum * Math.random();
        double r2 = collocationDegreeSum * Math.random();

        int index1 =getSelectedIndex(range,r1);
        int index2 =getSelectedIndex(range,r2) ;

        result.add(list.get(index1));
        result.add(list.get(index2));



        return result;
    }

    public   static int getSelectedIndex(double[]range,double r){
        int mid = range.length/2;
        while (mid > 0 ){
            if(range[mid]>r ){
                if(mid == 0) break;
                if(range[--mid] < r) {
                   return mid+1;
                }
            }else if(range[mid] <r){
                if(range[++mid]>r){
                    return mid;
                }
            }else if(range[mid] == r){
                return mid+1;
            }
        }
        return 0;
    }

}
