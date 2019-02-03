
package ca.queensu.cs.mcute.transformation;

import org.eclipse.uml2.common.util.UML2Util;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.CallEvent;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Collaboration;
import org.eclipse.uml2.uml.Connector;
import org.eclipse.uml2.uml.ConnectorEnd;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.OpaqueBehavior;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Port;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Pseudostate;
import org.eclipse.uml2.uml.PseudostateKind;
import org.eclipse.uml2.uml.RedefinableElement;
import org.eclipse.uml2.uml.Region;
import org.eclipse.uml2.uml.State;
import org.eclipse.uml2.uml.StateMachine;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.StructuredClassifier;
import org.eclipse.uml2.uml.Transition;
import org.eclipse.uml2.uml.Trigger;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.Vertex;
import org.eclipse.uml2.uml.internal.operations.StateMachineOperations;
import org.eclipse.uml2.uml.resource.UMLResource;
import org.eclipse.uml2.uml.util.UMLUtil.StereotypeApplicationHelper;

//import java.util.ArrayList;
import java.util.*;
//import java.awt.event.ItemListener;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.papyrusrt.umlrt.core.utils.CapsulePartUtils;
import org.eclipse.papyrusrt.umlrt.core.utils.CapsuleUtils;
import org.eclipse.papyrusrt.umlrt.core.utils.MessageUtils;
import org.eclipse.papyrusrt.umlrt.core.utils.ProtocolUtils;
import org.eclipse.papyrusrt.umlrt.core.utils.StateMachineUtils;
import org.eclipse.papyrusrt.umlrt.core.utils.UMLRTProfileUtils;
//import org.eclipse.emf.ecore.EObject;
//import org.eclipse.papyrusrt.umlrt.core.utils.CapsuleUtils;
//import org.eclipse.papyrusrt.xtumlrt.common.Capsule;
//import org.eclipse.papyrusrt.xtumlrt.util.XTUMLRTUtil;
//import org.eclipse.papyrusrt.xtumlrt.common.*;
import org.eclipse.papyrusrt.umlrt.profile.UMLRealTime.Capsule;
import org.eclipse.papyrusrt.umlrt.profile.UMLRealTime.CapsulePart;
import org.eclipse.papyrusrt.umlrt.profile.UMLRealTime.RTConnector;

/**
 * @author reza
 *
 */
public class UmlrtUtil {

	public static Class getCapsule(Class uml2Obj) {
		// As Papyrus-RT model elemets are UML2 classes with specific
		// stereotypes, we already have associated UML2 classes
		// ToDo: make sure we do not need another way
		// to retrieve the actual Capsule object
		if (isCapsule(uml2Obj))
			return uml2Obj;
		return null;
	}

	public static boolean isCapsule(Classifier obj) {
		return CapsuleUtils.isCapsule(obj);
		// for (Stereotype s : obj.getAppliedStereotypes()){
		// if (s instanceof Capsule)
		// return true;
		// }
		// return false;
	}

	// ToDo: this must be recursive
	public static List<Property> getCapsuleParts(Class capsule) {
		List<Property> res = new ArrayList<Property>();
		for (Property part : capsule.getParts()) {
			for (Stereotype s : part.getAppliedStereotypes())
				if (s.getName().equals("CapsulePart"))
					res.add(part);
		}

		return res;
		// return XTUMLRTUtil.getAllCapsuleParts(capsule);
	}

	public static boolean isCapsulePart(Property prop) {
		for (Stereotype s : prop.getAppliedStereotypes())
			if (s.getName().equals("CapsulePart"))
				return true;
		return false;
	}

	// static List<Property> parts = new ArrayList<Property>();
	public static void getCapsulePartsRecursive(Class capsule, List<Property> parts) {
		if (capsule == null)
			return;
		// List<Property> tmpParts = new ArrayList<Property>();
		for (Property part : capsule.getParts())
			for (Stereotype s : part.getAppliedStereotypes())
				if (s.getName().equals("CapsulePart")) {
					parts.add(part);
					getCapsulePartsRecursive((Class) part.getType(), parts);
				}
		// parts.addAll(tmpParts);
		// for (Property part : tmpParts)
		// getCapsulePartsRecursive((Class) part.getType(), parts);

		// return parts;
	}

