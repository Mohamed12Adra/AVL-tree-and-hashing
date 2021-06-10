//city class
public class City {
	//attributes
	private String CityName;
	private double numOfTourists;
	private int delete=0;
	private String CountryName;
	//Constructors
	public City(String cityName, double numOfTourists,String o) {
		super();
		CityName = cityName;
		this.numOfTourists = numOfTourists;
		CountryName=o;
	}
	
	public City(String cityName) {
		super();
		CityName = cityName;
	}
	//setters and getters
	public String getCityName() {
		return CityName;
	}

	public void setCityName(String cityName) {
		CityName = cityName;
	}

	public String getCountryName() {
		return CountryName;
	}
	public void setCountryName(String countryName) {
		CountryName = countryName;
	}

	public double getNumOfTourists() {
		return numOfTourists;
	}

	public void setNumOfTourists(int numOfTourists) {
		this.numOfTourists = numOfTourists;
	}

	public int getDelete() {
		return delete;
	}

	public void setDelete(int delete) {
		this.delete = delete;
	}
	//overriding tostring method
	@Override
	public String toString() {
		return "City [CityName=" + CityName + "]";
	}
}
