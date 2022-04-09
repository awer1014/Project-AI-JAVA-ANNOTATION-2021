class EnglishLetter extends Letter { 
void display ( ) { 
System . out . println ( "Dear " + title + " " + receiver + "," ) ; 
System . out . println ( content ) ; 
System . out . println ( "Sincerely" ) ; 
System . out . println ( sender ) ; 
System . out . println ( date ) ; 
} 
EnglishLetter ( String title , String receiver , String content , String sender , String date ) { 
super ( title , receiver , content , sender , date ) ; 
} 
} 
