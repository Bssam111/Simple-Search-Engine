import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Index {
public LinkedList<Document> documents;
LinkedList<String> countVocb;
int tokens;
int vocab;
String pathname;
public Index(String pathname) {
    this.pathname=pathname;
    documents=new LinkedList<Document>();
   countVocb=new LinkedList<String>();
    load_document();
}
public void print_document(int numOfdoc){
    File f=new File(pathname);
        try{
            Scanner in=new Scanner(f);
            System.out.print("Document "+numOfdoc+": ");
            String line=in.nextLine();
            for(int i=0;i<=numOfdoc;i++)//notice that if you want test dataset2.csv make i=1;
                line=in.nextLine();
            String content=line.substring(line.indexOf(',')+2,line.lastIndexOf('.'));
            System.out.println(content);    
        }catch(Exception e){
                e.printStackTrace();
            }
}
public void print_all_document(){
     if(documents.empty()) return;
     documents.findFirst();
     while(!documents.last()){
        documents.retrieve().display_words();
        documents.findNext();
     }
     documents.retrieve().display_words();

}
public boolean isExist(String str){
    if(!documents.empty()){
        documents.findFirst();
        while (!documents.last()) {
            documents.retrieve().words.findFirst();
            while (!documents.retrieve().words.last()) {
                if(documents.retrieve().words.retrieve().equals(str))
                    return true;
                  documents.retrieve().words.findNext();  
            }
            if(documents.retrieve().words.retrieve().equals(str))
                    return true;
            documents.findNext();
        }
        documents.retrieve().words.findFirst();
        while (!documents.retrieve().words.last()) {
            if(documents.retrieve().words.retrieve().equals(str))
                return true;
              documents.retrieve().words.findNext();  
        }
        if(documents.retrieve().words.retrieve().equals(str))
                return true;
    }
    return false;
}

 private void load_document(){ 
        File f=new File(pathname);
        try{
            Scanner in=new Scanner(f);
            String line=in.nextLine();
            while(in.hasNextLine()){
                    line=in.nextLine().trim();
                    if(line.length()<3){
                        break;
                    }
                    int id=Integer.parseInt(line.substring(0,line.indexOf(',')).trim());// find the number of document
                     Document document=new Document(id);
                     String content=line.toLowerCase();
                    //content=content.replace(".", "");//to separate word like record-brekaing --> record brekaing
                    content=content.replaceAll("[!-9]", ""); // delete the punctuation and numbers from ! to @ in ASCII
                   //content=content.replaceAll("[0-9]", "");
                    String[] arrContent=content.split(" ");// sparate all words in array of content for ex content= "hello world", arrContent=[hello,world]
                    tokens+=arrContent.length;
                  for(String s:arrContent){ // in document we have list of words so we want to add all words we get from the array above to this list
                    document.words.insert(s);
                    if(!ExistinLinkedList(countVocb,s)){
                        countVocb.insert(s);
                        vocab++;
                    }
                  }
                   delete_stop_words(document);// to delete the words that in File stop.txt like: this, is, the, etc...
                   documents.insert(document);// add the document to the linke list of document after delete stop words
                }
                
            }catch(Exception e){
            e.printStackTrace();
        }
        
    }
public <T> boolean ExistinLinkedList(LinkedList<T> ll,T data){
if(!ll.empty()){
    ll.findFirst();
    while(!ll.last()){
        if(ll.retrieve().equals(data))
            return true;
    ll.findNext();      
    }
    if(ll.retrieve().equals(data))
        return true;
}
return false;
}
private void delete_stop_words(Document d) throws FileNotFoundException { 
    File f=new File("stop.txt");  
    Scanner StopWordsScanner=new Scanner(f);
  
    while(StopWordsScanner.hasNext()){
      String stop=StopWordsScanner.next().trim();
      d.words.findFirst();
      while(!d.words.last()){
          if(stop.equals(d.words.retrieve()))
              d.words.remove();
              if(d.words.last()) break;
              d.words.findNext();
      }
      if(stop.equals(d.words.retrieve()))
      d.words.remove();
    }
}
public LinkedList<Integer> findIndexes(String str){
    LinkedList<Integer> indexs=new LinkedList<Integer>();
    if(!documents.empty()){
        documents.findFirst();
        while (!documents.last()) {
            documents.retrieve().words.findFirst();
            while(!documents.retrieve().words.last()){
                if(str.equals(documents.retrieve().words.retrieve())){
                    if(!ExistinLinkedList(indexs,documents.retrieve().id))
                        indexs.insert(documents.retrieve().id);
                        break;
                }
                documents.retrieve().words.findNext();
            }
            if(str.equals(documents.retrieve().words.retrieve()))
                if(!ExistinLinkedList(indexs,documents.retrieve().id))
                indexs.insert(documents.retrieve().id);
            documents.findNext();
        }
        documents.retrieve().words.findFirst();
        while(!documents.retrieve().words.last()){
            if(str.equals(documents.retrieve().words.retrieve())){
                if(!ExistinLinkedList(indexs,documents.retrieve().id))
                    indexs.insert(documents.retrieve().id);
                    break;
            }
            documents.retrieve().words.findNext();
        }
        if(str.equals(documents.retrieve().words.retrieve()))
            if(!ExistinLinkedList(indexs,documents.retrieve().id))
              indexs.insert(documents.retrieve().id);
    }
    return indexs;

}    
}
