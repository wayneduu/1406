/**
 * Auto Generated Java Class.
 */
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Stack;
import java.util.Random;
  public class ExtraCards extends Player{

 public ExtraCards(Card[] cards){this.hand = new ArrayList<Card>(Arrays.asList(cards));}
 
 /**this method is used to transform all the power cards from the hand, and deposit them in a two-dimensional arraylist
   * and return the arraylist**/
 public ArrayList<ArrayList<Integer>> checkPowerCards(ArrayList<Card> hand)
 { int i=0;
   ArrayList<ArrayList<Integer>> powerCards=new ArrayList<ArrayList<Integer>>(); /**the return arraylist**/
   ArrayList<Integer> cardTwo=new ArrayList<Integer>();                          /**array list for card two**/
   ArrayList<Integer> cardFour=new ArrayList<Integer>();                         /**array list for card four**/
   ArrayList<Integer> cardSeven=new ArrayList<Integer>();                        /**array list for card seven**/
   
   for(i=0;i<hand.size();i++){
     if(hand.get(i).rank.equals(Card.RANKS[2]))
     {cardTwo.add(i);}
     if(hand.get(i).rank.equals(Card.RANKS[4]))
     {cardFour.add(i);}
     if(hand.get(i).rank.equals(Card.RANKS[7]))
     {cardSeven.add(i);}

 }powerCards.add(cardTwo);                /**putting all the power cards into the two-dimensional arraylist**/
   powerCards.add(cardFour);
   powerCards.add(cardSeven);
   
    return powerCards;
 }
 /**checking whether the card is a power card**/
 public boolean isPowerCards(Card card){
 if(card.rank.equals(Card.RANKS[2])||card.rank.equals(Card.RANKS[4])||card.rank.equals(Card.RANKS[7]))
 {
   return true;
 }
  return false;
 }
 /**to access the index of the current player object**/                                                                                      
 public int getIndex(ArrayList<Player> players){
   int index=0;
   for(int i=0;i<players.size();i++)  
   {
     if(this.hand==players.get(i).hand) /**if the hand of the player is identical, it implies that it is the current player object**/
     {index=i; return index;}  
   }
   return -1;
 }
  public boolean play(DiscardPile       discardPile, 
                     Stack<Card>       drawPile, 
                     ArrayList<Player> players,
                     int player,
                     boolean direction)
 { if(drawPile.empty())
    { return true;}
   int indexOfPlayer=getIndex(players);
   int flag=1;
   Random r=new Random();
   
   if(players.get(Crazy8Game.nextPlayer(indexOfPlayer,direction,players)).hand.size()>1)
   {
     Card card=discardPile.top();
     ArrayList<Integer> index=new ArrayList<Integer>();
     ArrayList<Integer> eight=new ArrayList<Integer>();
     for(int i=0;i<this.hand.size();i++)
    { 
       eight=checkEight(this.hand);
       if(this.hand.get(i).compareTo(card)==0)
     {
         index.add(i);
     }
   }
   if(index.size()>0)
   {
     int r2=r.nextInt(index.size());
     int a=index.get(r2);
     discardPile.add(this.hand.remove(a));
   }
   else
   {
     if(eight.size()>=1)  
     { 
       int j=r.nextInt(eight.size());
       int a=eight.get(j);
       discardPile.add(this.hand.remove(a)); 
     }
     else
     {
         while(true)
         {
           if(takeCards(drawPile).compareTo(discardPile.top())==0)
          {
             discardPile.add(this.hand.remove(this.hand.size()-1));break;
           }
         else
         {
           takeCards(drawPile);
         }
       }
     }
    }
   }
    else
    { 
      flag=0;
      for(int i=0;i<3;i++)
      {
        if(checkPowerCards(this.hand).get(i).size()>0)
        {
        flag=1;
        if(checkPowerCards(this.hand).get(0).size()>0)
        {
          discardPile.add(this.hand.remove((int)checkPowerCards(this.hand).get(0).get(0)));
          break;
         }
        if(checkPowerCards(this.hand).get(1).size()>0)
        {
         discardPile.add(this.hand.remove((int)checkPowerCards(this.hand).get(1).get(0)));break;
        }
        if(checkPowerCards(this.hand).get(2).size()>0)
        {
         discardPile.add(this.hand.remove((int)checkPowerCards(this.hand).get(2).get(0)));break;
        }
        
      }
     }
      if(flag==0)
      {
         while(true)
         {
           if(isPowerCards(takeCards(drawPile))==true&&takeCards(drawPile).compareTo(discardPile.top())==0)
          {
             discardPile.add(this.hand.remove(this.hand.size()-1));break;
           }
         else
         {
           takeCards(drawPile);
         }
       }
      }  
    }
  if(this.hand.size() == 0 )
     {return true;}
  return false;
 }
  }
  /* ADD YOUR CODE HERE */
  