	// static List<Property> parts = new ArrayList<Property>();
	public static void getCapsulePartsWithDependencies(Class capsule, Map<Class, List<Property>> dependencies) {
		if (capsule == null)
			return;
		List<Property> dependents = new ArrayList<Property>();
		dependencies.put(capsule, dependents);
		// List<Property> tmpParts = new ArrayList<Property>();
		for (Property part : capsule.getParts()) {
			for (Stereotype s : part.getAppliedStereotypes()) {
				if (s.getName().equals("CapsulePart")) {
					dependents.add(part);
					getCapsulePartsWithDependencies((Class) part.getType(), dependencies);
				}
			}
		}
	}

	public static StateMachine getStateMachine(Class capsule) {
		// in ibm: capsule.getPrimaryStateMachine().getReferenceTarget();
		if (capsule.getOwnedBehaviors() != null && capsule.getOwnedBehaviors().size() > 0) {
			Behavior b = capsule.getOwnedBehaviors().get(0);
			if (b instanceof StateMachine)
				return (StateMachine) b;
			// throw new Exception("no state machine for capsule");
		}
		return null;
		// throw new Exception("no state machine for capsule");
	}

	public void getAllRedifinedPorts(Capsule capsule) {

	}

	public static Property getPartWithPort(ConnectorEnd end) {
		return end.getPartWithPort();
		// return end.getPartWithPort();
	}

	public static EList<Port> getOwnedPorts(Class capsule) {
		return capsule.getOwnedPorts();
	}

	public static EList<Port> getPorts(Class capsule) {
		return capsule.getOwnedPorts();
	}

	public static Port getPort(Class capsule, String portName) {
		for (Port p : getPorts(capsule)) {
			if (p.getName().equals(portName))
				return p;
		}
		return null;
	}

	public static List<Operation> getInEvents(Port port) {
		return ProtocolUtils.getMessageSetIn((Collaboration) port.getType()).getAllOperations();

	}

	public static List<Operation> getOutEvents(Port port) {
		return ProtocolUtils.getMessageSetOut((Collaboration) port.getType()).getAllOperations();
	}

	public static CallEvent getCallEvent(Operation op) {
		return MessageUtils.getCallEvent(op);
	}

	public static EList<Connector> getConnectors(Class capsule) {
		return capsule.getOwnedConnectors();
	}

	public static Port getLocallyRedefinedPort(Port port, Class capsule) {
		Port portRedefined = null;
		RedefinableElement elemRedefined = capsule.getRedefinedElement(port.getName());
		if (elemRedefined != null && elemRedefined instanceof Port)
			portRedefined = (Port) elemRedefined;

		// otherwise we cannot run the compound symbolic execution
		if (portRedefined == null)
			portRedefined = port;

		return portRedefined;
	}

	public static Region getLocallyRedefinedRegion(Region reg, Class stm) {
		Region regRedefined = null;
		RedefinableElement elemRedefined = stm.getRedefinedElement(reg.getName());
		if (elemRedefined != null && elemRedefined instanceof Region)
			regRedefined = (Region) elemRedefined;
		return regRedefined;
	}

	public static Class selectIfCapsule(Object selected) {
		Resource capsuleResource = null;
		if (selected instanceof Property)
			selected = ((Property) selected).getClass_();
		if (selected instanceof Class) {
			if (selected instanceof Behavior) {
				capsuleResource = ((Class) selected).eContainer().eResource();
				selected = ((Class) selected).eContainer();
			} else {
				capsuleResource = ((Class) selected).eResource();
			}
			return getCapsule((Class) selected); // RTFactory.CapsuleFactory.getCapsule((Class)selected);
		}
		return null;
	}

	// public static Class getCapsuleUnderTest(Object selected) {
	// Class capsule = selectIfCapsule(selected);
	// for (Property part: capsule.getParts()){
	// for (Stereotype s : part.getAppliedStereotypes())
	// if (s.getName().equals("CapsuleUnderTest"))
	// return (Class) part.getType();
	// }
	// return capsule;
	// }

