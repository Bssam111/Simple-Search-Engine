import java.io.File;
import java.util.Scanner;

public class Invertedindex {
Index index;    
public LinkedList<Word> words;

public Invertedindex(Index index) {
        words=new LinkedList<Word>();
        this.index=index;
        invertedindex();
    }
    public void displayword(int index){
        words.findFirst();
    for(int i=0;i<index;i++)
        words.findNext();
    System.out.println(words.retrieve().word);
    words.retrieve().showIndex();
    }

public void SearchForWord(String str){
    words.findFirst();
    while(!words.last()){
        if(str.equals(words.retrieve().word))
            words.retrieve().showIndex();
             words.findNext();
    } 
        if(str.equals(words.retrieve().word))
            words.retrieve().showIndex();
            
    }


public void invertedindex(){
    index.document.findFirst();
    LinkedList<String> tmp=null;
   while(!index.document.last()){
     tmp= index.document.retrieve().words;
        tmp.findFirst();
        while(!tmp.last()){
            Word w=FindOrCreateNewWord(tmp.retrieve());
            if(w==null){
                w=new Word(tmp.retrieve());
                words.insert(w);
            }
            w.index.insert(index.document.retrieve().id);
            
     
            tmp.findNext();
        }
        Word w=FindOrCreateNewWord(tmp.retrieve());
        if(w==null){
            w=new Word(tmp.retrieve());
            words.insert(w);
        }
            w.index.insert(index.document.retrieve().id); 
           
          
            index.document.findNext();
        }
        tmp= index.document.retrieve().words;
        tmp.findFirst();
        while(!tmp.last()){
            Word w=FindOrCreateNewWord(tmp.retrieve());
            if(w==null){
                w=new Word(tmp.retrieve());
                words.insert(w);
            }
            w.index.insert(index.document.retrieve().id);
           
            tmp.findNext();
        }
        Word w=FindOrCreateNewWord(tmp.retrieve());
        if(w==null){
            w=new Word(tmp.retrieve());
            words.insert(w);
        }
            w.index.insert(index.document.retrieve().id); 
            
    
            index.document.findNext();
    }
    public Word FindOrCreateNewWord(String word){
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

}



