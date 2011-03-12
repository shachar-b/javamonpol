package assets;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import monopoly.GameManager;

import players.Player;

public abstract class AssetGroup implements Collection<Asset> {

	
	protected ArrayList<Asset> assetsInGroup;
	protected String nameOfGroup;


	public AssetGroup(String nameOfGroup) {
		this.assetsInGroup = new ArrayList<Asset>();
		this.nameOfGroup = nameOfGroup;
	}




	/**
	 * @pre group is not empty
	 * @return true if and only if  the group is owned by a single player false otherwise
	 */
 	public boolean isOfSoleOwnership()
	{	
		Player owner=this.get(0).getOwner();
		for(Asset curr:this)
		{
			if(owner!=curr.getOwner() || curr.getOwner()==GameManager.assetKeeper)
			{
				return false;
			}
		}
		return true;
	}




	//collection functions
	@Override
	public boolean add(Asset asset) {
		return assetsInGroup.add(asset);
	}

	@Override
	public boolean addAll(Collection<? extends Asset> arg0) {
		return assetsInGroup.addAll(arg0);
	}

	@Override
	public void clear() {
		assetsInGroup.clear();
	}

	@Override
	public boolean contains(Object arg0) {
		return assetsInGroup.contains(arg0);
	}

	@Override
	public boolean containsAll(Collection<?> arg0) {
		return assetsInGroup.containsAll(arg0);
	}

	@Override
	public boolean isEmpty() {
		return assetsInGroup.isEmpty();
	}

	@Override
	public Iterator<Asset> iterator() {
		return assetsInGroup.iterator();
	}

	@Override
	public boolean remove(Object arg0) {
		return assetsInGroup.remove(arg0);
	}

	@Override
	public boolean removeAll(Collection<?> arg0) {
		return assetsInGroup.removeAll(arg0);
	}

	@Override
	public boolean retainAll(Collection<?> arg0) {
		return assetsInGroup.retainAll(arg0);
	}

	@Override
	public int size() {
		return assetsInGroup.size();
	}

	@Override
	public Object[] toArray() {
		return assetsInGroup.toArray();
	}

	@Override
	public <T> T[] toArray(T[] arg0) {
		return assetsInGroup.toArray(arg0);
	}

	/**
	 * @param index - an index
	 * @return the Asset in the index place
	 */
	public Asset get(int index)
	{
		return assetsInGroup.get(index);
	}


	/**
	 * @return the nameOfGroup
	 */
	public String getNameOfGroup() {
		return nameOfGroup;
	}

	@Override
	public String toString() {
		return "name:"+nameOfGroup + " Contains: " +assetsInGroup.toString();
	}
}



