##AQS 
AbstractQueuedSynchronizer  
底层使用的是双向链表  
- 使用Node实现FIFO队列，可以用于构建锁或者其他同步装置的基础构架
- 利用了一个int类型表示状态
- 使用方法是继承
- 子类通过继承并通过实现它的方法管理其状态 {`acquire` 和`release`}的方法操纵状态
- 可以同时实现`排它锁`和`共享锁`模式（独占、共享）  

## AQS同步组件
- CountDownLatch  闭锁
- Semaphore
- CyclicBarrier
- ReentrantLock
- Condition
- FutureTask  