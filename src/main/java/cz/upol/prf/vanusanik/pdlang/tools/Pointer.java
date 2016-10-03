/**
 * PDLang
 * Copyright (c) 2016-2017 Peter Vaňušanik <admin@en-circle.com>
 * 
 * Permission is hereby granted, free of charge, to any person
 * obtaining a copy of this software and associated documentation
 * files (the "Software"), to deal in the Software without
 * restriction, including without limitation the rights to use,
 * copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following
 * conditions:
 * 
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 */
package cz.upol.prf.vanusanik.pdlang.tools;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Pointer<T> implements Iterable<T>, Cloneable {

	public Pointer(){
		this(null);
	}
	
	public Pointer(T t){
		this(t, false);
	}
	
	public Pointer(T t, boolean finalPointer){
		this(t, finalPointer, false);
	}
	
	public Pointer(T t, boolean finalPointer, boolean checkNull){
		this.pointedObject = t;
		this.finalPointer = finalPointer;
		this.checkNull = checkNull;
	}

	private T pointedObject;
	private boolean finalPointer;
	private boolean checkNull;
	
	public T get(){
		if (checkNull && pointedObject == null)
			throw new NullPointerException();
		return pointedObject;
	}
	
	public void set(T t){
		if (finalPointer)
			throw new UnsupportedOperationException("read-only");
		pointedObject = t;
	}
	
	public boolean isVoid(){
		return pointedObject == null;
	}
	
	public Pointer<T> asReadOnly(){
		return new Pointer<T>(pointedObject, true, checkNull);
	}
	
	public Pointer<T> asCheckedPointer(){
		return new Pointer<T>(pointedObject, finalPointer, true);
	}
	
	@SuppressWarnings("unchecked")
	public <X> Pointer<X> cast(){
		return (Pointer<X>) clone();
	}
	
	public T setget(T t) {
		set(t);
		return get();
	}
	
	@Override
	public String toString(){
		return pointedObject == null ? "null" : pointedObject.toString();
	}
	
	public Object clone(){
		return new Pointer<T>(pointedObject, finalPointer, checkNull);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((pointedObject == null) ? 0 : pointedObject.hashCode());
		return result;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pointer other = (Pointer) obj;
		if (pointedObject == null) {
			if (other.pointedObject != null)
				return false;
		} else if (!pointedObject.equals(other.pointedObject))
			return false;
		return true;
	}

	public Iterator<T> iterator() {
		return new PointerIterator<T>();
	}
	
	public class PointerIterator<X> implements Iterator<X> {

		boolean iterated = false;
		
		public boolean hasNext() {
			return !iterated;
		}

		@SuppressWarnings("unchecked")
		public X next() {
			if (iterated)
				throw new NoSuchElementException();
			iterated = true;
			return (X) pointedObject;
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}

	}
}
