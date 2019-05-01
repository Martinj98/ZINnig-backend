package Devices.Logopedie.services;

public class ServiceProvider {
	private static CountryService countryService = new CountryService();
	private static LogopedistService logopedistService = new LogopedistService();
	private static ChildService childService = new ChildService();
	private static ParentService parentService = new ParentService();

	public static CountryService getCountryService() {
		return countryService;
	}

	public static LogopedistService getLogopedistService() {
		return logopedistService;
	}

	public static ChildService getChildService(){
		return childService;
	}

	public static ParentService getParentService(){
		return parentService;
	}
	
}
