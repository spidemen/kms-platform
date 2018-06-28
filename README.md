# zb-shiro

#### 项目介绍
Springboot + shiro权限管理。最精简的shiro上手项目。可以加[QQ群130512958](http://shang.qq.com/wpa/qunwpa?idkey=20b2a77fff013f0b11676e84c575b7f1eacc2f03c2eb1c5bc5aaa8a9a1916d0c)交流技术！

**如果喜欢，请多多分享！！多多Star！！**

![JDK](https://img.shields.io/badge/JDK-1.8-green.svg)
![Maven](https://img.shields.io/badge/Maven-3.3.9-green.svg)
![MySQL](https://img.shields.io/badge/MySQL-5.6.24-green.svg)
![Redis](https://img.shields.io/badge/Redis-3.0.503-green.svg)
[![license](https://img.shields.io/badge/license-apacheV2.0-yellow.svg)](https://gitee.com/supperzh/zb-shiro/blob/master/LICENSE)

#### 使用说明

1. 使用IDE导入本项目
2. 新建数据库`CREATE DATABASE zb-shiro;`
3. 导入数据库`docs/db/shiro.sql`
4. 修改(`resources/application.yml`)配置文件
   1. 数据库链接属性(可搜索`datasource`或定位到L.15) 
   2. redis配置(可搜索`redis`或定位到L.28)
5. 运行项目(三种方式)
   1. 项目根目录下执行`mvn -X clean package -Dmaven.test.skip=true`编译打包，然后执行`java -jar zb-shiro/target/zb-shiro.jar`
   2. 项目根目录下执行`mvn springboot:run`
   3. 直接运行`ShiroBootApplication.java`
6. 浏览器访问`http://localhost:8081`

**用户密码**

_管理员_： 账号：admin 密码：123456 

（测试账号直接使用系统功能注册即可）

**Druid监控**

用户名：admin 密码：123456


#### 参与贡献

1. Fork 本项目
2. 新建 Feat_xxx 分支
3. 提交代码
4. 新建 Pull Request

#### 图片预览

![首页](https://gitee.com/supperzh/zb-shiro/raw/master/docs/img/workdest.png?v=1.0)
![用户管理](https://gitee.com/supperzh/zb-shiro/raw/master/docs/img/userlist.png?v=1.0)
![角色管理](https://gitee.com/supperzh/zb-shiro/raw/master/docs/img/rolelist.png?v=1.0)
![角色分配资源](https://gitee.com/supperzh/zb-shiro/raw/master/docs/img/assignpermission.png?v=1.0)
![资源管理](https://gitee.com/supperzh/zb-shiro/raw/master/docs/img/permissionlist.png?v=1.0)

#### 其他开源项目

[zplayer音乐播放器](https://gitee.com/supperzh/zplayer)

