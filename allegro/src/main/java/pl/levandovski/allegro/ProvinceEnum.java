package pl.levandovski.allegro;

/**
 * Enum representing available provinces on filtering options
 * 
 * @author Marek Lewandowski
 *
 */
public enum ProvinceEnum {
	DOLNOSLASKIE("z dolnośląskiego"),
	KUJAWSKOPOMORSKIE("z kujawsko-pomorskiego"),
	LUBELSKIE("z lubelskiego"),
	LUBUSKIE("z lubuskiego"),
	LODZKIE("z łódzkiego"),
	MALOPOLSKIE("z małopolskiego"),
	MAZOWIECKIE("z mazowieckiego"),
	OPOLSKIE("z opolskiego"),
	PODKARPACKIE("z podkarpackiego"),
	PODLASKIE("z podlaskiego"),
	POMORSKIE("z pomorskiego"),
	SLASKIE("z śląskiego"),
	SWIETOKRZYSKIE("ze świętokrzyskiego"),
	WARMINSKOMAZURSKIE("z warmińsko-mazurskiego"),
	WIELKOPOLSKIE("z wielkopolskiego"),
	ZACHODNIOPOMORSKIE("z zachodniopomorskiego");
	
	private String value;
	
	private ProvinceEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
