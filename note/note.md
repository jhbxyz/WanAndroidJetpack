#### 1.Fragment 懒加载 

跟ViewPager 配合，需要三个变量控制

类：BaseLazyFragment

------

使用ViewPager2  当**setOffscreenPageLimit**被设置为**OFFSCREEN_PAGE_LIMIT_DEFAULT**时候会使用RecyclerView的**缓存**机制，这就是**懒加载**

 ViewPager2：  https://juejin.im/post/5df4aabe6fb9a0161104c8eb



#### 2.RV 复用问题

重写这个方法

```kotlin
override fun getItemViewType(position: Int) = position
```



#### 3.RV 列表 Item 中的LinearLayout 动态添加子View ，刷新列表时，子View 重新绘制闪动





#### 4.在 RV 请求下拉刷新时，滑动 item 报错

##### 报错

CrashHandler: In thread: Thread[main,5,main]
UncaughtException detected: java.lang.IndexOutOfBoundsException: Inconsistency detected. Invalid item position 

问题出现的本质是：操作问题，首先在主线程，清掉了 List，然后去子线程请求数据，得到数据然后添加到 List 中，整个item 数据经历了，主线程-子线程-主线程 的过程，并且都操作了 List

> 解决：不要提前清除数据，在请求结果的回调，再清楚数据，然后添加新数据到 List 中

下面是网上的办法，用的是 try catch 方法

[https://stackoverflow.com/questions/31759171/recyclerview-and-java-lang-indexoutofboundsexception-inconsistency-detected-in](https://stackoverflow.com/questions/31759171/recyclerview-and-java-lang-indexoutofboundsexception-inconsistency-detected-in)



#### 5.首页 Feed 流对条目 Item 复用的问题

其实没有复用，是在LinearLayout 动态添加，子 View 的时候，多了一层判空，导致结果为空的时候，没有清掉 子View

```kotlin
@BindingAdapter("addTags")
fun addTags(ll: LinearLayout, list: List<TagViewModel>) {
  	//多加了 判空操作，导致为空的时候，没有remove 掉子 View
    if (list.isEmpty()) return
    if (ll.childCount != 0) {
        ll.removeAllViews()
    }
    list.forEachIndexed { index, tagViewModel ->
        val binding = ViewModelTagBinding.inflate(LayoutInflater.from(ll.context))
        binding.root.layoutParams = ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        val layoutParams = binding.root.layoutParams as ViewGroup.MarginLayoutParams
        layoutParams.rightMargin = if (index == list.size - 1) 0 else R.dimen.dp_10.getResDimen().toInt()
        binding.root.layoutParams = layoutParams
        ll.gravity = Gravity.CENTER
        binding.tag = tagViewModel
        ll.addView(binding.root)
    }
}
```



#### 6.FAB 的图标不居中

使用自定义大小的属性

```xml
fabCustomSize
```



#### 7.Kotlin 协程和 Retrofit 配合使用，用Result接到的 response 为 null

协程和 RxJava 搞混了



#### 8.显示 Loading Dialog 时，应该状态栏颜色问题，没有适配沉浸式状态栏

```kotlin
window?.statusBarColor  = R.color.colorAccent.getResColor()
```



#### 9.TabLayout 和 ViewPager2配合使用，如何懒加载

```kotlin
tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
    override fun onTabReselected(tab: TabLayout.Tab?) {}
    override fun onTabUnselected(tab: TabLayout.Tab?) {}
    override fun onTabSelected(tab: TabLayout.Tab?) {
        viewPager2.setCurrentItem(tab?.position ?: 0, false)
    }
})
```

同时取消了切换动画，效果非常让人难以接受

tablayout 点击 tab





