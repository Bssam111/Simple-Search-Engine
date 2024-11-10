public class Word {
String word;
LinkedList<Integer> index;

public Word(String word) {
    index=new LinkedList<Integer>();
    this.word = word;
}
public void showIndex(){
    index.findFirst();
    while(!index.last()){
        System.out.print(index.retrieve()+",");
        index.findNext();
    }
    System.out.print(index.retrieve()+",");
    System.out.println();
}
}
