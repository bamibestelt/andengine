/*
 * Copyright 2010 Mario Zechner (contact@badlogicgames.com), Nathan Sweet (admin@esotericsoftware.com)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */

package com.badlogic.gdx.utils;

import java.util.Arrays;

/**
 * An unordered, resizable int array. Avoids the boxing that occurs with ArrayList<Integer>. Avoids a memory copy when removing
 * elements (the last element is moved to the removed element's position).
 * @author Riven
 * @author Nathan Sweet
 */
public class FloatBag {
	public float[] items;
	public int size;

	public FloatBag () {
		this(16);
	}

	public FloatBag (int capacity) {
		items = new float[capacity];
	}

	public FloatBag (FloatBag bag) {
		size = bag.size;
		items = new float[size];
		System.arraycopy(bag.items, 0, items, 0, size);
	}

	public FloatBag (FloatArray array) {
		size = array.size;
		items = new float[size];
		System.arraycopy(array.items, 0, items, 0, size);
	}

	public void add (float value) {
		float[] items = this.items;
		if (size == items.length) items = resize((int)(size * 1.75f), false);
		items[size++] = value;
	}

	public void addAll (FloatBag bag) {
		float[] items = this.items;
		int sizeNeeded = size + bag.size;
		if (sizeNeeded >= items.length) items = resize((int)(sizeNeeded * 1.75f), false);
		System.arraycopy(bag.items, 0, items, size, bag.size);
		size += bag.size;
	}

	public void addAll (FloatArray array) {
		float[] items = this.items;
		int sizeNeeded = size + array.size;
		if (sizeNeeded >= items.length) items = resize((int)(sizeNeeded * 1.75f), false);
		System.arraycopy(array.items, 0, items, size, array.size);
		size += array.size;
	}

	public float get (int index) {
		if (index >= size) throw new IndexOutOfBoundsException(String.valueOf(index));
		return items[index];
	}

	public boolean contains (float value) {
		int i = size - 1;
		float[] items = this.items;
		while (i >= 0)
			if (items[i--] == value) return true;
		return false;
	}

	public int indexOf (float value) {
		float[] items = this.items;
		for (int i = 0, n = size; i < n; i++)
			if (items[i] == value) return i;
		return -1;
	}

	public boolean removeValue (float value) {
		float[] items = this.items;
		for (int i = 0, n = size; i < n; i++) {
			if (items[i] == value) {
				removeIndex(i);
				return true;
			}
		}
		return false;
	}

	public void removeIndex (int index) {
		if (index >= size) throw new IndexOutOfBoundsException(String.valueOf(index));
		size--;
		float[] items = this.items;
		items[index] = items[size];
	}

	/**
	 * Removes and returns the last item.
	 */
	public float pop () {
		return items[--size];
	}

	/**
	 * Removes and returns the item at the specified index.
	 */
	public float pop (int index) {
		if (index >= size) throw new IndexOutOfBoundsException(String.valueOf(index));
		float[] items = this.items;
		float value = items[index];
		size--;
		items[index] = items[size];
		return value;
	}

	public void clear () {
		size = 0;
	}

	/**
	 * Reduces the size of the backing array to the size of the actual items. This is useful to release memory when many items have
	 * been removed, or if it is known the more items will not be added.
	 */
	public void shrink () {
		resize(size, true);
	}

	/**
	 * Increases the size of the backing array to acommodate the specified number of additional items. Useful before adding many
	 * items to avoid multiple backing array resizes.
	 */
	public void ensureCapacity (int additionalCapacity) {
		int sizeNeeded = size + additionalCapacity;
		if (sizeNeeded >= items.length) resize(sizeNeeded, false);
	}

	private float[] resize (int newSize, boolean exact) {
		if (!exact && newSize < 8) newSize = 8;
		float[] newItems = new float[newSize];
		float[] items = this.items;
		System.arraycopy(items, 0, newItems, 0, Math.min(items.length, newItems.length));
		this.items = newItems;
		return newItems;
	}

	/**
	 * Sorts the bag, which will stay ordered until an element is removed.
	 */
	public void sort () {
		Arrays.sort(items, 0, size);
	}

	public String toString () {
		if (size == 0) return "[]";
		float[] items = this.items;
		StringBuilder buffer = new StringBuilder(32);
		buffer.append('[');
		buffer.append(items[0]);
		for (int i = 1; i < size; i++) {
			buffer.append(", ");
			buffer.append(items[i]);
		}
		buffer.append(']');
		return buffer.toString();
	}
}
