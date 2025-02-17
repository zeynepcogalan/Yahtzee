
public class SingleLinkedList {
	Node head;

	 public void sortedAdd (Player dataToAdd) {
		 //to add empty SLL
		 if (head == null) {
			 Node newnode = new Node (dataToAdd);
			 head = newnode;
		 }
		 else {
			 Player data = (Player) head.getData();
			 //if new score bigger than first score, add new score to head
			 if ((int)dataToAdd.getScore() > (int)data.getScore()){
				 Node newnode = new Node(dataToAdd);
				 newnode.setLink(head);
				 head = newnode;
			 }
			 Node temp = head;
			 Node previous = null;
			 //to add new score between 2 scores
			 while (temp!= null && (Integer)dataToAdd.getScore() <= (Integer)data.getScore())
			 {
				 previous = temp;
				 temp = temp.getLink();
				 if(temp != null) data = (Player) temp.getData();	 
			 }
			 // listenin sonuna ekleme
			 if (temp == null)
			 {
				 Node newnode = new Node(dataToAdd);
				 previous.setLink(newnode);
			 }
			 // listenin ortasýna ekleme
			 else if(previous != null) { 
				 Node newnode = new Node (dataToAdd);
				 newnode.setLink(temp);
				 previous.setLink(newnode);
				 }
		 } 
	 }
	 
	 public void AddNumber (Object dataToAdd) {
		//to add empty SLL
		 if (head == null) {
			 Node newnode = new Node (dataToAdd);
			 head = newnode;
		 }
		 //to add other places
		 else {
			 Node temp = head;
			 while (temp.getLink() != null) {
				 temp = temp.getLink();
			 }
			 Node newnode = new Node(dataToAdd);
			 temp.setLink(newnode);
		 }
	 }
	 
	 public Boolean deleteLastPlayer(Player player) {
		 Boolean flag = false;
		 Node temp = head;
		 Node previous = null;
		 while (temp != null) {
			 //to find the player
			 if(temp.getLink() == null) {
				 Player lastPlayer = (Player)temp.getData(); 
				 //if last player's score smaller than new score, delete last player
				 if((int)lastPlayer.getScore() < (int)player.getScore()) {
					 previous.setLink(temp.getLink());
					 flag = true; 
				 }
			 }
			 previous = temp;
			 temp = temp.getLink();
		 }
		 return flag;
	 }
	 
	 public void deleteNumber (Object dataToDelete) {
		 if (head != null) {
			 //to delete first number
			 if((Integer)head.getData() == (Integer)dataToDelete) {
				 head = head.getLink();
			 }
			 //to delete other numbers
			 else {
				 Node temp = head;
				 Node previous = null;
				 while (temp != null)
				 {
					 if ((Integer)temp.getData() == (Integer)dataToDelete) {
						 previous.setLink(temp.getLink());
						 temp = previous;
						 break;
					 }
				 previous = temp;
				 temp = temp.getLink();
				 }
			 } 
		 }
	 }
	 
	 public void printListPlayer () {
		 //if list is empty
		 if (head == null)  System.out.print("-");
		 else {
			Node temp = head;
			while (temp != null) {
				Player data = (Player) temp.getData();
				 System.out.println(data.getName() + " " + data.getScore());
				 temp = temp.getLink ();
			 }
		 }
	 }
	 
	 public void printListNumber () {
		//if list is empty
		 if (head == null)  System.out.print("-");
		 else {
			Node temp = head;
			while (temp != null) {
				 System.out.print(temp.getData() + " ");
				 temp = temp.getLink ();
			 }
		 }
	 }	
}