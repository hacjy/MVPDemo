# MVPDemo

**思考**
---
model 对应一层IModel（接口层）：IModel将数据回调给Presenter
view 对应一层IView（接口）：Presenter通过IView调用Activity中的方法
Presenter持有IView对象和IModel对象

绑定IView；解绑IView；
Presenter中的IView对象是否为空；


>单一职责：做什么就是做什么，不要不需要的
开闭原则：可扩展，不修改原有的
依赖抽象：通用的抽象出来
接口隔离：需要什么定义什么接口

---
**独立搭建一个项目架构**

1、项目整体架构

**Common module**
=工具module：一般项目中都会用到一些变化不大可通用的工具，比如时间格式化，字符串格式化，加密算法等；
=网络相关module；
=通用组件

**UI module：**页面和业务化的控件view；

**常量module：**接口常量，通知tag等；

**数据module：**数据可以从网络、缓存、本地数据库获取，由统一的module去管理，方便好找；

**SDK Module：**管理一些自有的sdk和第三方SDK；

**Base Module：**包括通用Activity，Fragment，Model，Presenter等；

**Presenter：**业务逻辑处理层，按照模块划分，比如用户模块；