	public static Class getCapsuleByStereotypeName(Object selected, String stereotype) {
		Class capsule = selectIfCapsule(selected);
		List<Property> parts = new ArrayList<Property>();
		getCapsulePartsRecursive(capsule, parts);
		for (Property part : parts) {
			for (Stereotype s : part.getAppliedStereotypes())
				if (s.getName().equals(stereotype))
					return (Class) part.getType();
		}
		return capsule;
		// throw new Exception(String.format("Capsule with Stereotype: %s does
		// not exist.", stereotype));
	}

	public static Property getPartByStereotypeName(Class capsule, String stereotype) {
		List<Property> parts = new ArrayList<Property>();
		getCapsulePartsRecursive(capsule, parts);
		for (Property part : parts) {
			for (Stereotype s : part.getAppliedStereotypes())
				if (s.getName().equals(stereotype))
					return part;
		}
		return null;
	}

	// not recursive, only for mcute purposes
	public static Property getPartByName(Class capsule, String partName) {
		List<Property> parts = capsule.getParts();
		for (Property part : parts) {
			if (part.getName().equals(partName))
				return part;
		}
		return null;
	}

	// public static Class getCapsuleByStereotype(Object selected) {
	// Class capsule = selectIfCapsule(selected);
	// for (Property part: capsule.getParts()){
	// for (Stereotype s : part.getAppliedStereotypes())
	// if (s.getName().equals("TestSuits"))
	// return (Class) part.getType();
	// }
	// return null;
	// }
	//
	// public static List<Class> getCapsuleByStereotype(Class capsule) {
	// List<Class> testProps = new ArrayList<Class>();
	// for (Property part: capsule.getParts()){
	// for (Stereotype s : part.getAppliedStereotypes())
	// if (s.getName().equals("CapsuleTestProperty"))
	// testProps.add((Class) part.getType());
	// }
	// return testProps;
	// }
	//
	// public static List<String> getAllMessages(Class testProp) {
	//
	// List<String> triggers = new ArrayList<String>();
	// for (Interface op: getStateMachine(testProp).getAllImplementedInterfaces()){
	// String o = op.getName();
	// triggers.add(o);
	// }
	//
	// for (Operation op: getStateMachine(testProp).getAllOperations()){
	// String o = op.getName();
	// triggers.add(o);
	// }
	// return triggers;
	// }

	public static List<Transition> getAllTransitions(Class propertyCapsule) {

		List<Transition> transitions = new ArrayList<Transition>();
		StateMachine sm = getStateMachine(propertyCapsule);
		if (sm != null) {
			getAllTransitions(sm.getRegions().get(0), transitions);
		}
		return transitions;
	}

	public static List<Transition> getAllTransitions(StateMachine sm) {

		List<Transition> transitions = new ArrayList<Transition>();
		getAllTransitions(sm.getRegions().get(0), transitions);
		return transitions;
	}

	public static void getAllTransitions(Region reg, List<Transition> res) {
		List<Vertex> vertexes = new ArrayList<Vertex>();
		getAllVertexes(vertexes, reg);
		// List<Pseudostate> pseudostates = getPseudostates(reg);
		for (Vertex v : vertexes) {
			List<Transition> outIns = new ArrayList<Transition>();
			outIns.addAll(v.getIncomings());
			outIns.addAll(v.getOutgoings());

			for (Transition t : outIns) {
				if (!res.contains(t)) {
					res.add(t);
					// System.out.println("transition added: "+t.getName());
				}
			}

			// for nested states
			if (v instanceof State) {
				State s = (State) v;
				EList<Region> stateRegion = s.getRegions();
				if (stateRegion != null && !stateRegion.isEmpty()) {
					getAllTransitions(stateRegion.get(0), res);
				}
			}
		}
	}

	public static List<Vertex> getAllVertexes(Class propertyCapsule) {
		List<Vertex> vertexes = new ArrayList<Vertex>();
		StateMachine sm = UmlrtUtil.getStateMachine(propertyCapsule);
		if (sm == null)
			return vertexes;
		getAllVertexes(vertexes, sm.getRegions().get(0));
		return vertexes;
	}

