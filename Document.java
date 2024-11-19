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
public int count_apprence(String str){
    int count=0;
    if(!words.empty()){
        str=str.trim().toLowerCase();
    words.findFirst();
    while(!words.last()){
        if(words.retrieve().equals(str))
            count++;
       words.findNext();     
    }
    if(words.retrieve().equals(str))
    count++;
    }

    return count;
}
}
