package rvm;
import java.io.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;
import java.lang.ref.*;
import com.runtimeverification.rvmonitor.java.rt.*;
import com.runtimeverification.rvmonitor.java.rt.ref.*;
import com.runtimeverification.rvmonitor.java.rt.table.*;
import com.runtimeverification.rvmonitor.java.rt.tablebase.AbstractIndexingTree;
import com.runtimeverification.rvmonitor.java.rt.tablebase.SetEventDelegator;
import com.runtimeverification.rvmonitor.java.rt.tablebase.TableAdopter.Tuple2;
import com.runtimeverification.rvmonitor.java.rt.tablebase.TableAdopter.Tuple3;
import com.runtimeverification.rvmonitor.java.rt.tablebase.IDisableHolder;
import com.runtimeverification.rvmonitor.java.rt.tablebase.IMonitor;
import com.runtimeverification.rvmonitor.java.rt.tablebase.DisableHolder;
import com.runtimeverification.rvmonitor.java.rt.tablebase.TerminatedMonitorCleaner;
import java.util.concurrent.atomic.AtomicInteger;

final class HasNextMonitor_Set extends com.runtimeverification.rvmonitor.java.rt.tablebase.AbstractMonitorSet<HasNextMonitor> {

	HasNextMonitor_Set(){
		this.size = 0;
		this.elements = new HasNextMonitor[4];
	}
	final void event_hasnext(Iterator i) {
		int numAlive = 0 ;
		for(int i_1 = 0; i_1 < this.size; i_1++){
			HasNextMonitor monitor = this.elements[i_1];
			if(!monitor.isTerminated()){
				elements[numAlive] = monitor;
				numAlive++;

				final HasNextMonitor monitorfinalMonitor = monitor;
				monitor.Prop_1_event_hasnext(i);
				if(monitorfinalMonitor.Prop_1_Category_match) {
					monitorfinalMonitor.Prop_1_handler_match();
				}
			}
		}
		for(int i_1 = numAlive; i_1 < this.size; i_1++){
			this.elements[i_1] = null;
		}
		size = numAlive;
	}
	final void event_next(Iterator i) {
		int numAlive = 0 ;
		for(int i_1 = 0; i_1 < this.size; i_1++){
			HasNextMonitor monitor = this.elements[i_1];
			if(!monitor.isTerminated()){
				elements[numAlive] = monitor;
				numAlive++;

				final HasNextMonitor monitorfinalMonitor = monitor;
				monitor.Prop_1_event_next(i);
				if(monitorfinalMonitor.Prop_1_Category_match) {
					monitorfinalMonitor.Prop_1_handler_match();
				}
			}
		}
		for(int i_1 = numAlive; i_1 < this.size; i_1++){
			this.elements[i_1] = null;
		}
		size = numAlive;
	}
}

class HasNextMonitor extends com.runtimeverification.rvmonitor.java.rt.tablebase.AbstractAtomicMonitor implements Cloneable, com.runtimeverification.rvmonitor.java.rt.RVMObject {
	protected Object clone() {
		try {
			HasNextMonitor ret = (HasNextMonitor) super.clone();
			ret.pairValue = new AtomicInteger(pairValue.get());
			return ret;
		}
		catch (CloneNotSupportedException e) {
			throw new InternalError(e.toString());
		}
	}

	static final int Prop_1_transition_hasnext[] = {2, 2, 2, 3};;
	static final int Prop_1_transition_next[] = {1, 1, 0, 3};;

	volatile boolean Prop_1_Category_match = false;

	private AtomicInteger pairValue;

	HasNextMonitor() {
		this.pairValue = new AtomicInteger(this.calculatePairValue(-1, 0) ) ;

	}

	@Override public final int getState() {
		return this.getState(this.pairValue.get() ) ;
	}
	@Override public final int getLastEvent() {
		return this.getLastEvent(this.pairValue.get() ) ;
	}
	private final int getState(int pairValue) {
		return (pairValue & 3) ;
	}
	private final int getLastEvent(int pairValue) {
		return (pairValue >> 2) ;
	}
	private final int calculatePairValue(int lastEvent, int state) {
		return (((lastEvent + 1) << 2) | state) ;
	}

	private final int handleEvent(int eventId, int[] table) {
		int nextstate;
		while (true) {
			int oldpairvalue = this.pairValue.get() ;
			int oldstate = this.getState(oldpairvalue) ;
			nextstate = table [ oldstate ];
			int nextpairvalue = this.calculatePairValue(eventId, nextstate) ;
			if (this.pairValue.compareAndSet(oldpairvalue, nextpairvalue) ) {
				break;
			}
		}
		return nextstate;
	}

	final boolean Prop_1_event_hasnext(Iterator i) {
		{}

		int nextstate = this.handleEvent(0, Prop_1_transition_hasnext) ;
		this.Prop_1_Category_match = nextstate == 1;

		return true;
	}

	final boolean Prop_1_event_next(Iterator i) {
		{}

		int nextstate = this.handleEvent(1, Prop_1_transition_next) ;
		this.Prop_1_Category_match = nextstate == 1;

		return true;
	}

	final void Prop_1_handler_match (){
		{
			System.out.println("next called without hasNext!");
		}

	}

	final void reset() {
		this.pairValue.set(this.calculatePairValue(-1, 0) ) ;

		Prop_1_Category_match = false;
	}

	// RVMRef_i was suppressed to reduce memory overhead

	//alive_parameters_0 = [Iterator i]
	boolean alive_parameters_0 = true;