	public static List<Vertex> getAllVertexes(StateMachine m) {
		List<Vertex> vertexes = new ArrayList<Vertex>();
		getAllVertexes(vertexes, m.getRegions().get(0));
		return vertexes;
	}

	public static void getAllVertexes(List<Vertex> res, Region reg) {
		for (Vertex v : reg.getSubvertices()) {
			res.add(v);
			if (v instanceof State) {
				State s = (State) v;
				List<Region> regs = s.getRegions();
				if (regs != null && regs.size() > 0)
					getAllVertexes(res, regs.get(0));
			}
		}
	}

	public static List<String> getTriggers(Transition t) {
		List<String> triggers = new ArrayList<String>();
		for (Trigger trig : t.getTriggers()) {
			if (trig.getPorts() != null && trig.getPorts().size() > 0) {
				String port = trig.getPorts().get(0).getName();
				String msg = ((CallEvent) trig.getEvent()).getOperation().getName();
				// Object e3 = triggers.get(0).getEvent();
				// List<Element> elems = triggers.get(0).getEvent().eGet(Operation.class);
				triggers.add(String.format("%s.%s", port, msg));
			}
		}
		return triggers;
	}

	public static List<String> getTriggers(Class testProp) {

		// for (Transition t : UmlrtUtil.getAllTransitions(testProp)) {
		// List<Trigger> triggers = t.getTriggers();
		// if (triggers != null && triggers.size() > 0){
		// Object e2 = triggers.get(0).getPorts().get(0);
		// Object e3 = triggers.get(0).getEvent();
		//// List<Element> elems = triggers.get(0).getEvent().eGet(Operation.class);
		// propTriggers.add(triggers.get(0).getEvent().getName());
		// }
		// }

		// ToDo: fix this BS
		List<String> list = null;
		// if (testProp.getName().equals("prop1CruiseControl"))
		// list = new ArrayList<String>() {
		// {
		// add("on");
		// add("engineOff");
		// add("disableControl");
		// add("engineOn");
		// }
		// };
		// else if (testProp.getName().equals("prop2CruiseControl"))
		// list = new ArrayList<String>() {
		// {
		// add("engineOff");
		// add("engineOn");
		// add("on");
		// }
		// };

		return list;
	}

	public static Pseudostate getInitialStateByName(StateMachine stateMachine, String initName) {
		if (stateMachine == null)
			return null;
		for (Vertex v : stateMachine.getRegions().get(0).getSubvertices()) {
			if (v instanceof Pseudostate)
				if (((Pseudostate) v).getKind() == PseudostateKind.INITIAL_LITERAL && v.getName().equals(initName))
					return (Pseudostate) v;
		}
		return null;
	}

	public static Pseudostate getInitialState(StateMachine stateMachine) {
		if (stateMachine == null)
			return null;
		return getInitialState(stateMachine.getRegions().get(0).getSubvertices());
	}

	public static Pseudostate getInitialState(List<Vertex> vertexes) {

		for (Vertex v : vertexes) {
			if (v instanceof Pseudostate)
				if (((Pseudostate) v).getKind() == PseudostateKind.INITIAL_LITERAL)
					return (Pseudostate) v;
		}
		return null;
	}

	// ToDo: remove the above method as the below one support it.
	// public static Pseudostate getConnectionPoint(StateMachine stateMachine,
	// String exitpoint) {
	//
	// for (Pseudostate conn: stateMachine.getConnectionPoints()){
	// if ( ((Pseudostate)conn).getKind() == PseudostateKind.EXIT_POINT_LITERAL &&
	// conn.getName().equals(exitpoint))
	// return (Pseudostate)conn;
	// }
	// return null;
	// }

