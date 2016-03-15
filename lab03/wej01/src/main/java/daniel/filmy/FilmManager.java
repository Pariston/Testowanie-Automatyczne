package daniel.filmy;

import java.util.List;

public interface FilmManager {
	public void dodaj(Film f);
	public void usun(Film f);
	public List<Film> pobierzWszystko();
}
