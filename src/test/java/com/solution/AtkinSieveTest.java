/**
 * 
 */
package com.solution;

import static org.mockito.Mockito.verify;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.powermock.reflect.Whitebox;

import com.solution.storages.BooleanStorage;
import com.solution.storages.IntegerArrayBooleanStorage;

/**
 * @author Vyacheslav
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class AtkinSieveTest {

	private static final int PRIMES_LIMIT = 10;

	private AtkinSieve atkinSieve;

	@Mock
	private BooleanStorage booleanStorage;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {

		booleanStorage = new IntegerArrayBooleanStorage();
		atkinSieve = new AtkinSieve(PRIMES_LIMIT);
		MockitoAnnotations.initMocks(this);
		Whitebox.setInternalState(atkinSieve, "booleanStorage", booleanStorage);
	}

	/**
	 * Test method for {@link main.java.com.solution.AtkinSieve#filterPrimes()}.
	 * @throws IOException 
	 */
	@Test
	public void testFilterPrimes() throws IOException {
		atkinSieve.filterPrimes();
		
		verify(booleanStorage).write(2, true);
		verify(booleanStorage).write(3, true);
		verify(booleanStorage).write(5, true);
		verify(booleanStorage).write(7, true);
	}

	/**
	 * Test method for {@link main.java.com.solution.AtkinSieve#isPrime(long)}.
	 * @throws IOException 
	 */
	@Test
	public void testIsPrime() throws IOException {
		atkinSieve.isPrime(4);
		verify(booleanStorage).read(4);
	}

	/**
	 * Test method for {@link main.java.com.solution.AtkinSieve#close()}.
	 */
	@Test
	public void testClose() {
		atkinSieve.close();
		verify(booleanStorage).clean();
	}

}