	// ToDo: remove the above method as the below one support it.
	public static Pseudostate getConnectionPoint(StateMachine stateMachine, String point, PseudostateKind kind) {

		for (Vertex v : stateMachine.getRegions().get(0).getSubvertices()) {
			if (v instanceof State) {
				State state = (State) v;
				if (state.getRegions() != null && state.getRegions().size() > 0) {
					for (Pseudostate conn : state.getConnectionPoints()) {
						if (PseudostateKind.EXIT_POINT_LITERAL == kind) {
							if (((Pseudostate) conn).getKind() == PseudostateKind.EXIT_POINT_LITERAL
									&& conn.getName().equals(point))
								return (Pseudostate) conn;
						} else if (PseudostateKind.ENTRY_POINT_LITERAL == kind) {
							if (((Pseudostate) conn).getKind() == PseudostateKind.ENTRY_POINT_LITERAL
									&& conn.getName().equals(point))
								return (Pseudostate) conn;
						}
					}
				}
			}
		}
		return null;
	}

	public static List<Pseudostate> getPseudostates(Region reg) {
		List<Pseudostate> res = new ArrayList<Pseudostate>();

		for (Vertex v : reg.getSubvertices()) {
			if (v instanceof State) {
				State state = (State) v;
				if (state.getRegions() != null && state.getRegions().size() > 0) {
					for (Pseudostate conn : state.getConnectionPoints()) {
						if (conn.getKind() == PseudostateKind.CHOICE_LITERAL)
							// || conn.getKind() == PseudostateKind.ENTRY_POINT_LITERAL
							// || conn.getKind() == PseudostateKind.EXIT_POINT_LITERAL)
							res.add(conn);
					}
				}
			} else if (v instanceof Pseudostate) {
				Pseudostate conn = (Pseudostate) v;
				if (conn.getKind() == PseudostateKind.CHOICE_LITERAL)
					// || conn.getKind() == PseudostateKind.ENTRY_POINT_LITERAL
					// || conn.getKind() == PseudostateKind.EXIT_POINT_LITERAL)
					res.add((Pseudostate) v);
			}
		}
		return res;
	}

	public static List<Pseudostate> getPseudostates(Class capsule) {
		StateMachine sm = getStateMachine(capsule);
		if (sm == null)
			return null;
		return getPseudostates(sm.getRegions().get(0));
	}

	// e.g. on "TestData" port and "nextState" message
	public static Trigger createTrigger(Class capsule, String portName, String msgName) {

		Trigger trig = UMLFactory.eINSTANCE.createTrigger();
		Port port = UmlrtUtil.getPort(capsule, portName);
		if (port == null)
			return null;
		trig.getPorts().add(port);
		Operation nextStateOp = null;
		Collaboration testingProtocol = (Collaboration) port.getType();
		List<Interface> interfaces = null;
		if (port.isConjugated())
			interfaces = testingProtocol.allUsedInterfaces();
		else
			interfaces = testingProtocol.allRealizedInterfaces();
		for (Interface intr : interfaces) {
			for (Operation opr : intr.getAllOperations()) {
				if (opr.getName().equals(msgName)) {
					nextStateOp = opr; // (Operation)
										// opr.getOperation("nextState",
										// null, null);
					break;
				}
			}
		}

		CallEvent ev = UMLFactory.eINSTANCE.createCallEvent();
		ev.setOperation(nextStateOp);
		trig.setEvent(ev);

		return trig;
	}

	public static void addEffectToTransition(Transition trans, String actionCode) {
		OpaqueBehavior ob = UMLFactory.eINSTANCE.createOpaqueBehavior();
		ob.getLanguages().add("C++");
		if (trans.getEffect() != null && ((OpaqueBehavior) trans.getEffect()).getBodies() != null) {
			actionCode = ((OpaqueBehavior) trans.getEffect()).getBodies().get(0) + actionCode;
		}

		ob.getBodies().add(actionCode);
		trans.setEffect(ob);
	}

	// This function parses the transition's effect body to extract all output
	// messages
	// e.g., messages in this form: port1.msg1().send().
	// ToDo: finish this method
	public static List<String> getTransitionOutMessages(Transition trans) {
		List<String> outMessages = new ArrayList<String>();
		if (trans.getEffect() != null) {

			OpaqueBehavior effect = (OpaqueBehavior) trans.getEffect();
			for (String body : effect.getBodies()) {

			}
		}
		return outMessages;
	}

