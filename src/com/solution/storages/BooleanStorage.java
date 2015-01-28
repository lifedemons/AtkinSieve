/**
 * 
 */
package com.solution.storages;

import java.io.IOException;

/**
 * General interface of Boolean Storages, which might be used in the program.
 * @author Vyacheslav Yankovyi
 *
 */
public interface BooleanStorage {

		public void init(long limit) throws IOException;

		public void write(long position, boolean value) throws IOException;

		public boolean read(long position) throws IOException;

		public boolean clean();
}
