package assets;

public class Country extends AssetGroup {

	public Country(String nameOfCountry) {
		super(nameOfCountry);
	}

	
	public boolean hasHousesConstructed()
	{
		for (Asset city : assetsInGroup)
		{
			if (((City)city).getNumHouses()>0)
			{
				return true;
			}
		}
		return false;
	}
}
