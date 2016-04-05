package daniel.filmy;

import java.util.ArrayList;
import java.util.List;

public class FilmManager implements IFilmManager {
	private List<Film> filmy = new ArrayList();
	private IFilmManager ifm;
	
	public FilmManager(IFilmManager ifm) {
		super();
		this.ifm = ifm;
	}
	@Override
	public void dodaj(Film f) {
		this.ifm.dodaj(f);
	}

	/*
	@Override
	public List<Film> pobierzWszystko() {
		return this.filmy;
		
	}
	*/
	
	@Override
	public void usun(Film f) {
		this.ifm.usun(f);
	}
	@Override
	public IFilmManager getAll() {
		// TODO Auto-generated method stub
		return this.ifm;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return this.ifm.size();
	}

}
