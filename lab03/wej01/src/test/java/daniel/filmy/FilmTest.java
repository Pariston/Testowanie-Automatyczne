package daniel.filmy;

import static org.junit.Assert.*;

import org.easymock.EasyMockRule;
import org.easymock.Mock;
import static org.easymock.EasyMock.*;
import org.easymock.TestSubject;
import org.junit.Rule;
import org.junit.Test;

public class FilmTest {
	
	Film f = new Film("Wladca Pierscieni", "Film o hobbicie", 2004);
	
	@Rule
	public EasyMockRule mocks = new EasyMockRule(this);
	
	@Mock
	private IFilmManager mock;
	 
	@TestSubject
	private FilmManager fm = new FilmManager(mock);
	
	@Test
	public void filmManagerTest() {
		mock.dodaj(f);
		expectLastCall();
		expect(mock.size()).andReturn(1);
		expect(mock.getAll()).andReturn(mock);
		replay(mock);
		fm.dodaj(f);
		//assertEquals(0, fm.getAll().size());
		verify(mock);
	}
	
	/*
	FilmManager fR = new FilmManager();
	Film f = new Film("Władca Pierścieni", "Film o hobbicie owłosionym znakomicie", 2004);
	Film f2 = new Film("RzycieJestNobelom", "Tak przyjaznom, a raz dosyc", 2013);
	
	@Test
	public void checkList() {
		assertTrue(fR.pobierzWszystko().isEmpty()); //powinna byc pusta
	}
	
	@Test
	public void checkAdd() {
		fR.dodaj(f);
		fR.dodaj(f2);
		
		assertFalse(fR.pobierzWszystko().isEmpty()); //powinna być niepusta
		assertEquals("Władca Pierścieni", fR.pobierzWszystko().get(0).tytul);
		assertEquals("RzycieJestNobelom", fR.pobierzWszystko().get(1).tytul);
		assertEquals(f2, fR.pobierzWszystko().get(1));
		assertEquals(fR.pobierzWszystko().size(), 2);
		
		fR.usun(f); //teraz znowu pusta
		fR.usun(f2);
		assertNotEquals(fR.pobierzWszystko().size(), 2);
	}
	
	@Test
	public void checkRemove() {
		fR.dodaj(f);
		assertFalse(fR.pobierzWszystko().isEmpty()); //powinna być niepusta
		
		fR.usun(f); //teraz znowu pusta
		assertTrue(fR.pobierzWszystko().isEmpty()); //powinna byc pusta
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void checkException() {
		fR.pobierzWszystko().removeAll(fR.pobierzWszystko());
		fR.pobierzWszystko().get(0);
	}
	*/
}
