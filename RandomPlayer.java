/**
 * Auto Generated Java Class.
 */
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Stack;
import java.util.Random;
public class RandomPlayer extends Player{

 public RandomPlayer(Card[] cards){this.hand = new ArrayList<Card>(Arrays.asList(cards));}
 
  /* play a card */ 
 public boolean play(DiscardPile       discardPile, 
                     Stack<Card>       drawPile, 
                     ArrayList<Player> players,
                    int player,
                    boolean direction)
 { Card card=discardPile.top();
   ArrayList<Integer> index=new ArrayList<Integer>();
   Random r=new Random();
   
   ArrayList<Integer> eight=new ArrayList<Integer>();
   for(int i=0;i<this.hand.size();i++)
   { eight=checkEight(this.hand);
     if(this.hand.get(i).compareTo(card)==0){
       index.add(i);
     }
   }
   if(index.size()>0){
     int r2=r.nextInt(index.size());
     int a=index.get(r2);
     discardPile.add(this.hand.remove(a));}
   else{
     if(eight.size()>=1)  
     { int j=r.nextInt(eight.size());
       int a=eight.get(j);
       discardPile.add(this.hand.remove(a)); 
     }
     else
     {
       takeCards(drawPile);             
    }
   }
  if( this.hand.size() == 0 )
     {return true;}
  return false;
 }

}