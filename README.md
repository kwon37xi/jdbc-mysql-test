jdbc-mysql-test
===============

Testing MySQL JDBC Replication Driver and Spring 3.2 @Transactional(readOnly=true/false)

MySQL JDBC Replication Driver는 Connection.setReadOnly(true/false) 값에 따라 true이면 Slave로
false이면 Master로 요청을 보낸다.
```
# Replication MySQL JDBC Driver URL
jdbc:mysql:replication://MasterDB:3306,SlaveDB:3306/test?useUnicode=true&useServerPrepStmts=true&characterEncoding=utf8
```

이 코드는 test라는 DB에 products라는 테이블이 있다고 간주하고서 setReadOnly에 따라 정말로 Master와 Slave를 나눠서
요청을 보내는지 확인 할 수 있도록 한다.

```sql
CREATE TABLE `products` (
  `product_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `cnt` int(11) NOT NULL DEFAULT '0',
  `price` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`product_id`)
);
```

mysql master/slave의 my.cnf 에 다음 설정을 두고 /var/log/mysql/mysql.log 파일을 살펴보면 쿼리가 원하는 DB로
날라가는지 확인할 수 있다.

```
general_log_file        = /var/log/mysql/mysql.log
general_log             = 1
```

### mysqlreplicationdriver.groovy
Connection 객체에 직접 setReadOnly()를 지정하여 정말로 master/slave로 요청이 가는지 확인해본다.

```sh
# setReadOnly(true)로 요청보내기 테스트
groovy mysqlreplicationdriver.groovy true

# setReadOnly(false)로 요청보내기 테스트
groovy mysqlreplicationdriver.groovy false
```

### Spring @Transactional 테스트
Spring의 @Transactionl(readOnly=true/false)이 제대로 setReadOnly()를 지정해주는지 확인해본다.
```
# Transactional(readOnly=true) 테스트
gradle readService

# Transactional(readOnly=false) 테스트 -Pproduct=제품명,갯수,개당가격 insert
gradle writeService -Pproduct=제품명,3,5000
```

