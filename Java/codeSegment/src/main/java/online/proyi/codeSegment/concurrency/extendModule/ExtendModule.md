## 组件扩展
创建一个线程通常两种方式：

1. 继承Thread
2. 实现rannable  

## 缺陷
执行完任务后无法获取执行结果

## JDK 1.5
提供了`Future`和`Callable`，可以在任务执行完后获取执行结果  

## Callable和Runnable接口对比
Runnable是一个接口，并且只有一个Run()方法。创建一个类，实现它，然后使用某个线程执行该RUnnable的实现类，就可以实现多线程  
Callable是一个`泛型`接口，有一个call()函数，返回类型就是传递进来的V类型  
Callable与Runnable的功能大致相似，但更强大，线程执行后有返回值，并且能够抛出异常  
## Future接口  
对于具体的Runnable或Callable任务，可以进行取消，查询的任务是否被取消，查询是否完成以及获取结果  
future可以监视目标线程调用Callable的情况，当调用future的get()方法时，就可以获得结果  
future可以得到其他线程任务的返回值  

## FutureTask类
父类是RunnableFuture，实现了Runnable和Future两个接口  
所以FutureTask也是执行Callable类型的任务
如果构造函数参数类型是Runnable的话，会转换成Callable类型  
它既可以被作为Runable被线程执行，又可以作为future得到Callable的返回值  

## JDK 1.7 Fork/Join 框架
Java7提供的用于并行执行任务的框架  
它是将一个大任务分隔成若干个小任务，最终汇总每个小任务结果后得到大任务结果的框架  
主要应用的是`工作窃取算法`  
`工作窃取算法`是指某个线程从其他队列里窃取任务来执行  

避免系统资源浪费，和线程争抢  
窃取线程均从队列`尾部`获取任务，正常线程从`头部`执行，这样做就是为了充分利用线程资源进行并行计算  
### 局限性
1. 只能使用 Fork/Join同步机制。如果使用了其他同步机制，工作线程就不能执行其他任务了
2. 拆分的任务不应该去执行IO操作，如读或写数据文件  
3. 任务不能抛出，检查异常，必须通过必要的代码处理  

