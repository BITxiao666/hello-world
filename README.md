# scholar

### 北理工2017大三Java选修课项目
`scholar`读书人APP。针对北理工本科生设计，课程时间，起始周，综测管理，全部安照北京理工大学的情况设计。
本项目分为两个部分，课程管理由@BITxiao666负责开发，综测管理部分由@Liaowag负责开发。

## 课程管理部分

实现功能
> * 课程的查询，添加，删除，修改
> * 根据当前周动态显示课程
> * 新建学期
> * 修改当前周
> * 当天的名字加亮

* 查询课程

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

![img](https://github.com/BITxiao666/scholar/blob/master/gif/delete.gif)

---

* 修改当前周

![img](https://github.com/BITxiao666/scholar/blob/master/gif/change_week.gif)

