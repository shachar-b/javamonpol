package cards;

import java.io.File;
import java.util.ArrayList;
import monopoly.GameManager;
import monopoly.GameManager.AgainstWho;
import monopoly.Monopoly;
import players.Player;
import squares.Square;

public class ActionCard {
	private Monopoly monopoly;
	private int sign;
	private String action;
	private int amount;
	private AgainstWho against;
	private Class goOnNext;
	private boolean CollectBonus;

	public ActionCard(int sign, String action, int amount, AgainstWho against,
			Class goOnNext, boolean collectBonus) {
		super();
		this.monopoly = GameManager.currentGame;
		this.sign = sign;
		this.action = action;
		this.amount = amount;
		this.against = against;
		this.goOnNext = goOnNext; //Get Out of jail free card is characterized by goOnNext=Square (any kind of square)
		CollectBonus = collectBonus;
	}

	public String toString(){
		return action;
	}
	
	public boolean isGetOutOfJailFreeCard()
	{
		return (goOnNext==Square.class);
	}
	
	public void init(File inFile)
	{
		//TODO : Write Function
	}
	
	public void doCard(Player player)
	{
		if (goOnNext != null)
		{
			GameManager.currentGame.gotoNextSquareOfType(player, goOnNext, CollectBonus);			
		}//otherwise i assume it isn't this type of card
		if(amount!=0)
		{
			if(against == AgainstWho.OtherPlayers)
			{
				int sum = 0;
				ArrayList<Player> list = GameManager.currentGame.getGamePlayers();
				for(int currentPlayer=0;currentPlayer<list.size(); currentPlayer++)
				{
					Player other=list.get(currentPlayer);
					if(other != player)//Don't take money from yourself..
					{
						other.ChangeBalance(amount,-sign); //-sign to TAKE from players
						sum += amount;
					}
					if(!list.contains(other))
					{
						currentPlayer--;//player has been removed so next player is in his index
					}
				}
				player.ChangeBalance(sum,sign);
			}
			else { // get from Treasury
				player.ChangeBalance(amount,sign);
			}
		}
	}
}
