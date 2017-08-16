import java.util.Arrays;
import java.util.ArrayList;
import java.util.Stack;

public class HamperLeader extends Player{
  
  protected DiscardPile discard;
  public HamperLeader(Card[] cards){
    this.hand = new ArrayList<Card>(Arrays.asList(cards)); //put given card in hand
     }
  
 //check if is power card
  public boolean isPower(Card c){
    if (c.getRank() == 2 || c.getRank() == 4 || c.getRank() == 7) {
      return true;
    }else{
      return false;
    }
  }
  
  //check if is valid card
  public boolean isValid(Card c){
    if (c.getRank() == discard.top().getRank() || c.getSuit().equals(discard.top().getSuit())) {
      return true;
    }else{
      return false;
    }
  }
  public boolean play(DiscardPile       discardPile, 
                      Stack<Card>       drawPile, 
                      ArrayList<Player> players, int self, boolean direction)
  {
    discard = discardPile;
    
    boolean nextleader= false;//assume no leader
    boolean proleader= false;//assume no leader
    int least=0;//leader's index
    int min=players.get(0).hand.size();//assume player at index 0 is leader;
    
    //find the leader position;
    for(int i=0; i <players.size();i++){
      if(player.get(i).hand.size()<min){
        least=i; 
      }
    }
    //if leader before
    if(least ==nextPlayer(self,  direction)){
      nextleader=true;
    }else if(least ==previousPlayer(self,  direction)){
      
      //if leader behind;
      proleader=true;
    }
    ////If i next is leader
    if(nextleader){
      //set up ValidPower is false
      boolean hasValidPower = false;
      int index = -1;
      for(int i=0; i<hand.size(); i++){
        //check has power card or valid card can be put
        if(isPower(hand.get(i)) && isValid(hand.get(i))){
          
         //pass test ,change valid flag
          hasValidPower = true;
         
          index = i;
        }
      }
      //no valid card keep draw card
      if(!hasValidPower){
        while(!(isPower(drawPile.peek()) && isValid(drawPile.peek()))){
          hand.add(drawPile.pop());
         
          if(drawPile.empty()){return true;}
        }
        //until can discard
        discardPile.add(drawPile.pop());
      }else{
        //if has valid card,discard
        discardPile.add(hand.remove(index));
      }
      
      //if leader is before i
    }else if(proleader){
      boolean hasPower = false;
      int index = -1;
      //check power card on hand
      for(int i=0; i<hand.size(); i++){
        if(isPower(hand.get(i))){
          hasPower = true;
          index = i;
        }
      }
      
      if(!hasPower){
        while(!isPower(drawPile.peek())){
          hand.add(drawPile.pop());
          if(drawPile.empty()){return true;}
        }
        hand.add(drawPile.pop());
      }else{
        discardPile.add(hand.remove(index));
      }
      //if leader not just 1position before i or after
    }else{
      for(int i=0; i<hand.size(); i++){
        if(isValid(hand.get(i))){
          discardPile.add(hand.remove(i));
          if(drawPile.empty()){return true;}
          return false;
        }
      }
      while(!isValid(drawPile.peek())){
        hand.add(drawPile.pop());
        if(drawPile.empty()){return true;}
      }
      discardPile.add(drawPile.pop());
    }
    if(drawPile.empty()){return true;}
    return false;
    
  }
  
}


