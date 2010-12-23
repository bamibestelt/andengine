package org.anddev.andengine.util;

import java.util.ArrayList;

/**
 * @author Nicolas Gramlich
 * @since 12:43:39 - 11.03.2010
 */
public class ListUtils {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	public static <T> ArrayList<? extends T> toList(final T pElement) {
		final ArrayList<T> out = new ArrayList<T>();
		out.add(pElement);
		return out;
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}