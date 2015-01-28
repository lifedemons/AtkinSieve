package com.solution.storages;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * {@linkplain RandomAccessFileBooleanStorage} provides possibility to store large boolean arrays via using {@link RandomAccessFile}.
 * Might be useful for the computers with low RAM size, but this storage significantly increases execution time due to necessity to access HDD on every operation.
 * @author Vyacheslav Yankovyi
 *
 */
public class RandomAccessFileBooleanStorage implements BooleanStorage{
	private static final String BOOLEAN_STORAGE_FILE_NAME = "booleanStorage.txt";
	private final RandomAccessFile rafStorage;
	private File storageFile;

	public RandomAccessFileBooleanStorage() throws FileNotFoundException, IOException {
		super();
		this.storageFile = new File(BOOLEAN_STORAGE_FILE_NAME);
		this.rafStorage = new RandomAccessFile(storageFile, "rw");

	}

	@Override
	public void init(long limit) throws IOException {
		rafStorage.setLength(limit);
	}

	public void write(long position, boolean value) throws IOException{
		rafStorage.seek(position);
		rafStorage.writeBoolean(value);
	}

	public boolean read(long position) throws IOException{
		rafStorage.seek(position);
		return rafStorage.readBoolean();
	}

	public boolean clean(){

		boolean deletionResult = false;
		try {
			rafStorage.close();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			deletionResult = storageFile.delete();
		}

		return deletionResult;
	}

}