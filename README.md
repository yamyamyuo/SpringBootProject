# 英语流利说项目说明
项目的初步实现
## Introduction
这是一个使用了java SpringBoot框架开发的模块。SpringBoot提供了简便快捷的独立可运行的应用。可进行SpringCloud配置。
### 接口实现说明
- 添加用户和金币
```
1. 接口描述：添加用户以及金币数量
2. 接口定义： POST http://localhost:8080/user/add?user_id=1&coins=1
3. 参数：
user_id : required，用户账号，int类型
coins：required，用户金币值，int类型
4. 使用场景：如果用户已经存在，接口调用失败，如果用户不存在，则添加用户以及相应的金币值

```
- 获取用户金币值
```
1. 接口描述：获取某个用户的金币值
2. 接口定义：GET http://localhost:8080/coins/user/1
3. 参数：
pathVariable： required，该字段为user_id，int类型
4. 使用场景：如果用户不存在，调用失败，如果用户已经在数据库中，则返回其对用的金币值
```
- 用户间转账
```
1. 接口描述：从账户a转账m金额到账户b
2. 接口定义：POST http://localhost:8080/transaction/transfer?from_user_id=2&to_user_id=3&coins=1
3. 参数：
from_user_id：转出账户，required，int类型
to_user_id：转入账户，required，int类型
coins：转账金额，required，int类型
4. 场景描述：
如果转账过程中出现异常，将会保证转账的事务完整性，具有事务管理功能。要求from_user_id的账户余额足够转账，并且from_user_id已经存在，转账金额大于零。to_user_id如果不存在，系统创建一个to_user_id

```
- 监听进程接口
```
1. 说明：目前实现了需要声明端口启动java应用，监听进程的端口使用的是8081
2. 接口调用： GET localhost:8080/ops/jstack
3. 返回值：
当前应用的进程情况
```

### 程序测试情况
```
test
```

## System Setup
### Running Env prerequisite
1. java environment
### MySql Setup
1. mysql install
```
brew install mysql
```
[more installation info](http://www.jianshu.com/p/7a6e431d85bf)
2. mysql databases setup
```
drop table if exists`coins`;
```
```
CREATE TABLE `coins` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `coins` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`,`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
```
3. insert some data to table for test
```
insert into coins (user_id, coins) values (101, 5000);
insert into coins (user_id, coins) values (102, 5000);
insert into coins (user_id, coins) values (103, 2000);
```


### Testing plugins
1. Chrome plugin Postman
[Postman download](https://chrome.google.com/webstore/detail/postman/fhbjgbiflinjbdggehcddcbncdddomop?hl=en)

### Running Script
1. running locally

set server.port=8080 for API port
```
java -jar /your/path/to/firstboot-1.3.0.RELEASE.jar --server.port=8080
```
set server.port=8081 for jstack message listening
```
java -jar /your/path/to/firstboot-1.3.0.RELEASE.jar --server.port=8081
```
2. running with script in this document
```
sh firstBoot-run.sh start 8080
sh firstBoot-run.sh status 8080
sh firstBoot-run.sh stop 8080

sh firstBoot-run.sh start 8081
etc...
```
