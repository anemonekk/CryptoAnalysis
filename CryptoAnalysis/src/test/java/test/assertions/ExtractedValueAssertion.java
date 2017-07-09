package test.assertions;

import java.util.Map.Entry;

import com.google.common.collect.Multimap;

import crypto.typestate.CallSiteWithParamIndex;
import soot.Unit;
import soot.Value;
import test.Assertion;

public class ExtractedValueAssertion implements Assertion {
	private Unit stmt;
	private int index;
	private boolean satisfied;
	public ExtractedValueAssertion(Unit stmt, int index) {
		this.stmt = stmt;
		this.index = index;
	}
	
	public void computedValues(Multimap<CallSiteWithParamIndex, Unit> collectedValues){
		for(Entry<CallSiteWithParamIndex, Unit> e: collectedValues.entries()){
			if(e.getKey().getStmt().equals(stmt) && e.getKey().getIndex() == index)
				satisfied = true;
		}
	}
	
	@Override
	public boolean isSatisfied() {
		return satisfied;
	}
	@Override
	public boolean isImprecise() {
		return false;
	}

	@Override
	public String toString() {
		return "Did not extract parameter with index: " + index + " @ " + stmt;
	}
}
