public class QueryProcessing {
private Index index;    
private Invertedindex invertedindex;
private InvertedindexBST invertedindexBST;
/* if you wnat test method and see how they work you can use the main in the bottom of the class and test what you want*/
 public QueryProcessing(Index index,Invertedindex invertedindex,InvertedindexBST invertedindexBST) {
     this.index=index;
     this.invertedindex = invertedindex;
    this.invertedindexBST=invertedindexBST;
 }
 public void IndexQuery(String str){//"color AND green AND white
    str=str.replace(" ","");
    String splitingWords[]=str.split("OR");//[market], [sports]
    LinkedList<LinkedList<Integer>> orLinkedList=new LinkedList<LinkedList<Integer>>();
    LinkedList<LinkedList<Integer>> indexs=new LinkedList<LinkedList<Integer>>();
    for(String andString:splitingWords){ // just store in the linked list of linked list of int
        String[] splitANDs=andString.split("AND");
        for(String word:splitANDs)
        indexs.insert(index.findIndexes(word));
        orLinkedList.insert(Index_ANDQuery(indexs));
        
    }
   
        Query(str,orLinkedList);
 }
 public void invertedIndexQuery(String str){
    str=str.replace(" ","");
    String splitingWords[]=str.split("OR");
    LinkedList<LinkedList<Integer>> orLinkedList=new LinkedList<LinkedList<Integer>>();
    for(String andString:splitingWords){ // just store in the linked list of linked list of int
        String[] splitANDs=andString.split("AND");
        orLinkedList.insert(invertedIndex_ANDQuery(splitANDs));
    }
        Query(str,orLinkedList);
 }

 public void BSTQuery(String str){
    str=str.replace(" ","");
    String splitingWords[]=str.split("OR");
    LinkedList<LinkedList<Integer>> orLinkedList=new LinkedList<LinkedList<Integer>>();
    for(String andString:splitingWords){ // just store in the linked list of linked list of int
        String[] splitANDs=andString.split("AND");
        orLinkedList.insert(BST_ANDQuery(splitANDs));
    }
        Query(str,orLinkedList);
 }
 
