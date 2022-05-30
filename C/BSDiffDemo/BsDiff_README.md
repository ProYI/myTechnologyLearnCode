## BSDiff
差分算法的一种实现方法 bsdiff  

此动态库工程使用Cmake进行管理编译，压缩使用bzip2算法  
将实现编译成动态库，供其他项目调用
libBSDiff.dylib (mac)
libBSDiff.so (Linux)

必要依赖  
C基础库  
Cmake  
C++  
G++  
  
// 如果需要生成Java JNI可以调用的动态性  
需要将JDK中 include文件夹下的 jni.h和 jni_md.h拷贝到工程中
