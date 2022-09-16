#include "winx.h"
#if defined(_WIN32)  || defined(WIN32)
#include <windows.h>
#else
#include <X11/XwinUtil.h>
#endif

JNIEXPORT jobject JNICALL Java_com_wfh_emplos_platform_CrossPlatformWindow_getActiveWindow(JNIEnv *env, jclass clz){
	
	jmethodID constructor_id=NULL;
	

	
	jclass winClass=NULL;
	winClass=env->FindClass("Lcom/wfh/emplos/platform/Window;");
	if (winClass==NULL)return winClass;
	
	constructor_id=env->GetMethodID(winClass,"<init>","(Ljava/lang/String;)V");
	
	if (constructor_id==NULL)return NULL;	
	
	
	jobject obj=NULL;
	
	char buff[1024];
	#if defined(WIN32) || defined(_WIN32)
		HWND handle=GetForegroundWindow();
		GetWindowText(handle,(LPSTR)&buff,1024);
	#else
		Display* disp= XOpenDisplay(NULL);
		if (disp==NULL){
			return NULL;
		}
		
	#endif
	
	jstring windowTitle=env->NewStringUTF(buff);
	
	
	obj=env->NewObject(winClass,constructor_id,windowTitle);
	
	if (obj==NULL)return NULL;
	
	
	return obj;
}
