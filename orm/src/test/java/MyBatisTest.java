import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.Reader;

public class MyBatisTest {

    String resource = "mybatis-config.xml";

    @Test
    public void startTest() {
        Reader reader;
        try {
            reader = Resources.getResourceAsReader(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            SqlSession sqlSession = sqlSessionFactory.openSession();
            try {
                UserMapper mapper = sqlSession.getMapper(UserMapper.class);
                String result = mapper.selectById();
                System.out.println(result);
            } catch (Exception e) {
                System.err.println(e);
            } finally {
                sqlSession.close();
            }
        } catch (Exception e) {

        }
    }
}
