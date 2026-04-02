# 03-mall-list

安卓开发课程实验三：购物商场列表。

## 实验目标

参考课本中的“动物列表”示例，使用 `RecyclerView` 实现一个简单的购物商场列表，把原本的动物数据改成商品数据。

## 这个实验做了哪些事

- 新建了第三个独立模块 `mallApp`
- 使用 `RecyclerView` 实现竖直商品列表
- 每个商品条目展示：
  - 商品图片
  - 商品名称
  - 商品简介
- 将老师示例中的列表结构改造成“商场商品展示”
- 使用你提供的 6 张图片作为商品图片资源
- 更新仓库根 README，让第三个实验也能清晰查看和运行

## 具体实现过程

### 1. 模块接入

第三个实验被做成单独模块，避免影响前两个实验。

模块接入位置：

- [settings.gradle](/Users/cuing/AndroidStudioProjects/MyApplication/settings.gradle)

新增模块名：

- `mallApp`

对应目录：

- [projects/03-mall-list/app](/Users/cuing/AndroidStudioProjects/MyApplication/projects/03-mall-list/app)

### 2. 页面结构实现

主页面在 [activity_main.xml](/Users/cuing/AndroidStudioProjects/MyApplication/projects/03-mall-list/app/src/main/res/layout/activity_main.xml)。

页面结构很简单，主要分为两部分：

- 顶部标题：显示“购物商场列表”
- 下方 `RecyclerView`：用于显示商品列表

和实验二相比，这个实验更偏向 `RecyclerView` 的入门使用，不再处理多类型条目，只保留一种卡片样式。

### 3. 列表项布局实现

条目布局在 [recycler_item.xml](/Users/cuing/AndroidStudioProjects/MyApplication/projects/03-mall-list/app/src/main/res/layout/recycler_item.xml)。

每个条目包含：

- 左侧商品图片 `ImageView`
- 右侧商品名称 `TextView`
- 商品简介 `TextView`

为了让界面更像商场列表，我额外加了：

- 白色卡片背景
- 圆角效果
- 浅灰色页面背景
- 图片与文字左右排布

卡片背景定义在 [item_card_bg.xml](/Users/cuing/AndroidStudioProjects/MyApplication/projects/03-mall-list/app/src/main/res/drawable/item_card_bg.xml)。

### 4. 数据模型设计

商品实体类在 [Goods.kt](/Users/cuing/AndroidStudioProjects/MyApplication/projects/03-mall-list/app/src/main/java/com/example/malllist/Goods.kt)。

字段包括：

- `name`：商品名称
- `description`：商品简介
- `imageRes`：商品图片资源 ID

这种写法可以让页面数据更清晰，也方便适配器统一绑定。

### 5. 适配器实现

适配器在 [GoodsAdapter.kt](/Users/cuing/AndroidStudioProjects/MyApplication/projects/03-mall-list/app/src/main/java/com/example/malllist/GoodsAdapter.kt)。

实现步骤：

- 继承 `RecyclerView.Adapter`
- 在 `onCreateViewHolder()` 中加载 `recycler_item.xml`
- 在 `onBindViewHolder()` 中把商品图片、名称和简介绑定到控件
- 在 `getItemCount()` 中返回商品总数

这个实验的重点就是理解：

- `RecyclerView` 如何与适配器配合
- `ViewHolder` 如何缓存条目中的控件
- 数据如何映射到每一个列表项上

### 6. 页面数据准备

数据准备在 [MainActivity.kt](/Users/cuing/AndroidStudioProjects/MyApplication/projects/03-mall-list/app/src/main/java/com/example/malllist/MainActivity.kt) 中完成。

实现方式：

- 在 `MainActivity` 中直接定义一个 `goodsList`
- 用 6 个 `Goods` 对象对应 6 个商品
- 每个商品都绑定一张本地图片和一段介绍文字

商品内容包括：

- 针织毛衣
- 户外圆桌
- 轻盈围巾
- 奇异果礼盒
- 奶油蛋糕
- 红苹果

### 7. RecyclerView 初始化

在 `MainActivity` 中完成以下操作：

- `findViewById()` 获取 `RecyclerView`
- 设置 `LinearLayoutManager`
- 设置 `GoodsAdapter`

这样就完成了一个最基础的竖直商品列表。

## 关键文件

- [MainActivity.kt](/Users/cuing/AndroidStudioProjects/MyApplication/projects/03-mall-list/app/src/main/java/com/example/malllist/MainActivity.kt)
- [GoodsAdapter.kt](/Users/cuing/AndroidStudioProjects/MyApplication/projects/03-mall-list/app/src/main/java/com/example/malllist/GoodsAdapter.kt)
- [Goods.kt](/Users/cuing/AndroidStudioProjects/MyApplication/projects/03-mall-list/app/src/main/java/com/example/malllist/Goods.kt)
- [activity_main.xml](/Users/cuing/AndroidStudioProjects/MyApplication/projects/03-mall-list/app/src/main/res/layout/activity_main.xml)
- [recycler_item.xml](/Users/cuing/AndroidStudioProjects/MyApplication/projects/03-mall-list/app/src/main/res/layout/recycler_item.xml)
- [colors.xml](/Users/cuing/AndroidStudioProjects/MyApplication/projects/03-mall-list/app/src/main/res/values/colors.xml)
- [strings.xml](/Users/cuing/AndroidStudioProjects/MyApplication/projects/03-mall-list/app/src/main/res/values/strings.xml)

## 运行方式

在仓库根目录运行：

```bash
./gradlew :mallApp:assembleDebug
```

在 Android Studio 中运行时，选择模块 `mallApp`。

## 学到的内容

- `RecyclerView` 的基础结构
- 单一类型列表项的实现
- 自定义数据类与适配器绑定
- 图文列表页面的构建方法
- 如何把老师示例改造成自己的业务场景
