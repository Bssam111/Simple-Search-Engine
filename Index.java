import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Index {
public LinkedList<Document> documents;

public Index(String pathname) {
    documents=new LinkedList<Document>();
    load_document(pathname);
}

 private void load_document(String pathname){ 
        File f=new File(pathname);
        try{
            Scanner in=new Scanner(f);
            String line=in.nextLine();
            while(in.hasNextLine()){
                    line=in.nextLine().trim();
                    if(line.length()<3){
                        break;
                    }
                    //System.out.println(line);
                    int id=Integer.parseInt(line.substring(0,line.indexOf(',')).trim());
                     Document document=new Document(id);
                    String content=line.substring(line.indexOf(',')+2,line.lastIndexOf('.')).trim().toLowerCase();
                    content=content.replace(".", "");content=content.replace(",", "");
                    String[] arrContent=content.split(" ");
                  for(String s:arrContent){
                    document.words.insert(s);
                  }
                   delete_stop_words(document);
                   documents.insert(document);
                }
                
            }catch(Exception e){
            e.printStackTrace();
        }
        
    }
public void delete_stop_words(Document d) throws FileNotFoundException { // to delete the words that in File stop.txt
    File f=new File("stop.txt");  // from here we
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
      if(stop==d.words.retrieve())
      d.words.remove();
    }
}    
}
