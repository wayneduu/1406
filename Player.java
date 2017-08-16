import java.util.ArrayList;
import java.util.Stack;
import java.util.Random;

public abstract class Player{
 protected ArrayList<Card> hand;
 protected int points;
 public int getSizeOfHand(){
  return hand.size();
 }

 
 
 public void placeEight(String str, DiscardPile discardPile)
 {
     
     Card card=new Card(str,"8");
     discardPile.add(card);
     }

 public ArrayList<Integer> checkEight( ArrayList<Card> hand )
 {
   ArrayList<Integer> eight=new ArrayList<Integer>();
   for(int i=0;i<hand.size();i++)
   {
    if(this.hand.get(i).rank.equals("8"))  
     { 
      eight.add(i);
     }
   }
   return eight;
 }
 
 public Card takeCards(Stack<Card>       drawPile)
 {
    this.hand.add(drawPile.peek());
    drawPile.pop();
    return this.hand.get( this.hand.size() - 1);
 } 
 
 /* play a card  */
    public abstract boolean play(DiscardPile       discardPile,
                                 Stack<Card>       drawPile,
                                 ArrayList<Player> players,
                                 int player,
                                 boolean direction);
}

 // return true if player wins game by playing last card
 // returns false otherwise
 // side effects: plays a card to top of discard Pile, possibly taking zero
 //               or more cards from the top of the drawPile
 //               card played must be valid card
