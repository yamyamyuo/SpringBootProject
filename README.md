# 英语流利说项目说明
项目的初步实现
### System Setup
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
java -jar ./firstboot-1.3.0.RELEASE.jar --server.port=8080
```
set server.port=8081 for jstack message listening
```
java -jar ./firstboot-1.3.0.RELEASE.jar --server.port=8081
```
2. running with script
```
#!/bin/sh
```
