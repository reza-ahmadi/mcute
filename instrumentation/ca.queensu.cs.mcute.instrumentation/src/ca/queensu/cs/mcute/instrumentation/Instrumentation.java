/******************************************************************************
File: Instrumentation.java
Description: Instrument a UML-RT capsule under test for concolic testing.
Author: Nicolas Hili <hili@cs.queensu.ca>
Contributors:
******************************************************************************/

package ca.queensu.cs.mcute.instrumentation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Stream;

import javax.xml.stream.util.StreamReaderDelegate;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceFactoryImpl;
import org.eclipse.papyrusrt.umlrt.uml.util.UMLRTResourcesUtil;
import org.eclipse.uml2.common.util.UML2Util;
import org.eclipse.uml2.common.util.UML2Util.EObjectMatcher;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Collaboration;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.Event;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.OpaqueBehavior;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.Port;
import org.eclipse.uml2.uml.PrimitiveType;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Pseudostate;
import org.eclipse.uml2.uml.PseudostateKind;
import org.eclipse.uml2.uml.Region;
import org.eclipse.uml2.uml.State;
import org.eclipse.uml2.uml.StateMachine;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.Transition;
import org.eclipse.uml2.uml.Trigger;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.Vertex;

public class Instrumentation {

	/**
	 * The resource set containing the instrumented model
	 */
	private ResourceSet resourceSet;

	/**
	 * Input path of the model to instrument
	 */
	private String inputPath;

	/**
	 * Output path of the instrumented model
	 */
	private String outputPath;

	/**
	 * Output resource
	 */
	private Resource outputResource;

	/**
	 * Output instrumented model
	 */
	private Model outputModel;

	/**
	 * Capsule to instrument (CUT)
	 */
	private Class capsule;

	/**
	 * The state machine of the CUT
	 */
	private StateMachine statemachine;

	/**
	 * The region of the state machine
	 */
	private Region region;

	/**
	 * The list of capsule attribute
	 */
	private Property[] attributes;

	/**
	 * @constructor Initialize and perform the instrumentation
	 */
	public Instrumentation() throws Exception {
	}

	/**
	 * @param path
	 *            - the path of the input/output file
	 * @return {@link Resource} - a Resource located at the corresponding path
	 */
	private Resource getResource(String path) {
		URI uri = URI.createFileURI(path);
		Resource resource = resourceSet.getResource(uri, true);
		EcoreUtil.resolveAll(resourceSet);
		return resource;
	}

	/**
	 * Helper that returns the unique region of a UML-RT state machine
	 * 
	 * @return the unique region of the UML-RT state machine
	 */
	public Region getRegion() {
		if (region == null)
			region = statemachine.getRegions().get(0);

		return region;
	}

	/**
	 * Initialize the model to instrument. This method copies the input model into
	 * the output model. Then, it retrieves the output model.
	 * 
	 * @return {@link Boolean} indicates whether the initialization succeeded or
	 *         failed.
	 */
	private boolean initOutputModel() {

		copyFile(inputPath, outputPath);

		outputResource = getResource(outputPath);

		if (outputResource == null)
			return false;

		Object element = outputResource.getContents().get(0);

		if (!(element instanceof Model))
			return false;

		outputModel = (Model) element;

		return true;
	}

	/**
	 * Retireve the capsule to instrument.
	 * 
	 * @param capsuleName
	 *            - the name of the capsule to instrument
	 * @return {@link Boolean} indicates whether the capsule was correctly
	 *         initialized.
	 */
	private boolean initCapsule(String capsuleName) {

		if (outputModel == null)
			return false;

		EObject eobj = UML2Util.findEObject(outputModel.getPackagedElements(), new EObjectMatcher() {

			@Override
			public boolean matches(EObject el) {
				// TODO Auto-generated method stub
				return (el instanceof Class && ((Class) el).getName().equals(capsuleName));
			}
		});

		if (eobj != null) {
			capsule = (Class) eobj;
			statemachine = (StateMachine) capsule.getOwnedBehaviors().get(0);
		}

		return capsule != null && statemachine != null;

	}

	/**
	 * Initialize the resource set. Uses {@link UMLRTResourcesUtil} to correctly
	 * initialize it for UML-RT models.
	 */
	private void initResourceSet() {
		resourceSet = new ResourceSetImpl();
		UMLRTResourcesUtil.init(resourceSet);

	}

