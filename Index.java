import java.io.File;
import java.util.Scanner;

public class Index {
public LinkedList<Document> document;

public Index(String pathname) {
    document=new LinkedList<Document>();
    LoadDocument(pathname);
    // Reading r=new Reading();
    // r.load("dataset.csv", document);
}

public void DisplayDocument(int docnum){
document.findFirst();
for(int i=0;i<docnum;i++)
document.findNext();
document.retrieve().displayWords();

}
public LinkedList<String> DocumentWord(int docnum){
    document.findFirst();
    for(int i=0;i<docnum;i++)
    document.findNext();
    return document.retrieve().words;
}



 private void LoadDocument(String pathname){
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
                     Document tmp=new Document(id);
                    String content=line.substring(line.indexOf(',')+2,line.lastIndexOf('.')).trim().toLowerCase();
                    content=content.replace(".", "");content=content.replace(",", "");
                    String[] arrContent=content.split(" ");
                  for(String s:arrContent){
                    tmp.words.insert(s);
                  }
                  File f1=new File("stop.txt");
                  Scanner StopWordsScanner=new Scanner(f1);
                
                  while(StopWordsScanner.hasNext()){
                    String stop=StopWordsScanner.next().trim();
                    tmp.words.findFirst();
                    while(!tmp.words.last()){
                        if(stop.equals(tmp.words.retrieve()))
                            tmp.words.remove();
                            if(tmp.words.last()) break;
                            tmp.words.findNext();
                    }
                    if(stop==tmp.words.retrieve())
                    tmp.words.remove();
                  }
                   document.insert(tmp);
                }
                
            }catch(Exception e){
            e.printStackTrace();
        }
        
    }
}
