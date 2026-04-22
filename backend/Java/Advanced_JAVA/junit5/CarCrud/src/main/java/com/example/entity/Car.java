<persistence xmlns="https://jakarta.ee/xml/ns/persistence"
version="3.0">

    <persistence-unit name="car-unit">

        <provider>org.hibernate.jpa.HibernatePersistenceProvider</provider>

        <class>com.example.entity.Car</class>

        <properties>

            <!-- JDBC Configuration -->
            <property name="jakarta.persistence.jdbc.driver"
value="com.mysql.cj.jdbc.Driver"/>

            <property name="jakarta.persistence.jdbc.url"
value="jdbc:mysql://localhost:3306/test"/>

            <property name="jakarta.persistence.jdbc.user"
value="root"/>

            <property name="jakarta.persistence.jdbc.password"
value="root"/>

            <!-- Hibernate Properties -->
            <property name="hibernate.hbm2ddl.auto" value="update"/>
            <property name="hibernate.show_sql" value="true"/>
            <property name="hibernate.format_sql" value="true"/>

        </properties>

    </persistence-unit>

</persistence>