package daniel.filmy;

import java.util.List;

public interface IFilmManager {
	public void dodaj(Film f);
	public void usun(Film f);
	public List<Film> pobierzWszystko();
}
