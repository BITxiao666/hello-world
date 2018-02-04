
### 北理工2017大三Java选修课项目
`scholar`读书人APP。针对北理工本科生设计，课程时间，起始周，综测管理，全部安照北京理工大学的情况设计。
本项目分为两个部分，课程管理由@BITxiao666负责开发，综测管理部分由@Liaowag负责开发。数据库框架为`LitePal` ，图表绘制使用`PhilJay/MPAndroidChart`开源框架。

## 课程管理部分

实现功能
> * 课程的查询，添加，删除，修改
> * 根据当前周动态显示课程
> * 新建学期
> * 修改当前周
> * 当天的名字加亮

* 查询课程
显示课程，最上方的数字为当前周，周一到周五中，橙色的代表今天，课程格的颜色会根据当前周的不同而不同，例如：当前为第4周，第4周有的课程用彩色表示，不在第4周的课为灰色，如果周一上午第一节在1到8周为一门课，在9到16周又是一门课，则根据当前周选一个来显示。

![img](https://github.com/BITxiao666/scholar/blob/master/gif/query.gif)

---

* 添加课程

带有输入检查和一定的容错机制，当输入合法时才会写入数据库，否则提示用户。
根据北理工的课程安排，教学周必须为1到19周，一天当中只会有5个时段有课。

![img](https://github.com/BITxiao666/scholar/blob/master/gif/add.gif)

---
* 修改课程

点击课程栏可以直接跳到相应的编辑框。

![img](https://github.com/BITxiao666/scholar/blob/master/gif/edit.gif)

---


* 删除课程

带有删除确认提示，只有从编辑按钮进入编辑界面才能进行删除，从添加课程按钮进入无法删除。

![img](https://github.com/BITxiao666/scholar/blob/master/gif/delete.gif)

---

* 修改当前周

所有课程根据当前周动态显示，例如，如果当前周为第六周，则显示操作系统课程设计，若为第九周，则显示计算机体系结构。
非当前周的课程为灰色。

![img](https://github.com/BITxiao666/scholar/blob/master/gif/change_week.gif)

---

## GPA管理部分

实现功能
> * 综测的查询，添加，删除，修改
> * 综测的总分生成
> * 综测的雷达图显示


* 综测的添加

点击相应的项目之后，会跳转到相应的编辑界面，点击加号后添加，添加完成后会根据权重计算综测总分

![img](https://github.com/BITxiao666/scholar/blob/master/gif/add_gpa.gif)

---

* 总分的图表显示

点击总分一栏后，会跳转到雷达图生成界面，雷达图界面根据各项的分数，显示本学期的成绩情况

![img](https://github.com/BITxiao666/scholar/blob/master/gif/graph_gpa.gif)

---

## 部署向导

将项目部署到本地首先需要修改`build.gradle`文件，添加相关依赖。
```gradle
dependencies {
    implementation fileTree(dir: 'libs', include: ['*.jar'])
    implementation 'com.android.support:appcompat-v7:26.1.0'
    implementation 'com.android.support.constraint:constraint-layout:1.0.2'
    implementation 'com.android.support:design:26.1.0'
    testImplementation 'junit:junit:4.12'
    androidTestImplementation 'com.android.support.test:runner:1.0.1'
    androidTestImplementation 'com.android.support.test.espresso:espresso-core:3.0.1'
    compile 'com.yalantis:contextmenu:1.0.7'
    compile 'com.jakewharton:butterknife:8.5.1'
    annotationProcessor 'com.jakewharton:butterknife-compiler:8.5.1'
    compile 'org.litepal.android:core:1.6.1'
}
```

若要修改业务逻辑，请注意修改`Litepal.xml`中的版本号和类名，版本号为比1大的整数即可。
```xml
<?xml version="1.0" encoding="utf-8"?>
<litepal>
    <dbname value="CourseInfo"></dbname>
    <version value="1"></version>
    <list>
        <mapping class="com.example.bitshaw.menutest.Course"></mapping>
    </list>
</litepal>
```
### Thanks to

https://github.com/PhilJay/MPAndroidChart

https://github.com/Yalantis/Context-Menu.Android