package daniel.filmy;

import java.util.List;

public interface filmManager {
	public void dodaj(film f);
	public void usun(film f);
	public List<film> pobierzWszystko();
}