	public static Vertex getTestableState(StateMachine stateMachine) {

		for (Vertex v : stateMachine.getRegions().get(0).getSubvertices()) {
			if (v.getName() != null && v.getName().equals("TESTABLE")) {
				State s = (State) v;
				Pseudostate entry = s.getConnectionPoint("init_entry");
				if (entry != null)
					return entry.getOutgoings().get(0).getTarget();
			}
		}
		return getInitialState(stateMachine);
	}

	public static void addOpaqueBehaviourToState(Vertex state, String actionCode, OpaqueBehaviourPoint point) {
		OpaqueBehavior ob = UMLFactory.eINSTANCE.createOpaqueBehavior();
		ob.getLanguages().add("C++");
		if (point == OpaqueBehaviourPoint.OnEntry) {
			if (((State) state).getEntry() != null
					&& ((OpaqueBehavior) ((State) state).getEntry()).getBodies() != null) {
				actionCode = ((OpaqueBehavior) ((State) state).getEntry()).getBodies().get(0) + actionCode;
			}

			ob.getBodies().add(actionCode);
			((State) state).setEntry(ob);

		} else { // OpaqueBehaviourPoint.OnExit
			if (((State) state).getExit() != null && ((OpaqueBehavior) ((State) state).getExit()).getBodies() != null) {
				actionCode = ((OpaqueBehavior) ((State) state).getExit()).getBodies().get(0) + actionCode;
			}

			ob.getBodies().add(actionCode);
			((State) state).setExit(ob);
		}
	}

	public static String getGuardStr(Transition t1) {
		if (t1.getGuard() != null && t1.getGuard().getSpecification() != null)
			return t1.getGuard().getSpecification().stringValue();
		return "";
	}

	public static String getActionCodeStr(Transition trans) {
		String ac = "";
		if (trans.getEffect() != null && ((OpaqueBehavior) trans.getEffect()).getBodies() != null) {
			ac = ((OpaqueBehavior) trans.getEffect()).getBodies().get(0);
		}
		return ac;
	}

	public static List<Property> getCapsuleProperties(Class capsule) {
		return capsule.getAllAttributes();
	}

	public static boolean isCapsuleProperty(Class capsule, String propName) {
		return getCapsuleProperties(capsule).stream().anyMatch(prop -> prop.getName().equals(propName));
	}

	public static StateMachine getStateMachine(Object obj) {
		if (obj instanceof Transition)
			return ((Transition) obj).getContainer().getStateMachine();
		else if (obj instanceof State)
			return ((State) obj).getContainer().getStateMachine();
		return null;
		// throw new Exception("object is not a transition nor a state to have a state
		// machine");
	}

	public static Vertex getState(Class cut, String sName) {
		for (Vertex v : getAllVertexes(cut)) {
			if (v.getName().equals(sName))
				return v;
		}
		return null;
	}

	public static void removeTransition(StateMachine sm, Transition t) {
		// List<Transition> outs = t.getTarget().getIncomings();
		// do not remove a transition T if: T.TargetState.getIncomings < 2
		// if (outs!=null && outs.size()>1) {
		if (sm != null) {
			sm.getRegions().get(0).getTransitions().remove(t);
			System.out.println("removed " + t.getName());
		}
		// }
	}

	public static void removeVertex(Class c, Vertex v) {
		// List<Transition> outs = t.getTarget().getIncomings();
		// do not remove a transition T if: T.TargetState.getIncomings < 2
		// if (outs!=null && outs.size()>1) {
		getStateMachine(c).getRegions().get(0).getSubvertices().remove(v);
		// }
	}

	public static void removeTransition(Class c, Transition t) {
		removeTransition(getStateMachine(c), t);
	}

	public static List<Transition> getSelfTransitions(Class capsule) {
		List<Transition> res = new ArrayList<Transition>();

		for (Transition t : getAllTransitions(capsule)) {
			if (t.getSource().equals(t.getTarget()))
				res.add(t);
		}
		return res;
	}

