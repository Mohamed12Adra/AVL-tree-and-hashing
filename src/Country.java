import java.util.ArrayList;

public class Country {
	//attributes
	private String CountryName;
	private int NumberOfTouristCities=0;
	private double TotalNumOfTourists=0;
	private LinkedList cities;
	//Constructors
	public Country(String countryName) {
		super();
		CountryName = countryName;
		cities = new LinkedList();
	}
	//adding the cities to the linked list
	public void addCity(City x) {
		TotalNumOfTourists +=x.getNumOfTourists();
		cities.insertNode(x);
		NumberOfTouristCities+=1;
	}
	//setters and getters
	public String getCountryName() {
		return CountryName;
	}
	public void setCountryName(String countryName) {
		CountryName = countryName;
	}
	public int getNumberOfTouristCities() {
		return NumberOfTouristCities;
	}
	public void setNumberOfTouristCities(int numberOfTouristCities) {
		NumberOfTouristCities = numberOfTouristCities;
	}
	public double getTotalNumOfTourists() {
		return TotalNumOfTourists;
	}
	public void setTotalNumOfTourists(int totalNumOfTourists) {
		TotalNumOfTourists = totalNumOfTourists;
	}

	public LinkedList getCities() {
		return cities;
	}
	public void setCities(LinkedList cities) {
		this.cities = cities;
	}
	//overriding tostring method
	@Override
	public String toString() {
		ArrayList<String> x = new ArrayList<>();
		for(int j=0; j<cities.getCount(); j++) {
			City o = (City)cities.Search(j).getData();
			x.add(o.getCityName());
		}
		return "Country [CountryName=" + CountryName + ", NumberOfTouristCities=" + NumberOfTouristCities
			+ ", TotalNumOfTourists=" + TotalNumOfTourists +x.toString()+ "]";
	}

}
