## 并发容器
- ArrayList -> CopyOnWriteArrayList  
  写操作时复制，Add()操作是在锁下进行的。因为会拷贝数组，会消耗内存，如果数据量大，会造成GC  
  不能用于实时读的场景，数据拷贝等均需耗时。虽然能做到最终的一致性，但无法满足实时性要求
  更适合读多写少的场景，如果不确定数据量建议慎用
- HashSet、TreeSet -> CopyOnWriteArraySet ConcurrentSkipListSet
- HashMap、TreeMap -> ConcurrentHashMap ConcurrentSkipListMap