	@Override
	protected final void terminateInternal(int idnum) {
		int lastEvent = this.getLastEvent();

		switch(idnum){
			case 0:
			alive_parameters_0 = false;
			break;
		}
		switch(lastEvent) {
			case -1:
			return;
			case 0:
			//hasnext
			//alive_i
			if(!(alive_parameters_0)){
				RVM_terminated = true;
				return;
			}
			break;

			case 1:
			//next
			//alive_i
			if(!(alive_parameters_0)){
				RVM_terminated = true;
				return;
			}
			break;

		}
		return;
	}

	public static int getNumberOfEvents() {
		return 2;
	}

	public static int getNumberOfStates() {
		return 4;
	}

}

public final class HasNextRuntimeMonitor implements com.runtimeverification.rvmonitor.java.rt.RVMObject {
	private static com.runtimeverification.rvmonitor.java.rt.map.RVMMapManager HasNextMapManager;
	static {
		HasNextMapManager = new com.runtimeverification.rvmonitor.java.rt.map.RVMMapManager();
		HasNextMapManager.start();
	}

	// Declarations for the Lock
	static final ReentrantLock HasNext_RVMLock = new ReentrantLock();
	static final Condition HasNext_RVMLock_cond = HasNext_RVMLock.newCondition();

	private static boolean HasNext_activated = false;

	// Declarations for Indexing Trees
	private static Object HasNext_i_Map_cachekey_i;
	private static HasNextMonitor HasNext_i_Map_cachevalue;
	private static final MapOfMonitor<HasNextMonitor> HasNext_i_Map = new MapOfMonitor<HasNextMonitor>(0) ;

	public static int cleanUp() {
		int collected = 0;
		// indexing trees
		collected += HasNext_i_Map.cleanUpUnnecessaryMappings();
		return collected;
	}

	// Removing terminated monitors from partitioned sets
	static {
		TerminatedMonitorCleaner.start() ;
	}
	// Setting the behavior of the runtime library according to the compile-time option
	static {
		RuntimeOption.enableFineGrainedLock(false) ;
	}

	public static final void hasnextEvent(Iterator i) {
		HasNext_activated = true;
		while (!HasNext_RVMLock.tryLock()) {
			Thread.yield();
		}

		CachedWeakReference wr_i = null;
		MapOfMonitor<HasNextMonitor> matchedLastMap = null;
		HasNextMonitor matchedEntry = null;
		boolean cachehit = false;
		if ((i == HasNext_i_Map_cachekey_i) ) {
			matchedEntry = HasNext_i_Map_cachevalue;
			cachehit = true;
		}
		else {
			wr_i = new CachedWeakReference(i) ;
			{
				// FindOrCreateEntry
				MapOfMonitor<HasNextMonitor> itmdMap = HasNext_i_Map;
				matchedLastMap = itmdMap;
				HasNextMonitor node_i = HasNext_i_Map.getNodeEquivalent(wr_i) ;
				matchedEntry = node_i;
			}
		}
		// D(X) main:1
		if ((matchedEntry == null) ) {
			if ((wr_i == null) ) {
				wr_i = new CachedWeakReference(i) ;
			}
			// D(X) main:4
			HasNextMonitor created = new HasNextMonitor() ;
			matchedEntry = created;
			matchedLastMap.putNode(wr_i, created) ;
		}
		// D(X) main:8--9
		final HasNextMonitor matchedEntryfinalMonitor = matchedEntry;
		matchedEntry.Prop_1_event_hasnext(i);
		if(matchedEntryfinalMonitor.Prop_1_Category_match) {
			matchedEntryfinalMonitor.Prop_1_handler_match();
		}

		if ((cachehit == false) ) {
			HasNext_i_Map_cachekey_i = i;
			HasNext_i_Map_cachevalue = matchedEntry;
		}

		HasNext_RVMLock.unlock();
	}

	public static final void nextEvent(Iterator i) {
		HasNext_activated = true;
		while (!HasNext_RVMLock.tryLock()) {
			Thread.yield();
		}

		CachedWeakReference wr_i = null;
		MapOfMonitor<HasNextMonitor> matchedLastMap = null;
		HasNextMonitor matchedEntry = null;
		boolean cachehit = false;
		if ((i == HasNext_i_Map_cachekey_i) ) {
			matchedEntry = HasNext_i_Map_cachevalue;
			cachehit = true;
		}
		else {
			wr_i = new CachedWeakReference(i) ;
			{
				// FindOrCreateEntry
				MapOfMonitor<HasNextMonitor> itmdMap = HasNext_i_Map;
				matchedLastMap = itmdMap;
				HasNextMonitor node_i = HasNext_i_Map.getNodeEquivalent(wr_i) ;
				matchedEntry = node_i;
			}
		}
		// D(X) main:1
		if ((matchedEntry == null) ) {
			if ((wr_i == null) ) {
				wr_i = new CachedWeakReference(i) ;
			}
			// D(X) main:4
			HasNextMonitor created = new HasNextMonitor() ;
			matchedEntry = created;
			matchedLastMap.putNode(wr_i, created) ;
		}
		// D(X) main:8--9
		final HasNextMonitor matchedEntryfinalMonitor = matchedEntry;
		matchedEntry.Prop_1_event_next(i);
		if(matchedEntryfinalMonitor.Prop_1_Category_match) {
			matchedEntryfinalMonitor.Prop_1_handler_match();
		}

		if ((cachehit == false) ) {
			HasNext_i_Map_cachekey_i = i;
			HasNext_i_Map_cachevalue = matchedEntry;
		}

		HasNext_RVMLock.unlock();
	}

}
