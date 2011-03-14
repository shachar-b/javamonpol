package assets;

import players.Player;
import ui.OfferType;

public interface Offerable {
	
	public String getName();
	public void setOwner(Player owner);
	public OfferType getType();

}
