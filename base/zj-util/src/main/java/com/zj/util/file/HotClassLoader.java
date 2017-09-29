/**
 * 
 */
package com.zj.util.file;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;

public class HotClassLoader extends URLClassLoader {
	private static final String PACKAGENAME = "com.ehome.web.script";
	private static final String INTERFACENAME = "com.ehome.web.script.QueryInterface";
	private ClassLoader parent;

	public HotClassLoader(URL[] urls, ClassLoader parent) {
		super(urls, parent);
		this.parent = parent;
	}

	@Override
	public Class<?> loadClass(String name) throws ClassNotFoundException {

		return (loadClass(name, false));
	}

	@Override
	protected synchronized Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
		Class<?> clazz = null;

		if (!name.startsWith(PACKAGENAME) || name.equals(INTERFACENAME)) {
			// Class is not script, therefore, have our
			// parent load it
			clazz = parent.loadClass(name);
			if (resolve)
				resolveClass(clazz);
			return clazz;
		}
		// (0) Check our previously loaded class cache
		clazz = findLoadedClass(name);
		if (clazz != null) {
			if (resolve)
				resolveClass(clazz);
			return (clazz);
		}
		return findClass(name);
	}

	/***
	 * Delegate to parent
	 * 
	 * @see java.lang.ClassLoader#getResourceAsStream(java.lang.String)
	 */
	@Override
	public InputStream getResourceAsStream(String name) {
		InputStream is = parent.getResourceAsStream(name);
		if (is == null) {
			URL url = findResource(name);
			if (url != null) {
				try {
					is = url.openStream();
				} catch (IOException e) {
					// Ignore
				}
			}
		}
		return is;
	}
}
