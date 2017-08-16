import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import java.util.Random;

public class Crazy8Game{

    public static void main(String[] args){

		/* create the deck */
        Card[] deck = new Card[52];
        int index = 0;
        for(int r=2; r<=14; r+=1){
            for(int s=0; s<4; s+=1){
                deck[index++] = new Card(Card.SUITS[s], Card.RANKS[r]);
            }
        }

		/* shuffle the deck */
        Random rnd = new Random();
        Card swap;
        for(int i = deck.length-1; i>=0; i=i-1){
            int pos = rnd.nextInt(i+1);
            swap = deck[pos];
            deck[pos] = deck[i];
            deck[i] = swap;
        }

		/* players in the game */
        Player[] players = new Player[3];
        players[0] = new mindEightPlayer( Arrays.copyOfRange(deck, 0, 5) );
        System.out.println("0 : " + Arrays.toString( Arrays.copyOfRange(deck, 0, 5)));
        players[1] = new mindEightPlayer( Arrays.copyOfRange(deck, 5, 10) );
        System.out.println("0 : " + Arrays.toString( Arrays.copyOfRange(deck, 5, 10)));
        players[2] = new mindEightPlayer( Arrays.copyOfRange(deck, 10, 15) );
        System.out.println("0 : " + Arrays.toString( Arrays.copyOfRange(deck, 10, 15)));


		/* discard and draw piles */
        DiscardPile discardPile = new DiscardPile();
        Stack<Card> drawPile = new Stack<Card>();
        for(int i=15; i<deck.length; i++){
            drawPile.push(deck[i]);
        }

        System.out.println("draw pile is : " + Arrays.toString( Arrays.copyOfRange(deck, 15, deck.length) ));

        deck = null;

        boolean win = false;
        int player = -1;    // start game play with player 0

        ArrayList<Player> people = new ArrayList<Player>(Arrays.asList(players));
        discardPile.add( drawPile.pop() );
        boolean direction = true;

        while( !win && !drawPile.empty()){
            // player = (player + 1) % players.length;
            player = Crazy8Game.nextPlayer(player,direction,people);
            System.out.println("player " + player);
            System.out.println("draw pile    : " + drawPile.peek() );
            System.out.println("discard pile : " + discardPile.top() );

            win = people.get(player).play(discardPile, drawPile, people,player,direction);
            if(discardPile.top().getRank() == 2){
                players[nextPlayer(player,direction,people)].takeCards(drawPile);
                players[nextPlayer(player,direction,people)].takeCards(drawPile);
                player = nextPlayer(player,direction,people);
            }
            else if(discardPile.top().getRank() == 4)
            {
                player = nextPlayer(player,direction,people);
            }
            else if(discardPile.top().getRank() == 7){
                if(direction){
                    direction = false;
                }
                else{
                direction = true;}
            }
            else if(discardPile.top().getRank() == 8){
                if(players[player].hand.size()>0){
                players[player].placeEight(players[player].hand.get(0).getSuit(),discardPile);}
            }
            System.out.println("draw pile   : " + drawPile.peek() );
            System.out.println("discard pile : " + discardPile.top() );

        }
        ArrayList<Card> winCard = new ArrayList<Card>();
        for(int i =0;i<players.length;i++) {
            if (players[i].hand.size() != 0) {
                winCard.addAll(players[i].hand);
            } else {
                player = i;
            }
        }
        System.out.println("marks:  " + Crazy8Game.handValue(winCard));
        System.out.println("winner is player " + player);

    }
    public static int nextPlayer(int player, boolean direction, ArrayList<Player> people){
        if(direction){
            return (player + 1) % people.size();
        }
        if(player!=0){
        return Math.abs((player - 1) % people.size());}
        return people.size()-1;
    }
    public static int previousPlayer(int player, boolean direction, ArrayList<Player> people){
        if(direction=false){
            return (player + 1) % people.size();
        }
        if(player!=0){
            return Math.abs((player - 1) % people.size());}
        return people.size()-1;
    }

    public static int handValue(ArrayList<Card> hand) {
       // hand.addAll(hand2);
        int value = 0;
        for (int i = 0; i < hand.size(); i++) {
            if (hand.get(i).getRankString().equals("Jack") || hand.get(i).getRankString().equals("Queen") || hand.get(i).getRankString().equals("King")) {
                value = value + 10;
            } else if (hand.get(i).getRankString().equals("Ace")) {
                value = value + 1;
            } else if (hand.get(i).getRankString().equals("8")) {
                value = value + 50;
            } else if (hand.get(i).getRankString().equals("2") || hand.get(i).getRankString().equals("4")) {
                value = value + 25;  } else if (hand.get(i).getRankString().equals("7")) {
                value = value + 20;
            } else if (hand.get(i).getRankString().equals("3")) {
                value = value + 3;
            } else if (hand.get(i).getRankString().equals("5")) {
                value = value + 5;
            } else if (hand.get(i).getRankString().equals("6")) {
                value = value + 6;
            } else if (hand.get(i).getRankString().equals("9")) {
                value = value + 9;
            } else if (hand.get(i).getRankString().equals("10")) {
                value = value + 10;
            }
        }
        return value;}


}
