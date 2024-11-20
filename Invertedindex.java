import java.io.File;
import java.util.Scanner;

public class Invertedindex {
Index index;    
public LinkedList<Word> words;

public Invertedindex(Index index) {
        words=new LinkedList<Word>();
        this.index=index;
        invert_index();
    }

public void display(){
    words.findFirst();
    while(!words.last()){
        System.out.print(words.retrieve().word+" [");
        words.retrieve().print_indexs();
        System.out.println("]");
        words.findNext();
    }
    System.out.print(words.retrieve().word+" [");
    words.retrieve().print_indexs();
    System.out.println("]");
    words.findNext();
}

public Word search_for_word(String str){
    if(!words.empty()){
    words.findFirst();
    while(!words.last()){
        if(str.equals(words.retrieve().word))
            return words.retrieve();
             words.findNext();
    } 
    if(str.equals(words.retrieve().word))
        return words.retrieve();
    }
    return null;
}


public void invert_index(){ // this method will convert every word in the class document that is saved in the list of documents to word class and add the index in the list and add the word object in the word list
    index.documents.findFirst();
    LinkedList<String> tmp=null;
   while(!index.documents.last()){
     tmp= index.documents.retrieve().words;
        tmp.findFirst();
        while(!tmp.last()){
            Word w=findWord(tmp.retrieve());
            if(w==null){
                w=new Word(tmp.retrieve());
                words.insert(w);
            }
            w.add_index(index.documents.retrieve().id);
            
     
            tmp.findNext();
        }
        Word w=findWord(tmp.retrieve());
        if(w==null){
            w=new Word(tmp.retrieve());
            words.insert(w);
        }
            w.add_index(index.documents.retrieve().id); 
           
          
            index.documents.findNext();
        }
        tmp= index.documents.retrieve().words;
        tmp.findFirst();
        while(!tmp.last()){
            Word w=findWord(tmp.retrieve());
            if(w==null){
                w=new Word(tmp.retrieve());
                words.insert(w);
            }
            w.add_index(index.documents.retrieve().id);
            tmp.findNext();
        }
        Word w=findWord(tmp.retrieve());
        if(w==null){
            w=new Word(tmp.retrieve());
            words.insert(w);
        }
            w.add_index(index.documents.retrieve().id); 
            
    
            index.documents.findNext();
    }
public Word findWord(String word){ // if word is exist in list words then will return the same object if not a new object will be created
    if(!words.empty()){
        words.findFirst();
        while(!words.last()){
             if(words.retrieve().word.equals(word))
                return words.retrieve();
             words.findNext();
        } 
        if(words.retrieve().word.equals(word))
             return words.retrieve();
    }
    return null;  
    }
public int freq(int DocID, String word){
    if(index.documents.empty()) return 0;
    index.documents.findFirst();
    for(int i=1;i<DocID;i++)
        index.documents.findNext();
return index.documents.retrieve().count_apprence(word);
}    

}



