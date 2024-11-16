public class QueryProcessing {
 Invertedindex invertedindex;
/* if you wnat test method and see how they work you can use the main in the bottom of the class and test what you want*/
 public QueryProcessing(Invertedindex i) { 
    invertedindex = i;
 }
 public void Query(String str){// here we split the str with 'OR' and then using AND, like if we have str="world AND color OR white"  we add world AND color to the linked list of linked list of string so we will have this ndoes (worldANDcolor)-->(white) we will do 'AND' operation in the first like if we have world in documets={1,2,5} and color in documets={1,4,5} that will equal in AND operation the intersection beetwen them which is {1,5} and then the AND operation for another nodes which is white is the same since we havent AND with it so let say white is {1,3} now we store [{1,5},{1,3}] in linked list of linked list of intgears and then do OR operstion beetwen them so OR in {1,3} , {1,5} is equal to {1,3,5} so these document that we will print. 
    str=str.replace(" ",""); // remove the space so if we have 'world AND color OR white' will be 'worldANDcolorORwhite'
    String splitingWords[]=str.split("OR");// here splitng by OR so if we have worldANDcolorORwhite we will get an array of string thats include splitingWords=[worldANDcolor,white]
    LinkedList<LinkedList<String>> andLinkedList=new LinkedList<LinkedList<String>>();// here we will initiate andLinkedList that we will store the words in thr array above
    LinkedList<String> tmp=null;

    LinkedList<LinkedList<Integer>> orLinkedList=new LinkedList<LinkedList<Integer>>();// here will initiate orLinkedList to store the LinkedList of int that we will do AND for them
    for(String s1:splitingWords){ // just store in the linked list of linked list of int
            tmp=new LinkedList<String>();
            tmp.insert(s1);
            andLinkedList.insert(tmp);
        }
        andLinkedList.findFirst();

            while(!andLinkedList.last()){ //here we do AND operstion to words in andLinkedList
                String[] splitANDs=andLinkedList.retrieve().retrieve().split("AND");
                orLinkedList.insert(ANDQuery(splitANDs));// and then store them in orLinekdList sind the method  ANDQuery return LinkedList of int
                andLinkedList.findNext();
            }
            String[] splitANDs=andLinkedList.retrieve().retrieve().split("AND");
                orLinkedList.insert(ANDQuery(splitANDs));
            LinkedList<Integer> orquery=ORQuery(orLinkedList);
        if(orquery==null) return;
        if(orquery.empty()) {System.out.println("There is no intersection in the documents you provide.");
        return;
        }
        orquery.findFirst();
        while(!orquery.last()){
            invertedindex.index.print_document(orquery.retrieve());
            orquery.findNext();
        }
        invertedindex.index.print_document(orquery.retrieve());
 }
 private String[] splitANDs(String s){
    return s.split("AND");
 }
private LinkedList<Integer> ANDQuery(String[] words){
    if(!isExist(words))// check if the word not exist print message and retuen false
        return null;

    LinkedList<Integer> x=new LinkedList<Integer>();
    x=invertedindex.search_for_word(words[0]).indexs;//here we will search on word in the invertedinex list and return it and get index by using .indexs, so if we have like indexs={1,3,5}
    LinkedList<Integer> y=null;
    for(int i=1;i<words.length;i++){// start 1 sincce we get 0 above
     y=invertedindex.search_for_word(words[i]).indexs;// here let say x={1,3,5} and y= {1,4,5} we will get {1,5}
      x=find_intersection(x,y);//here we will chane x value to the interstion since if have more than two wrod we can handle it
    }
    return x;
        
}
private static LinkedList<Integer> ORQuery(LinkedList<LinkedList<Integer>> indexs){// ORQuery is simply merge the list
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
        if(invertedindex.search_for_word(s)==null)
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
     x.findFirst();               
     while(!x.last()){
        y.findFirst();
        while(!y.last()){
             if(x.retrieve().equals(y.retrieve()))
                   intersection.insert(x.retrieve());
            y.findNext();
         }
         if(x.retrieve().equals(y.retrieve()))
            intersection.insert(x.retrieve());
         x.findNext();
     }
     y.findFirst();
    while(!y.last()){
        if(x.retrieve().equals(y.retrieve()))
               intersection.insert(x.retrieve());
         y.findNext();
     }
     if(x.retrieve().equals(y.retrieve()))
          intersection.insert(x.retrieve());
     x=intersection;
    
 return intersection;
}
private static LinkedList<Integer> merge(LinkedList<Integer> x, LinkedList<Integer> y){// simple method just merger to set like if we have {1,5,6,8} and {5,9,4,1} we get {1,5,6,8,9,4} ( merge without duplication )
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
private static boolean inList(LinkedList<Integer> l,Integer i){// return true if the element in the linked list otherwise return false
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
private static void display(LinkedList<Integer> l){
    l.findFirst();
    while (!l.last()) {
        System.out.print(l.retrieve()+",");
        l.findNext();
    }
    System.out.print(l.retrieve());
}
public static void main(String[] args){
  
    LinkedList<Integer> l1=new LinkedList<Integer>();
    LinkedList<Integer> l2=new LinkedList<Integer>();
    LinkedList<Integer> l4=new LinkedList<Integer>();
    LinkedList<LinkedList<Integer>> l3=new LinkedList<LinkedList<Integer>>();
    l1.insert(3);l1.insert(5);l1.insert(2);l1.insert(1);
    l2.insert(4);l2.insert(5);l2.insert(6);l2.insert(7);
    l4.insert(9);
    l3.insert(l1);l3.insert(l2);l3.insert(l4);
    //LinkedList<Integer> l3= merge(l1,l2);
    
   display(ORQuery(l3));
    
   }
   
}
