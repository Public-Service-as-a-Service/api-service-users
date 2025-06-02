package se.sundsvall.users.apptest;

import org.springframework.test.context.jdbc.Sql;
import se.sundsvall.dept44.test.AbstractAppTest;
import se.sundsvall.dept44.test.annotation.wiremock.WireMockAppTestSuite;
import se.sundsvall.users.Application;

@WireMockAppTestSuite(files = "classpath/UsersIT/", classes = Application.class)
@Sql(scripts = {
        "/sql/truncate.sql",
        "/sql/init-db.sql"
})
public class UsersIT extends AbstractAppTest {
}
