@GrabConfig(systemClassLoader=true)
@Grapes([
    @Grab(group='mysql', module='mysql-connector-java', version='5.1.25'),
    @Grab(group='commons-dbcp', module='commons-dbcp', version='1.4')
])

import java.sql.*
import java.util.*
import javax.sql.DataSource
import org.apache.commons.dbcp.BasicDataSource

String driver = 'com.mysql.jdbc.Driver'
String url = 'jdbc:mysql:replication://localhost:3306,s9vmubuntu:3306/test?useUnicode=true&characterEncoding=utf8&useServerPrepStmts=true'

println 'create datasource...'
BasicDataSource ds = new BasicDataSource()
ds.setMaxActive(2)
ds.setMaxIdle(2);
ds.setDriverClassName(driver)
ds.setUsername('test')
ds.setPassword('test')
ds.setValidationQuery('/* ping */ SELECT 1')
ds.addConnectionProperty('autoReconnect', 'true')
ds.addConnectionProperty('roundRobinLoadBalance', 'true')
ds.setUrl(url)

println 'create connections...'
Connection con = ds.getConnection()

println 'set readonly...'
con.setReadOnly(Boolean.valueOf(args[0]))

println 'create statement...'
Statement stmt = con.createStatement()

println 'execute query...'
ResultSet rs = stmt.executeQuery('select * from products')

println '####################################'
while (rs.next()) {
    println "  - ${rs.getInt('product_id')}, ${rs.getString('name')}, ${rs.getInt('cnt')}, ${rs.getInt('price')}"
}

println 'closing resources...'
rs.close()
stmt.close()
con.close()

ds.close()
println 'end...'
