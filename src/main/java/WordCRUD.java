import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class WordCRUD implements ICRUD{
    ArrayList<Word> list;
    ArrayList<Word> searchList;
    Scanner s;
    String fileName ="Dictionary.txt";

    WordCRUD(Scanner s){
        list = new ArrayList<>();
        searchList = new ArrayList<>();
        this.s = s;
    }

    @Override
    public Object add() {
        System.out.print("난이도(1,2,3) : ");
        int level = s.nextInt();
        s.nextLine();
        System.out.print("새 단어 입력 : ");
        String word = s.nextLine();
        System.out.print("뜻 입력 : ");
        String meaning = s.nextLine();

        return new Word(0, level, word, meaning);
    }
    public void addWord() {
        Word one = (Word)add();
        list.add(one);
        System.out.println("새 단어가 단어장에 추가되었습니다.");
    }

    public void listAll() {
        System.out.println("---------------------------");
        for(int i = 0; i< list.size(); i++) {
            System.out.print((i+1) + " ");
            System.out.println(list.get(i).toString());
        }
        System.out.println("---------------------------");

    }

    public void listAll(int level) {
        int j= 0;
        System.out.println("---------------------------");
        for(int i = 0; i< list.size(); i++) {
            if(list.get(i).getLevel() == level){
                System.out.print((j+1) + " ");
                System.out.println(list.get(i).toString());
                j++;
            }

        }
        System.out.println("---------------------------");
    }
    public void listAll(String name) {
        int j= 0;
        System.out.println("---------------------------");
        for(int i = 0; i< list.size(); i++) {
            if(list.get(i).getWord().contains(name)){
                System.out.print((j+1) + " ");
                System.out.println(list.get(i).toString());
                j++;
            }

        }
        System.out.println("---------------------------");
    }

    @Override
    public void update() {
        searchList.clear();
        s.nextLine();
        System.out.print("수정하고 싶은 단어를 입력하세요 : ");
        String searchWord = s.nextLine();
        System.out.println("\n---------------------------");
        searchWord(searchWord);
        System.out.print("수정할 번호를 선택하세요 : ");
        int searchIndex = s.nextInt(); s.nextLine();
        System.out.print("새로운 뜻을 입력하세요 : ");
        String newMeanning = s.nextLine();
        for(int i = 0; i< list.size(); i++) {
            if(list.get(i).getWord().contains(searchList.get(searchIndex-1).getWord()) && list.get(i).getMeaning().contains(searchList.get(searchIndex-1).getMeaning())){
                list.get(i).setMeaning(newMeanning);
            }
        }
        System.out.println("단어 수정이 완료되었습니다.");
    }

    public void searchWord(String searchWord) {
        int j= 1;
        for(int i = 0; i< list.size(); i++) {
            if(list.get(i).getWord().contains(searchWord.trim())){
                searchList.add(list.get(i));
                System.out.print((j++) + " ");
                System.out.println(list.get(i).toString());
            }
        }
        System.out.println("---------------------------");
    }

    @Override
    public void delete() {
        searchList.clear();
        s.nextLine();
        System.out.print("삭제할 단어를 검색해주세요 : ");
        String deleteWord = s.nextLine();
        System.out.println("---------------------------");
        searchWord(deleteWord);
        System.out.print("삭제할 번호를 선택하세요 : ");
        int searchIndex = s.nextInt(); s.nextLine();
        System.out.print("정말로 삭제하실래요?(Y/n) : ");
        String confirm = s.nextLine();
        if(confirm.trim().equals("Y") || confirm.trim().equals("y")){
            for(int i = 0; i< list.size(); i++) {
                if(list.get(i).getWord().contains(searchList.get(searchIndex-1).getWord()) && list.get(i).getMeaning().contains(searchList.get(searchIndex-1).getMeaning())) {
                    list.remove(i);
                }
            }
            System.out.println("단어가 삭제되었습니다.");
        }
        else{
            System.out.println("단어 삭제가 취소되었습니다.");
        }

    }
    public void saveFile(){
        try {
            PrintWriter printWriter = new PrintWriter(new FileWriter(fileName));
            for(Word one : list){
                printWriter.write(one.toFileString()+ "\n");
            }
            printWriter.close();
            System.out.print("단어장이 저장되었습니다.\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void select(int id) {
        // TODO Auto-generated method stub

    }

    public void levelWordSearch() {
        System.out.print("찾고 싶은 레벨을 입력하세요 : ");
        int level = s.nextInt();
        listAll(level);
    }

    public void wordSearch() {
        s.nextLine();
        String name;
        System.out.print("검색할 단어를 입력해주세요 : ");
        name = s.nextLine();
        listAll(name);

    }
}
