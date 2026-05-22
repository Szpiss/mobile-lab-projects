# 02-headline

安卓开发课程实验二：仿今日头条推荐列表。

## 实验目标

参考课本中的“仿今日头条推荐列表”案例，实现一个包含顶部标题栏、频道栏和新闻列表的页面，重点练习 `RecyclerView` 的使用。

## 这个实验做了哪些事

- 实现了类似资讯 App 的顶部标题栏。
- 加入了频道分类栏，例如“推荐”“AI”“小说”“北京”等。
- 使用 `RecyclerView` 显示新闻列表。
- 实现了两种条目样式：
  - 单图新闻
  - 三图新闻
- 为第一条新闻加入“置顶”标记。
- 使用本地图片资源构建静态新闻数据。

## 具体实现过程

### 1. 模块结构

这个实验对应模块 `headlineApp`，代码目录在：

- [projects/android-app-development/02-headline/app](/Users/cuing/AndroidStudioProjects/MyApplication/projects/android-app-development/02-headline/app)

根目录通过 [settings.gradle](/Users/cuing/AndroidStudioProjects/MyApplication/settings.gradle) 把它接入为独立模块，方便直接运行。

### 2. 页面布局设计

主页面在 [activity_main.xml](/Users/cuing/AndroidStudioProjects/MyApplication/projects/android-app-development/02-headline/app/src/main/res/layout/activity_main.xml)。

布局分成三部分：

- 顶部标题栏：通过 [titlebar.xml](/Users/cuing/AndroidStudioProjects/MyApplication/projects/android-app-development/02-headline/app/src/main/res/layout/titlebar.xml) 单独抽取
- 分类频道栏：直接在 `activity_main.xml` 中排列多个 `TextView`
- 内容列表：使用 `RecyclerView`

这样拆分的优点是：

- 页面结构更清晰
- 标题栏可以独立维护
- 后续扩展频道栏或新闻列表更方便

### 3. 条目布局实现

为了模拟今日头条样式，做了两种列表项布局：

- [item_one.xml](/Users/cuing/AndroidStudioProjects/MyApplication/projects/android-app-development/02-headline/app/src/main/res/layout/item_one.xml)
  - 用于单图新闻或无图置顶新闻
  - 包含标题、来源、评论数、发布时间和右侧图片
- [item_two.xml](/Users/cuing/AndroidStudioProjects/MyApplication/projects/android-app-development/02-headline/app/src/main/res/layout/item_two.xml)
  - 用于三图新闻
  - 包含标题、三张图片和底部新闻信息

### 4. 数据结构设计

新闻实体在 [NewsBean.kt](/Users/cuing/AndroidStudioProjects/MyApplication/projects/android-app-development/02-headline/app/src/main/java/com/example/headlineapp/NewsBean.kt)。

字段包括：

- `id`
- `title`
- `source`
- `comment`
- `time`
- `type`
- `images`
- `isTop`

这样设计之后，可以让不同类型的新闻共用一个数据模型，适配器只需要根据 `type` 决定使用哪种布局。

### 5. 适配器实现

适配器在 [NewsAdapter.kt](/Users/cuing/AndroidStudioProjects/MyApplication/projects/android-app-development/02-headline/app/src/main/java/com/example/headlineapp/NewsAdapter.kt)。

具体做法：

- 继承 `RecyclerView.Adapter<RecyclerView.ViewHolder>`
- 重写 `getItemViewType()`，通过 `NewsBean.type` 区分条目类型
- 在 `onCreateViewHolder()` 中根据类型加载不同布局
- 在 `onBindViewHolder()` 中完成对应控件的数据绑定

这个实验的重点就在这里：

- 学会让一个 `RecyclerView` 显示多种不同样式的条目
- 学会根据数据结构驱动界面展示

### 6. 页面数据准备

数据准备在 [MainActivity.kt](/Users/cuing/AndroidStudioProjects/MyApplication/projects/android-app-development/02-headline/app/src/main/java/com/example/headlineapp/MainActivity.kt) 中完成。

实现过程：

- 先定义标题、来源、评论数、发布时间等数组
- 再定义单图和三图资源数组
- 在 `buildNewsList()` 中按索引循环生成新闻对象
- 不同位置生成不同类型的数据：
  - 第一条：无图且置顶
  - 第二条、第四条、第六条：单图
  - 第三条、第五条：三图

最后把构造出来的列表传给 `NewsAdapter`。

### 7. RecyclerView 初始化

在 `MainActivity` 中：

- 设置 `LinearLayoutManager`
- 添加 `DividerItemDecoration`
- 设置适配器

这样列表就能按竖直方向显示，并自带分隔线效果。

## 关键文件

- [MainActivity.kt](/Users/cuing/AndroidStudioProjects/MyApplication/projects/android-app-development/02-headline/app/src/main/java/com/example/headlineapp/MainActivity.kt)
- [NewsAdapter.kt](/Users/cuing/AndroidStudioProjects/MyApplication/projects/android-app-development/02-headline/app/src/main/java/com/example/headlineapp/NewsAdapter.kt)
- [NewsBean.kt](/Users/cuing/AndroidStudioProjects/MyApplication/projects/android-app-development/02-headline/app/src/main/java/com/example/headlineapp/NewsBean.kt)
- [activity_main.xml](/Users/cuing/AndroidStudioProjects/MyApplication/projects/android-app-development/02-headline/app/src/main/res/layout/activity_main.xml)
- [item_one.xml](/Users/cuing/AndroidStudioProjects/MyApplication/projects/android-app-development/02-headline/app/src/main/res/layout/item_one.xml)
- [item_two.xml](/Users/cuing/AndroidStudioProjects/MyApplication/projects/android-app-development/02-headline/app/src/main/res/layout/item_two.xml)
- [titlebar.xml](/Users/cuing/AndroidStudioProjects/MyApplication/projects/android-app-development/02-headline/app/src/main/res/layout/titlebar.xml)

## 运行方式

在仓库根目录运行：

```bash
./gradlew :headlineApp:assembleDebug
```

在 Android Studio 中运行时，选择模块 `headlineApp`。

## 学到的内容

- `RecyclerView` 的基础使用
- 多种条目布局的实现方法
- `ViewHolder` 模式
- 静态数据组织与适配器绑定
- 页面结构拆分和布局复用
