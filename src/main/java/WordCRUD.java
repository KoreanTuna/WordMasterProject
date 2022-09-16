import java.util.ArrayList;
import java.util.Scanner;

public class WordCRUD implements ICRUD{
    ArrayList<Word> list;
    ArrayList<Word> searchList;
    Scanner s;

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

    @Override
    public void update() {
        searchList.clear();
        s.nextLine();
        System.out.print("수정하고 싶은 단어를 입력하세요 : ");
        String searchWord = s.nextLine();

        System.out.println("\n---------------------------");
        for(int i = 0; i< list.size(); i++) {
            if(list.get(i).getWord().contains(searchWord.trim())){
                searchList.add(list.get(i));
                System.out.print((i+1) + " ");
                System.out.println(list.get(i).toString());
            }
        }
        System.out.print("수정할 번호를 선택하세요 : ");
        int searchIndex = s.nextInt(); s.nextLine();
        System.out.print("새로운 뜻을 입력하세요 : ");
        String newMeanning = s.nextLine();
        for(int i = 0; i< list.size(); i++) {
            if(list.get(i).getWord().contains(searchList.get(searchIndex-1).getWord())){
                list.get(i).setMeaning(newMeanning);
            }
        }
        System.out.println("단어 수정이 완료되었습니다.");
    }

    @Override
    public void delete() {
    }

    @Override
    public void select(int id) {
        // TODO Auto-generated method stub

    }

}
