#### 1.Fragment 懒加载 

​	更ViewPager 配合，需要三个变量控制

​	类：BaseLazyFragment

------

​	使用ViewPager2  当**setOffscreenPageLimit**被设置为**OFFSCREEN_PAGE_LIMIT_DEFAULT**时候会使用RecyclerView的**缓存**机制，这就是**懒加载**

 ViewPager2：  https://juejin.im/post/5df4aabe6fb9a0161104c8eb



#### 2.RV 复用问题

重写这个方法

```kotlin
override fun getItemViewType(position: Int) = position
```



#### 3.RV 列表 Item 中的LinearLayout 动态添加子View ，刷新列表时，子View 重新绘制闪动