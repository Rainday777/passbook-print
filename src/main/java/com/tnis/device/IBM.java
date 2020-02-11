package com.tnis.device;

public class IBM {
	
	static {
        try {     	
        	System.loadLibrary("WNT47X2");
        	
        } catch (UnsatisfiedLinkError ignore) {
        	ignore.printStackTrace();
        }
        try {
        	System.loadLibrary("WNTSFPRT");	
			
        }catch (UnsatisfiedLinkError ignore) {
        	ignore.printStackTrace();
        }
        try {      	
			System.loadLibrary("IBMImp");
        }catch (UnsatisfiedLinkError ignore) {
        	ignore.printStackTrace();
        }
    }
	
	public IBM(){
		//System.loadLibrary("IBMImp");
	}

	// The native keyword is required by the compiler
	// to indicate that the function is implemented
	// in a shared library somewhere.
	public native boolean ibmInitialize();//Call DLL
	
	public native void ibmAddBuffer(String data,int size);//Call DLL

	public native boolean ibmPrint(int timeOut);//Call DLL\
	
	public native short ibmPrintByteArray(byte[] data);//Call DLL	
	
	public native void ibmPrint();//Call DLL

	public native void ibmClose();//Call DLL
	
	public native boolean ibmCheckDevice();//Call DLL

	public native short  ibmBeep();//Call DLL

	public native short  ibmPrintData();//Call DLL

	public native short  ibmEject();//Call DLL

	public native short  ibmPaperCheck(int timeout);//Call DLL
	
	// Code within the static section is executed
	// when this class is initialized. Since part of
	// the functionality of this class resides in
	// a shared library, it must be loaded at runtime
	// when this class is initialized. 

//	static 
//	{
//		// The shared library that contains the implementation
//		// of the native methods is loaded here. The name of the
//		// shared library file is arbitrary, but it is a good
//		// idea to give it a meaningful name.
//		System.loadLibrary("IBMImp");
//	}

}