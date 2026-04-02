# android-course-projects

Android 开发课程项目仓库，用来统一保存每次实验的代码、资源和说明文档。

## 仓库结构

- `projects/`：所有实验项目都放在这个目录下。
- `projects/01-register-page/`：实验一，注册界面实战。
- `projects/02-headline/`：实验二，仿今日头条推荐列表。
- `projects/03-mall-list/`：实验三，购物商场列表。

## 当前多模块配置

为了在 Android Studio 中直接打开仓库根目录运行，这个仓库被配置成了多模块工程。

- `app`：对应 `projects/01-register-page/app`
- `headlineApp`：对应 `projects/02-headline/app`
- `mallApp`：对应 `projects/03-mall-list/app`

模块映射定义在 [settings.gradle](/Users/cuing/AndroidStudioProjects/MyApplication/settings.gradle)。

## 每个实验做了什么

### 实验一：注册界面

- 目标：完成一个带背景图、注册方式入口、输入框、单选框、复选框和提交按钮的注册页面。
- 重点：基础控件使用、样式抽取、点击事件处理、输入校验。
- 实现方式：
  - 使用 XML 完成整体布局和公共样式封装。
  - 在 `MainActivity` 中获取控件引用并处理提交事件。
  - 对姓名、邮箱、密码、性别、兴趣爱好做非空校验。
  - 提交成功后通过 `Toast` 提示，并在 Logcat 输出注册信息。

详细说明见 [projects/01-register-page/README.md](/Users/cuing/AndroidStudioProjects/MyApplication/projects/01-register-page/README.md)。

### 实验二：仿今日头条推荐列表

- 目标：实现一个类似今日头条推荐页的新闻列表界面。
- 重点：`RecyclerView` 多条目类型、标题栏布局、静态数据适配、列表分隔效果。
- 实现方式：
  - 顶部使用自定义标题栏和频道栏。
  - 列表部分使用 `RecyclerView`。
  - 通过 `NewsAdapter` 区分单图新闻和三图新闻两种条目样式。
  - 在 `MainActivity` 中构造新闻静态数据并绑定到适配器。

详细说明见 [projects/02-headline/README.md](/Users/cuing/AndroidStudioProjects/MyApplication/projects/02-headline/README.md)。

### 实验三：购物商场列表

- 目标：参考课本中的动物列表示例，实现一个购物商场商品列表。
- 重点：`RecyclerView` 的基础使用、单一条目布局、数据类与适配器配合。
- 实现方式：
  - 每个列表项展示商品图片、商品名称和商品简介。
  - 使用 `Goods` 数据类描述每个商品。
  - 使用 `GoodsAdapter` 负责条目创建和数据绑定。
  - 在 `MainActivity` 中准备商品数据并设置线性布局管理器。

详细说明见 [projects/03-mall-list/README.md](/Users/cuing/AndroidStudioProjects/MyApplication/projects/03-mall-list/README.md)。

## Android Studio 使用方式

1. 直接打开仓库根目录 `MyApplication/`。
2. 等待 Gradle 同步完成。
3. 在顶部运行配置中按实验选择对应模块：
   - 实验一选择 `app`
   - 实验二选择 `headlineApp`
   - 实验三选择 `mallApp`
4. 连接模拟器或真机后运行。

## 常用命令

在仓库根目录可以直接执行以下命令进行构建：

```bash
./gradlew :app:assembleDebug
./gradlew :headlineApp:assembleDebug
./gradlew :mallApp:assembleDebug
```

## 后续扩展建议

- 新实验继续按 `projects/04-xxx`、`projects/05-xxx` 的方式新增。
- 每个实验目录保留自己的 `README.md`，记录实验目标、实现步骤和运行说明。
- 如果某次实验有特别的素材、截图或老师要求，也可以放在对应实验目录下统一管理。
