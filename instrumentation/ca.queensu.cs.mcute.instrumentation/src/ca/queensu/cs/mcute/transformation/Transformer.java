/**
 * 
 */
package ca.queensu.cs.mcute.transformation;

/**
 * @author reza
 *
 *Contributors:
 *reza ahmadi
 *nicolas hili
 *
 */

import org.eclipse.uml2.uml.Package;
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
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.resource.impl.URIMappingRegistryImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.PackageNotFoundException;
import org.eclipse.papyrusrt.codegen.cpp.AnsiCLibraryMetadata;
//import org.eclipse.emf.transaction.RecordingCommand;
//import org.eclipse.emf.transaction.TransactionalEditingDomain;
//import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.papyrusrt.codegen.cpp.profile.RTCppProperties.RTCppPropertiesPackage;
import org.eclipse.papyrusrt.codegen.cpp.profile.RTCppProperties.util.RTCppPropertiesAdapterFactory;
import org.eclipse.papyrusrt.umlrt.profile.UMLRealTime.UMLRealTimePackage;
import org.eclipse.papyrusrt.umlrt.profile.statemachine.UMLRTStateMachines.UMLRTStateMachinesPackage;
import org.eclipse.papyrusrt.umlrt.uml.util.UMLRTResourcesUtil;
import org.eclipse.uml2.common.util.UML2Util;
import org.eclipse.uml2.common.util.UML2Util.EObjectMatcher;
import org.eclipse.uml2.types.TypesPackage;
import org.eclipse.uml2.uml.CallEvent;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Collaboration;
import org.eclipse.uml2.uml.Connector;
import org.eclipse.uml2.uml.Constraint;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.InterfaceRealization;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.OpaqueBehavior;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.PackageableElement;
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
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.ValueSpecification;
import org.eclipse.uml2.uml.Vertex;
import org.eclipse.uml2.uml.internal.resource.UML212UMLLoadImpl;
import org.eclipse.uml2.uml.internal.resource.UMLResourceFactoryImpl;
import org.eclipse.uml2.uml.profile.standard.StandardPackage;
import org.eclipse.uml2.uml.resource.UML212UMLResource;
import org.eclipse.uml2.uml.resource.UMLResource;
import org.eclipse.uml2.uml.util.UMLUtil.StereotypeApplicationHelper;

public class Transformer {

	private String harnessPath;
	// private final String harnessPackageName = "MCUTE";
	private String harnessCapsuleName;
	private String topCapsuleName;
	private String commandsPort;
	private boolean debug = false;

	/**
	 * The resource set containing the instrumented model
	 */
	private ResourceSet resourceSet;

	private String CurrState;

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
	private Resource modelUnderTestResource;

	/**
	 * Output instrumented model
	 */
	private Model modelUnderTest;

	/**
	 * @return the outputModel
	 */
	public Model getOutputModel() {
		return modelUnderTest;
	}

	/**
	 * Capsule to instrument (CUT)
	 */
	private Class cutCapsule;

	private Class harnessCapsule;

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
	public Transformer() throws Exception {
	}