	public static void removeVertexs(Class c, List<Vertex> delVertexes) {
		for (Vertex v : delVertexes) {
			removeVertex(c, v);
		}

	}

	public static boolean isChoicePoint(Vertex source) {
		if (source instanceof Pseudostate) {
			Pseudostate p = (Pseudostate) source;
			if (p.getKind() == PseudostateKind.CHOICE_LITERAL)
				return true;
		}
		return false;
	}

	public static Resource loadSlicingUnitTestModels() {
		Resource res = loadUmlModelFromFile("/Users/rezaahmadi/Dropbox/Qlab/code/UMLrtModels/MyTests/SlicingTest.uml");
		// Resource res =
		// loadResource("/Users/rezaahmadi/Dropbox/Qlab/code/UMLrtModels/Nicolas/Observer/ca.queensu.cs.observer.umlrt/libraries/observer.uml");
		for (EObject e : res.getContents()) {
		}
		return res;
	}

	private static Resource loadUmlModelFromFile(String path) {
		Resource slicingModelsResource = null;
		slicingModelsResource = loadResource(path);
		EcoreUtil.resolveAll(slicingModelsResource);
		return slicingModelsResource;
	}

	private static Resource loadResource(String path) {
		ResourceSet set = new ResourceSetImpl();

		set.getPackageRegistry().put(UMLPackage.eNS_URI, UMLPackage.eINSTANCE);

		set.getResourceFactoryRegistry().getExtensionToFactoryMap().put(UMLResource.FILE_EXTENSION,
				UMLResource.Factory.INSTANCE);

		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(UMLResource.FILE_EXTENSION,
				UMLResource.Factory.INSTANCE);

		Resource res = set.getResource(URI.createFileURI(path), true);
		if (res.isLoaded())
			return res;
		return null;
	}

	public static void setNewEffect(Transition t, String actionCodesStr) {
		OpaqueBehavior ob = UMLFactory.eINSTANCE.createOpaqueBehavior();
		ob.getLanguages().add("C++");
		ob.getBodies().add(actionCodesStr);
		t.setEffect(ob);
	}

	public static boolean isCompositeState(Vertex v) {
		if (!(v instanceof State))
			return false;
		State s = (State) v;
		if (s.getRegions() != null && s.getRegions().size() > 0) {
			return true;
		}
		return false;
	}

	public static boolean hasEmptySm(Vertex v) {
		if (!isCompositeState(v))
			return true;
		State s = (State) v;
		if (s.getRegions() != null && s.getRegions().size() > 0) {
			return UmlrtUtil.getAllTransitions(s.getRegions().get(0).getStateMachine()).size() == 0;
		}
		return false;
	}

	public static List<ConnectorEnd> getConnectorEnds(Class containerCapsule, Property part) {

		List<ConnectorEnd> connectorEnds = new ArrayList<ConnectorEnd>();
		for (Connector con : containerCapsule.getOwnedConnectors()) {
			ConnectorEnd end1 = con.getEnds().get(0);
			ConnectorEnd end2 = con.getEnds().get(1);
			if (end1 != null && end1.getPartWithPort() != null && end1.getPartWithPort().equals(part))
				connectorEnds.add(end1);
			if (end2 != null && end2.getPartWithPort() != null && end2.getPartWithPort().equals(part))
				connectorEnds.add(end2);
		}
		return connectorEnds;
	}

	public static List<ConnectorEnd> getConnectorEnds(Class containerCapsule) {

		List<ConnectorEnd> connectorEnds = new ArrayList<ConnectorEnd>();
		for (Connector con : containerCapsule.getOwnedConnectors()) {
			ConnectorEnd end1 = con.getEnds().get(0);
			ConnectorEnd end2 = con.getEnds().get(1);
			connectorEnds.add(end1);
			connectorEnds.add(end2);
		}
		return connectorEnds;
	}

	public static boolean isCompositeCapsule(Class capsule) {
		List<Property> parts = getCapsuleParts(capsule);
		return (parts != null && parts.size() > 0);
	}

