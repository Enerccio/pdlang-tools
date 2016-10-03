package cz.upol.prf.vanusanik.pdlang.tools;

import junit.framework.Test;
import junit.framework.TestSuite;

public class UtilsTests {

	public static Test suite() {
		TestSuite suite = new TestSuite(UtilsTests.class.getName());
		//$JUnit-BEGIN$
		suite.addTestSuite(PairTest.class);
		suite.addTestSuite(PointerTest.class);
		//$JUnit-END$
		return suite;
	}

}
