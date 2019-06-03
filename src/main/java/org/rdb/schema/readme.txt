mvn clean -f "/app/workspace/MySpring/pom.xml"
mvn compile -f "/app/workspace/MySpring/pom.xml"
mvn package -f "/app/workspace/MySpring/pom.xml"

mvn exec:java -Dexec.mainClass="org.rdb.schema.DatabaseBuildMSSQL"  -Dexec.args="org.rdb.beans.DependTargetTable org.rdb.beans.Table org.rdb.beans.DependTargetTable org.rdb.beans.Field"