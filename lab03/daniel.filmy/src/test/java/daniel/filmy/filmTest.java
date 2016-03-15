package daniel.filmy;

import static org.junit.Assert.*;

import org.junit.Test;

public class filmTest {
	filmRepo fR = new filmRepo();
	film f = new film("Władca Pierścieni", "Film o hobbicie owłosionym znakomicie", 2004);
	
	@Test
	public void checkList() {
		assertTrue(fR.pobierzWszystko().isEmpty()); //powinna byc pusta
	}
	
	@Test
	public void checkAdd() {
		fR.dodaj(f);
		assertFalse(fR.pobierzWszystko().isEmpty()); //powinna być niepusta		
		fR.usun(f); //teraz znowu pusta
	}
	
	@Test
	public void checkRemove() {
		fR.dodaj(f);
		assertFalse(fR.pobierzWszystko().isEmpty()); //powinna być niepusta
		
		fR.usun(f); //teraz znowu pusta
		assertTrue(fR.pobierzWszystko().isEmpty()); //powinna byc pusta
	}
}
