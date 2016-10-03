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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import junit.framework.TestCase;

public class PairTest extends TestCase {
	
	public void testPairCreation() {
		Pair<Integer, String> p = Pair.makePair(0, "test");
		Pair<Integer, String> p2 = 
				new Pair<Integer, String>(0, "test");
		Pair<Integer, String> p3 = new Pair<Integer, String>();
		p3.setFirst(0);
		p3.setSecond("test");
		
		// creation
		assertNotNull(p);
		assertNotNull(p.getFirst());
		assertNotNull(p.getSecond());
		assertNotNull(p2);
		assertNotNull(p2.getFirst());
		assertNotNull(p2.getSecond());
		assertNotNull(p3);
		assertNotNull(p3.getFirst());
		assertNotNull(p3.getSecond());
		assertNotSame(p, p2);
		assertNotSame(p, p3);
		assertNotSame(p2, p3);
		
		// getters
		assertEquals(p.getFirst(), new Integer(0));
		assertEquals(p2.getFirst(), new Integer(0));
		assertEquals(p3.getFirst(), new Integer(0));
		assertEquals(p.getSecond(), "test");
		assertEquals(p2.getSecond(), "test");
		assertEquals(p3.getSecond(), "test");
		
		assertEquals(p.getFirst(), p2.getFirst());
		assertEquals(p.getSecond(), p2.getSecond());
		assertEquals(p.getFirst(), p3.getFirst());
		assertEquals(p.getSecond(), p3.getSecond());
		assertEquals(p2.getFirst(), p3.getFirst());
		assertEquals(p2.getSecond(), p3.getSecond());
	}
	
	public void testPairFromVarargs() {
		// pair from array
		Pair<String, String[]> pFromArr = 
				Pair.makePairFromArray("a", "b", "c");
		assertNotNull(pFromArr);
		assertEquals(pFromArr.getFirst(), "a");
		assertTrue(Arrays.equals(pFromArr.getSecond(), 
				new String[] {"b", "c"}));
		
		pFromArr = Pair.makePairFromArray("a", "b");
		assertNotNull(pFromArr);
		assertEquals(pFromArr.getFirst(), "a");
		assertTrue(Arrays.equals(pFromArr.getSecond(), 
				new String[] {"b"}));
		
		pFromArr = Pair.makePairFromArray("a");
		assertNotNull(pFromArr);
		assertEquals(pFromArr.getFirst(), "a");
		assertNull(pFromArr.getSecond());
	}
	
	public void testPairWrappers() {
		Pair<Integer, Integer> p = Pair.makePair(0, 0);
		
		Pair<Integer, Integer> imp = Pair.immutablePair(p);
		assertNotSame(p, imp);
		assertEquals(p, imp);
		assertEquals(imp, p);
		
		try {
			imp.setFirst(1);
		} catch (Exception e) {
			assertEquals(e.getClass(), UnsupportedOperationException.class);
		}
		assertEquals(imp.getFirst(), (Integer)0);
		
		Pair<Integer, Integer> syncPair = Pair.synchronizedPair(p);
		assertNotSame(p, syncPair);
		assertEquals(p, syncPair);
		assertEquals(syncPair, p);
	}

	public void testPairOperations() {
		Pair<Integer, Integer> p = Pair.makePair(0, 0);
		
		for (Object i : p) {
			assertEquals(i, 0);
		}
		
		for (Iterator<Integer> it = Pair.typedIterator(p); 
				it.hasNext();) {
			Integer i = it.next();
			assertEquals(i, (Integer)0);
		}
		
		for (Integer i : Pair.asList(p)) {
			assertEquals(i, (Integer)0);
		}
		
		List<Pair<Integer, Integer>> pList = 
				new ArrayList<Pair<Integer, Integer>>();
		pList.add(Pair.makePair(0, 1));
		pList.add(Pair.makePair(0, 1));
		pList.add(Pair.makePair(0, 1));
		pList.add(Pair.makePair(0, 1));
		assertEquals(pList.size(), 4);
		
		for (Pair<Integer, Integer> pp : pList) {
			assertNotNull(pp);
		}
		
		for (Integer i : Pair.listIteratorFirst(pList)) {
			assertEquals(i, (Integer)0);
		}
		
		for (Integer i : Pair.listIteratorSecond(pList)) {
			assertEquals(i, (Integer)1);
		}
	}
}
