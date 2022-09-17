#include "winx.h"
#if defined(_WIN32) || defined(WIN32)
#include <windows.h>
#else
#include <X11/Xlib.h>
#include <X11/Xatom.h>
#include <unistd.h>
#include <stdlib.h>

Window *list(Display* disp,unsigned long *len){
	Atom prop=XInternAtom(disp,"_NET_ACTIVE_WINDOW",False);
	Atom type;
	int form;
	unsigned long remain;
	unsigned char *list;
	XGetWindowProperty(disp,XDefaultRootWindow(disp),prop,0,1025,False,XA_WINDOW,&type,&form,len,&remain,&list);
	
	return (Window*)list;
}

char *name(Display* disp,Window window){
	Atom pro=XInternAtom(disp,"WM_NAME",False),type;
	int form;
	unsigned long remain;
	unsigned char* list;
	XGetWindowProperty(disp,window,prop,0,1024,False,AnyPropertyType,&type,&form,&len,&remain,&list);
	
	return (char*)list;
}

Display* globalDisplay=NULL;

int catcher(Display* disp,XErrorEvent *xe){
	
}

#endif


JNIEXPORT jobject JNICALL Java_com_wfh_emplos_platform_CrossPlatformWindow_getActiveWindow(JNIEnv *env, jclass clz){
	  jmethodID constructor_id=NULL;
	

	
	jclass winClass=NULL;
	winClass=env->FindClass("Lcom/wfh/emplos/platform/Window;");
	if (winClass==NULL)return winClass;
	
	constructor_id=env->GetMethodID(winClass,"<init>","(Ljava/lang/String;)V");
	
	if (constructor_id==NULL)return NULL;	
	
	
	jobject obj=NULL;
	
	
	#if defined(WIN32) || defined(_WIN32)
		char buff[1024];
		HWND handle=GetForegroundWindow();
		GetWindowText(handle,(LPSTR)&buff,1024);
		jstring windowTitle=env->NewStringUTF(buff);
	#else
		if (globalDisplay==NULL){
			globalDisplay=XOpenDisplay(NULL);
			XSetErrorHandler(catcher);
		}
		Window *wlist;
		unsigned long len;
		char* wname;
		wlist=(Window*)list(globalDisplay,&len);
		wname=name(globalDisplay,wlist[0]);
		jstring windowTitle=env->NewStringUTF(wname);
		free(wname);
		free(wlist);
	#endif
	
	
	
	
	obj=env->NewObject(winClass,constructor_id,windowTitle);
	
	if (obj==NULL)return NULL;
	
	
	return obj;
}



