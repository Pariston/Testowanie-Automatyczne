package daniel.filmy;

import java.util.ArrayList;
import java.util.List;

public class Film {
	public String tytul;
	public String opis;
	public int rokProdukcji;
	//public List<film> filmy = new ArrayList();
	
	public Film(String Tytul, String Opis, int RokProdukcji) {
		this.tytul = Tytul;
		this.opis = Opis;
		this.rokProdukcji = RokProdukcji;
	}
}
