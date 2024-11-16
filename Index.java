import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Index {
public LinkedList<Document> documents;
String pathname;
public Index(String pathname) {
    this.pathname=pathname;
    documents=new LinkedList<Document>();
    load_document();
}
public void print_document(int numOfdoc){
    File f=new File(pathname);
        try{
            Scanner in=new Scanner(f);
            System.out.print("Document "+numOfdoc+": ");
            String line=in.nextLine();
            for(int i=0;i<=numOfdoc;i++)
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
                     content=content.replace("-", " ");//to separate word like record-brekaing --> record brekaing
                    content=content.replaceAll("[!-@]", ""); // delete the punctuation and numbers from ! to @ in ASCII
                    String[] arrContent=content.split(" ");// sparate all words in array of content for ex content= "hello world", arrContent=[hello,world]
                  for(String s:arrContent){ // in document we have list of words so we want to add all words we get from the array above to this list
                    document.words.insert(s);
                  }
                   delete_stop_words(document);// to delete the words that in File stop.txt like: this, is, the, etc...
                   documents.insert(document);// add the document to the linke list of document after delete stop words
                }
                
            }catch(Exception e){
            e.printStackTrace();
        }
        
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
}
