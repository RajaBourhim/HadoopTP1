import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Main {

    public static List<String> readFile(File file) throws IOException {
        List<String> result = new ArrayList<String>();
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        for (String line = br.readLine(); line != null; line = br.readLine()) {
            result.add(line);
        }
        br.close();
        fr.close();
        return result;
    }

    public static File getFile() {
        final String completeFileName = "C:/Users/Raja/IdeaProjects/HadoopTP1/src/ressources/classeurmapreduce.csv";
        File file = new File(completeFileName);
        return file;
    }

    public static class Map {
        int key;
        String value;

        public Map(int key, String value) {
            this.key = key;
            this.value = value;
        }

        public int getKey() {
            return key;
        }

        public void setKey(int key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    public static List<Map> mapper (List<String> listFile){
        int i;
        List<Map> values = new ArrayList();

        Iterator it=listFile.iterator();
        while(it.hasNext()) {
            String str = (String)it.next();
            System.out.println(str);
            String[] line = str.split(";");
            for (i = 0; i < line.length; i++) {
                Map value = new Map(i + 1, line[i]);
                values.add(value);
            }
        }
        return  values;
    }

    public static List<String> reduce(List<Map> values){
        int i;
        List<String> list = new ArrayList<String>();
        Iterator it=values.iterator();
        while(it.hasNext()) {
            Map mapResult = (Map)it.next();
            int keyMap = mapResult.getKey()-1;
            String valueMap = mapResult.getValue();
            String data = "";
            //System.out.println(mapResult.getValue());
            try {
                data = list.get(keyMap) +";"+valueMap;
                list.set(keyMap,data);
            }catch ( Exception e){
                data = valueMap;
                list.add(keyMap,data);
            }
        }
        return list;
    }

    public static void main(String[] args) throws IOException {
        System.out.println("Hello World!");
        File file = getFile();
        List<String> listFile = readFile(file);
        List<String> listResult = new ArrayList<>();

        List<Map> values = mapper(listFile);
        listResult = reduce(values);

        Iterator ita=listResult.iterator();
        while(ita.hasNext()) {
           String str = (String)ita.next();
           System.out.println(str);
        }

    }
}