	/**
	 * Copy a file from one path into another.
	 * 
	 * @param inputPath
	 *            the path of the input file
	 * @param outputPath
	 *            the path of the output file
	 */
	private void copyFile(String inputPath, String outputPath) {

		InputStream in = null;
		OutputStream out = null;
		try {

			// create output directory if it doesn't exist
			File dir = new File(outputPath);

			in = new FileInputStream(inputPath);
			out = new FileOutputStream(outputPath);

			byte[] buffer = new byte[1024];
			int read;
			while ((read = in.read(buffer)) != -1) {
				out.write(buffer, 0, read);
			}
			in.close();
			in = null;

			// write the output file (You have now copied the file)
			out.flush();
			out.close();
			out = null;

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Create the transition enumeration
	 */
	private void createTransitionEnumeration() {
		Enumeration transitionEnumeration = UMLFactory.eINSTANCE.createEnumeration();
		transitionEnumeration.setName("TRANSITIONS");
		transitionEnumeration.createOwnedLiteral("INITIAL");

		// retrieve all transitions of the statemachine (assuming the state machine is
		// flat)
		Region region = statemachine.getRegions().get(0);
		List<Transition> transitions = region.getTransitions();

		for (int i = 0; i < transitions.size(); i++) {
			Transition transition = transitions.get(i);
			if (transition.getSource() instanceof Pseudostate
					&& ((Pseudostate) transition.getSource()).getKind() == PseudostateKind.INITIAL_LITERAL) {
				continue;
			}
			transitionEnumeration.createOwnedLiteral("T" + i);
		}

		outputModel.getPackagedElements().add(transitionEnumeration);
	}

	/**
	 * Create the state enumeration
	 */
	private void createStateEnumeration() {
		Enumeration stateEnumeration = UMLFactory.eINSTANCE.createEnumeration();
		stateEnumeration.setName("STATES");

		// retrieve all states of the statemachine (assuming states are non-composite)
		Region region = statemachine.getRegions().get(0);
		List<Vertex> vertices = region.getSubvertices();
		Stream<Vertex> states = vertices.stream().filter(v -> v instanceof State);

		for (Iterator<Vertex> it = states.iterator(); it.hasNext();) {
			Vertex state = it.next();
			stateEnumeration.createOwnedLiteral(state.getName());
		}

		outputModel.getPackagedElements().add(stateEnumeration);
	}

	/**
	 * Create the branch enumeration TODO: invoke this method after instrumenting
	 * the code with crest
	 */
	private void createBranchEnumeration() {
		Enumeration branchEnumeration = UMLFactory.eINSTANCE.createEnumeration();
		branchEnumeration.setName("BRANCHES");

		// TODO: how to parse if-then conditions?

		outputModel.getPackagedElements().add(branchEnumeration);
	}

	/**
	 * Create an entry action of a state if no entry action exists yet. Also, add
	 * the cute command at the end of the entry action code. FIXME: both actions
	 * should be separated
	 * 
	 * @param state
	 *            the state to which an entry action code must be created
	 */
	private void createEntryActionIfAny(State state) {
		OpaqueBehavior entry;
		int languageIndex = -1;
		String body = "";
		if (state.getEntry() != null && state.getEntry() instanceof OpaqueBehavior) {
			entry = (OpaqueBehavior) state.getEntry();
			languageIndex = entry.getLanguages().indexOf("C++");
			if (languageIndex == -1) {
				entry.getLanguages().add("C++");
				entry.getBodies().add("");
				languageIndex = entry.getLanguages().size() - 1;
			} else {
				body = entry.getBodies().get(languageIndex);
			}
		} else {
			entry = UMLFactory.eINSTANCE.createOpaqueBehavior();
			entry.getLanguages().add("C++");
			entry.getBodies().add("");
			languageIndex = 0;
			state.setEntry(entry);
		}
		body += "cuteCommands.newState(" + state.getName() + ").send();";
		entry.getBodies().set(languageIndex, body);
	}

	/**
	 * Create a transition effect if no effect exists yet. Also, add the cute
	 * command at the end of the transition effect. FIXME: both actions should be
	 * separated
	 * 
	 * @param transition
	 *            the transition to which an effect must be created
	 * @param index
	 *            the index of the transition
	 */
	private void createEffectIfAny(Transition transition, int index) {
		OpaqueBehavior effect;
		int languageIndex = -1;
		String body = "";
		if (transition.getEffect() != null && transition.getEffect() instanceof OpaqueBehavior) {
			effect = (OpaqueBehavior) transition.getEffect();
			languageIndex = effect.getLanguages().indexOf("C++");
			if (languageIndex == -1) {
				effect.getLanguages().add("C++");
				effect.getBodies().add("");
				languageIndex = effect.getLanguages().size() - 1;
			} else {
				body = effect.getBodies().get(languageIndex);
			}
		} else {
			effect = UMLFactory.eINSTANCE.createOpaqueBehavior();
			effect.getLanguages().add("C++");
			effect.getBodies().add("");
			languageIndex = 0;
			transition.setEffect(effect);
		}
		body += "cuteCommands.newTransition(T" + index + ").send();";
		effect.getBodies().set(languageIndex, body);
	}

	/**
	 * Create the cute command for every state. Invoke createEntryActionIfAny
	 */
	private void createCuteCommandsForStates() {

		// retrieve all states of the statemachine (assuming states are non-composite)
		Region region = getRegion();
		List<Vertex> vertices = region.getSubvertices();
		Stream<Vertex> states = vertices.stream().filter(v -> v instanceof State);

		for (Iterator<Vertex> it = states.iterator(); it.hasNext();) {
			State state = (State) it.next();
			createEntryActionIfAny(state);
		}
	}

	/**
	 * Save all variables before executing entry action code.
	 */
	private void saveCurrentAttributesValues() {

		// retrieve all states of the statemachine (assuming states are non-composite)
		Region region = getRegion();
		List<Vertex> vertices = region.getSubvertices();
		Stream<Vertex> states = vertices.stream().filter(v -> v instanceof State);
		String prebody = "";

		// For all attribute, prepare the prebody
		Property[] attrs = getCapsuleAttributes();
		for (int i = 0; i < attrs.length; i++) {
			Property attr = attrs[i];
			prebody += "restore_".concat(attr.getName()) + " = " + attr.getName() + ";\n";
		}

		for (Iterator<Vertex> it = states.iterator(); it.hasNext();) {
			State state = (State) it.next();
			OpaqueBehavior behavior = (OpaqueBehavior) state.getEntry();
			int index = behavior.getLanguages().indexOf("C++");
			String body = behavior.getBodies().get(index);
			behavior.getBodies().set(index, prebody + body);
		}
	}

	/**
	 * Restore all variables during transition effect of reset transitions.
	 * 
	 * @param transition
	 *            the transition to instrument
	 */
	private void restoreCurrentAttributesValues(Transition transition) {

		String prebody = "";

		// For all attribute, prepare the prebody
		Property[] attrs = getCapsuleAttributes();
		for (int i = 0; i < attrs.length; i++) {
			Property attr = attrs[i];
			prebody += attr.getName() + " = " + "restore_".concat(attr.getName()) + ";\n";
		}

		OpaqueBehavior behavior = (OpaqueBehavior) transition.getEffect();
		int index = behavior.getLanguages().indexOf("C++");
		String body = behavior.getBodies().get(index);
		behavior.getBodies().set(index, prebody + body);
	}

	/**
	 * Create the cute command for every transition. Invoke createEffectIfAny
	 */
	private void createCuteCommandsForTransitions() {

		// retrieve all transitions of the statemachine (assuming states are
		// non-composite)
		Region region = getRegion();
		List<Transition> transitions = region.getTransitions();

		for (int i = 0; i < transitions.size(); i++) {
			Transition transition = transitions.get(i);
			if (transition.getSource() instanceof Pseudostate
					&& ((Pseudostate) transition.getSource()).getKind() == PseudostateKind.INITIAL_LITERAL) {
				continue;
			}
			createEffectIfAny(transition, i);
		}
	}

	/**
	 * Create an opposite transition for the transition passed as parameter
	 * 
	 * @param transition
	 *            the transition from which an opposite transition will be created
	 * @param index
	 *            the index of the transition
	 */
	private void createOppositeTransition(Transition transition, int index) {
		Vertex source = transition.getSource();
		Vertex target = transition.getTarget();
		Transition opposite = UMLFactory.eINSTANCE.createTransition();
		opposite.setSource(target);
		opposite.setTarget(source);
		opposite.setName("reset" + index);
		getRegion().getTransitions().add(opposite);

		OpaqueBehavior behavior = UMLFactory.eINSTANCE.createOpaqueBehavior();
		behavior.getLanguages().add("C++");
		behavior.getBodies().add("");
		opposite.setEffect(behavior);
		restoreCurrentAttributesValues(opposite);
	}

	/**
	 * Create an opposite transition for every external transition but the initial
	 * transition of the CUT Invoke createOppositeTransition(Transition transition,
	 * int index)
	 */
	private void createOppositeTransitions() {
		// retrieve all transitions of the statemachine (assuming states are
		// non-composite)
		Region region = getRegion();
		List<Transition> transitions = region.getTransitions();
		int size = transitions.size();

		for (int i = 0; i < size; i++) {
			Transition transition = transitions.get(i);
			if (transition.getSource() instanceof Pseudostate
					&& ((Pseudostate) transition.getSource()).getKind() == PseudostateKind.INITIAL_LITERAL) {
				continue;
			}
			createOppositeTransition(transition, i);

		}
	}

	/**
	 * Duplicate all capsule attributes Invoke getCapsuleAttributes()
	 */
	private void duplicateCapsuleAttributes() {
		Property[] attrs = getCapsuleAttributes();
		for (int i = 0; i < attrs.length; i++) {
			Property attr = attrs[i];
			Property restoreAttr = EcoreUtil.copy(attr);
			restoreAttr.setName("restore_" + attr.getName());
			capsule.getOwnedAttributes().add(restoreAttr);
		}
	}

	/**
	 * Get all capsule's attributes that are primitive. Exclude the ports.
	 * 
	 * @return the list of all primitive attributes of the capsule
	 */
	private Property[] getCapsuleAttributes() {

		if (attributes == null) {
			// Only primitive types for the moment
			Predicate<Property> isNotPort = p -> !(p instanceof Port);
			Predicate<Property> isPrimitive = p -> p.getType() instanceof PrimitiveType;
			attributes = capsule.getOwnedAttributes().stream().filter(isNotPort.and(isPrimitive))
					.toArray(Property[]::new);
		}
		return attributes;
	}

	private Integer generatableTransitions;

	/**
	 * Initialise the instrumentation
	 * 
	 * @param args
	 *            the list of args passed to the Java program
	 * @return whether the initialization succeeded or failed
	 * @throws Exception
	 */
	private boolean init(String[] args) throws Exception {
		if (args.length < 6) {
			// throw new Exception("Not enough argument. Usage: java Instrumentation -i "
			// + "<input file> -o <output file> -c <capsule name>");
			System.out.println("NOTE: missing input parameters, using defaults...");
			args = new String[10];
			args[0] = "-i";
			args[1] = "/Users/rezaahmadi/Dropbox/Qlab/code/UMLrtModels/MyTests/SoSyM2/modelGen.uml";
			args[2] = "-o";
			args[3] = "/Users/rezaahmadi/Dropbox/Qlab/code/UMLrtModels/MyTests/SoSyM2/modelGen2.uml";
			args[4] = "-c";
			args[5] = "Capsule1";
			args[6] = "-g";
			args[7] = "5";
		}

		List<String> arguments = Arrays.asList(args);

		int i = arguments.indexOf("-i");
		int o = arguments.indexOf("-o");
		int c = arguments.indexOf("-c");
		if (i == -1 || o == -1 || c == -1) {
			throw new Exception("Missing argument. Usage: java Instrumentation -i "
					+ "<input file> -o <output file> -c <capsule name>");
		}

		int g = arguments.indexOf("-g");
		if (g != -1) {
			generatableTransitions = Integer.parseInt(arguments.get(++g));
		}

		inputPath = arguments.get(++i);
		outputPath = arguments.get(++o);

		initResourceSet();

		boolean result = initOutputModel() & initCapsule(arguments.get(++c));

		if (generatableTransitions > 0)
			result = generateRandomStateMachine() & result;

		return result;
	}

	public boolean instrumentActionCode() throws IOException {
		for (Transition t : statemachine.getRegions().get(0).getTransitions()) {
			String actionCode = "";
			if (t.getEffect() != null && t.getEffect() instanceof OpaqueBehavior) {
				actionCode = ((OpaqueBehavior) t.getEffect()).getBodies().get(0);
				
				//copy the action code in a file
//				FileInputStream acFile = new FileInputStream("/tmp/crest/actioncode.c");
				FileWriter fw = new FileWriter("/tmp/crest/actioncode.c");
				fw.write(actionCode);
				fw.flush();
				fw.close();
				
				//instrument it
				//ToDo
				
				//read the new action code
//				FileReader fr = new FileReader("/tmp/crest/actioncode.cil.c");
				String newActioncode="";
//				Scanner scanner = new Scanner("/tmp/crest/actioncode.cil.c");
				FileReader fr = new FileReader("/tmp/crest/actioncode.cil.c");
				int c;
				while ((c = fr.read())!=-1) {
					newActioncode+=(char)c;
				}
				fr.close();
				
				//updating the action code
				actionCode=newActioncode;
				OpaqueBehavior obNew = UMLFactory.eINSTANCE.createOpaqueBehavior();
				obNew.getLanguages().add("C++");
				obNew.getBodies().add(actionCode);
				t.setEffect(obNew);
			}
		}
		return true;
	}

	public List<Operation> getMessages(Class capsule, String portName) {
		List<Operation> messages = new ArrayList<Operation>();
		Port port = UmlrtUtil.getPort(capsule, portName);
		Collaboration testingProtocol = (Collaboration) port.getType();
		List<Interface> interfaces = null;
		if (port.isConjugated())
			interfaces = testingProtocol.allUsedInterfaces();
		else
			interfaces = testingProtocol.allRealizedInterfaces();
		for (Interface intr : interfaces) {
			for (Operation opr : intr.getAllOperations()) {
				messages.add(opr); // (Operation)
			}
		}

		if (messages.size() == 0) {
			// List<Operation> ops = testingProtocol.getAllOperations();
			// List<Interface> ints = testingProtocol.getAllImplementedInterfaces();
			// List<Property> atts =testingProtocol.getAllAttributes();

			boolean isBeh = port.isBehavior();
			if (isBeh && port.getName().startsWith("tim")) {// it is timer probably
				Operation opTime = UMLFactory.eINSTANCE.createOperation();
				opTime.setName("timeout");
				messages.add(opTime);
			}
		}

		return messages;
	}

	private boolean generateRandomStateMachine() {
		// Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("xml", new
		// XMLResourceFactoryImpl());

		if (generatableTransitions > 0) {
			Vertex initialState = UmlrtUtil.getInitialState(statemachine);

			if (initialState == null)
				return false;

			Region region = statemachine.getRegions().get(0);
			List<Port> capsulePorts = UmlrtUtil.getPorts(capsule);
			if (capsulePorts == null || capsulePorts.size() == 0)
				return false;

			Vertex sourceState = initialState.getOutgoings().get(0).getTarget();
			if (sourceState == null)
				return false;

			// List<EObject> capsuleContents = capsule.eContents();

			for (int i = 0; i < generatableTransitions; i++) {

				// for every input signal in a test input

				// int nodeCounter = 1;
				Transition trans = null;
				State targetState = null;

				// target state
				Vertex newState = region.createSubvertex("__test__node__" + i, UMLPackage.eINSTANCE.getState()); // UMLFactory.eINSTANCE.createState();
				targetState = (State) newState;

				// ToDo: apply RTState stereotype
				EObject rtState = (EObject) sourceState.getAppliedStereotypes().get(0);
				if (rtState instanceof Stereotype)
					targetState.applyStereotype((Stereotype) rtState);
				// targetState.setName(testInput +
				// "_n_testingnode");
				region.getSubvertices().add(targetState);

				// if event is not timeout, then an input
				// signal is added to the test state
				// getting a port
				List<Operation> portMessages = null;
				String randomPortName = "";
				Operation randomMessage = null;
				List<Parameter> messageParams = null;
				while (portMessages == null || portMessages.size() == 0) {
					int randPortIdx = new Random().nextInt(capsulePorts.size());
					Port randomPort = capsulePorts.get(randPortIdx);
					randomPortName = randomPort.getName();
					portMessages = getMessages(capsule, randomPortName);
					if (portMessages.size() > 0) {
						int randMsgIdx = new Random().nextInt(portMessages.size());
						randomMessage = portMessages.get(randMsgIdx);
						messageParams = randomMessage.getOwnedParameters();
						break;
					}
				}
				if (randomMessage == null || randomPortName == "")
					return false;

				String stateEntryStr = "";

				// adding an entry code to report about the upcoming test & tell about the
				// upcoming test
				// only for the first state of the test

				// String params = testInput.getParamtersString();
				// State Entry code
				// stateEntryStr = String.format("std::cout<<\"*** Test Harness: %s.%s(%s) ***\"
				// << std::endl;\n "
				// + "%s.%s(%s).send();\n", port, msg, params, port, msg, params);
				// adding an action code to record the event
				stateEntryStr += String.format("log.log(\"CUT is in state: %s\");\n", targetState.getName());

				// else {
				// stateEntryStr = String.format("std::cout<<\"*** Test Harness: waiting for
				// timer[%s] to time out ***\" << std::endl;\n", port);
				// //adding an action code to record the event
				// stateEntryStr += String.format("TE.setEvent(\"WAITING_FOR_%s.%s\");", port,
				// msg);
				// }
				// adding the opaque behavior
				UmlrtUtil.addOpaqueBehaviourToState(targetState, stateEntryStr, OpaqueBehaviourPoint.OnEntry);
				if (randomMessage.getName().equals("timeout")) {// needs to set the timer
					String timerSetStr = String.format("%s.informIn(UMLRTTimespec(0,10));", randomPortName);
					UmlrtUtil.addOpaqueBehaviourToState(sourceState, timerSetStr, OpaqueBehaviourPoint.OnEntry);

				}
				/*
				 * OpaqueBehavior stateEntry = UMLFactory.eINSTANCE.createOpaqueBehavior();
				 * stateEntry.getLanguages().add("C++");
				 * stateEntry.getBodies().add(stateEntryStr); //
				 * setStateEntryInTransaction(targetState, // stateEntry);
				 * targetState.setEntry(stateEntry);
				 */

				// creating transition
				trans = region.createTransition("t" + i);// UMLFactory.eINSTANCE.createTransition();
				// trans = testRegion.createTransition("_t_" + nodeCounter);//
				// UMLFactory.eINSTANCE.createTransition();

				// creating a trigger
				// if this is the first transition of the test we add nextTest else nextState
				Trigger trig = null;
				trig = UmlrtUtil.createTrigger(capsule, randomPortName, randomMessage.getName());
				// //CallEvent object needs to be added to the model resources
				// so it can be serialized
				outputModel.getPackagedElements().add(trig.getEvent());

				// Also an action code for adding the current TestEvent to the TestReportObj
				// if the first transition of the test state machine
				UmlrtUtil.addEffectToTransition(trans, String.format("log.log(\"transition %s action code\");%s\n",
						trans.getName(), generateRandomActioncode(messageParams)));

				// ToDo: use UML-RT Facade objects
				// UMLRTFactory.createTransition(trans);
				// UMLRTTransition tt = UMLRTFactory.createTransition(transition);

				// nodeCounter++;
				// trans.setName(testInput + "_t_testingnode");
				region.getTransitions().add(trans);
				trans.getTriggers().add(trig);
				// System.out.println(trig.getEvent().eResource());
				// System.out.println(trig.eResource());
				// System.out.println(trig.getPorts().get(0).getType().eResource());
				// join source and target states
				trans.setSource(sourceState);
				trans.setTarget(targetState);

				// adding new trigger and state
				// testHarnessSm.getRegions().get(0).getSubvertices().add(targetState);
				// testHarnessSm.getRegions().get(0).getTransitions().add(trans);

				// set the current target state as source
				// state
				sourceState = targetState;

				// trig.getEvent().eContainer().eContents().add(trig.getEvent());

			}
		} else
			return false;
		return true;
	}

	int loc = 0;

	private String generateRandomActioncode(List<Parameter> messageParams) {
		// TODO Auto-generated method stub
		if (messageParams != null && messageParams.size() > 0) {
			StringBuilder actionCodeBuilder = new StringBuilder();
			String endIf = "";
			for (Parameter msgParam : messageParams) {
				String paramName = msgParam.getName();
				int randNum2 = new Random().nextInt(Integer.MAX_VALUE);
				int randNum1 = new Random().nextInt(randNum2);
				actionCodeBuilder
						.append(String.format("if(%s > %d && %s <=%d){", paramName, randNum1, paramName, randNum2));
				actionCodeBuilder.append(String.format("%s+= %d;", paramName, randNum1));
				actionCodeBuilder.append(String.format("%s*= %d;", paramName, randNum2));
				endIf += "}else{}";
				loc += 7;
			}
			actionCodeBuilder.append(endIf);
			return actionCodeBuilder.toString();
		} else
			return "";
	}

	/**
	 * Save the instrumented model.
	 * 
	 * @throws IOException
	 */
	private void save() throws IOException {
		outputResource.save(null);

	}

	// private void ii() {
	// log.log("transition t14 action code");
	// if (p1 > 362712335 && p1 <= 373378718) {
	// p1 += 362712335;
	// p1 *= 373378718;
	// if (CopyOf_p1_1 > 112829125 && CopyOf_p1_1 <= 577734218) {
	// CopyOf_p1_1 += 112829125;
	// CopyOf_p1_1 *= 577734218;
	// if (CopyOf_p1_2 > 33561401 && CopyOf_p1_2 <= 58432050) {
	// CopyOf_p1_2 += 33561401;
	// CopyOf_p1_2 *= 58432050;
	// if (CopyOf_p1_3 > 993550817 && CopyOf_p1_3 <= 1004916880) {
	// CopyOf_p1_3 += 993550817;
	// CopyOf_p1_3 *= 1004916880;
	// if (CopyOf_p1_4 > 825353711 && CopyOf_p1_4 <= 829136907) {
	// CopyOf_p1_4 += 825353711;
	// CopyOf_p1_4 *= 829136907;
	// } else {
	// }
	// } else {
	// }
	// } else {
	// }
	// } else {
	// }
	// } else {
	// }
	//
	// }

	public static void main(String[] args) throws Exception {
		Instrumentation instrumentation = new Instrumentation();

		if (!instrumentation.init(args)) {
			throw new Exception("Error during initialization. Check the argments");
		}
		//
		// instrumentation.getCapsuleAttributes();
		//
		// // Step 2: Duplicate capsule's attributes so the capsule state can be restore
		// after a reset
		// System.out.print("Duplicating the capsule's attributes... ");
		// instrumentation.duplicateCapsuleAttributes();
		// System.out.println("done.");
		//
		// // Step 3: Create a TRANSITIONS enumeration
		// System.out.print("Creating a TRANSITIONS enumeration... ");
		// instrumentation.createTransitionEnumeration();
		// System.out.println("done.");
		//
		// // Step 4: Create a STATES enumeration
		// System.out.print("Creating a STATES enumeration... ");
		// instrumentation.createStateEnumeration();
		// System.out.println("done.");
		//
		// // Step 5: Create a BRANCHES enumeration
		// System.out.print("Creating a BRANCHES enumeration... ");
		// instrumentation.createBranchEnumeration();
		// System.out.println("done.");
		//
		// // Step 6: Create cute commands for every state
		// System.out.print("Adding cuteCommands.newState(STATE).send(); at the end of
		// entry action code... ");
		// instrumentation.createCuteCommandsForStates();
		// System.out.println("done.");
		//
		// // Step 6a: Create entry code to save current values of capsule attributes
		// System.out.print("Saving current values of capsule attributes...");
		// instrumentation.saveCurrentAttributesValues();
		// System.out.println("done.");
		//
		// // Step 7: Create cute commands for every transition
		// System.out.print("Adding cuteCommands.newTransition(Tx).send(); at the end of
		// entry action code... ");
		// instrumentation.createCuteCommandsForTransitions();
		// System.out.println("done.");
		//
		// // Step 8: Create an opposite transition to every transition
		// System.out.print("Creating an opposite transition for every transition of the
		// system... ");
		// instrumentation.createOppositeTransitions();
		// System.out.println("done.");

		System.out.println("instrumenting the state machine");
		instrumentation.instrumentActionCode();
		System.out.println("done.");

		// Saving the instrumented model
		instrumentation.save();

		System.out.println("done!");
		System.out.println(String.format("model info: %d states, %d transitions, %d loc",
				instrumentation.generatableTransitions + 1, instrumentation.generatableTransitions,
				instrumentation.loc));

	}
}