	/**
	 * @param path
	 *            - the path of the input/output file
	 * @return {@link Resource} - a Resource located at the corresponding path
	 */
	// public Resource getResource(String path) {
	// URI uri = URI.createFileURI(path);
	// Resource resource = resourceSet.getResource(uri, true);
	// EcoreUtil.resolveAll(resourceSet);
	// return resource;
	// }
	public Resource getResource(String path) {
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

	public boolean injectHarnessPackage() {

		/*
		 * // loading the harness resources ResourceSet resourceSetHarness = new
		 * ResourceSetImpl(); UMLRTResourcesUtil.init(resourceSetHarness); URI uri =
		 * URI.createFileURI(harnessPath); Resource harnessResource =
		 * resourceSetHarness.getResource(uri, true);
		 * EcoreUtil.resolveAll(resourceSetHarness); if (harnessResource == null) return
		 * false;
		 * 
		 * // getting the harness model // Model harnessModel = (Model) element;
		 * 
		 * // retrieve the harness objects to be imported into the model under test if
		 * (modelUnderTest == null) return false;
		 * 
		 * // copy all harness elements EcoreUtil.Copier modelCopier = new
		 * EcoreUtil.Copier(); Collection<EObject> harnessObjs =
		 * modelCopier.copyAll(harnessResource.getContents());
		 * modelCopier.copyReferences();
		 * 
		 * // set copied elements in goods resources final EList<EObject> contents =
		 * modelUnderTest.eResource().getContents();
		 * 
		 * // for (EObject umlObject : harnessObjs) { // if (umlObject instanceof Model)
		 * { // Model m = (Model) umlObject; // Package newPackage =
		 * UMLFactory.eINSTANCE.createPackage(); // newPackage.setName("HARNESS"); //
		 * modelUnderTest.getPackagedElements().add(newPackage); // for (int i = 0; i <
		 * m.getPackagedElements().size(); i++) { // EObject elem =
		 * m.getPackagedElements().get(i); // if (elem instanceof Class) { //
		 * newPackage.getPackagedElements().add((Class) elem); //
		 * modelUnderTest.getPackagedElements().add((Class)elem); // } // } // } else //
		 * contents.add(umlObject); // }
		 * 
		 * for (EObject umlObject : harnessObjs) { if (umlObject instanceof Model)
		 * modelUnderTest.getPackagedElements().add((Model) umlObject); else
		 * contents.add(umlObject); } // for (EObject obj : harnessObjs) { // if ((obj
		 * instanceof PackageableElement)) { //
		 * modelUnderTest.getPackagedElements().add((PackageableElement) obj); // } // }
		 * 
		 * return true;
		 */

		// loading the harness resources
		Resource harnessResource = getResource(harnessPath);

		// copy all elements
		EcoreUtil.Copier copier = new EcoreUtil.Copier();
		Collection<EObject> umlObjects = copier.copyAll(harnessResource.getContents());
		copier.copyReferences();

		for (EObject umlObject : umlObjects) {
			if (umlObject instanceof Package && ((Package) umlObject).getName().equalsIgnoreCase("mCUTE"))
				modelUnderTest.getPackagedElements().add((Package) umlObject);
			else
				modelUnderTest.eResource().getContents().add(umlObject);
		}

		// Hack for collaborations
		Package mcute_fse = (Package) modelUnderTest.getPackagedElement("mCUTE");
		for (PackageableElement el : mcute_fse.getPackagedElements()) {
			if (el instanceof Package) { // This is a protocol
				Collaboration protocol = (Collaboration) ((Package) el).getPackagedElements().get(0);
				Interface collaborationInterface = protocol.getInterfaceRealizations().get(0).getContract();
				Interface collaborationInterfaceIO = protocol.getInterfaceRealizations().get(0).getContract();
				protocol.getInterfaceRealizations().clear();

				InterfaceRealization collaborationInterfaceRealization = UMLFactory.eINSTANCE
						.createInterfaceRealization();
				InterfaceRealization collaborationInterfaceRealizationIO = UMLFactory.eINSTANCE
						.createInterfaceRealization();

				collaborationInterfaceRealization.setContract(collaborationInterface);
				collaborationInterfaceRealizationIO.setContract(collaborationInterfaceIO);
				protocol.getInterfaceRealizations().add(collaborationInterfaceRealization);
				protocol.getInterfaceRealizations().add(collaborationInterfaceRealizationIO);
			}
		}

		return true;

	}

	public boolean configureTopCapsule() {

		//
		// TransactionalEditingDomain domain =
		// TransactionUtil.getEditingDomain(modelUnderTest);
		// domain.getCommandStack().execute(new RecordingCommand(domain) {
		// @Override
		// protected void doExecute() {

		// retrieve the top capsule
		Class topCapsule = null;
		for (EObject element : modelUnderTest.getPackagedElements()) {
			if (element instanceof Package) {
				Package mcuteObjects = (Package) element;
				for (EObject subelement : mcuteObjects.getPackagedElements()) {
					if (subelement instanceof Class && ((Class) subelement).getName().equals(topCapsuleName)) {
						topCapsule = (Class) subelement;
						break;
					}
				}
			}
		}
		if (topCapsule == null)
			return false;

		// retrieve the harness capsule
		Class thCapsule = null;
		for (EObject element : modelUnderTest.getPackagedElements()) {
			if (element instanceof Package) {
				Package mcuteObjects = (Package) element;
				for (EObject subelement : mcuteObjects.getPackagedElements()) {
					if (subelement instanceof Class && ((Class) subelement).getName().equals(harnessCapsuleName)) {
						thCapsule = (Class) subelement;
						break;
					}
				}
			}
		}
		if (thCapsule == null)
			return false;

		// adding commands port on the CUT
		Port port1 = UmlrtUtil.getPort(thCapsule, "commands");
		Port newPort1 = cutCapsule.createOwnedPort(port1.getName(), port1.getType());
		if (port1.getAppliedStereotypes() != null && port1.getAppliedStereotypes().size() > 0)
			newPort1.applyStereotype(port1.getAppliedStereotypes().get(0));
		newPort1.setIsConjugated(!port1.isConjugated());
		newPort1.setIsBehavior(true); // this is not a relay port

		// adding CUT ports to the harness
		for (Port portCUT : cutCapsule.getOwnedPorts()) {
			Port newPortTH = thCapsule.createOwnedPort(portCUT.getName(), portCUT.getType());
			if (portCUT.getAppliedStereotypes() != null && portCUT.getAppliedStereotypes().size() > 0)
				newPortTH.applyStereotype(portCUT.getAppliedStereotypes().get(0));
			newPortTH.setIsConjugated(!portCUT.isConjugated());
			newPortTH.setIsBehavior(true); // this is not a relay port
		}

		// add an instance of the cut inside the top capsule
		Property harnessPart = UmlrtUtil.getPartByName(topCapsule, "harness");
		Property cut = UMLFactory.eINSTANCE.createProperty();
		cut.setType(cutCapsule);
		cut.setName("cut");
		topCapsule.getOwnedAttributes().add(cut);

		// apply stereotype to cut
		if (harnessPart.getAppliedStereotypes() != null && harnessPart.getAppliedStereotypes().size() > 0)
			cut.applyStereotype(harnessPart.getAppliedStereotypes().get(0));

		// creating the connectors between TH and the cut
		// Property cutPart = UmlrtUtil.getPartByName(topCapsule, "cut");

		// there is one connector on the topCapsule
		Connector con3 = UmlrtUtil.getConnector(topCapsule, "RTConnector3");
		for (Port portTH : thCapsule.getOwnedPorts()) {
			if (portTH.getType().getName() == null)// system port? log, timing,..
				continue;
			Port portCUT = cutCapsule.getOwnedPort(portTH.getName(), portTH.getType());
			if (portCUT != null) {
				UmlrtUtil.createConnector(topCapsule, harnessPart, cut, portTH, portCUT, con3);
			}
		}

		// }// doExecute
		//
		// });
		
		//set top capsule
		EAnnotation annotation = modelUnderTest.getEAnnotation("UMLRT_Default_top");
		if (annotation == null)
			annotation = modelUnderTest.createEAnnotation("UMLRT_Default_top");
		annotation.getDetails().put("top_name", "mCUTE__TOP");

		return true;

	}

	/**
	 * Initialize the model to instrument. This method copies the input model into
	 * the output model. Then, it retrieves the output model.
	 * 
	 * @return {@link Boolean} indicates whether the initialization succeeded or
	 *         failed.
	 */
	public boolean duplicateModelUnderTest() {

		copyFile(inputPath, outputPath);

		modelUnderTestResource = getResource(outputPath);

		if (modelUnderTestResource == null)
			return false;

		Object element = modelUnderTestResource.getContents().get(0);

		if (!(element instanceof Model))
			return false;

		modelUnderTest = (Model) element;

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
	public boolean initCutCapsule(String capsuleName) {

		if (modelUnderTest == null)
			return false;

		EObject eobj = UML2Util.findEObject(modelUnderTest.getPackagedElements(), new EObjectMatcher() {

			@Override
			public boolean matches(EObject el) {
				// TODO Auto-generated method stub
				return (el instanceof Class && ((Class) el).getName().equals(capsuleName));
			}
		});

		if (eobj != null) {
			cutCapsule = (Class) eobj;
			statemachine = (StateMachine) cutCapsule.getOwnedBehaviors().get(0);
		}

		return cutCapsule != null && statemachine != null;

	}

	/**
	 * Initialize the resource set. Uses {@link UMLRTResourcesUtil} to correctly
	 * initialize it for UML-RT models.
	 */
	public void initResourceSet() {
		resourceSet = new ResourceSetImpl();
		UMLRTResourcesUtil.init(resourceSet);
		resourceSet.getPackageRegistry().put(RTCppPropertiesPackage.eNS_URI, RTCppPropertiesPackage.eINSTANCE);

		// resourceSet.getPackageRegistry().put("pathmap://PapyrusC_Cpp_LIBRARIES/AnsiCLibrary.uml",
		// null);

		// added to support AnsiC library model
		// URI primitiveTypesURI =
		// URI.createURI("pathmap://PapyrusC_Cpp_LIBRARIES/AnsiCLibrary.uml");
		// Resource primitiveTypes = resourceSet.createResource(primitiveTypesURI);
		// resourceSet.getPackageRegistry().put("pathmap://PapyrusC_Cpp_LIBRARIES/AnsiCLibrary.uml",
		// primitiveTypes);

		// added to support AnsiC library model
		loadExtraUMLResources();
		// added to support AnsiC library model
	}

	private void loadExtraUMLResources() {

		Map<URI, URI> uriMap = org.eclipse.uml2.uml.resources.util.UMLResourcesUtil
				.initURIConverterURIMap(new HashMap<URI, URI>());
		// load profile from papyrus-rt jar file, the jar file should be set in
		// classpath

		/*
		 * 
		 * String UMLRealTimeSMProfilePath = this.getClass().getClassLoader()
		 * .getResource("umlProfile/UMLRealTimeSM-addendum.profile.uml").toString();
		 * uriMap.put(URI.createURI(
		 * "pathmap://UML_RT_PROFILE/UMLRealTimeSM-addendum.profile.uml"),
		 * URI.createURI(UMLRealTimeSMProfilePath));
		 * 
		 * String RTCppPropertiesProfilePath = this.getClass().getClassLoader()
		 * .getResource("profiles/RTCppProperties.profile.uml").toString();
		 * uriMap.put(URI.createURI("pathmap://UMLRT_CPP/RTCppProperties.profile.uml"),
		 * URI.createURI(RTCppPropertiesProfilePath));
		 * 
		 * String UMLRTProfilePath =
		 * this.getClass().getClassLoader().getResource("umlProfile/uml-rt.profile.uml")
		 * .toString();
		 * uriMap.put(URI.createURI("pathmap://UML_RT_PROFILE/uml-rt.profile.uml"),
		 * URI.createURI(UMLRTProfilePath));
		 * 
		 * Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("uml", new
		 * UMLResourceFactoryImpl());
		 * Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(UMLResource
		 * .FILE_EXTENSION, UMLResource.Factory.INSTANCE);
		 * resourceSet.getPackageRegistry().put(UMLRealTimePackage.eNS_URI,
		 * UMLRealTimePackage.eINSTANCE); //
		 * EPackage.Registry.INSTANCE.put(RTCppPropertiesPackage.eNS_URI, //
		 * RTCppPropertiesPackage.eINSTANCE);
		 * resourceSet.getPackageRegistry().put(UMLRTStateMachinesPackage.eNS_URI,
		 * UMLRTStateMachinesPackage.eINSTANCE);
		 * resourceSet.getPackageRegistry().put(StandardPackage.eNS_URI,
		 * StandardPackage.eINSTANCE);
		 * resourceSet.getPackageRegistry().put(UMLPackage.eNS_URI,
		 * UMLPackage.eINSTANCE);
		 * 
		 * 
		 */
		String ansiCLibrary = "pathmap://PapyrusC_Cpp_LIBRARIES/AnsiCLibrary.uml";
//		File ansiCLibFile = new File(System.getProperty("user.dir") + "/libs2/AnsiCLibrary.uml");
		File ansiCLibFile = new File("/home/vagrant/mcute/lib/AnsiCLibrary.uml");
		// String ansiCLibraryAbsPath=
		// "/Users/rezaahmadi/mcute/instrumentation/ca.queensu.cs.mcute.instrumentation/libs2/AnsiCLibrary.uml";
		uriMap.put(URI.createURI(ansiCLibrary), URI.createURI(ansiCLibFile.getAbsolutePath()));
		/// register packages for UMLRT packages
		URIMappingRegistryImpl.INSTANCE.putAll(uriMap);

		// for AnsiCLibrary resource
		// String ansiCLibrary=
		// "/Users/rezaahmadi/mcute/instrumentation/ca.queensu.cs.mcute.instrumentation/libs2/AnsiCLibrary.uml";
		resourceSet.getPackageRegistry().put(ansiCLibrary, AnsiCLibraryMetadata.INSTANCE);
		// AnsiCLibraryMetadata

		// org.eclipse.papyrusrt.umlrt.profile.UMLRealTime.
		// RTCppPropertiesPackage
		// for AnsiCLibrary resource

		System.out.println("UML resources and Metamodels loaded successfully!");

	}

	public boolean instrumentActionCode() throws IOException, InterruptedException {
		String bidFileName = "/home/vagrant/mcute/tmp/bidSeed";
//		String bidFileName = "/tmp/mcute/bidSeed";
		String strSeed = "";
		int seed = 0;
		// pattern to find lines that represent UML-RT commands
		Pattern commandPattern = Pattern.compile(".*printf.*[#]\\d+[#].*",
				Pattern.CASE_INSENSITIVE | Pattern.DOTALL | Pattern.MULTILINE);
		// init seed with 0
		writeSeed(bidFileName, seed);
		String firstStableState = UmlrtUtil.getInitialState(statemachine).getOutgoings().get(0).getTarget().getName();
		for (Transition t : UmlrtUtil.getAllTransitions(statemachine)) {
			if (t.getTriggers() != null && t.getTriggers().size() > 0) {

				try {

					System.out.print(".");
					// reading seed
					Scanner scanner1 = new Scanner(new File(bidFileName));
					if (scanner1.hasNext()) {
						strSeed = scanner1.nextLine();
						if (strSeed != "")
							seed = Integer.parseInt(strSeed);
					}
					scanner1.close();

					// preparing action code for instrumentation
					String actionCode = "";
					if (t.getName() != null && t.getEffect() != null && t.getEffect() instanceof OpaqueBehavior) {
						actionCode = ((OpaqueBehavior) t.getEffect()).getBodies().get(0);

						// replace log.log with printf
						// actionCode = actionCode.replace("log.log", "printf");

						// comment out message sending commands
						String[] actionCodeLines = actionCode.split("\\r?\\n");
						actionCode = "";
						Map<String, String> umlrtCommands = new HashMap<String, String>();
						int key = 0;
						for (String line : actionCodeLines) {
							if (line.contains(".log") || line.contains(".send()") || line.contains(".sendAt")
									|| line.contains(".informIn")) {
								// line = String.format("printf (\"MCUTESTART %s MCUTEEND\");",
								// line.replace("\"", "\\\"$"));
								umlrtCommands.put(String.valueOf(key), line);
								line = String.format("printf(\"#%d#\");", key);
								key++;
							}
							actionCode += line + "\n";
						}

						String actionCodeParams = "";
						String capsuleAttributes = "";

						for (Property att : getCapsuleAttributes()) {
							if (att.getType() instanceof PrimitiveType) {
								String parameterTypeName = getCompatibleTypeName(((PrimitiveType) att.getType()));
								capsuleAttributes += String.format("%s %s;\n", parameterTypeName, att.getName());
							}
						}
						for (Parameter p : ((CallEvent) t.getTriggers().get(0).getEvent()).getOperation()
								.getOwnedParameters()) {
							String parameterTypeName = getCompatibleTypeName(p.getType());
							actionCodeParams += String.format("%s %s;\n", parameterTypeName, p.getName());
							// for now just integer and char
							if (parameterTypeName.equals("int"))
								actionCodeParams += String.format("CREST_int(%s);", p.getName());
							if (parameterTypeName.equals("char"))
								actionCodeParams += String.format("CREST_char(%s);", p.getName());
							if (parameterTypeName.equals("unsigned int"))
								actionCodeParams += String.format("CREST_unsigned_int(%s);", p.getName());
							if (parameterTypeName.equals("bool"))
								actionCodeParams += String.format("CREST_unsigned_char(%s);", p.getName());
						} // for
						actionCode = String.format(
								"#include <crest.h> \n #include <stdbool.h> \n #include <stdio.h>\n void main(){\n%s%s%s\n}",
								capsuleAttributes, actionCodeParams, actionCode);

//						 System.out.println(String.format( "^^^^^^^^^^^^^^\n%s\n^^^^^^^^^^^^^^^^", actionCode));

						// copy the action code in a file
						// FileInputStream acFile = new FileInputStream("/tmp/crest/actioncode.c");
						final String actionCodeFile = "/home/vagrant/mcute/tmp/actioncode.c";
						FileWriter fw = new FileWriter(actionCodeFile);
						fw.write(actionCode);
						fw.flush();
						fw.close();

						// instrument it
						final String instrumentScriptPath = "/home/vagrant/mcute/bin/mcute_instrument";
						Process instrumentCommand = new ProcessBuilder(instrumentScriptPath, actionCodeFile,
								t.getName()).start();
						int res = instrumentCommand.waitFor();
						if (res != 0) {
							System.out.println(String.format("failed to instrument the transition:%s. Action code:\n%s",
									t.getName(), actionCode));
							return false;
						}

						// reading and customizing the instrumented action code
						String actionCodeInstrumented = "";
						final String instrumentedActioncodeFile = "/home/vagrant/mcute/tmp/actioncode.cil.c";
						Scanner scanner = new Scanner(new File(instrumentedActioncodeFile));
						int c;
						boolean read = false;
						while (scanner.hasNextLine()) {
							String line = scanner.nextLine();
							if (line.contains("globinit_actioncode();")) {
								read = true;
								// only first transition initializes the symbolic execution object
								if (t.getSource().getName().equals(firstStableState))
									line = "__CrestInit();";
								else
									line = "";
							}
							if (line.contains("return;"))
								break;
							Matcher matcher = commandPattern.matcher(line);
							if (matcher.find()) {
								String match = matcher.group(0);
								int i = match.indexOf("#");
								int j = match.lastIndexOf("#");
								String digit = match.substring(i + 1, j);
								line = umlrtCommands.get(digit);
							}
							if (read == true)
								actionCodeInstrumented += line + "\n";
						}
						scanner.close();
						actionCodeInstrumented += "__CrestClearStack(0); \n __CrestWriteSE(); \n";
						// actionCodeInstrumented += "__CrestClearStack(0); \n";
						// updating the transition
						OpaqueBehavior obNew = UMLFactory.eINSTANCE.createOpaqueBehavior();
						obNew.getLanguages().add("C++");
						obNew.getBodies().add(actionCodeInstrumented);
						t.setEffect(obNew);
					} // if

					// update the branch seed
					seed += 100; // todo this must be based on the largest branch id
					writeSeed(bidFileName, seed);
					// FileWriter writer = new FileWriter(new File(bidFileName));
					// writer.write(String.valueOf(seed));
					// writer.flush();
					// writer.close();

				} catch (Exception e) {
					String line = "---------------------";
					System.out.print(
							String.format("%s\nWarning: could not instrument the transition: %s. more info: %s%s\n",
									line, t.getName(), e.getMessage(), line));
				}
			} // if

		} // for
		return true;
	}

	private String getCompatibleTypeName(Type type) {
		String parameterTypeName = "int";
		if (type instanceof PrimitiveType && type.getName() != null) {
			parameterTypeName = type.getName();
			if (parameterTypeName.equals("Integer"))
				parameterTypeName = "int";
			else if (parameterTypeName.equals("Boolean"))
				parameterTypeName = "bool";
			// if it is anything else, e.g., char, leave it as is
		}

		return parameterTypeName;
	}

	private void writeSeed(String bidFileName, int seed) throws IOException {
		FileWriter writer = new FileWriter(new File(bidFileName));
		writer.write(String.valueOf(seed));
		writer.flush();
		writer.close();
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

		long time1 = System.currentTimeMillis();

		if (generatableTransitions > 0) {
			Vertex initialState = UmlrtUtil.getInitialState(statemachine);

			if (initialState == null)
				return false;

			Region region = statemachine.getRegions().get(0);
			List<Port> capsulePorts = UmlrtUtil.getPorts(cutCapsule).stream()
					.filter(p -> !(p.getName().equals(commandsPort))).collect(Collectors.toList());
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
					portMessages = getMessages(cutCapsule, randomPortName);
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
				trig = UmlrtUtil.createTrigger(cutCapsule, randomPortName, randomMessage.getName());
				// //CallEvent object needs to be added to the model resources
				// so it can be serialized
				modelUnderTest.getPackagedElements().add(trig.getEvent());

				// Also an action code for adding the current TestEvent to the TestReportObj
				// if the first transition of the test state machine
				UmlrtUtil.addEffectToTransition(trans, String.format("log.log(\"transition %s action code\");%s\n",
						trans.getName(), generateRandomActioncode(messageParams, trans.getName())));

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

			System.out.println(String.format("state machine generated. info: %d states, %d transitions, %d loc. done.",
					generatableTransitions + 1, generatableTransitions, loc));

		} else
			return false;

		System.out.println(String.format("random state machine generation took %s seconds",
				(System.currentTimeMillis() - time1) / 1000));

		return true;
	}

	int loc = 0;

	private String generateRandomActioncode(List<Parameter> messageParams, String transition) {
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
			if (debug == true)
				System.out.println(
						String.format("action code %s----\n %s \n ------", transition, actionCodeBuilder.toString()));
			return actionCodeBuilder.toString();
		} else
			return "";
	}

	// generates SendNextMessageOpaqueBehavior and
	// SelectNextTransitionOpaqueBehavior inside the harness
	// based on the capsule under test
	private boolean customizeTestHarness() {

		// EObject harnessObj =
		// UML2Util.findEObject(modelUnderTest.getPackagedElements(), new
		// EObjectMatcher() {
		//
		// @Override
		// public boolean matches(EObject el) {
		// // TODO Auto-generated method stub
		// return (el instanceof Class && ((Class)
		// el).getName().equals(harnessCapsuleName));
		// }
		// });
		// if (harnessObj == null)
		// return false;

		EObject harnessPackage = UML2Util.findEObject(modelUnderTest.getPackagedElements(), new EObjectMatcher() {

			@Override
			public boolean matches(EObject el) {
				// TODO Auto-generated method stub
				return (el instanceof Package && ((Package) el).getName().equals("mCUTE"));
			}
		});
		EObject harnessObj = null;
		if (harnessPackage == null)
			return false;
		else {
			for (PackageableElement elem : ((Package) harnessPackage).getPackagedElements()) {
				if (elem.getName().equals(harnessCapsuleName))
					harnessObj = elem;
			}
		}

		if (harnessObj != null && harnessObj instanceof Class) {
			harnessCapsule = (Class) harnessObj;

			// finding the operations inside the harness
			List<Operation> list = null;
			list = harnessCapsule.getAllOperations().stream()
					.filter(prop -> prop.getName().equals("SelectNextTransition")).collect(Collectors.toList());
			if (list == null || list.size() < 1)
				return false;
			Operation selectNextTransitionOperation = list.get(0);
			list = harnessCapsule.getAllOperations().stream().filter(prop -> prop.getName().equals("SendNextMessage"))
					.collect(Collectors.toList());
			if (list == null || list.size() < 1)
				return false;
			Operation sendNextMessageOperation = list.get(0);
			list = harnessCapsule.getAllOperations().stream()
					.filter(prop -> prop.getName().equals("CreateCoverageUtilTable")).collect(Collectors.toList());
			if (list == null || list.size() < 1)
				return false;
			Operation createCoverageUtilTableOperation = list.get(0);

			// constructing SendNextMessageOpaqueBehavior
			// ToDo: fix sending random messages in black-box testing e.g.,:
			// if (Strategy=="black-box"){//send a random message
			OpaqueBehavior sendNextMessageOpaqueBehavior = UMLFactory.eINSTANCE.createOpaqueBehavior();
			sendNextMessageOpaqueBehavior.setName("SendNextMessageOpaqueBehavior");
			sendNextMessageOpaqueBehavior.getLanguages().add("C++");
			String sendNextMessageBody = "";
			/////////////////////////////////////////
			// to generate something like this:
			/////////////////////////////////////////
			/*
			 * if (next_t == "t1") { data.setup(0, 0).send();
			 * log.log("++++++Harness: msg 'setup' sent"); } else if (next_t == "t2") {
			 * data.start(0, 0, 0, 0).send(); log.log("++++++Harness: msg 'start' sent"); }
			 */
			/////////////////////////////////////////
			// ToDo: only for Integers for now
			Map<String, String> messagesCallInfo = new HashMap<String, String>();
			sendNextMessageBody += "vector<value_t> inputs;"; // for random inputs to be written to a file
			sendNextMessageBody += "if (Strategy!=\"black-box\"){ ";
			for (Transition t : statemachine.getRegions().get(0).getTransitions()) {
				try {
					if (t.getName() != null && t.getSource() instanceof State) {
						String port = "", msg = "", params = "";
						if (t.getTriggers() != null && t.getTriggers().size() > 0) {
							port = t.getTriggers().get(0).getPorts().get(0).getName();
							CallEvent ce = (CallEvent) t.getTriggers().get(0).getEvent();
							if (ce.getOperation() == null || msg.equals("timeout")) { // transitions with timeouts do
																						// not
																						// trigger from outside
								continue;
							}
							msg = ce.getOperation().getName();
							String writeInputsScript = "";
							for (Parameter p : ce.getOperation().inputParameters()) {
								int randomParam = new Random().nextInt();
								params += String.format("%d,", randomParam);
								// writeInputsScript += String.format("inputs.push_back(%d);", randomParam);
							}
							if (params != "")
								params = params.substring(0, params.length() - 1);
							String messageCall = String.format("%s.%s(%s).send()", port, msg, params);
							messagesCallInfo.put(String.format("%s.%s", port, msg), messageCall);
							sendNextMessageBody += String.format(
									"if (next_t==\"%s\"){%s \n %s;\n log.log(\"Harness: msg '%s.%s' sent\"); \n}\n",
									t.getName(), writeInputsScript, messageCall, port, msg);
						}
					}
				} catch (Exception e) {
					String line = "---------------------";
					System.out.print(String.format(
							"%s\nError happend, during processing the transition: %s. If that transition triggers by a timeout, just ignore this message. more info: %s%s\n",
							line, t.getName(), e.getMessage(), line));
				}
			}
			sendNextMessageBody += "}else{"; // if strategy is black-box
			sendNextMessageBody += "int x;";
			int c = 0;
			sendNextMessageBody += String.format("x = rand() %% %d + 0;", messagesCallInfo.size());
			for (Map.Entry<String, String> messageCall : messagesCallInfo.entrySet()) {
				sendNextMessageBody += String.format("if (x==%d) {%s; \n log.log(\"Harness: msg '%s' sent\");}", c,
						messageCall.getValue(), messageCall.getKey());
				c++;
			}
			sendNextMessageBody += "}";

			// saving the inputs generated to a file so (in random testing) the action code
			// can restore them from there
			// sendNextMessageBody += "if (Strategy!=\"conc\"){
			// fileutil::writeInputs(\"input\", inputs);}";
			sendNextMessageOpaqueBehavior.getBodies().add(sendNextMessageBody);
			sendNextMessageOperation.getMethods().add(sendNextMessageOpaqueBehavior);
			modelUnderTest.getPackagedElements().add(sendNextMessageOpaqueBehavior);

			// constructing SelectNextTransitionOpaqueBehavior
			OpaqueBehavior selectNextTransitionOpaqueBehavior = UMLFactory.eINSTANCE.createOpaqueBehavior();
			selectNextTransitionOpaqueBehavior.setName("SelectNextTransitionOpaqueBehavior");
			selectNextTransitionOpaqueBehavior.getLanguages().add("C++");
			String selectNextTransitionBody = "vector<string> allTransitions;";
			/////////////////////////////////////////
			// to generate something like this:
			/////////////////////////////////////////
			/*
			 * if (Curr_State == INIT) { // it is possible to run t3 next_t = "t3";
			 * log.log("Curr state: INIT"); } if (Curr_State == SETUP) { // it is possible
			 * to run t4 next_t = "t4"; log.log("Curr state: SETUP"); } // adding the
			 * transition to the list of covered transitions
			 * if(std::find(VisitedTransitions.begin(), VisitedTransitions.end(), next_t) ==
			 * VisitedTransitions.end()){ VisitedTransitions.push_back(next_t); }
			 */
			/////////////////////////////////////////
			// ToDo: only for Integers for now
			List<Transition> allTransitions = new ArrayList<Transition>();
			// selectNextTransitionBody += "if (Strategy!=\"black-box\"){";
			int id = 1;
			for (Vertex v : statemachine.getRegions().get(0).getSubvertices()) {
				String allTransitionsStr = "";
				if (v instanceof State) {
					State s = (State) v;
					List<Transition> outgoings = s.getOutgoings();
					for (Transition t : outgoings) {
						allTransitions.add(t);
						allTransitionsStr += String.format("allTransitions.push_back(\"%s\");\n", t.getName());
					}
					System.out.print(".");
					if (outgoings != null && outgoings.size() > 0) {
						int randomTransitionIdx = new Random().nextInt(outgoings.size());
						// String candidateTransition = outgoings.get(randomTransitionIdx).getName();
						// selectNextTransitionBody += String.format("if (Curr_State == %s){ next_t =
						// \"%s\";\n }",
						// s.getName(), candidateTransition);
						// System.out.println("\ngenerating transition selection for the
						// state:"+s.getName());
						selectNextTransitionBody += String.format(
								"if (Curr_State == %d){  %s\n int idx = rand()%%allTransitions.size(); next_t = allTransitions.at(idx);\n }",
								id, allTransitionsStr);
					}
					id++;
				}
			} // for
				// selectNextTransitionBody += "}else{";
				// selectNextTransitionBody += "std::vector<string> transitions;";
				// for (Transition t : allTransitions) {
				// selectNextTransitionBody += String.format("transitions.push_back(\"%s\");",
				// t.getName());
				// }
				// selectNextTransitionBody += "int irand = rand()%transitions.size();";
				// selectNextTransitionBody+="next_t = transitions.at(irand);";
				// selectNextTransitionBody += "}";

			// if (selectNextTransitionBody != "")
			// selectNextTransitionBody += "if(std::find(VisitedTransitions.begin(),
			// VisitedTransitions.end(), next_t) ==
			// VisitedTransitions.end()){VisitedTransitions.push_back(next_t);}";
			selectNextTransitionOpaqueBehavior.getBodies().add(selectNextTransitionBody);
			selectNextTransitionOperation.getMethods().add(selectNextTransitionOpaqueBehavior);
			modelUnderTest.getPackagedElements().add(selectNextTransitionOpaqueBehavior);

			// constructing CreateCoverageUtilTableOpaqueBehavior
			OpaqueBehavior createCoverageUtilTableOpaqueBehavior = UMLFactory.eINSTANCE.createOpaqueBehavior();
			createCoverageUtilTableOpaqueBehavior.setName("CreateCoverageUtilTableOpaqueBehavior");
			createCoverageUtilTableOpaqueBehavior.getLanguages().add("C++");
			String createCoverageUtilTableBody = "";
			/////////////////////////////////////////
			// to generate something like this:
			/////////////////////////////////////////
			/*
			 * coverage_util* cu1 = new coverage_util(string("t3"));
			 * cu1->initCoverageInfo();
			 * 
			 * coverage_util* cu2 = new coverage_util(string("t4"));
			 * cu2->initCoverageInfo();
			 * 
			 * CoverageUtilTable.insert(pair<string, coverage_util*>("t3",cu1));
			 * CoverageUtilTable.insert(pair<string, coverage_util*>("t4",cu2));
			 */
			/////////////////////////////////////////
			// ToDo: only for Integers for now
			// only for transitions from a stable state
			for (Transition t : statemachine.getRegions().get(0).getTransitions()) {
				if (t.getSource() instanceof State) {
					if (t.getEffect() != null && t.getEffect() instanceof OpaqueBehavior) {
						createCoverageUtilTableBody += String.format(
								"coverage_util* cu1_%s = new coverage_util(string(\"%s\")); \n cu1_%s->initCoverageInfo();",
								t.getName(), t.getName(), t.getName());
						createCoverageUtilTableBody += String.format(
								"CoverageUtilTable.insert(pair<string, coverage_util*>(\"%s\",cu1_%s));", t.getName(),
								t.getName());
					}
				}
			} // for
			if (createCoverageUtilTableBody != "") {
				createCoverageUtilTableOpaqueBehavior.getBodies().add(createCoverageUtilTableBody);
				createCoverageUtilTableOperation.getMethods().add(createCoverageUtilTableOpaqueBehavior);
				modelUnderTest.getPackagedElements().add(createCoverageUtilTableOpaqueBehavior);
			}

			// initializing the next_t, Curr_State, States, and Transitions
			// finding some information about the model to write in the hanress
			String next_t = "";
			Vertex initialState = UmlrtUtil.getInitialState(statemachine);
			Vertex firstStateState = initialState.getOutgoings().get(0).getTarget();
			// currState = firstStateState.getName();
			// CurrState = "1";
			next_t = firstStateState.getOutgoings().get(0).getName();
			int totalStates = UmlrtUtil.getAllVertexes(statemachine).size();
			int totalTransitions = UmlrtUtil.getAllTransitions(statemachine).size();

			Vertex harnessInitState = UmlrtUtil.getStateMachine(harnessCapsule).getRegions().get(0)
					.getSubvertex("Init");
			if (harnessInitState != null) {
				String actionCode = String.format(
						"\n next_t=\"%s\";\n Curr_State = %s;\n States=%d;\n Transitions=%d;\n  ", next_t, CurrState,
						totalStates, totalTransitions);
				UmlrtUtil.addOpaqueBehaviourToState(harnessInitState, actionCode, OpaqueBehaviourPoint.OnEntry);
			} else {
				return false;
			}
			// next_t="t1";
			// Curr_State=INIT;
			// States=3;
			// Transitions=2;

			// List<Property> listAtt =
			// cutCapsule.getAttributes().stream().filter(att->att.getName().equals("next_t")).collect(Collectors.toList());
			// if (listAtt == null || listAtt.size() < 1)
			// return false;
			// Property next_t = listAtt.get(0);
			// Vertex firstState =
			// UmlrtUtil.getInitialState(statemachine).getOutgoings().get(0).getTarget();
			// Transition firstTransition = firstState.getOutgoings().get(0);
			// ValueSpecification firstTransitionValue =
			// UMLFactory.eINSTANCE.createValueSpecificationAction().createValue(firstTransition.getName(),
			// String.class, arg2)
			// next_t.setDefaultValue(firstTransition.getName());

		} else
			return false;

		return true;
	}

	private Collection<Operation> getAllMessages(Class capsule) {
		Collection<Operation> allMessages = new ArrayList<Operation>();
		List<Port> capsulePorts = UmlrtUtil.getPorts(capsule).stream().filter(p -> !(p.getName().equals(commandsPort)))
				.collect(Collectors.toList());
		for (Port p : capsulePorts) {
			List<Operation> portMessages = getMessages(capsule, p.getName());
			allMessages.addAll(portMessages);
		}
		return allMessages;
	}

	/**
	 * Copy a file from one path into another.
	 * 
	 * @param inputPath
	 *            the path of the input file
	 * @param outputPath
	 *            the path of the output file
	 */
	public void copyFile(String inputPath, String outputPath) {

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
	/*
	 * public void createTransitionEnumeration() { Enumeration transitionEnumeration
	 * = UMLFactory.eINSTANCE.createEnumeration();
	 * transitionEnumeration.setName("TRANSITIONS");
	 * transitionEnumeration.createOwnedLiteral("INITIAL");
	 * 
	 * // retrieve all transitions of the statemachine (assuming the state machine
	 * is // flat) Region region = statemachine.getRegions().get(0);
	 * List<Transition> transitions = region.getTransitions();
	 * 
	 * for (int i = 0; i < transitions.size(); i++) { Transition transition =
	 * transitions.get(i); if (transition.getSource() instanceof Pseudostate &&
	 * ((Pseudostate) transition.getSource()).getKind() ==
	 * PseudostateKind.INITIAL_LITERAL) { continue; }
	 * transitionEnumeration.createOwnedLiteral("T" + i); }
	 * 
	 * modelUnderTest.getPackagedElements().add(transitionEnumeration); }
	 */

	/**
	 * Create the state enumeration
	 */
	public void createStateEnumeration() {
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

		modelUnderTest.getPackagedElements().add(stateEnumeration);
	}

	/**
	 * Create the branch enumeration TODO: invoke this method after instrumenting
	 * the code with crest
	 */
	public void createBranchEnumeration() {
		Enumeration branchEnumeration = UMLFactory.eINSTANCE.createEnumeration();
		branchEnumeration.setName("BRANCHES");

		// TODO: how to parse if-then conditions?

		modelUnderTest.getPackagedElements().add(branchEnumeration);
	}

	/**
	 * Create an entry action of a state if no entry action exists yet. Also, add
	 * the cute command at the end of the entry action code. FIXME: both actions
	 * should be separated
	 * 
	 * @param state
	 *            the state to which an entry action code must be created
	 */
	public void createEntryActionIfAny(State state, int id) {
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
		// body += "commands.newState(" + state.getName() + ").send();";
		body += "commands.newState(" + id + ").send();";
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
	public void createEffectIfAny(Transition transition, int index) {
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
		body += "commands.newTransition(T" + index + ").send();";
		effect.getBodies().set(languageIndex, body);
	}

	/**
	 * Create the cute command for every state. Invoke createEntryActionIfAny
	 */
	public void createCuteCommandsForStates() {

		// retrieve all states of the statemachine (assuming states are non-composite)
		Region region = getRegion();
		List<Vertex> vertices = region.getSubvertices();
		Stream<Vertex> states = vertices.stream().filter(v -> v instanceof State);

		int id = 1;
		Pseudostate initialState = UmlrtUtil.getInitialState(statemachine);
		for (Iterator<Vertex> it = states.iterator(); it.hasNext();) {
			State state = (State) it.next();
			createEntryActionIfAny(state, id);
			if (initialState.getOutgoings().get(0).getTarget().getName().equals(state.getName())) {
				CurrState = String.valueOf(id);
			}
			id++;
		}
	}

	/**
	 * Save all variables before executing entry action code.
	 */
	/*
	 * public void saveCurrentAttributesValues() {
	 * 
	 * // retrieve all states of the statemachine (assuming states are
	 * non-composite) Region region = getRegion(); List<Vertex> vertices =
	 * region.getSubvertices(); Stream<Vertex> states = vertices.stream().filter(v
	 * -> v instanceof State); String prebody = "";
	 * 
	 * // For all attribute, prepare the prebody Property[] attrs =
	 * getCapsuleAttributes(); for (int i = 0; i < attrs.length; i++) { Property
	 * attr = attrs[i]; prebody += "restore_".concat(attr.getName()) + " = " +
	 * attr.getName() + ";\n"; }
	 * 
	 * for (Iterator<Vertex> it = states.iterator(); it.hasNext();) { State state =
	 * (State) it.next(); OpaqueBehavior behavior = (OpaqueBehavior)
	 * state.getEntry(); int index = behavior.getLanguages().indexOf("C++"); String
	 * body = behavior.getBodies().get(index); behavior.getBodies().set(index,
	 * prebody + body); } }
	 */

	/**
	 * Restore all variables during transition effect of reset transitions.
	 * 
	 * @param transition
	 *            the transition to instrument
	 */

	public void restoreDefaultAttributesValues(Transition transition) {

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
	/*
	 * public void createCuteCommandsForTransitions() { Region region = getRegion();
	 * List<Transition> transitions = region.getTransitions();
	 * 
	 * for (int i = 0; i < transitions.size(); i++) { Transition transition =
	 * transitions.get(i); if (transition.getSource() instanceof Pseudostate &&
	 * ((Pseudostate) transition.getSource()).getKind() ==
	 * PseudostateKind.INITIAL_LITERAL) { continue; } createEffectIfAny(transition,
	 * i); } }
	 */

	public void createIterationTransitions() {
		Pseudostate init = UmlrtUtil.getInitialState(statemachine);
		Vertex targetState = init.getOutgoings().get(0).getTarget();

		Region region = getRegion();
		// List<Transition> transitions = region.getTransitions();
		List<Vertex> vertexes = region.getSubvertices().stream().filter(v -> !(v instanceof Pseudostate))
				.collect(Collectors.toList());
		int size = vertexes.size();

		for (int i = 0; i < size; i++) {
			// Transition transition = transitions.get(i);
			// if (transition.getSource() instanceof Pseudostate
			// && ((Pseudostate) transition.getSource()).getKind() ==
			// PseudostateKind.INITIAL_LITERAL) {
			// continue;
			// }
			createIterationTransition(targetState, vertexes.get(i), i);

		}
	}

	public void createIterationTransition(Vertex target, Vertex source, int index) {
		// Vertex source = transition.getSource();
		// Vertex target = transition.getTarget();
		Transition iterate = UMLFactory.eINSTANCE.createTransition();
		iterate.setSource(source);
		iterate.setTarget(target);
		iterate.setName("iterate" + index);
		getRegion().getTransitions().add(iterate);

		OpaqueBehavior behavior = UMLFactory.eINSTANCE.createOpaqueBehavior();
		behavior.getLanguages().add("C++");
		behavior.getBodies().add("__CrestWriteSE();");
		iterate.setEffect(behavior);
		Trigger trig = UmlrtUtil.createTrigger(cutCapsule, commandsPort, "iterate");
		if (trig != null)
			iterate.getTriggers().add(trig);
		else
			System.out.println("Note: transition: " + iterate.getName() + " has no trigger!");
		modelUnderTest.getPackagedElements().add(trig.getEvent());
		// restoreDefaultAttributesValues(iterate);
	}

	/**
	 * Duplicate all capsule attributes Invoke getCapsuleAttributes()
	 */
	public void duplicateCapsuleAttributes() {
		Property[] attrs = getCapsuleAttributes();
		for (int i = 0; i < attrs.length; i++) {
			Property attr = attrs[i];
			Property restoreAttr = EcoreUtil.copy(attr);
			restoreAttr.setName("restore_" + attr.getName());
			cutCapsule.getOwnedAttributes().add(restoreAttr);
		}
	}

	/**
	 * Get all capsule's attributes that are primitive. Exclude the ports.
	 * 
	 * @return the list of all primitive attributes of the capsule
	 */
	public Property[] getCapsuleAttributes() {

		if (attributes == null) {
			// Only primitive types for the moment
			Predicate<Property> isNotPort = p -> !(p instanceof Port);
			Predicate<Property> isPrimitive = p -> p.getType() instanceof PrimitiveType;
			attributes = cutCapsule.getOwnedAttributes().stream().filter(isNotPort.and(isPrimitive))
					.toArray(Property[]::new);
		}
		return attributes;
	}

	/**
	 * Save the instrumented model.
	 * 
	 * @throws IOException
	 */
	public void save() throws IOException {
		modelUnderTestResource.save(null);

	}

	private int generatableTransitions;

	/**
	 * Initialise the instrumentation
	 * 
	 * @param args
	 *            the list of args passed to the Java program
	 * @return whether the initialization succeeded or failed
	 * @throws Exception
	 */
	private boolean initialize(String[] args) throws Exception {

		harnessPath = "/home/vagrant/mcute/Harness_UMLRT/mcute_package3.uml";
		// harnessPath = "/Users/rezaahmadi/mcute/Harness_UMLRT/mcute_package3.uml";

		// private final String harnessPackageName = "MCUTE";
		harnessCapsuleName = "mCUTE_Harness";
		topCapsuleName = "mCUTE__TOP";
		commandsPort = "commands";

		// this is for debuggin purposes only
		// the transformer is typically called by mCUTE
		// and the args parameters are sent over by a config file
//		debug = true;
		if (debug) {
			// throw new Exception("Not enough argument. Usage: java Instrumentation -i "
			// + "<input file> -o <output file> -c <capsule name>");
			System.out.println("NOTE: missing input parameters, using defaults...");

			harnessPath = "/Users/rezaahmadi/mcute/Harness_UMLRT/mcute_package3.uml";

			args = new String[10];
			args[0] = "-i";
			// args[1] = "/home/vagrant/MyTests/SoSyM2/emptymodel.uml";
			// args[1] =
			// "/Users/rezaahmadi/Dropbox/Qlab/code/UMLrtModels/MyTests/Present22Jan/MODELS2019/RPS.uml";
			args[1] = "/Users/rezaahmadi/mcute/sample-models/RPS.uml";

			args[2] = "-o";
			// args[3] = "/home/vagrant/MyTests/SoSyM2/modelGen2.uml";
			// args[3] =
			// "/Users/rezaahmadi/Dropbox/Qlab/code/UMLrtModels/MyTests/Present22Jan/MODELS2019/RPS_transformed2.uml";
			args[3] = "/Users/rezaahmadi/mcute/sample-models/RPS_transformed3.uml";

			args[4] = "-c";
			args[5] = "Referee";
			// args[5] = "Cars_Generator";
			// args[6] = "-g";
			// args[7] = "20";

			// -os ${target.os} -ws ${target.ws} -arch ${target.arch} -nl ${target.nl}
			// -consoleLog
			// -i
			// /home/reza/Dropbox/Qlab/code/UMLrtModels/MyTests/SoSyM2/Harness_CA_System3.uml
			// -o
			// /home/reza/Dropbox/Qlab/code/UMLrtModels/MyTests/SoSyM2/Harness_CA_System3__gen.uml
			// -c CA_Main
			// -g 50
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

		boolean result = duplicateModelUnderTest() & initCutCapsule(arguments.get(++c));

		return result;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		boolean res;
		int c = 1;
		long timeMain = System.currentTimeMillis();
		Transformer transformer = new Transformer();
		// HarnessImporter harnessImporter = new
		// HarnessImporter("/home/reza/Dropbox/Qlab/code/MCUTE/Harness_UMLRT/mcute_package.uml");

		if (!transformer.initialize(args)) {
			throw new Exception("Error during initialization. Check the argments");
		}

		// System.out.print("Adding the required references to the capsule... ");
		// res = transformer.addReferencesToCUT();
		// printRes(res, c);
		// c++;

		System.out.print("Making sure all the transitions have names");
		res = transformer.checkTransitionsNames();
		printRes(res, c);
		c++;

		if (transformer.generatableTransitions > 0) {
			res = transformer.generateRandomStateMachine();
			printRes(res, c);
			c++;
		}
		// transformer.getCapsuleAttributes();

		// after a reset
		// System.out.print("Duplicating the capsule's attributes... ");
		// transformer.duplicateCapsuleAttributes();
		// System.out.println("done.");

		// System.out.print("Creating a TRANSITIONS enumeration... ");
		// transformer.createTransitionEnumeration();
		// System.out.println("done.");

		System.out.print("Creating a STATES enumeration... ");
		transformer.createStateEnumeration();
		printRes(true, c);
		c++;

		// System.out.print("Creating a BRANCHES enumeration... ");
		// transformer.createBranchEnumeration();
		// System.out.println("done.");

		System.out.print("Adding commands.newState(STATE).send(); at the end of entry action code... ");
		transformer.createCuteCommandsForStates();
		printRes(true, c);
		c++;

		// System.out.print("Saving current values of capsule attributes...");
		// transformer.saveCurrentAttributesValues();
		// System.out.println("done.");

		// System.out.print("Adding cuteCommands.newTransition(Tx).send(); at the end of
		// entry action code... ");
		// transformer.createCuteCommandsForTransitions();
		// System.out.println("done.");

		// Reza-new-start
		System.out.println("creating new branch points for guards.");
		res = transformer.transformGuards();
		printRes(res, c);
		c++;
		// Reza-new-end

		System.out.println("instrumenting the state machine.");
		res = transformer.instrumentActionCode();
		printRes(res, c);
		c++;

		System.out.print("injecting the harness package inside the model under test... ");
		res = transformer.injectHarnessPackage();
		printRes(res, c);
		c++;

		System.out.print("constructing the top capsule... ");
		res = transformer.configureTopCapsule();
		printRes(res, c);
		c++;

		System.out.println("customizing the test harness for the CUT...");
		res = transformer.customizeTestHarness();
		printRes(res, c);
		c++;

		System.out.print("Creating iteration transitions for every transition of the system... ");
		transformer.createIterationTransitions();
		printRes(true, c);
		c++;

		// Saving the instrumented model
		transformer.save();

		System.out.println(String.format("end! model preparation took %s seconds",
				(System.currentTimeMillis() - timeMain) / 1000));

	}

	private boolean checkTransitionsNames() {
		List<Transition> transitions = UmlrtUtil.getAllTransitions(statemachine);
		int c = 0;
		int i = 0;
		int size = transitions.size() - 1;
		while (i < size) {
			System.out.print(".");
			Transition t = transitions.get(i);
			String newName = "t" + String.valueOf(c);
			if (transitions.stream().anyMatch(item -> item.getName() != null && item.getName().equals(newName))) {
				c++;
				continue;
			}
			if (t.getName() == null || t.getName().equals("")) {
				t.setName(newName);
				c++;
			}
			i++;
		}

		return true;
	}

	private boolean addReferencesToCUT() {

		// EList<Stereotype> allStereotypes = cutCapsule.getApplicableStereotypes();
		// for (Stereotype ss : allStereotypes) {
		// System.out.println(ss.getName() + " , " + ss.getQualifiedName());
		//
		// }

		try {
			Stereotype stereoCapsuleProperties = cutCapsule.getAppliedStereotype("RTCppProperties::CapsuleProperties");
			if (stereoCapsuleProperties == null) {
				stereoCapsuleProperties = cutCapsule.getApplicableStereotype("RTCppProperties::CapsuleProperties");
				cutCapsule.applyStereotype(stereoCapsuleProperties);
			}
			// Object preface = cutCapsule.hasValue(stereoCapsuleProperties,
			// "headerPreface");
			// allStereotypes = cutCapsule.getAppliedStereotypes();
			cutCapsule.setValue(stereoCapsuleProperties, "headerPreface",
					String.format("%s %s %s %s %s %s %s %s", "#include <map>", "#include <assert.h>",
							"#include <vector>", "#include <iostream>", "#include <fstream>", "#include <string>",
							"#include \"libcrest/crest.h\"", "#include <stdbool.h>"));
		} catch (Exception e) {
			return false;
		}

		// }

		return true;
	}

	// Reza-new-start

	private boolean transformGuards() {
		List<Transition> newTransitions = new ArrayList<Transition>();
		int c = -1;
		for (Transition t : statemachine.getRegions().get(0).getTransitions()) {
			c++;
			if (t.getGuard() != null && t.getGuard().getSpecification() != null) {
				String actionCodeStr = "";
				// Constraint constraint = t.getGuard();
				if (t.getEffect() != null) {
					actionCodeStr = ((OpaqueBehavior) t.getEffect()).getBodies().get(0);
				}
				String guardStr = ((OpaqueExpression) (t.getGuard().getSpecification())).getBodies().get(0);
				String newActionCode = String.format("if (%s){%s}", guardStr.replace("return ", "").replace(";", ""),
						actionCodeStr);
				updateTransitionEffect(t, newActionCode);
				t.setGuard(null);

				// choice point
				Vertex choicePoint = region.createSubvertex("", UMLPackage.eINSTANCE.getPseudostate());
				choicePoint.setName("choice" + c);
				((Pseudostate) choicePoint).setKind(PseudostateKind.CHOICE_LITERAL);
				EObject rtPseodostate = (EObject) choicePoint.getApplicableStereotypes().get(0);
				if (rtPseodostate instanceof Stereotype) {
					choicePoint.applyStereotype((Stereotype) rtPseodostate);
				}
				region.getSubvertices().add(choicePoint);
				// modelUnderTest.eResource().getContents().add(choicePoint);

				// new transition from choice point to target state
				Transition newTransition = UMLFactory.eINSTANCE.createTransition();
				newTransition.setSource(choicePoint);
				newTransition.setTarget(t.getTarget());

				// update the new transition
				Constraint newConstraint = newTransition.createGuard("");
				OpaqueExpression guardExpr = UMLFactory.eINSTANCE.createOpaqueExpression();
				guardExpr.getLanguages().add("C++");
				guardExpr.getBodies().add(guardStr);
				newConstraint.setSpecification(guardExpr);
				newTransition.setGuard(newConstraint);
				// modelUnderTest.getPackagedElements().add(newConstraint);
				updateTransitionEffect(newTransition, actionCodeStr);
				// newTransition.setName("choicePoint_" + t.getTarget().getName());
				newTransitions.add(newTransition);

				// from the choice point to the target state
				t.setTarget(choicePoint);

				// second transition from the choice point to the source of t
				Transition secondOptionTransition = UMLFactory.eINSTANCE.createTransition();
				secondOptionTransition.setSource(choicePoint);
				secondOptionTransition.setTarget(t.getSource());
				// region.getTransitions().add(secondOptionTransition);
				newTransitions.add(secondOptionTransition);

				// adding choice point to resources
				// modelUnderTest.getPackagedElements().add((Package) choicePoint);
				// modelUnderTest.eResource().getContents().add(choicePoint);
			}
		}

		// adding the new transitions in the state machine
		for (int i = 0; i < newTransitions.size(); i++) {
			region.getTransitions().add(newTransitions.get(i));
		}
		return true;
	}
	

	public static void updateTransitionEffect(Transition trans, String actionCode) {
		OpaqueBehavior ob = UMLFactory.eINSTANCE.createOpaqueBehavior();
		ob.getLanguages().add("C++");
		ob.getBodies().add(actionCode);
		trans.setEffect(ob);
	}

	// Reza-new-end

	static void printRes(boolean res, int n) {
		if (res)
			System.out.println(n + ". done.");
		else
			System.out.println(n + ". failed!");
	}

}
