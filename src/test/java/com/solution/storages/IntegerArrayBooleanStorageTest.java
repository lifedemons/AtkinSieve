package com.solution.storages;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class IntegerArrayBooleanStorageTest {

	private static final int PRIMES_LIMIT = 100;

	private IntegerArrayBooleanStorage booleanStorage = new IntegerArrayBooleanStorage();

	@Before
	public void setUp() throws IOException {
		booleanStorage.init(PRIMES_LIMIT);
	}

	@After
	public void tearDown(){
		booleanStorage.clean();
	}

	@Test
	public void testInit() throws IOException {

		assertEquals(booleanStorage.getLimit(), PRIMES_LIMIT);
	}

	@Test
	public void testRead() throws IOException {

		try{
			booleanStorage.read(-1);
			fail("Failed, due to possibility to read negative positions");
		} catch (IllegalArgumentException ex){
			assertTrue("Its OK", true);
		}

		
		assertFalse(booleanStorage.read(30));

		readWrite();

		try{
			booleanStorage.write(PRIMES_LIMIT + 1, true);
			fail("Failed, due to possibility to read positions which exceeds limit");
		} catch (IllegalArgumentException ex){
			assertTrue("Its OK", true);
		}
	}

	@Test
	public void testWrite() throws IOException {

		try{
			booleanStorage.write(-1, true);
			fail("Failed, due to possibility to write negative positions");
		} catch (IllegalArgumentException ex){
			assertTrue("Its OK", true);
		}
		
		readWrite();

		try{
			booleanStorage.write(PRIMES_LIMIT + 1, true);
			fail("Failed, due to possibility to write to positions which exceeds limit");
		} catch (IllegalArgumentException ex){
			assertTrue("Its OK", true);
		}

	}

	private void readWrite() throws IOException {
		booleanStorage.write(5, true);
		assertTrue(booleanStorage.read(5));

		booleanStorage.write(0, false);
		assertFalse(booleanStorage.read(0));

		booleanStorage.write(PRIMES_LIMIT, false);
		assertFalse(booleanStorage.read(PRIMES_LIMIT));
	}

}
