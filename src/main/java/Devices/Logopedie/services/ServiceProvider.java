package Devices.Logopedie.services;

public class ServiceProvider {
	private static CountryService countryService = new CountryService();

	public static CountryService getCountryService() {
		return countryService;
	}
	
}
