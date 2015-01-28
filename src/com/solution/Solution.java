package com.solution;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Implementation of filtering of Primes according to Sieve of Atkin algorithm.<br>
 * Calculation time of Sieve of Atkin is faster than N*(log(N)).<br>
 * <i>"Sieve of Atkin can theoretically compute primes up to N using O(N/log log N) operations"</i><br>
 * {@link https://en.wikipedia.org/wiki/Sieve_of_Atkin}
 * @author Vyacheslav Yankovyi
 */
public class Solution {

	private static final long MAX_POSSIBLE_NUMBER = 4294967295L;
	private static final String OUTPUT_FILE_NAME = "output.txt";
	private static final String COMMA = ",";

	public static void main(String[] args) {

		AtkinSieve atkinSieve = null;

		FileInputStream fis = null;
		Scanner input = null;

		FileOutputStream fos = null;
		PrintStream ps = null;

		if(args.length == 0)
			throw new IllegalArgumentException("Input file name must be provided as App argument");

		String inputFileName = args[0];

		try {
			fis = new FileInputStream(inputFileName);
			input = new Scanner(fis);

			fos = new FileOutputStream(OUTPUT_FILE_NAME);
			ps = System.out;

			while(input.hasNextLong()){
				long maxLimit = input.nextLong();

				if((maxLimit <= 0) || (maxLimit >= MAX_POSSIBLE_NUMBER))
						throw new IllegalArgumentException("N must be positive and less than 4294967295");

				atkinSieve = new AtkinSieve(maxLimit);
				atkinSieve.filterPrimes();

				//Write primes
				for (long i = 2; i < maxLimit; i++) {
					if (atkinSieve.isPrime(i)) {
						 ps.print(i + COMMA);
					}
				}

				ps.println();
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			if(input != null){
				input.close();
			}

			if(fis != null){
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if(ps != null){
				ps.close();
			}

			if(fos != null){
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if(atkinSieve != null){
				atkinSieve.close();
			}
		}

	}

}
