public class Word {
public String word;
public LinkedList<Integer> indexs;

public Word(String word) {
    indexs=new LinkedList<Integer>();
    this.word = word;
}
public void print_indexs(){
    indexs.findFirst();
    while(!indexs.last()){
        System.out.print(indexs.retrieve()+",");
        indexs.findNext();
    }
    System.out.print(indexs.retrieve());
}

public void add_index(Integer var){
    if(!is_exist(var))
    indexs.insert(var);
}
public boolean is_exist(Integer var){
    if(!indexs.empty()) {
    indexs.findFirst();
    while(!indexs.last()){
        if(indexs.retrieve().equals(var))
            return true;
            indexs.findNext();
    }
    if(indexs.retrieve().equals(var))
            return true;
    }
    return false;
}

public static void main(String []args){
Word w=new Word("Hello");
w.add_index(1);w.add_index(1);w.add_index(2);w.add_index(3);

w.print_indexs();

}
}
