package daniel.filmy;

import java.util.ArrayList;
import java.util.List;

public class filmRepo implements filmManager {
	public List<film> filmy = new ArrayList();
	
	@Override
	public void dodaj(film f) {
		this.filmy.add(f);
		
	}

	@Override
	public List<film> pobierzWszystko() {
		return this.filmy;
		
	}

	@Override
	public void usun(film f) {
		this.filmy.remove(f);
	}

}
