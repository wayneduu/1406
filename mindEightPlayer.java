import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class mindEightPlayer extends Player{
    // public int playerIndex = -1;
    public mindEightPlayer(Card[] cards){
        this.hand = new ArrayList<Card>(Arrays.asList(cards));
    }


public boolean play(DiscardPile      discardPile,
                        Stack<Card>       drawPile,
                        ArrayList<Player> players,
                        int player,
                        boolean direction){
        if(drawPile.empty()){return true;}
        if(this.getSizeOfHand() == 1){
            discardPile.add(this.hand.remove(0));
            return true;
        }

        int count = 0;
        for(int i = 0;i<hand.size();i++){
            if(hand.get(i).getRank()== 8){
                count++;
            }
        }

        if(players.get(Crazy8Game.nextPlayer(player,direction,players)).getSizeOfHand() == count){

            for(int i = 0;i<hand.size();i++){
                if(hand.get(i).getRank() == 8){
                    Card Discard = this.hand.remove(i);
                    if(this.hand.size()>0){
                    Discard.suit = hand.get(0).getSuit();
                    discardPile.add(this.hand.remove(i));
                    }
                }
            }
        }


        else {
            int largest = 0;
            int indexLarge = 0;
            ArrayList<Card> validCard = new ArrayList<Card>();

            for(int i = 0;i<hand.size();i++){
                if(hand.get(i).getSuit().equals(discardPile.top().getSuit()) || (
                        hand.get(i).getRank() == (discardPile.top().getRank()) &&
                                hand.get(i).getRank() != 8)
                        ){
                    validCard.add(hand.get(i));
                }
            }
            while(validCard.isEmpty()){
                Card newCard = super.takeCards(drawPile);
                if(newCard.getSuit().equals(discardPile.top().getSuit()) || (
                        newCard.getRank() == (discardPile.top().getRank()) &&
                                newCard.getRank() != 8)){
                    validCard.add(newCard);
                }
                
                hand.add(newCard);
            }
            if(!validCard.isEmpty()){
                for(int i =0; i<validCard.size();i++){
                    if(validCard.get(i).getRank()>largest){
                        largest = validCard.get(i).getRank();
                        indexLarge = i;
                    }
                }
                this.hand.remove(validCard.get(indexLarge));
                discardPile.add(validCard.get(indexLarge));
                validCard.clear();
            }



        }

        return false;

    }

}