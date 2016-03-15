package daniel.filmy;

import java.util.ArrayList;
import java.util.List;

public class FilmRepo implements FilmManager {
	public List<Film> filmy = new ArrayList();
	
	@Override
	public void dodaj(Film f) {
		this.filmy.add(f);
		
	}

	@Override
	public List<Film> pobierzWszystko() {
		return this.filmy;
		
	}

	@Override
	public void usun(Film f) {
		this.filmy.remove(f);
	}

}
