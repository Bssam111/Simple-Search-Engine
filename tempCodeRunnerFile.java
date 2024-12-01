  else if(this.score==tmp.score&&this.docID<tmp.docID){
                    list.update(this);
                    list.insert(tmp);
                    return;
            }