	public static void removeElement(Class capsule, Object dependent) {
		// for (Element elem: capsule.allOwnedElements())
		// System.out.println(elem.get);
		// TODO Auto-generated method stub
		if (dependent != null) {
			if (dependent instanceof Transition)
				removeTransition(capsule, (Transition) dependent);
			else if (dependent instanceof Vertex)
				removeVertex(capsule, (Vertex) dependent);
			else if (dependent instanceof Port)
				getPorts(capsule).remove(dependent);
			else if (dependent instanceof Property) {
				// List<Property> parts = getCapsuleParts(capsule);
				// getCapsuleParts(capsule).remove(dependent);
				capsule.getOwnedAttributes().remove(dependent);
			}
			// List<Element> elems = capsule.getOwnedElements();
			// elems.remove(dependent);

			System.out.println("removed " + dependent);
		}
	}

	public static State getOneStableState(StateMachine stateMachine) {
		// TODO Auto-generated method stub
		for (Vertex v : stateMachine.getRegions().get(0).getSubvertices())
			if (v instanceof State)
				return (State) v;
		return null;
	}

	public static Vertex createNewVertex(String stateName, Region stateMachineRegion, Vertex vertex) {
		// TODO Auto-generated method stub
		Vertex newState = stateMachineRegion.createSubvertex(stateName, vertex.eClass());
		EObject stereotype = (EObject) vertex.getAppliedStereotypes().get(0);
		if (stereotype instanceof Stereotype)
			newState.applyStereotype((Stereotype) stereotype);
		stateMachineRegion.getSubvertices().add(newState);
		return newState;
	}

	public static boolean outGoingsNeedTrigger(Vertex v) {
		// TODO Auto-generated method stub
		// for (Transition t : v.getOutgoings()) {
		// if (t.getSource() instanceof Pseudostate && t.getTarget() instanceof
		// Pseudostate)
		// return false;
		// }
		State s = (State) v;
		return !(s.getRegions() != null && s.getRegions().size() > 0);
		// return true;
	}

	public static boolean stereotypeApplied(Property part, String stereotype) {
		boolean res = false;
		// for (Stereotype s : part.getAppliedStereotypes())
		for (Stereotype s : part.getAppliedStereotypes())
			if (s.getName().equals(stereotype)) {
				res = true;
				break;
			}

		return res;
	}

	public static void createConnector(Class container, Property part1, Property part2, Port port1, Port port2,
			Connector sampleConnector) {
		// TODO Auto-generated method stub
		// RTConnector
		if (part1 != null && part2 != null && port1 != null && port2 != null) {
			String connectorName = String.format("con_%s_%s_%s", port1.getName(), part1.getName(), part2.getName());
			Connector con = container.createOwnedConnector(connectorName);
			ConnectorEnd ce1 = con.createEnd();
			ConnectorEnd ce2 = con.createEnd();
			ce1.setPartWithPort(part1);
			ce1.setRole(port1);
			ce2.setPartWithPort(part2);
			ce2.setRole(port2);
			Stereotype rtConnector = null;
			if (sampleConnector != null && sampleConnector.getAppliedStereotypes() != null
					&& sampleConnector.getAppliedStereotypes().size() > 0) {
				rtConnector = sampleConnector.getAppliedStereotypes().get(0);
			} else {
				rtConnector = con.getApplicableStereotypes().get(0); // con.getApplicableStereotype("RTConnector");
			}
			if (rtConnector != null) {
				con.applyStereotype(rtConnector);
			}
			container.getOwnedConnectors().add(con);
		}
	}

	public static boolean capsuleHasPort(Class testHarnessCapsule, String name) {
		for (Port p : testHarnessCapsule.getOwnedPorts())
			if (p.getName().equals(name))
				return true;
		return false;
	}

	public static Connector getConnector(Class topCapsule, String name) {
		// TODO Auto-generated method stub
		for (Element elem : topCapsule.getOwnedElements()) {
			if (elem instanceof Connector && ((Connector) elem).getName().equals(name))
				return (Connector) elem;
		}
		return null;
	}

	// private void printTest(TestData test){
	// //printing tests
	// System.out.println(String.format("%s: %s", test.getName(), test.toString()));
	// }
}
