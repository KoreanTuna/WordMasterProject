import java.io.*;
import java.util.StringTokenizer;

public class FileBufferedReader {
    WordCRUD wordCRUD;
    BufferedReader br;
    FileBufferedReader(){};
    FileBufferedReader(WordCRUD wordCRUD){
        this.wordCRUD = wordCRUD;
    };
    String fileName = "Dictionary.txt";
    public void loadFile(){
        {
            try {


                br = new BufferedReader(new FileReader(fileName));
                String oneline;
                int number = 0;
                while((oneline = br.readLine()) != null){
                    StringTokenizer st = new StringTokenizer(oneline, "|");
                    int level = Integer.parseInt(st.nextToken());
                    String word = st.nextToken();
                    String meaning = st.nextToken();
                    Word newWord =new Word(0, level, word, meaning);
                    wordCRUD.list.add(newWord);
                    number++;
                }
                br.close();
                System.out.println(number+ "개의 데이터 로딩 완료!!!");
            } catch (FileNotFoundException e) {
                System.err.println(e);
            } catch (IOException e) {
                System.err.println(e);

            }
        }
    }

}