 private void Query(String str,LinkedList<LinkedList<Integer>> orLinkedList){// here we split the str with 'OR' and then using AND, like if we have str="world AND color OR white"  we add world AND color to the linked list of linked list of string so we will have this ndoes (worldANDcolor)-->(white) we will do 'AND' operation in the first like if we have world in documets={1,2,5} and color in documets={1,4,5} that will equal in AND operation the intersection beetwen them which is {1,5} and then the AND operation for another nodes which is white is the same since we havent AND with it so let say white is {1,3} now we store [{1,5},{1,3}] in linked list of linked list of intgears and then do OR operstion beetwen them so OR in {1,3} , {1,5} is equal to {1,3,5} so these document that we will print. 
        LinkedList<Integer> orquery=ORQuery(orLinkedList);
        if(orquery==null) 
            return;
        if(orquery.empty()) {
            System.out.println("There is no intersection in the documents you provide.");
            return;
        }
        displyAllDoc(orquery);
 }
 private LinkedList<Integer> Index_ANDQuery(LinkedList<LinkedList<Integer>> indexs){
    // if(!isExist(words))// check if the word not exist print message and retuen false
    //     return null;                                     c
    if(indexs==null&&indexs.empty()) // 2 4 5
        return null; 
    indexs.findFirst();             // l1= 2 4 5, l2=5 4 2
    if(indexs.last()) 
        return indexs.retrieve();
    LinkedList<Integer> l1=new LinkedList<Integer>();
    l1=indexs.retrieve();
    LinkedList<Integer> l2=null;
    indexs.findNext();
    while(!indexs.last()){
     l2=indexs.retrieve();
      l1=find_intersection(l1,l2);
      indexs.findNext();
    }
    l2=indexs.retrieve();
    l1=find_intersection(l1,l2);
    return l1;
}
private LinkedList<Integer> invertedIndex_ANDQuery(String[] words){
    if(!isExist(words))// check if the word not exist print message and retuen false
        return null;
    LinkedList<Integer> l1=new LinkedList<Integer>();
    l1=invertedindex.findWord(words[0]).indexs;//here we will search on word in the invertedinex list and return it and get index by using .indexs, so if we have like indexs={1,3,5}
    LinkedList<Integer> l2=null;
    for(int i=1;i<words.length;i++){// start 1 sincce we get 0 above
     l2=invertedindex.findWord(words[i]).indexs;// here let say l1={1,3,5} and l2= {1,4,5} we will get {1,5}
      l1=find_intersection(l1,l2);//here we will chane x value to the interstion since if have more than two wrod we can handle it
    }
    return l1;
}
private LinkedList<Integer> BST_ANDQuery(String[] words){
    if(!isExist(words))// check if the word not exist print message and retuen false
        return null;

    LinkedList<Integer> l1=new LinkedList<Integer>();
    l1=(LinkedList<Integer>)(invertedindexBST.bst.findWord(words[0]).data);//here we will search on word in the invertedinex list and return it and get index by using .indexs, so if we have like indexs={1,3,5}
    LinkedList<Integer> l2=null;
    for(int i=1;i<words.length;i++){// start 1 sincce we get 0 above
     l2=(LinkedList<Integer>)(invertedindexBST.bst.findWord(words[i]).data);// here let say l1={1,3,5} and l2= {1,4,5} we will get {1,5}
      l1=find_intersection(l1,l2);//here we will chane x value to the interstion since if have more than two wrod we can handle it
    }
    return l1;
        
}
private LinkedList<Integer> ORQuery(LinkedList<LinkedList<Integer>> indexs){// ORQuery is simply merge the list
    indexs.findFirst(); // this while to return null if we have null linked list of int in indexs
    while(!indexs.last()){
    if(indexs.retrieve()==null)
        return null;
    indexs.findNext();    
   }
   if(indexs.retrieve()==null)
        return null;

    LinkedList<Integer> x=new LinkedList<Integer>();// it like the intersecton we do same procecss
    indexs.findFirst();
    x=indexs.retrieve();
    LinkedList<Integer> y=null;
    while(!indexs.last()){
      y=indexs.retrieve();
      x=merge(x,y);
      indexs.findNext();
    }
      y=indexs.retrieve();
      x=merge(x,y);
    return x;

}
private boolean isExist(String[] words){//this method is simply search for words in the array of words if all words in the array are exist in the document the method will return true otherwise will print a message contaain the not found word or words and then return false
    if(words.length<=0){
        System.out.println("using Query Operation without words is not acceptable."); 
        return false;
    }
    LinkedList<String> notFound=new LinkedList<String>();
    for(String s:words)
        if(invertedindex.findWord(s)==null)
            notFound.insert(s);
    if(!notFound.empty()){
        notFound.findFirst();
        while(!notFound.last()){
            System.out.print("'"+notFound.retrieve()+"'"+" AND ");
            notFound.findNext();
        }
        System.out.print("'"+notFound.retrieve()+"'");
        notFound.findFirst();
        if(notFound.last()) System.out.println(" is not found in the documents.");
        else System.out.println(" are not found in the documents.");
        return false;
    }
    return true;

} 
private LinkedList<Integer> find_intersection(LinkedList<Integer> x, LinkedList<Integer> y){// just find intersction like if we have {1,5,8} and {2,5,7} we will get {5}
    LinkedList<Integer> intersection=new LinkedList<Integer>();
     x.findFirst();       //x      //y       // intercectioin= 2,5,4
     while(!x.last()){// 2 5 4 // 1 5 4 2
        y.findFirst();//     c          c
        while(!y.last()){
             if(x.retrieve().equals(y.retrieve())){
                   intersection.insert(x.retrieve());
                   y.findNext();
                   break;
             }
            y.findNext();
         }
         if(x.retrieve().equals(y.retrieve()))
            intersection.insert(x.retrieve());
         x.findNext();
     }
     y.findFirst();
    while(!y.last()){
        if(x.retrieve().equals(y.retrieve())){
            intersection.insert(x.retrieve());
            y.findNext();
            break;
      }
         y.findNext();
     }
     if(x.retrieve().equals(y.retrieve()))
          intersection.insert(x.retrieve());
    return intersection;
}
private  LinkedList<Integer> merge(LinkedList<Integer> x, LinkedList<Integer> y){// simple method just merger to set like if we have {1,5,6,8} and {5,9,4,1} we get {1,5,6,8,9,4} ( merge without duplication )
    LinkedList<Integer> merge=new LinkedList<Integer>();
    x.findFirst();
    while(!x.empty()&&!x.last()){
        merge.insert(x.retrieve());
        x.findNext();
    }
    merge.insert(x.retrieve());
    if(merge.empty()) return y;
    y.findFirst();
  while(!y.empty()&&!y.last()){
    if(!inList(merge, y.retrieve()))
    merge.insert(y.retrieve());
    y.findNext();
  }
  if(!inList(merge, y.retrieve()))
  merge.insert(y.retrieve());
return merge;
}
private  boolean inList(LinkedList<Integer> l,Integer i){// return true if the element in the linked list otherwise return false
    if(l!=null&&!l.empty()){ 
        l.findFirst();
        while(!l.last()){
            if(l.retrieve().equals(i))
                return true;
            l.findNext();
        }
        if(l.retrieve().equals(i))
            return true;
    }    
    return false;    
}
private void displyAllDoc(LinkedList<Integer> orquery) {
    orquery.findFirst();
    while(!orquery.last()){
        invertedindex.index.print_document(orquery.retrieve());
        orquery.findNext();
    }
    invertedindex.index.print_document(orquery.retrieve());
}
private void display(LinkedList<Integer> l){
    l.findFirst();
    while (!l.last()) {
        System.out.print(l.retrieve()+",");
        l.findNext();
    }
    System.out.print(l.retrieve());
}
public static void main(String[] args) {
    QueryProcessing q=new QueryProcessing(null, null, null);
    LinkedList<Integer> l1=new LinkedList<Integer>();
    LinkedList<Integer> l2=new LinkedList<Integer>();
    LinkedList<Integer> l3=new LinkedList<Integer>();
    LinkedList<LinkedList<Integer>> l=new LinkedList<LinkedList<Integer>>();
    l1.insert(2);l1.insert(4);l1.insert(5);
    l2.insert(5);l2.insert(4);l2.insert(2);
    l3.insert(1);l3.insert(5);l3.insert(4);l3.insert(2);
    l.insert(l1); l.insert(l2); l.insert(l3);
    LinkedList<Integer> ll =q.Index_ANDQuery(l);

    ll.findFirst();
    while (!ll.last()) {
        System.out.print(ll.retrieve()+" ");
        ll.findNext();
    }
    System.out.println(ll.retrieve());
}
}
