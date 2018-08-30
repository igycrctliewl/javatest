package com.trinetbss.io;

import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;

public class FileWriter extends java.io.FileWriter {

	public FileWriter(File file) throws IOException {
		super(file);
	}

	public FileWriter(File file, boolean append) throws IOException {
		super(file, append);
	}

	public FileWriter(FileDescriptor fd) {
		super(fd);
	}
	
	public FileWriter(String fileName) throws IOException {
		super(fileName);
	}

	public FileWriter(String fileName, boolean append) throws IOException {
		super(fileName,append);
	}

	/**
	 * Writes a line of output from a String, terminated by the system-dependent line separator.
	 * @param str - String to be written
	 * @throws IOException If an I/O error occurs
	 */
	public void writeLine( String str ) throws IOException {
		super.write( str + System.lineSeparator() );
	}
}
