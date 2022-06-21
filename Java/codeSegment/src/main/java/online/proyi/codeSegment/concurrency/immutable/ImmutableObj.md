## 不可变对象
不可变对象需要满足的条件：

- 对象创建以后其状态就不能修改
- 对象所有域都是final类型
- 对象是正确创建的（在对象创建期间，this引用没有逸出）
## 其他工具创建不可变对象

- Collections.unmodifiableXXX : Collection、List、Set、Map...  
- Guava : ImmutableXXX：Collection、List、Set、Map...
