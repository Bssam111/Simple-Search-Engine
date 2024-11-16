public class Document {
public int id;
public LinkedList<String> words;

public Document(int id) {
    words=new LinkedList<String>();
    this.id = id;
}
public void display_words(){
    words.findFirst();
    while(!words.last()){
        System.out.print(words.retrieve()+" ");
        words.findNext();
    }
    System.out.print(words.retrieve()+" ");
    System.err.println();
}
}
