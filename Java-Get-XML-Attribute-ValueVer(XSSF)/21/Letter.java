public abstract class Letter implements Comparable{
    
        String title,receiver,content,sender,date;
        Letter(String title, String receiver, String content, String sender, String date){
            this.title=title;
            this.receiver=receiver;
            this.content=content;
            this.sender=sender;
            this.date=date;
        }
        String getSender(){
            return sender;
        }
        String getReceiver(){
            return receiver;
        }
        String getDate(){
            return date;
        }

        abstract void display();
        @Override
        public int compareTo(Object o) {
        Letter obj = (Letter) o;
        if(Integer.parseInt(this.getDate()) < Integer.parseInt(obj.getDate()) ){return 1;} //代表this物件比　o 物件大
        else if(Integer.parseInt(this.getDate()) > Integer.parseInt(obj.getDate())){return -1;} //代表this物件比　o 物件小
        else
        {
            return 0;   
        }//代表this物件跟　o 物件一樣大
    }
}

