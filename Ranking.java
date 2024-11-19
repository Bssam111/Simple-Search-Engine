public class Ranking{
Index index;
Invertedindex invertedindex;
InvertedindexBST invertedindexBST;
LinkedList <DocumentScore> scorelLinkedList;


public Ranking( Index index,Invertedindex invertedindex, InvertedindexBST invertedindexBST) {
    scorelLinkedList =new LinkedList<DocumentScore>();
    this.index = index;
    this.invertedindex = invertedindex;
    this.invertedindexBST = invertedindexBST;
}

public void indexQuery(String str){
    str=str.trim().toLowerCase();
    String words[]=str.split(" ");

    for(String word: words){
        int score=0;
        index.documents.findFirst();
        while(!index.documents.last()){
            score=index.documents.retrieve().count_apprence(word);
            if(score==0){
                index.documents.findNext();
                continue;
                }
            DocumentScore documentScore=find_or_createDcoumentScore(index.documents.retrieve().id);
                for(int i=0;i<score;i++)
                    documentScore.incress_score();
                 if(!is_inScorelLinkedList(index.documents.retrieve().id))
                    scorelLinkedList.insert(documentScore);         
            index.documents.findNext();
        }
        score=index.documents.retrieve().count_apprence(word);
        DocumentScore documentScore=find_or_createDcoumentScore(index.documents.retrieve().id);
            for(int i=0;i<score;i++)
                documentScore.incress_score();
            if(!is_inScorelLinkedList(index.documents.retrieve().id))  
                scorelLinkedList.insert(documentScore); 
    }
    display();  
    }
    public boolean is_inScorelLinkedList(int id){
        if(!scorelLinkedList.empty()){
            scorelLinkedList.findFirst();
            while (!scorelLinkedList.last()) {
                    if(scorelLinkedList.retrieve().docID==id)
                        return true;
                    scorelLinkedList.findNext();
            }
            if(scorelLinkedList.retrieve().docID==id)
                return true;
        }
        return false;
    }
    public DocumentScore find_or_createDcoumentScore(int id){
        if(!scorelLinkedList.empty()){
            scorelLinkedList.findFirst();
            while(!scorelLinkedList.last()){
                if(scorelLinkedList.retrieve().docID==id)
                    return scorelLinkedList.retrieve();
                scorelLinkedList.findNext();  
            }
            if(scorelLinkedList.retrieve().docID==id)
                    return scorelLinkedList.retrieve();     
        }
        return new DocumentScore(id); 
    }
public void display(){
    if(!scorelLinkedList.empty()){
        System.out.println("DocID    Score");
        scorelLinkedList.findFirst();
        while(!scorelLinkedList.last()){
            System.out.println(scorelLinkedList.retrieve().docID+"          "+scorelLinkedList.retrieve().score);
            scorelLinkedList.findNext();
        }
        System.out.println(scorelLinkedList.retrieve().docID+"          "+scorelLinkedList.retrieve().score);

    }
    else System.out.println("empty");
}       

}
